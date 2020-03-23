/*
*this class implements the generateLetterBody from LetterBody abstract class
 */

public class OntarioLetter extends LetterBody {
	public String generateLetterBody(double taxPayable){
		return "Your tax is $" + taxPayable + "\n\n" +
		       "Yours sincerely"; 
	}
}
