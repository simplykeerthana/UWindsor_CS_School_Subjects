#include <atomic>
#include <cstdio>
#include <cstdlib>
#include <iostream>
#include "signal.hxx"

using namespace std;
using namespace comp3400_2020w::a03;
  
int const INVALID_SIGNAL = -1;
atomic<int> id = INVALID_SIGNAL;

int main()
{
  [[maybe_unused]] auto old = install_signal_handler(
    { SIGINT, SIGHUP, SIGQUIT },
    [](int signo) -> void 
    {
      id.store(signo, memory_order_release);
    },
    signal_handler_flags::synchronous_default
  );

  for (auto c=cin.get(); char_traits<char>::not_eof(c); c=cin.get()) 
  {
    if (id != INVALID_SIGNAL)
    {
      cout << "[signo:" << id.load(memory_order_acquire) << "] ";
      id.store(INVALID_SIGNAL, memory_order_release);
    }
    cout << static_cast<unsigned>(c) << ' ';
  }
  cout << '\n';
}
