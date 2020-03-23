import java.util.Scanner;

public class DemlimterDemo
{
    public static void main(String[] args)
    {
        Scanner keyboard1 = new Scanner(System.in);
        Scanner keyboard2 = new Scanner(System.in);
        
        keyboard2.useDelimiter("##");
        //Delimiter for keyboard  1 is whitespace
        //Delimitter for keyboard 2 is the ##
        
        String word1, word2;
        
        System.out.println("Enter a line of text");
        word1 = keyboard1.next();
        word2 = keyboard1.next();
        System.out.println("for keyboard 1 the two words read are: ");
        System.out.println(word1);
        System.out.println(word2);
        
        String junk =  keyboard1.nextLine(); // to get rid of the rest of the line.
        
        System.out.println("Reenter the same the line of textt");
        word1 = keyboard2.next();
        word2 = keyboard2.next();
        System.out.println("For keyboard3 the two words read are : ");
        System.out.println(word1);
        System.out.println(word2);
        
        
    }
}
