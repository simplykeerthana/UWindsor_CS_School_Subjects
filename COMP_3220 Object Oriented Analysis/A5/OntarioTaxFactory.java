
/*
 * For explanations of the methods, see IncomeTaxFactory.
 * Note that the object returned by the following methods 
 * are OntarioDateAddress, OntarioTaxComputer, OntarioLetter,
 * EnglishSalutation and EnglishDate. 
 * These are the subclasses of DateAddress, TaxComputer 
 * LetterBody, Salutation and  MyDate and is appropriate for Ontario.
 */

public class OntarioTaxFactory extends IncomeTaxFactory{
	
	public  OntarioDateAddress createDateAddress(){
		return new OntarioDateAddress();
	}
	
	public  OntarioTaxComputer computeTax(){
		return new OntarioTaxComputer();
	}
	public  OntarioLetter displayBodyLetter(){
		return new OntarioLetter();
	}
	public EnglishSalutation displaySalutation(){
		return new EnglishSalutation();
	}
	public  EnglishDate dateFactory(){
		return  new EnglishDate();
		
	}

}
