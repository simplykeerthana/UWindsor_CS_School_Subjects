/*
*concrete class that gives english Date
 */

public class EnglishDate extends MyDate {

	//method to print date in English
	public EnglishDate(){
		super();
	}
	public String toString() {
			String monthNames[] = {"January", "February", "March", "April", "May",
	                "June", "July", "August", "September", "October",
	                "November", "December"};	

				return ( monthNames[month-1] + " " + day +  ", " + year );
		
	}

}
