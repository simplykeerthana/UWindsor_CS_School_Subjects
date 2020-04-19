
import java.awt.event.*;
 import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.io.*;
import java.util.*;


// this paintframe gets an integer k from the c server.

// the server code should go in the  main function

 public class PaintFrame extends JFrame
{
    static Random generator = new Random();
    private Color color = new Color(Math.abs(generator.nextInt())% 16777216);
     private JPanel content = new JPanel();
     private JButton drawButton = new JButton("Draw");
    private PaintPanel paintPanel;
    private static int value, count;
    private static JFrame frame;

     public PaintFrame(int value) {

         getContentPane().add(content);
         content.setLayout(new BorderLayout());
         paintPanel = new PaintPanel(value);
         content.add(paintPanel, BorderLayout.CENTER);
     }

     
     class PaintPanel extends JPanel {

         public PaintPanel(int value) {
             count = value;
             setSize(500, 500);
             setBackground(Color.WHITE);
         }

         @Override
         protected void paintComponent(Graphics g) {
             
             int red, green, blue;
             
             // choose the rgb values
             
             super.paintComponent(g);
             Random random = new Random();
            
             // draw 5 random circles
             
             for (int i = 0; i < count; i++) {
                 red = (int) (Math.random() * 256);
                 green = (int) (Math.random() * 256);
                 blue = (int) (Math.random() * 256);
                  g.setColor(new Color(red, green, blue));
                 g.drawOval(random.nextInt(500), random.nextInt(500),
                         6, 6);
             }
         }

     }
     
     

         public static void main(String[] args)  throws IOException{


             
             
             //get the localhost IP address
                    int count=0;
                    InetAddress host = InetAddress.getLocalHost();
                    Socket socket = null;
                   byte[] b=new byte[100];
                    DataInputStream ois = null;
                  String message="";
                        //establish socket connection to server
                        
                    while(!message.equals("0"))
                    {
                        socket = new Socket(host.getHostName(), 7776);
                       
                    
                        //write to socket using ObjectOutputStream
                        System.out.println("Connected!");
                        ois = new DataInputStream(socket.getInputStream());
                    try{
                                 ois.readFully(b);
                    }
                    catch(EOFException e)
                    {
                        
                    }
                    message=new String(b).trim();
                        System.out.println("Message: " + message);
                    
                        //close resources
                    
                        
                     value=Integer.parseInt(message);
                    frame = new PaintFrame(value);
                            
                            frame.setDefaultCloseOperation(PaintFrame.EXIT_ON_CLOSE);
                                        frame.setTitle("Client");
                                        frame.setSize(500, 500);
                                        frame.setVisible(true);
                            
                    }
             
            

         }

     
 }
