/*
     Name: Keerthana Madhavan
     Date: 27/5/19
     Program: Q1 from Chapter 1, Measuring metabolic equivalents (MET) of activities done by different person.
 */

public class question1
{
    public static void main(String [ ]args)
    {
        String activity1 = "Running";
        String activity2 = "Basketball";
        String activity3 = "Sleeping";
        
        int met1 = 10, met2 = 8, met3 = 1;
        
        double personWeight = 150 * 2.2; // converted to kg
        double calPerMinute = 0; // holds the Metabolic equivalents
        
        calPerMinute = (0.0175 * met1 * personWeight) + (0.0175 * met2 * personWeight) + (0.0175 * met3 * personWeight);
        
        System.out.println("Calories burned by the person is: " + calPerMinute);
        
    
        
        
    }


}
 
 
