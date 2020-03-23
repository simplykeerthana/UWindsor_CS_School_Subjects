/*
 Name: Keerthana Madhavan
 Date: 4/6/19
 Program: 2.1 Console Input and Output
 
 */

public class chap2_1
{

    public static void main(String [] args)
    {
        System.out.println("One two buckle your shoe.");
        System.out.println("Three four shut the door.");
        
        String s = "Hello" + "Joe";
        
        System.out.println(s);
        
        System.out.println(2 + " " + 2);
        System.out.println( 2 + 2);
        System.out.println("2" + "2");
        
        
        double value = 85.99934;
        System.out.printf("Start%8.2fEnd", value);
        System.out.println();
        System.out.printf("Start%-8.2fEnd", value);
        System.out.println();
        
        String exam = "I need to study for my exam";
        
        System.out.printf("%6.2f is need, so %s. %n", value, exam);
        System.out.print("I can do this");
        
        String aString = "Jelly beans";
        System.out.println("START1234567890");
        System.out.printf("START%sEND %n", aString);
        System.out.printf("START%4sEND %n", aString);
        System.out.printf("START%13sEND %n", aString);
        System.out.println();
        
        double d = 123.1234567890;
        
        System.out.println("START1234567890");
        System.out.printf("START%sEND %n %9f %n", aString, d);
        
    }


}
