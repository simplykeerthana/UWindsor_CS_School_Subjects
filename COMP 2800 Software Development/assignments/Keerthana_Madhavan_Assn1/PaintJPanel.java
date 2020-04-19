import java.awt.*; 
import java.awt.event.*;
 import java.util.*;
 import javax.swing.*;
import java.util.ArrayList;

public class PaintJPanel extends JPanel 
{
    
    /**
    	 *
    	 */
    private static final long serialVersionUID = 1L;

    // ArrayList to hold the shapes
    private ArrayList shapesArrayList = new ArrayList();

    private MyShape currentShape;


private String currentType = "Rectangle";

private Color currentColor = new Color(204, 204, 204);

public PaintJPanel()
{
    addMouseListener(

    new MouseAdapter()
    {
        // if the mouse is pressed
        public void mousePressed(MouseEvent event)
        {
            // here it repaints the every time the user tries to draw a shape
            shapesArrayList.clear();
            repaint();
            PaintJPanelMousePressed(event);
            
        }
        
    }

    );

    addMouseMotionListener(

    new MouseMotionAdapter()
    {
        // if the mouse is dragged
        public void mouseDragged(MouseEvent event)
        {
            PaintJPanelMouseDragged(event);
            repaint(); //new added
        }
    }

    );
  

}

    

    public void setCurrentShapeType(String shape)
    {
        currentType = shape;

    }

public void setCurrentColor(Color shapeColor)
{
    currentColor = shapeColor;
}

public void PaintJPanelMousePressed(MouseEvent event)
{
  
    if(currentType.equals("Rectangle"))
    {

        
        currentShape = new MyRectangle(event.getX(), event.getY(), event.getX(),event.getY(), currentColor );
      
    }
    else if(currentType.equals("Oval"))
    {
        
        currentShape = new MyOval(event.getX(), event.getY(), event.getX(),event.getY(), currentColor );
       
    }

    shapesArrayList.add(currentShape);

}

public void PaintJPanelMouseDragged(MouseEvent event)
{
    currentShape.setX2(event.getX());
    currentShape.setY2(event.getY());
    
}

public void paintComponent( Graphics g)
{
    super.paintComponent(g);

    MyShape nextShape;

    Iterator shapesIterator = shapesArrayList.iterator();

    while(shapesIterator.hasNext())
    {
        nextShape = (MyShape) shapesIterator.next();
    
        nextShape.draw(g);
        
    }
}



}
