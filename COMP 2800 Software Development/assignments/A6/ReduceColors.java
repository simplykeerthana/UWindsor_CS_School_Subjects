 import java.io.*;
import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.PixelGrabber;
import java.awt.Dimension;

public class ReduceColors{
    
     private static  JFrame frame = new JFrame("Color Reduce Panels");
     
    private static ImageIcon icon1 = new ImageIcon("Original Image");
    private static ImageIcon icon2 = new ImageIcon("Blue Reduced");
    private static ImageIcon icon3 = new ImageIcon("Green Reduced");
    private static ImageIcon icon4 = new ImageIcon("Red Reduced");
    
    private static String image_name;
    private static File f_orig, f, f1, f2 = null;
    private static  BufferedImage img_orig, img, img1, img2 = null;
    private static BufferedImage resize_image;
    private static int width, height;
    private static String image_extension, image_non_extension;
     
  public static void main(String args[])throws IOException
    {
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      PaintPanel app1 = new PaintPanel();
        app1.setBounds(0,0, 200,200);
      PaintPanel2 app2 = new PaintPanel2();
      PaintPanel3 app3 = new PaintPanel3();
      PaintPanel4 app4 = new PaintPanel4();
      GridLayout layout = new GridLayout(1,4);
      layout.setVgap(10);
      layout.setHgap(10);
     
       frame.setLayout(layout);
      
       frame.add(frame.getContentPane().add(app1));
       frame.add(frame.getContentPane().add(app2));
       frame.add(frame.getContentPane().add(app3));
       frame.add(frame.getContentPane().add(app4));
            
       
       frame.setSize(1600, 1600);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
      image_name = args[0];
      
      //read original image;
      
      try{
        f_orig = new File(image_name);
        img_orig = ImageIO.read(f_orig);
          resize_image = resize(img_orig, img_orig.getHeight(),img_orig.getWidth());
         
        //  image_type.setText(Integer.toString(img_orig.getType()));
      }catch(IOException e){
        System.out.println(e);
      }
        image_extension = "";
        
        

       int i = image_name.lastIndexOf('.');
              if (i > 0) {
                  image_extension = image_name.substring(i+1);
              }
              
              System.out.println(image_extension);
        
       image_non_extension = image_name.replaceFirst("[.][^.]+$", "");
        System.out.println(image_non_extension);
        
    //read image to drop blue colors
    try{
      f = new File(image_name);
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }
      
      

    //get image width and height
     width = img.getWidth();
     height = img.getHeight();

    //remove all the blue pixels off
    for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int p = img.getRGB(x,y);

        int a = (p>>24)&0xff;
        int r = (p>>16)&0xff;
        int g = (p>>8)&0xff;
        int b = p&0xff;

        
        //replace RGB value. this removes all the blue image
        p = (a<<24) | (r<<16) | (g<<8) | 0;
        img.setRGB(x, y, p);
      }
    }

    //write image
    try
      {
         
          f = new  File(image_non_extension + "-b." + image_extension);
      ImageIO.write(img, image_extension, f);
    }catch(IOException e){
      System.out.println(e);
    }
      
      try{
        f1 = new File(image_name);
        img1 = ImageIO.read(f1);
      }catch(IOException e){
        System.out.println(e);
      }
      
      //remove all the green pixels off
      for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
          int p = img1.getRGB(x,y);

          int a = (p>>24)&0xff;
          int r = (p>>16)&0xff;
          int g = (p>>8)&0xff;
          int b = p&0xff;

          //calculate average
          //replace RGB value. this removes all the green image
            p = (a<<24)|  (r<<16) | 0 | b;
          img1.setRGB(x, y, p);
        }
      }

      //write image
      try
        {
          
            f1 = new File(image_non_extension + "-g." + image_extension);
        ImageIO.write(img1, image_extension, f1);
      }catch(IOException e){
        System.out.println(e);
      }
      
      
      try{
        f2 = new File(image_name);
        img2 = ImageIO.read(f2);
      }catch(IOException e){
        System.out.println(e);
      }
      
      //remove all the green pixels off
           for(int y = 0; y < height; y++){
             for(int x = 0; x < width; x++){
               int p = img2.getRGB(x,y);

               int a = (p>>24)&0xff;
               int r = (p>>16)&0xff;
               int g = (p>>8)&0xff;
               int b = p&0xff;

               //calculate average
               //replace RGB value. this removes all the red image
                 p =(a<<24)|  (g<<8) | b ;
               img2.setRGB(x, y, p);
             }
           }
      
    
      //write image
                try
                  {
                      
                     
                      f2 = new File(image_non_extension.concat("-r.") + image_extension);
                  ImageIO.write(img2, image_extension, f2);
                }catch(IOException e){
                  System.out.println(e);
                }
      

          

      
  }//main() ends here
    
    static class PaintPanel extends JPanel
       {
          
           
           
        

              protected void paintComponent(Graphics g)
              {
                  super.paintComponent(g);
                  
                  Graphics2D g2d = (Graphics2D) g.create();
                 // Graphics g2;
                  
                 g2d.drawImage(resize_image, 0, 0,width, height, this);
                  
                  repaint();
              }
              

          }
    
    static class PaintPanel2 extends JPanel
         {
            
              //setPreferredSize(new Dimension(150, 150));
             
             
          

                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    
                    Graphics2D g2d = (Graphics2D) g.create();
                   // Graphics g2;
                    
                   g2d.drawImage(img, 0, 0,width, height, this);
                    
                    repaint();
                     
                }
                

            }
    static class PaintPanel3 extends JPanel
         {
            
              //setPreferredSize(new Dimension(150, 150));
             
            // int width = img_orig.getWidth();
            // int height = img_orig.getHeight();
          

                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    
                    Graphics2D g2d = (Graphics2D) g.create();
                   // Graphics g2;
                    
                   g2d.drawImage(img1, 0, 0,width,height, this);
                    
                    repaint();
                     
                }
                

            }

    static class PaintPanel4 extends JPanel
         {
            
             //setPreferredSize(new Dimension(150, 150));
             
             
          

                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    
                    Graphics2D g2d = (Graphics2D) g.create();
                 
                    
                   g2d.drawImage(img2, 0, 0,width, height , this);
                    
                    repaint();
                }
                

            }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    
}//class ends here
