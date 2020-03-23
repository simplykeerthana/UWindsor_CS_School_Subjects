/*
*this abstract class initializes the date and time for other classes to implement
 */

import java.util.StringTokenizer;

import java.util.Calendar;
import java.text.SimpleDateFormat;
public abstract class MyDate {
	public abstract String toString();
	protected int day;
	protected int month;
	protected int year;
	
	public MyDate(){
		Calendar cal;
		SimpleDateFormat sdf;
		String dateString ="";
		cal = Calendar.getInstance();
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateString = sdf.format(cal.getTime());
		StringTokenizer tokenizeDate = new StringTokenizer(dateString, "/");
		this.day = Integer.parseInt(tokenizeDate.nextToken());
		this.month = Integer.parseInt(tokenizeDate.nextToken());
		this.year = Integer.parseInt(tokenizeDate.nextToken());
	}
}
