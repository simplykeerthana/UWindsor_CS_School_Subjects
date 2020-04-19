import java.awt.*;


public abstract class MyShape extends Object
{
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;

    public MyShape(int firstX, int firstY, int secondX, int secondY, Color shapeColor)
    {
        setX1(firstX);
        setY1(firstY);
        setX2(secondX);
        setY2(secondY);
        setColor(shapeColor);
    }


// uses the set and getter methods to set the size retried from the mouse listeners
    
    public void setX1(int x)
    {
        x1 = x;
    }

    public int getX1()
    {
        return x1;
    }

    public void setY1(int y)
    {
        y1 = y;
    }

    public int getY1()
    {
        return y1;
    }

    public void setX2(int x)
    {
        x2 = x;
    }

    public int getX2()
    {
        return x2;
    }

    public void setY2(int y)
    {
        y2 = y;
    }

    public int getY2()
    {
        return y2;
    }

    public void setColor(Color c)
    {
        color = c;
    }

    public Color getColor()
    {
        return color;
    }

     public abstract void draw(Graphics g);



}

