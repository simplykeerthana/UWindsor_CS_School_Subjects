/*
*this class implements generateLetterBody method
* for Quebec province
 */

public class QuebecLetter extends LetterBody {
	public String generateLetterBody(double taxPayable){
		return "Votre taxe est $" + taxPayable + "\n\n" +
		"Sincerement votre"; 
	}
}
