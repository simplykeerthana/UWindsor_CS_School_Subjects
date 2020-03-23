#include <stdio.h>
#include <dirent.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include<stdlib.h>
#include<time.h>
#include<pwd.h>
#include <grp.h>

int listdir(const char * path)
{
    struct dirent *entry;
  
    DIR * dp; // the directory: the "." folder we are try to look into

    struct passwd *pw;
    struct group *gp;
    struct stat fileStatistics;
    
    char *time;
    int i;
    
  dp = opendir(path);
    stat(path, &fileStatistics);
    
  if(dp == NULL)
  {
    perror("could not open directory");
    return 1;
  }

  while((entry = readdir(dp)))
  {
    /* This loop iterates over each file entry in the directory.
     * Each file name is stored in entry->d_name. */
      // checks for permission while using the wildcard symbol to search for respective permission character
      
      //if(entry-d_name[0])
      stat(entry->d_name,&fileStatistics);
      printf( (S_ISDIR(fileStatistics.st_mode)) ? "d" : "-"); // directory
      printf( (fileStatistics.st_mode & S_IRUSR) ? "r" : "-"); // read
      printf( (fileStatistics.st_mode & S_IWUSR) ? "w" : "-"); // write
      printf( (fileStatistics.st_mode & S_IXUSR) ? "x" : "-"); 
      printf( (fileStatistics.st_mode & S_IRGRP) ? "r" : "-");
      printf( (fileStatistics.st_mode & S_IWGRP) ? "w" : "-");
      printf( (fileStatistics.st_mode & S_IXGRP) ? "x" : "-");
      printf( (fileStatistics.st_mode & S_IROTH) ? "r" : "-");
      printf( (fileStatistics.st_mode & S_IWOTH) ? "w" : "-");
      printf( (fileStatistics.st_mode & S_IXOTH) ? "x" : "-");
      
      printf("\t");
      
      printf("%d\t ",fileStatistics.st_nlink); // hardlinks
      pw=getpwuid(fileStatistics.st_uid); // user id
      printf("%s\t ",pw->pw_name);
      gp=getgrgid(fileStatistics.st_gid); //group id
      printf("%s\t ",gp->gr_name);
      printf("%4lld \t",fileStatistics.st_size); // size of the files
      time =ctime(&fileStatistics.st_mtime); // time of the last modification
     // printf("%s",  ctime(&fileStatistics.st_mtime));
      // this loop prints the string representing local time hh:mm:ss without the year
      for(i=0;i<=18;i++)
          printf("%c",time[i]);
      printf(" ");
      puts(entry->d_name);
  }

  closedir(dp);

  return 0;
}

int main(int argc, char * argv[])
{
  if(argc == 1)
    return listdir("."); // works with all directories.
  else
    return listdir(argv[1]);
}
