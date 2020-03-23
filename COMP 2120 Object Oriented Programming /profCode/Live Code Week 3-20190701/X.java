public class X
{
  private int i;

  public X(int x, int y)
  {
    this.setI(x,y);
  }

  public int getI()
  {
    return this.i;
  }

  public void setI(int i)
  {
    this.i = i;
  }

  public void setI(int x, int y)
  {
    this.i = 3*x + y;
  }

  public void output()
  {
    System.out.println("i is: "+this.i);
  }

  public static void main(String[] args)
  {
    X v = new X(4,8);
    v.output();
  }
}
