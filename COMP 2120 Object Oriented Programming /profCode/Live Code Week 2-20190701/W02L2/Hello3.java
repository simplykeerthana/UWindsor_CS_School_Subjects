import java.util.Scanner;

class Hello3
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    while (in.hasNextDouble())
    {
      System.out.println("Value: "+in.nextDouble());
    }
  }
}
