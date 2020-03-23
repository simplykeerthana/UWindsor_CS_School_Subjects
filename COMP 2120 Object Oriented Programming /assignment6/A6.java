//package COMP_2120;

import java.util.*;

/**
 * This program takes in a list of words from the user and sorts it using String Comparator
 * @author Keerthana Madhavan, but this code is instructed by the professor
 */

public class A6
{
    /**
     * this function does not return anything but has a anonymous method for string compare
     * @param args
     */
    public static void main(String [] args)
    {
        TreeSet<String> sorted_set = new TreeSet<String>(new Comparator() {
           /**
            * @param s1 s2
            * @return a integer value based on what the compare function return 
            */
            public int compare(Object s1, Object s2)
            {
                return  - ((String) s1).compareToIgnoreCase((String)s2);
            }
            
            /**
             * @param O
             * @return a boolan value based on the equals function 
             */
            public boolean equals(Object o)
            {
                return this == o;
            }

        });
        
        // this scanner function reads in the words from the user. 
        Scanner keyboard = new Scanner(System.in);
        
        // this while loop reads in the words from the user and adds it to the sorted.set

        while(keyboard.hasNext())
        {
            sorted_set.add(keyboard.next());
        }
        
        // range-based for-loop output
            for(String value : sorted_set)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        // lamba function output
            sorted_set.forEach(value->System.out.print(value + " "));
            System.out.println();
        // iterator fucntion output
            Iterator it = sorted_set.iterator();
            while(it.hasNext())
            {
                System.out.print(it.next() + " ");
            }
            System.out.println();
    }
}
