
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class server{
    
    static ArrayList<File> files = new ArrayList<File>();
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 6868;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        InetAddress ip=InetAddress.getLocalHost();
            System.out.println("Files to send to client: "+args.length);
            for(int i=0;i<args.length;i++)
            {
                files.add(new File(args[i]));
                System.out.print(args[i]);
            }
            System.out.println();
            System.out.println("Ask the client to connect to: "+ip.getHostAddress());
            System.out.println("In port: "+server.getLocalPort());
            
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            System.out.println("Connected!\nSending...");
            
            // send number of files
            DataOutputStream ois = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            ois.writeInt(args.length);
            
            
            for(int i=0;i<args.length;i++)
            {
                //send name of the file
                ois.writeUTF(args[i]);
                System.out.println(args[i]);
                ois.flush();
                //send size of file
                ois.writeInt((int)files.get(i).length());
                System.out.println("file size: "+ files.get(i).length());
                ois.flush();
                //sends file
                byte[] buf = new byte[(int) files.get(i).length()];
                FileInputStream fis = new FileInputStream(files.get(i));
                int n;
                while ((n = fis.read(buf)) != -1) {
                    ois.write(buf, 0, n);
                    System.out.println("file length: "+n);
                    ois.flush();
                }
                ois.flush();
            }
           
            DataInputStream ij=new DataInputStream(socket.getInputStream());
            String ansgot="";
            
            while(!ansgot.equals("exit")) {
                ansgot =ij.readUTF();
                System.out.println("Message from Client: "+ansgot);
            }
                
            
           
            
            
            //close resources
            ois.close();
            socket.close();
            
            //terminate the server if client sends exit request
            
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    
    }
}
