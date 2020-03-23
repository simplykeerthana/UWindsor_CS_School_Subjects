import java.util.Scanner;

public class ScannerDemo2
{

    public static void main(String [] args)
    {
        int n1, n2;
        Scanner scannerObject = new Scanner(System.in);
        
        System.out.println("Enter two whole numbers \n separated by two or more spaces");
        
        n1 = scannerObject.nextInt();
        n2 = scannerObject.nextInt();
          System.out.println("you entered " + n1 + " and " + n2 );
        System.out.println("Next enter two numbers. \n Decimals points are allowed");
        
        double d1, d2;
        Scanner scannerObject2 = new Scanner(System.in);
        
        d1 =  scannerObject.nextDouble();
        d2 =  scannerObject.nextDouble();
        System.out.println("you entered " + d1 + " and " + d2 );
        
        
        System.out.println("Next enter two words ");
        
       // Scanner scannerObject3 = new Scanner(System.in);
        String word1 = scannerObject.next(); // two read one word from the keyboard
        String word2 = scannerObject.next();
        
          System.out.println("you entered  " + word1 + " and " + word2 );
        
        String junk = scannerObject.nextLine(); // gets rid of the empty lin
        
        System.out.println("Next enter a line of a text: ");
        String line = scannerObject.nextLine();
        System.out.println("You entered: \"" + line + "\"");
        
        
        
    }
}
