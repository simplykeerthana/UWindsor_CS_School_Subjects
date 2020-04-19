#include <stdio.h>
#include<unistd.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<string.h>
#include<netdb.h>

int main(int argc, char *argv[]){
	char buffer[100];  
	
	int sd, client;
	socklen_t len;

	struct sockaddr_in servAdd; //server socket address  
	struct sockaddr_in cliAdd; //client socket address

	sd = socket(AF_INET, SOCK_STREAM, 0);
  
	servAdd.sin_family = AF_INET;  
	servAdd.sin_addr.s_addr =htonl(INADDR_ANY);
 	servAdd.sin_port = htons(7776); // a port number

//all steps above are for calling bind.
	bind(sd,(struct sockaddr*)&servAdd,sizeof(servAdd));
	listen(sd, 5);


		printf("Connected!");
		while(1){
		len = sizeof(cliAdd);

		client=accept(sd,(struct sockaddr*)&cliAdd, &len);
		printf("\nEnter a number k to send to the java program: ");
		scanf("%s",buffer);
		write(client, buffer, strlen(buffer) + 1);  
		close(client);
		}
		
	
}


