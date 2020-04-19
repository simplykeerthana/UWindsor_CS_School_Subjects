import java.net.*;
import java.io.*;
import java.util.*;


public class server {
    
    
      private static ArrayList<File> myFile = new ArrayList<>();
    public static void main (String [] args ) throws IOException
    {
        for(int i = 0; i< args.length; i++)
               {
                   myFile.add(new File(args[i]));
               }
         System.out.println(myFile.toString());
        
        ServerSocket serverSocket = new ServerSocket(55588);
        Socket sock = serverSocket.accept();
        System.out.println("Accepted connection : " + sock);
      
        
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
                       System.out.println(myFile.size());
               //write the number of files to the server
                       dos.writeInt(myFile.size());
                       dos.flush();
        
        //File myFile = new File("Shapes2JPanel$2.class");
        for (int i = 0; i < myFile.size(); i++)
        {
            byte[] mybytearray = new byte[(int) myFile.get(i).length()];
               
            FileInputStream fis = new FileInputStream(myFile.get(i));
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);
               
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);
               
            OutputStream os = sock.getOutputStream();
               
            //Sending file name and file size to the server
            DataOutputStream dos1 = new DataOutputStream(os);
            dos1.writeUTF(myFile.get(i).getName());
            dos1.writeLong(mybytearray.length);
            dos1.write(mybytearray, 0, mybytearray.length);
            dos1.flush();
               
            //Sending file data to the server
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
               
            //Closing socket
            os.close();
            dos1.close();
            
            
            
            sock.close();
            System.out.println("File transfer complete");
            
        }
            
        }
    
    
}


