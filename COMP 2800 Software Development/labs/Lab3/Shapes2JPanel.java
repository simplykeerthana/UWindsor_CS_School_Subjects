

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.security.SecureRandom;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class Shapes2JPanel extends JPanel
{
  Timer timer;
  public static  int speed;
    private static JButton toggleButton = new JButton("Toggle");
    private boolean rotateValue = false;
    private double angleInDegrees = 1;

    public Shapes2JPanel()
    {
        timer = new Timer(speed, new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
              {
                  if(!rotateValue)
                  {
                      //rotates clockwise
                      angleInDegrees = angleInDegrees + 1;
                  }
                  else
                  {
                      //rotatess counterclockwise
                      angleInDegrees = angleInDegrees -1;
                  }
                if (angleInDegrees == 360)
                {
                    // If there is a full circle, it will reset the angle to zero
                    angleInDegrees = 0;
                }
                    repaint();
              }
        });
        timer.start();
        
        toggleButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                rotateValue = !rotateValue; // everytime the toggle button is clicked  if chanhes to clockwise and counterclockwise
            
                System.out.println("The toggle button has been clicked:");
            }
        });
    }
    
    
   // draw general paths
    
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      SecureRandom random = new SecureRandom(); 

      int[] xPoints = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
      int[] yPoints = {0, 36, 36, 54, 96, 72, 96, 54, 36, 36};

      Graphics2D g2d = (Graphics2D) g;
      GeneralPath star = new GeneralPath(); // create GeneralPath object

      // set the initial coordinate of the General Path
      star.moveTo(xPoints[0], yPoints[0]);

      // create the star--this does not draw the star
      for (int count = 1; count < xPoints.length; count++)
       star.lineTo(xPoints[count], yPoints[count]);

      star.closePath(); // close the shape
       
       
      g2d.translate(150, 150); // translate the origin to (150, 150), around this axis everytime
       
       int startAngle = 360;
   
      // rotate around origin and draw stars in random colors
      for (int count = 1; count <= 20; count++) 
      {
          double angle = startAngle - 90;
          // rotate coordinate system
           g2d.rotate(angleInDegrees * Math.PI / angle); //rotates as per the rotated angle
         // set random drawing color
          g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        // draw filled star
           g2d.fill(star);
        // dispose the star
            g2d.dispose();
        
      }
       
       
   }
    
    public static void main(String[] args)
      {
         // create frame for Shapes2JPanel
         JFrame frame = new JFrame("Moving Star");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          speed = Integer.parseInt(args[0]);
         Shapes2JPanel shapes2JPanel = new Shapes2JPanel();
         frame.add(shapes2JPanel);
         
         frame.setBackground(Color.WHITE);
         frame.setSize(315, 330);
         // frame.setLayout(new FlowLayout());
          
          toggleButton.setBounds(50, 100, 150, 20);
                   toggleButton.setVisible(true);
          frame.add(toggleButton, BorderLayout.SOUTH);
          
          
         // frame.resize(true);
         frame.setVisible(true);
      }
    
}

