/*
	Name:  Keerthana Madhavan
	Program: Lab 1  Hello World
	Date: 15/5/19
*/



public class HelloWorld
{

	public static void main(String [] args)
	{

		System.out.println("Hello World!"); //prints the Hello World on the first line of the output
		System.out.println("The arguments are: "); //prints this statement on the next line
	 	
		for( String  element : args) //a loop that takes in the input of an array of characters from the command line
		{
			System.out.println("\t" + element); //prints each string of characters in new line, along with a tab.
		}

	}

}
