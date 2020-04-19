import java.awt.*;

public class MyRectangle extends MyShape
{
    public MyRectangle(int firstX, int firstY, int secondX, int secondY, Color shapeColor)
    {
        // gets the setter and getter from the MyShape class and molds it here
        super(firstX, firstY, secondX, secondY, shapeColor);
    }

    public void draw(Graphics g)
    {
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());

        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1()-getY2());

        g.setColor(getColor());

        // fills in the rectangle
        g.fillRect(upperLeftX, upperLeftY, width, height);
    }

  
}
