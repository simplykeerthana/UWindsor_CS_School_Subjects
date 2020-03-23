import java.util.Scanner;

public  class ScannerDemo
{

    public static  void main(String [] args)
    {
        Scanner keyboard = new  Scanner(System.in);
        
        System.out.println("Enter the number of pods followed by ");
        System.out.println("the number of peas in a pod");
        int numberOfPods = keyboard.nextInt(); // read an integer value from keyboard
        int peasPerPod = keyboard.nextInt();
        
        int totalNumberOfPeas = numberOfPods * peasPerPod;
        
        System.out.println(numberOfPods  + "pods and " + peasPerPod + "peas per pod");
        System.out.println("The total Number Of Peas is " + totalNumberOfPeas );
    }
}
