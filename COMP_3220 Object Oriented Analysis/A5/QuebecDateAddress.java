/*
*This class implements the displayDateAddress method from DateAddress abstract class
 */

public class QuebecDateAddress extends DateAddress {

	public String displayDateAddress(MyDate d, String name, String address) {
		return d.toString() + 
		       "\n\n" + 
		       name + 
		       "\n" + 
               address + 
               "\n" +
               "Quebec";
	}

}
