/*
*this class returns the toString method that gives the date in french
 */

public class FrenchDate extends MyDate {

	public FrenchDate(){
		super();
	}
	public String toString() {
		String monthNames[] = {"janvier", "fevrier", "mars",
				"avril", "mai", "juin", "juillet", "aout", 
				"septembre", "octobre", "novembre", "decembre"};
			return ( day + " " + monthNames[month-1] +  ", " + year );
		
	}

}
