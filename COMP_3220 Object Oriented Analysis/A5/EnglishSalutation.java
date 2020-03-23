/*
*concrete class for english salutation method
 */

public class EnglishSalutation extends Salutation {
	public String salutationGenerator(String name, String gender){
		if (gender.equals("male")){
			   return "Dear Mr " + name + ",\n";
			} else{
				return "Dear Ms " + name + ",\n";	
			}
	}
}
