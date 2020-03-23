import java.util.Scanner;

class Hello1
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);

    for (String element : args)
    {
      System.out.println("Element: "+element);
    }
  }
}
