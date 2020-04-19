/*
    Name: Keerthana Madhavan
    Date: 21/01/2020
    Program: When clicked on a oval, a popup menu is shown with a option to clear the oval
    Student ID: 104995097
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class PopUpMenu
{

    public PopUpMenu()
    {
        //creating the JFrame
        JFrame frame = new JFrame();
        frame.setTitle("Oval Clear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(true);

        initComponents(frame);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        //create frame and components on EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PopUpMenu(); // runs the pop up menu
            }
        });
    }

    private void initComponents(JFrame frame) {
        frame.add(new ShapePanel()); //adds the shape panel to the screen
    }
}


//custom panel
class ShapePanel extends JPanel
{

    JPopupMenu menu = new JPopupMenu("popup menu"); // new popup menu
        private Shape cirlce = new Ellipse2D.Double(260, 100, 10, 10); // creating oval of a fixed size
         private Dimension dim = new Dimension(450, 300);
        private final ArrayList<Shape> shapes; // arraylist of shapes
  

    public ShapePanel() {
        shapes = new ArrayList<>();
        shapes.add(cirlce); // initial oval in the arraylist
       
        // a mouse listener that listens for the
        addMouseListener(
                new MouseAdapter() // anonymous inner class
                {
                  
                   @Override
                   public void mousePressed(MouseEvent event)
                   {
                       if(event.getButton() == MouseEvent.BUTTON1)
                       {
                           
                          shapes.clear(); // to clear the previous oval on the screen
                          cirlce = new Ellipse2D.Double(event.getX(),event.getY(), 10, 10);
                          shapes.add(cirlce);
                                                                          
                          repaint(); // repaint JFrame
                       }
                       else if(event.getButton() == MouseEvent.BUTTON3)
                       { // if the back button is clicked
                           for (Shape s : shapes) // this checks for each shape in the array if the clicked point exists or not
                           {

                                   if (s.contains(event.getPoint()))
                                   {//check if mouse is clicked within shape

                                       //we can either just print out the object class name
                                       System.out.println("Clicked a "+s.getClass().getName());

                                       //or check the shape class we are dealing with using instance of with nested if
                                      if (s instanceof Ellipse2D)
                                      {
                                           System.out.println("Clicked a circle");
                                           menu.show(event.getComponent(), event.getX(), event.getY()); //shows the menu only when a shape exists
                                       }
                                   }
                            }
                       }
                   }
                }
             );
        
       
              // aa action listener for the popup menu
              
              JMenuItem clearPoint = new JMenuItem("Clear");
              clearPoint.addActionListener(new ActionListener()
               {
                  
                  public void actionPerformed(ActionEvent e)
                  {
                      System.out.println("Menu item clear is shown");
                      
                      shapes.clear(); //to clear the existing oval when clicked on clear
                      repaint();
                  }
              });
              
            menu.add(clearPoint); //adds the color option to the
     
    }

    @Override
    protected void paintComponent(Graphics grphcs)
    {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setColor(Color.BLUE); // set the shape color to be blue
     
        for (Shape s : shapes)
        {
            g2d.fill(s); // fills the color
        }
    }
}
