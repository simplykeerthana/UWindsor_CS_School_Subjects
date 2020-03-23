/*
*this class implements the salutationGenerator from Salutation class
 */

public class FrenchSalutation extends Salutation {
	public String salutationGenerator(String name, String gender){
		if (gender.equals("male")){
			   return ("Cher Mr " + name + ",\n");
			} else{
				return ("Chere Mme/Melle " + name + ",\n");	
			}
	}
}
