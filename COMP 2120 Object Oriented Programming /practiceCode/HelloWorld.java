/*
 
        hello World program
 
 */

public class HelloWorld{
    
    public static void main(String [] args)
    {
        System.out.println("Hello World!");
        
        for(String element : args )
        {
            System.out.println("\tElement : " + element);
        }
        
    }
    
    
}
