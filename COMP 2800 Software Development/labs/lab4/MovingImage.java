
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.*; // all the libraries needed for sound



public class MovingImage extends JFrame
{
    
    private static JFrame f = new JFrame();
    private static boolean playSound = false;
    public static int speed;
    
    Clip clip;

    public static void main(String [] args)
    {
        f = new MovingImage();
        
        //speed = Integer.parseInt(args[0]);
        System.out.println(speed);
        
    }
        private Container win; // contains all the window components
        private Timer t;
        private Random rg; // genrates a random numberr
        private ImageIcon bouncer; // the “moving” part
    
        int height, width; // for the height and width of the imageIcon
        
 
    
    // frame’s content pane
        // changes picture once per second
         public MovingImage()
        {
            
            // for the music read in with a try-catch block
            try
            {
                //the file-path is created
                File soundFile = new File("media.io_Ultimate-Victory-WST010901.wav");
                 // create an audiostream from the inputstream
                 AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                    
                clip = AudioSystem.getClip(); // gets the clip frrom the path
                clip.open(audioStream); // opens the audio file
            }
            catch (UnsupportedAudioFileException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                  e.printStackTrace();
            } catch (LineUnavailableException e)
            {
                  e.printStackTrace();
            }
            
        
        setTitle("Moving Image");
        setSize(1000,1000);
        setAlwaysOnTop(true);
        win = this.getContentPane();
        win.setBackground(Color.WHITE);
             
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rg = new Random(); // a random place
        bouncer = new ImageIcon("sunrise.jpg");
         height = bouncer.getIconHeight();
         width = bouncer.getIconWidth();
        setVisible(true);
             
        
       
       // a timer that repaints the image after it is moved to a new position
             
        t = new Timer(1000, new ActionListener ()
        {
            public void actionPerformed(ActionEvent event)
                {
                    repaint();
                }
            
        });
                t.start(); //starts the timer
        }
    
    

public void paint (Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(0,0,1000,1000); // other boudns for the rectangle so the imaage won't go out of the screen
        int xImage =  rg.nextInt(800); //x positon of the moved image
        int yImage = rg.nextInt(800); // y position of the moved image
       // bouncer.paintIcon(win, g, rg.nextInt(800), rg.nextInt(800));
        
         bouncer.paintIcon(win, g, xImage, yImage); // creates the icon on the screen
        
        f.addMouseListener(new MouseAdapter()
        {
        
   

               public void mouseClicked(MouseEvent e)
                    {
                        // creaate a exact ditto rectangular shape of the imaage size, so we can click on it and the get the coordinates
                            Rectangle imageBounds = new Rectangle( xImage,yImage,width, height);
                        
                    
                        
                            if (imageBounds.contains(e.getPoint()))
                            {
                                //point is inside given image
                                System.out.println("The frame has been clicked");
                                t.stop();
                                bouncer = new ImageIcon("flower.jpg"); // changes the image and
                                repaint(); // repisnts the icon image
                                playSound = true;
                                clip.start(); // plays the music
                            }
                        
                        
                    }

                    public void mousePressed(MouseEvent e)
                    {
                        int x = e.getX();
                        int y = e.getY();
                        System.out.println(x + " , " + y);
                        
                    }

                    public void mouseReleased(MouseEvent e)
                    {
                        clip.stop();
                        t.start();
                        bouncer = new ImageIcon("sunrise.jpg");
                        repaint();
                        
                    }

                    public void mouseEntered(MouseEvent e){}
                    
                    public void mouseExited(MouseEvent e){}
     
        });
}
    
    
}
