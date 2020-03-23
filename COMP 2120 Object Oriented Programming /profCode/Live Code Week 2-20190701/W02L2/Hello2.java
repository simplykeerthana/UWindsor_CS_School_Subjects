import java.util.Scanner;

class Hello2
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    try
    {
      while (in.hasNext())
      {
        System.out.println("Value: "+in.nextDouble());
      }
    }
    catch (Exception e)
    {
      System.out.println("An Exception occurred.");
    }
    finally 
    {
      System.out.println("This always runs.");
    }
  }
}
