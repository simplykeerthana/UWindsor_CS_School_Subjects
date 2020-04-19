#include <cstring>
#include "signal.hxx"

namespace comp3400_2020w::a03 {

signal_handler_op::traditional_op_type const signal_handler_op::ERROR = SIG_ERR;
signal_handler_op::traditional_op_type const signal_handler_op::IGNORE = SIG_IGN;
signal_handler_op::traditional_op_type const signal_handler_op::DEFAULT = SIG_DFL;

signal_handler_op install_signal_handler(
  signal_id const& id,
  signal_handler_op const& op,
  signal_handler_flags const& flags
) 
{
  struct sigaction act;
  sigemptyset(&act.sa_mask);

  // Set the required flags...
  act.sa_flags = 
    static_cast<std::underlying_type_t<signal_handler_flags>>(flags)
  ;

  // Set the appropriate handler function...
  if (op.is_traditional())
    act.sa_handler = op.traditional_op();
  else
  {
    act.sa_sigaction = op.sigaction_op();
    act.sa_flags |= SA_SIGINFO;
  }

  struct sigaction old_act;
  memset(&old_act, 0, sizeof(old_act));

  if (sigaction(id, &act, &old_act) == -1)
    return { signal_handler_error };
  else if ((old_act.sa_flags & SA_SIGINFO) == SA_SIGINFO)
    return { old_act.sa_sigaction };
  else
    return { old_act.sa_handler };
}

bool block_signals(std::initializer_list<signal_id> const& ids) 
{
  sigset_t mask;
  sigemptyset(&mask);
  for (auto const& id : ids)
    sigaddset(&mask, id);
  return sigprocmask(SIG_SETMASK, &mask, NULL) != -1;
}

bool unblock_signals(std::initializer_list<signal_id> const& ids) 
{
  sigset_t mask;
  sigemptyset(&mask);
  for (auto const& id : ids)
    sigaddset(&mask, id);
  return sigprocmask(SIG_UNBLOCK, &mask, NULL) != -1;
}

} // namespace comp3400_2020w::a03

