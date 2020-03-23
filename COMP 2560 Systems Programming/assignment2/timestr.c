#include <time.h>
#include <stdio.h>

int main(int argc, char * argv[])
{
  // pick a random time
  time_t t = 1183489119;

  // convert it to a string with strftime
  char ts[24];
  strftime(ts, 24, "%b %d %H:%M", localtime(&t));

  printf("time_t %ld is much more readable as %s, wouldn't you say?\n", t, ts);

  return 0;
}
