/*
    Name: Keerthana Madhavan
    Student No: 104995097
    Course: COMP_2800 Lab 2
    Program: Use a JPanel and SubPanel to draw rectangle below and oval above
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ShapeSwitchTest extends JFrame {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  // JPanel for the shape and color controls
  private JPanel controlsJPanel;
 

  // JButton to select the color
  private JButton colorJButton;

  // PaintJPanel for drawing shapes
  private PaintJPanel painterPaintJPanel;
    private PaintJPanel painterPaintJPanel1;

  // array of shape types
  
  private String[] shapeTypes = { "Oval" };

  public ShapeSwitchTest() {
    createUserInterface();
  }

    // this creates a drawing pane for the user to draw in
  private void createUserInterface()
    {
    Container contentPane = getContentPane();

    // enable explicit positioning of GUI components
    contentPane.setLayout(null);

    // set up controlsJPanel
    controlsJPanel = new JPanel();
    controlsJPanel.setBounds(0, 0, 400, 40);
    controlsJPanel.setLayout(null);
    contentPane.add(controlsJPanel);

    // set up painterPaintJPanel - internal specific drawing pane just for the user
        
        
    // PaintJPanel for the rectangle
    painterPaintJPanel = new PaintJPanel();
         painterPaintJPanel.setPreferredSize(new Dimension(1000,1000));
    painterPaintJPanel.setBounds(0, 0, 1000, 1000);
         
     painterPaintJPanel.setBackground(Color.WHITE);
     contentPane.add(painterPaintJPanel);
       
    // PaintJPanel for the Oval
         painterPaintJPanel1 = new PaintJPanel();
         painterPaintJPanel1.setPreferredSize(new Dimension(450,450));
            painterPaintJPanel1.setBounds(100, 100, 300, 300);
            painterPaintJPanel1.setBackground(Color.YELLOW);
           painterPaintJPanel1.setCurrentShapeType("Oval");
         painterPaintJPanel.add(painterPaintJPanel1); // adding the oval as a sub panel for the
        
    // for choosign colors of the shape just for fun

    colorJButton = new JButton();
    colorJButton.setBounds(210, 2, 80, 24);
    colorJButton.setText("Color");
    controlsJPanel.add(colorJButton);
    colorJButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        colorJButtonActionPerformed(event);

      }
    });

    setTitle("Shapes in Seperate Panes"); // set title bar string
    setSize(1000, 1000); // set window size
    setVisible(true); // display window

  } // end method createUserInterface
  
    // select a new color for the shape, if new color is selected

  private void colorJButtonActionPerformed(ActionEvent event) {
    Color selection = JColorChooser.showDialog(null, "Chosse a Color", Color.BLACK);

    if (selection != null) {
      colorJButton.setBackground(selection);
      painterPaintJPanel.setCurrentColor(selection);
      painterPaintJPanel1.setCurrentColor(selection);
    }

  } // end method colorJButtonActionPerformed

  
  // end method shapeJComboBoxActionPerformed
  // main method
  public static void main(String args[])
    {

        ShapeSwitchTest application = new ShapeSwitchTest();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  // end method main
} // end class ShapeSwitch Test
