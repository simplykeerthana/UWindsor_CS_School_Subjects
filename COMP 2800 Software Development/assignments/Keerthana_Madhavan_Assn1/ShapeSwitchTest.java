/*
    Name: Keerthana Madhavan
    Student No: 104995097
    Course: COMP_2800 Assignment 1
    Program: Use JPanel drawing area to allow users to drag the mouse to draw   either an oval or a rectangle, according to the user's choice from a JComboBox.
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
  // JComboBox to allow selection of a shape
  private JComboBox shapeJComboBox;

  // JButton to select the color
  private JButton colorJButton;

  // PaintJPanel for drawing shapes
  private PaintJPanel painterPaintJPanel;

  // array of shape types
  
  private String[] shapeTypes = { "Rectangle", "Oval" };

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
    // drawing
    painterPaintJPanel = new PaintJPanel();

    painterPaintJPanel.setBounds(0, 40, 400, 340);
    painterPaintJPanel.setBackground(Color.WHITE);
    contentPane.add(painterPaintJPanel);
    // set up shapeJComboBox

    shapeJComboBox = new JComboBox(shapeTypes);
    shapeJComboBox.setBounds(90, 2, 100, 24);
    controlsJPanel.add(shapeJComboBox);
    shapeJComboBox.addActionListener(

    // listenes for which option is chosen
        new ActionListener() // anonymous inner class
        {
          // event method called when shapeJComboBox is selected
          public void actionPerformed(ActionEvent event)
        {

            shapeJComboBoxActionPerformed(event);

          }

        });  

    colorJButton = new JButton();
    colorJButton.setBounds(210, 2, 80, 24);
    colorJButton.setText("Color");
    controlsJPanel.add(colorJButton);
    colorJButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        colorJButtonActionPerformed(event);

      }
    });

    setTitle("Shape Switching"); // set title bar string
    setSize(408, 407); // set window size
    setVisible(true); // display window

  } // end method createUserInterface
  
    // select a new color for the shape, if new color is selected

  private void colorJButtonActionPerformed(ActionEvent event) {
    Color selection = JColorChooser.showDialog(null, "Chosse a Color", Color.BLACK);

    if (selection != null) {
      colorJButton.setBackground(selection);
      painterPaintJPanel.setCurrentColor(selection);
    }

  } // end method colorJButtonActionPerformed

  // set the selected shape in the painting panel to draw that respective shape
  private void shapeJComboBoxActionPerformed(ActionEvent event) {
    repaint();
    painterPaintJPanel.setCurrentShapeType((String) shapeJComboBox.getSelectedItem());

  }

  // end method shapeJComboBoxActionPerformed
  // main method
  public static void main(String args[]) {

    ShapeSwitchTest application = new ShapeSwitchTest();
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
  // end method main
} // end class ShapeSwitch Test
