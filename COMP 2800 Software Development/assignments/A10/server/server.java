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
import java.util.Scanner;

public class server{
    
    static ArrayList<File> files = new ArrayList<File>();
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 6868;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
	
        
        String fileChosen = args[0];
        System.out.println(fileChosen);
		while(true){
			//create the socket server object
			server = new ServerSocket(port);
			
			//keep listens indefinitely until receives 'exit' call or program terminates
			InetAddress ip=InetAddress.getLocalHost();
			
			
			
			files.add(new File("page.class"));
			
			
			System.out.println();
			System.out.println("Ask the client to connect to: "+ip.getHostAddress());
			System.out.println();
			
			//creating socket and waiting for client connection
			Socket socket = server.accept();
			System.out.println("Connected!\nSending...");
			
			// send number of files
			DataOutputStream ois = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			ois.writeInt(1);
			
			
			for(int i=0;i< 1;i++)
			{
				//send name of the file
				ois.writeUTF("page.class");
				
				ois.flush();
				
				//send size of file
				ois.writeInt((int)files.get(i).length());
				ois.flush();
				
				//sends file
				byte[] buf = new byte[(int) files.get(i).length()];
				FileInputStream fis = new FileInputStream(files.get(i));
				int n;
				while ((n = fis.read(buf)) != -1) {
					ois.write(buf, 0, n);
					System.out.println("file size: "+n);
					ois.flush();
				}
				ois.flush();
			}
		   
			DataInputStream ij = new DataInputStream(socket.getInputStream());
			
			System.out.println("waiting..");
			System.out.println();
			//read student name
			String sname = ij.readUTF();
			//read file name
			String fname = ij.readUTF();
			System.out.println("recieving "+fname+"...");
			//read file size
			int size = ij.readInt();
			//read file
			byte[] b = new byte[size];
			ij.read(b);
			//save file
			File file = new File(".//"+sname);
			file.mkdir();
			file = new File(".//"+sname+"//"+fname);
			FileOutputStream out =  new FileOutputStream(file);
			out.write(b);
			out.flush();
			//close file writing stream
			out.close();
			
			System.out.println("recieved all files");
			System.out.println();
			//test program
			String classname = fname; // the class name
			String textfile = null;
			
			File rfile = new File(".//"+sname+"//report.txt");
			FileOutputStream fout =  new FileOutputStream(rfile);

			BufferedReader pin;
			BufferedWriter pout;
			
			int num = 1;
			 
			Process p; //process
			Scanner scan;
			String txtans = "defualt";
			String txtinput = "";
			String progAns = "default";
			String Report = "";
			
			try {
				
				
				p = Runtime.getRuntime().exec("gcc ./"+sname+"/"+classname);
				p.waitFor();

				System.out.println("compiling done!");
				Report += "  Program File Compiled Successfully!  \n";
				if(fname.contains("1")){
					textfile = "testcase1.txt";
				}
				else if(fname.contains("2")){
					textfile = "testcase2.txt";
				}
				System.out.println(textfile +" chosen");
				
				scan =  new Scanner(new FileInputStream(textfile));
				
				p = Runtime.getRuntime().exec("./a.out");
				
				pin = new BufferedReader(new InputStreamReader(p.getInputStream()));
				pout = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
				
				System.out.println("executing...");
				
				while(scan.hasNext()) {
					
					txtinput = scan.nextLine();
					
					
					if(txtinput.contains("#")) {
						num++;
						p = Runtime.getRuntime().exec("./a.out");
						pin = new BufferedReader(new InputStreamReader(p.getInputStream()));
						pout = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

					}
					else if(txtinput.contains("*")) {
						
						txtans = scan.nextLine();
						
						progAns = pin.readLine();
						
						if(progAns.equals(txtans)) {
							Report += "  Test Case#"+num+" : Pass  \n";
						}
						else {
							Report += "  Test Case#"+num+" : Fail  \n";
						}
						
					}
					else{
						pout.write(txtinput + "\n");
						pout.flush();
					}
				}
				p.destroy();
				if (pin != null)
					pin.close();
				if (pout != null)
					pout.close();
				
			} catch (Exception e1) {
				Report = "Failed to compile.";
			}
			finally{
				//send report to the client
				ois.writeUTF(Report);
				ois.flush();
				//write report to text file;
				byte[] rb = Report.getBytes(); // report bytes
				fout.write(rb);
				System.out.println("Testing Complete. Report has been sent.");
				
				
				//close resources
				ois.close();
				socket.close();
				fout.close();
				out.close();
				//terminate the server if client sends exit request
					
				System.out.println("Shutting down Socket server!!");
				//close the ServerSocket object
				server.close();
			}
		
		}
	}
}
