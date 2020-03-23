import java.util.Scanner;


public class midterm1
{
    public static void main(String [] args)
    {
        Scanner objectScanner = new Scanner(System.in);

        int totalLine = 0;
        String line;

        while(objectScanner.hasNextLine())
        {
                line = objectScanner.nextLine();
            System.out.println("Line: " + line);
            totalLine = totalLine + 1;

                if(totalLine < 0)
                {
                    System.out.println("There are no line entered");
                    break;

                }
        }

        if(totalLine > 0)
        {
            System.out.println("Total number of lines: " + totalLine);
        }


    }

}
