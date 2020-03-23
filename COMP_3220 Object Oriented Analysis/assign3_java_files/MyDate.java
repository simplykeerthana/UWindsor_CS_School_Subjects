package aPackage;
import java.util.*;
public class MyDate implements Sortable{

    /* Three integer instance variables to store the day, the month and the year. */
    private int day;
    private int month;
    private int year;


    /* This is the constructor with a String parameter that stores the date and the time
     * the date is specified in the format: "DD/MM/YYYY"  
    */

    public MyDate(MyDate d){
    	this.day = d.day;
    	this.month = d.month;
    	this.year = d.year;
    }
    
    public boolean lessThan(Sortable x){
    	MyDate someDate = (MyDate) x;
    	if (this.year < someDate.year) {
    		return true;
    	} else if (this.year > someDate.year) {
    		return false;
    	} else if (this.month < someDate.month) {
    		return true;
    	} else if (this.month > someDate.month) {
    		return false;
    	}
    	else if (this.day < someDate.day) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public MyDate(String dayString) {
        StringTokenizer tokens;                           // declaration of the StringTokenizer instance variable
        tokens = new StringTokenizer(dayString, "/");    // constructs a string tokenizer for the dayString using the delimiter "/"
        day = Integer.parseInt(tokens.nextToken());     // get the first token and convert to an int representing the day
        month = Integer.parseInt(tokens.nextToken()); // get the next token corresponding to the month
        year = Integer.parseInt(tokens.nextToken());    // get next token, corresponding to the year (in 2 digits)
    }

    /* This is the toString method with no arguments which returns the date
     * in the form of a string.
     * The format of the string is as follows:
     * name of month + space + date + comma + space + apostrophe + last 2 digits of year
	 */

    public String toString() {
        String monthName;  // declare a string to store the complete name of the month
        int yearInTwoDigits;  // an integer to store the last 2 digits of the year
        switch (month) {      // convert the month in digit form to a word form
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default: //The default case processes wrong input for month
                monthName = "Wrong Info";
                break;
        }
        yearInTwoDigits = year % 100;  // remove the first two digits of year

        /* an integer can not store the heading 0 if it is less than 10,
         * (example -  09 can not be store as an integer.
		 */

        if (yearInTwoDigits < 10) {
            return (monthName + " " + day + ", '0" + yearInTwoDigits); // if the year is less then 10, convert to a
			                                                           // two-digit form when forming a string
        } else {
            return (monthName + " " + day + ", '" + yearInTwoDigits);
        }
    }



    public boolean sameDay(MyDate someDateAndTime) {
        /* campares someDate in the order of year, month and day
         * note that the  comparison  continues only as long as the corresponding variables
         *  have the same value
		 */

        if ((someDateAndTime.year == this.year) &&
            (someDateAndTime.month == this.month) &&
            (someDateAndTime.day == this.day)) {
                return true;
            } else {
                  return false;
            }
    }
}
