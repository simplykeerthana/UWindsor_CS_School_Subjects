package COMP_2120;

import java.util.*;


/**
 *  This class is sorts a list of double values using the TreeSet 
 * and then stores in the cotainer arraylist of doubles
 * @author Keerthana Madhavan but the code is from the professor
 */
public class A5
{
/**
 * 
 * @param args
 */    public static void main(String [] args)
    {
        TreeSet<Double> sorted_set = new TreeSet<Double>(); // a new empty TreeSet is created. 
        
        Scanner keyboard = new Scanner(System.in); // takes in user input
        
        while(keyboard.hasNextDouble()) //only process double values
        {
            sorted_set.add(Math.abs(keyboard.nextDouble())); // keep adding the value to the tree
        }
        
        ArrayList<Double> sorted_seq = new ArrayList<Double>(sorted_set); // creating a array list with the values from the tree sorted
        
        ListIterator<Double> li = sorted_seq.listIterator(sorted_seq.size()); //iterates to the last value in the list
        
        while(li.hasPrevious())
        {
            System.out.println(li.previous() * -1.0); // prints from teh largest item in the list to smallest
        }
        
        while(li.hasNext())
        {
            System.out.println(li.next()); // from beginning of the list to the last value
        }
    }


}
