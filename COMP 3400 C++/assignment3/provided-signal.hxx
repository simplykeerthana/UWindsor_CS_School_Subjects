#ifndef COMP3400_2020W_A03_provided_signal_hxx_
#define COMP3400_2020W_A03_provided_signal_hxx_

//===========================================================================

#include <vector>
#include <csignal>
#include <type_traits>
#include <initializer_list>

//===========================================================================

namespace comp3400_2020w::a03 {

//===========================================================================

//
// The macros below used to detect an operating system varies by compiler
// and operating system. A site that lists these macro names by OS
// is here:
//
//   https://sourceforge.net/p/predef/wiki/OperatingSystems/
//

//
// POSIX systems define struct sigaction's sa_flags member to be an "int".
// One might want to write code to determine the type of a struct member.
// This can be easily done in C++11. See the detail namespace below.
//

namespace detail {

//
// Since sigaction is also the name of a function, referring to sigaction
// can refer to struct sigaction or the function. To avoid a problem, a
// type alias, struct_sigaction_type, is defined and set to the struct
// sigaction type --not the sigaction function type.
//
using struct_sigaction_type = struct sigaction;

//
// A common idiom in C++ is to use a function template prototype in 
// conjunction with code that ONLY looks at the return type of a function to
// figure out things about the type(s) passed in to that function's
// arguments. Such expressions must be only used within decltype
// (or noexcept() clauses).
//
//   NOTE: This function will NEVER be defined: only its prototype exists.
//
// The get_member_type() prototype only accepts a pointer to a member. If
// called with a pointer to a member, per language rules with function
// template argument type deduction, type deduction occurs. This causes
// T to be the struct/union/class type of the pointer passed in, and,
// M to be the type of the member.
//
//   NOTE: Clearly this all happens at compile-time.
//
template <typename T, typename M>
M get_member_type(M T::*);

//
// Define a type alias, sigaction_flags_type, to be the type of the
// struct sigaction's sa_flags member. Two things are important here:
// The '&' is needed some one needs a pointer to the member, and,
// the entire function "call" is wrapped in decltype which ONLY
// determines the resulting type of the expression: no code is ever called.
//
// The result is sigaction_flags_type has the type of the sa_flags member.
//
using sigaction_flags_type =
  decltype(detail::get_member_type(&detail::struct_sigaction_type::sa_flags))
;

} // namespace detail

enum class signal_handler_flags : 
  typename detail::sigaction_flags_type
{
  interruptable = SA_NODEFER,
  auto_restart_system_calls = SA_RESTART,
  revert_to_default = 
#if defined(__gnu_linux__) || defined(__linux__)
    //
    // For some unknown reason, SA_RESETHAND in Linux is set to a value 
    // larger than the maximum allowed by int, i.e., the value is likely 
    // intended to be negative being improperly set with a positive value greater than
    // std::numeric_limits<int>::max(). A static_cast is used here to force
    // successful compilation (and non-portable integer wrapping).
    //
    // NOTE: Since this is from the operating system it is "defacto" correct
    //       even though it violates the C++ standard, thus, we force the
    //       value to be set.
    //
    static_cast<typename detail::sigaction_flags_type>(SA_RESETHAND),
#else
    // Otherwise, just set the correct POSIX value...
    SA_RESETHAND,
#endif // #ifdef __linux

  synchronous_default = auto_restart_system_calls,
  asynchronous_default = interruptable | auto_restart_system_calls
};

//===========================================================================

signal_handler_op install_signal_handler(
  signal_id const& id,
  signal_handler_op const& op = signal_handler_default,
  signal_handler_flags const& flags = signal_handler_flags::synchronous_default
);

//
// The following inline functions allow one to pass a signal handler
// using a function pointer (signal-style or sigaction-style) instead.
//
inline signal_handler_op install_signal_handler(
  signal_id const& id,
  signal_handler_op::traditional_op_type const& op,
  signal_handler_flags const& flags=signal_handler_flags::synchronous_default
)
{ 
  return install_signal_handler(id, signal_handler_op{op}, flags);
}

inline signal_handler_op install_signal_handler(
  signal_id const& id,
  signal_handler_op::sigaction_op_type const& op,
  signal_handler_flags const& flags=signal_handler_flags::synchronous_default
)
{ 
  return install_signal_handler(id, signal_handler_op{op}, flags);
}

//===========================================================================

//
// The following convenience inline functions allow one pass a list (i.e., a
// std::initializer_list<signal_id>) of signal numbers to install a specific
// handler.
//
inline std::vector<signal_handler_op> install_signal_handler(
  std::initializer_list<signal_id> const& ids,
  signal_handler_op const& op = signal_handler_default,
  signal_handler_flags const& flags=signal_handler_flags::synchronous_default
)
{
  std::vector<signal_handler_op> retval{};
  for (auto& id : ids)
    retval.push_back(install_signal_handler(id, op, flags));
  return retval;
}

inline std::vector<signal_handler_op> install_signal_handler(
  std::initializer_list<signal_id> const& ids,
  signal_handler_op::traditional_op_type const& op,
  signal_handler_flags const& flags=signal_handler_flags::synchronous_default
)
{
  std::vector<signal_handler_op> retval{};
  for (auto& id : ids)
    retval.push_back(
      install_signal_handler(id, signal_handler_op{op}, flags)
    );
  return retval;
}

inline std::vector<signal_handler_op> install_signal_handler(
  std::initializer_list<signal_id> const& ids,
  signal_handler_op::sigaction_op_type const& op,
  signal_handler_flags const& flags=signal_handler_flags::synchronous_default
)
{
  std::vector<signal_handler_op> retval{};
  for (auto& id : ids)
    retval.push_back(
      install_signal_handler(id, signal_handler_op{op}, flags)
    );
  return retval;
}

//===========================================================================

bool block_signals(std::initializer_list<signal_id> const& ids);
bool unblock_signals(std::initializer_list<signal_id> const& ids);

//===========================================================================

template <typename Container>
auto block_signals(Container const& c) ->
  std::enable_if<
    std::is_same_v<
      std::remove_cv_t<
        std::remove_reference_t<typename Container::value_type>
      >,
      signal_id
    >,
    bool
  >
{
  sigset_t mask;
  sigemptyset(&mask);
  for (auto const& id : c)
    sigaddset(&mask, id);
  return sigprocmask(SIG_SETMASK, &mask, NULL) != -1;
}

//===========================================================================

template <typename Container>
auto unblock_signals(Container const& c) ->
  std::enable_if<
    std::is_same_v<
      std::remove_cv_t<
        std::remove_reference_t<typename Container::value_type>
      >,
      signal_id
    >,
    bool
  >
{
  sigset_t mask;
  sigemptyset(&mask);
  for (auto const& id : c)
    sigaddset(&mask, id);
  return sigprocmask(SIG_UNBLOCK, &mask, NULL) != -1;
}

//===========================================================================

inline bool block_signal(signal_id const& id) 
{
  return block_signals({id});
}

inline bool unblock_signal(signal_id const& id) 
{
  return unblock_signals({id});
}

//===========================================================================

} // namespace comp3400_2020w::a03

//===========================================================================

#endif // #ifndef COMP3400_2020W_A03_provided_signal_hxx_
