import java.awt.*;

public class MyOval extends MyShape
{
    public MyOval(int firstX, int firstY, int secondX, int secondY, Color shapeColor)
    {
        super(firstX, firstY, secondX, secondY, shapeColor);
    }

    public void draw(Graphics g)
    {
        int upperLeftX = Math.min(getX1(), getX2());
        int upperLeftY = Math.min(getY1(), getY2());

        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1()-getY2());

        g.setColor(getColor());
        g.fillOval(upperLeftX, upperLeftY, width,height);
    }

}
