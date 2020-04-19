import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class client extends JFrame
{
    boolean resolve;
    private static String name, name_non_extension, serverName;
    JPanel a = new JPanel();
    private static InetAddress host;
    private static Socket socket;
        
    
    
    
    
    public client() throws IOException{
            super("loader");
            
            JLayeredPane l = new JLayeredPane();
            JPanel top = new JPanel();
            JPanel bot = new JPanel();
            setLayout(new BorderLayout());
            getContentPane().add(l, BorderLayout.CENTER);
            
            l.setBounds(0, 0, 600, 800);
            top.setBounds(0, 0, 600, 25);
            
            bot.setBackground(Color.white);
            bot.setBounds(0,30,600,775);
            
            l.add(top, 0, 0);
            l.add(bot, 1, 0);
            
            
            JLabel string = new JLabel("Server IP: ");
            JTextField classname = new JTextField();
            classname.setColumns(20);
        
      //  String name1 = "Shapes2JPanel";
            
            classname.addActionListener(new ActionListener() {
    
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    serverName = classname.getText();
               
                    System.out.println(serverName);
                    
                    try{
                            serverConnect(serverName);
                    }
                    catch(Exception e1)
                    {
                         System.out.println(e1);
                        
                    }
                    
                    try {
                        Class newC = Class.forName(name_non_extension);
                        Object c = Class.forName(name_non_extension).newInstance();
                        Method m = newC.getDeclaredMethod("getMyField");
                        m.setAccessible(true);
                        a.removeAll();
                        a = (JPanel) c;
                        
                        a.setBounds(0,30,600,600);
                        a.revalidate();
                        
                        l.add(a,1,0);
                        
                        
                        
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
                
            });
            a.setVisible(true);
            
            top.add(string);
            top.add(classname);
            
        }

    public static void main (String [] args ) throws IOException
        {
            
            
            client frame = new client();
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(600,800);
            frame.setVisible(true);
                          
    }
    
    
    public void  serverConnect(String serverName) throws IOException
    {
        
       String myip=ipid.getText();
       int mysock=Integer.parseInt(JOptionPane.showInputDialog("Which socket should I connect to ?"));
       
       FileOutputStream fos=null;
       
       try {
           socket = new Socket(myip, mysock);
       }
       catch(Exception ex)
       {}
       DataInputStream os=null;
       try {
           os = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
       } catch (IOException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       
       try {
           count = os.readInt();
           System.out.println("number of files: "+count);
       } catch (IOException e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       
       
       for(int i=0;i<count;i++)
       {
           byte[] b = new byte[1000];
           String FILE_TO_RECEIVE = "";
           try {
               //get name
               message = os.readUTF();
               System.out.println("Received: "+ message);
               FILE_TO_RECEIVE += message;
               //get size
               int size = os.readInt();
               //get file
               b = new byte[size];
               os.read(b);
               
               File file = new File(FILE_TO_RECEIVE);
               FileOutputStream out =  new FileOutputStream(file);
               out.write(b);
               out.flush();
               
               out.close();
                   
           } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
           }
       }
    }
}

