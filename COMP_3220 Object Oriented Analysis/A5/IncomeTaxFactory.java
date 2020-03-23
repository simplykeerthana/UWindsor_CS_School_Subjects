
/* 
 * IncomeTaxFactory is the abstract tax factory. The code indicates that
 * concrete classes (One for Ontario one for Quebec) of IncomeTaxFactory  
 * will include a method createDateAddress that will return an object of 
 * DateAddress  class - itself an abstract class.
 * A concrete subclass of IncomeTaxFactory will return an object of
 * 		i) either a subclass of DateAddress, which includes a method that 
 *         returns a string corresponding to English 
 * 			date and address, to be used for letters to Ontario.
 * 		ii) or  another subclass of DateAddress with similar responsibility
 * 			for letters to Quebec.
 * All the other abstract methods have similar objectives as follows:
 * 		i) computeTax will return an object that is responsible for 
 * 			calculating the tax (following either Quebec rules or Ontario rules).
 * 		ii) displayBodyLetter  will return an object that is responsible for
 *          the body of the letter (either in English or in French)
 *      iii) displaySalutation will return an object that is responsible for
 *          the first line of the letter (e.g., Dear Mr Smith)
 *      iv) dateFactory will return an object that is responsible for
 *          displaying the date either using English or using French.
 * 			
 */
public abstract class IncomeTaxFactory {
	public abstract DateAddress createDateAddress();
	public abstract TaxComputer computeTax();
	public abstract LetterBody displayBodyLetter();
	public abstract Salutation displaySalutation();
	public abstract MyDate dateFactory();
}
