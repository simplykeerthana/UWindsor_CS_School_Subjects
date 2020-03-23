
/*
 * Method displayDateAddress  in OntarioDateAddress return the date and 
 * the address for use in Ontario. Note that the date object here is of 
 * the EnglishDate class.
 * 
 */

public class OntarioDateAddress extends DateAddress {

	public String displayDateAddress(MyDate d, String name, String address) {
		return d.toString() + 
		       "\n\n" + 
		       name + 
		       "\n" + 
               address + 
               "\n" +
               "Ontario";
	}

}
