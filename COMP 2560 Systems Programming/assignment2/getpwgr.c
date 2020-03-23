#include <pwd.h>
#include <grp.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char * argv[])
{
  // check arguments
  if(argc < 3)
  {
    fprintf(stderr, "Usage: %s uid gid\n", argv[0]);
    return 1;
  }

  // convert arguments to integers
  int uid = atoi(argv[1]);
  int gid = atoi(argv[2]);

  // get information about the given UID - if it exists, print the username
  struct passwd * pwd;
  if((pwd = getpwuid(uid)) != NULL)
    printf("User ID %d is %s.\n", uid, pwd->pw_name);

  // get information about the given GID - if it exists, print the group name
  struct group * grp;
  if((grp = getgrgid(gid)) != NULL)
    printf("Group ID %d is %s.\n", gid, grp->gr_name);
  
  return 0;
}
