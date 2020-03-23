public class Y extends X
{
  public Y(int x, int y)
  {
    super(x,y);
  }

  public void setI(int x, int y)
  {
    this.i = -3*x - y;
  }

  public static void main(String[] args)
  {
    Y v = new Y(4,8);
    v.output();
  }
}
