public class X
{
  public int i;
  public X(int x, int y)
  {
    i = 3*x + y;
  }
  public static void main(String[] args)
  {
    X v = new X(4,8);
    System.out.println("v's i is: "+v.i);
  }
}
