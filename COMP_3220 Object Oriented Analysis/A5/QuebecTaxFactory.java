

/*
 * For explanations of the methods, see IncomeTaxFactory.
 * Note that the object returned by the following methods 
 * are in the respective subclasses for Quebec.
 */

public class QuebecTaxFactory extends IncomeTaxFactory{
	
	public  QuebecDateAddress createDateAddress(){
		return new QuebecDateAddress();
	}
	
	public  QuebecTaxComputer computeTax(){
		return new QuebecTaxComputer();
	}
	public  QuebecLetter displayBodyLetter(){
		return new QuebecLetter();
	}
	public FrenchSalutation displaySalutation(){
		return new FrenchSalutation();
	}
	public  FrenchDate dateFactory(){
		return  new FrenchDate();
		
	}

}
