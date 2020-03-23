
//import all the file stuff
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Keerthana Madhavan, with parts of the instruction for the exception
 * provided by the prof
 */


public class A9
{
    /**
     * this function reads in the double values from a file, 
     * stores it in a ArrayList and then multiplies each element by 3.14 and
     * creates a new file with the updated elements.
     * @param args
     */
     public static void main(String[] args)
      {
            //store the nums in the arraylist

                ArrayList<Double> list = new ArrayList<>(); //double arraylist stuff

                Scanner readFile = null; //read pointer

                PrintWriter printWriter = null; // to write to the file using PrintWriter Object

                File inputFile = null,outputFile = null;

                try
                {
                    inputFile = new File(args[0]); //inputs the file
                    outputFile = new File(args[1]); //to write the contents of the file
                    
                     readFile = new Scanner(inputFile);

                    printWriter = new PrintWriter(outputFile);

                    while(readFile.hasNext())
                    {
                        list.add(readFile.nextDouble()); // adds the elements to the list
                    }

                    Collections.sort(list); // sorts the list in ascending

                    for(Double i:list)
                    {
                        i = i*3.14; // multiplies each element in the list by 3.14

                        printWriter.print(i + " "); // writes the multiplied version to the new created file
                    }
                }
                catch(IOException e) //exceptions
                {
                    System.err.print(e); // prints out the error in opening the file

                    if(outputFile.delete())
                    {
                        System.out.println("The file was deleted");

                    }
                    else
                    {
                        System.out.println("Cannot delete the file");
                    }
                }
                finally
                {
                    readFile.close();
                    printWriter.close();
                }
    }
}