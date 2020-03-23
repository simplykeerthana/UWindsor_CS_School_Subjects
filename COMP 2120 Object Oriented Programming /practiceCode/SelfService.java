import java.util.Scanner;

public class SelfService
{
    public static void main(String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("Enter number od items purchased \n followed by the cost of one item. \n Do not use the dollar sign.");
        
        int count = keyboard.nextInt();
        double price = keyboard.nextDouble();
        double total = count * price;
        
        System.out.printf("%d items at $%.2f each. %n", count, price);
        System.out.printf("Total amount due is $%.2f. %n", total);
        
        System.out.println("Please take your merchandise.");
        System.out.printf("place $%.2f in a envelope $n", total);
        System.out.println("and slide it under the office dooe. \n Thank you for using the self-service line");
    }
}
