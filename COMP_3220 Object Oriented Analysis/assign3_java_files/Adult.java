package aPackage;

public abstract class Adult extends Human{   
	public Adult(String aName, MyDate d){
		super(aName, d);

	}

	public abstract boolean isRich();
	// An extended class may contain  other abstract methods as well!!
	// Adult is abstract since it has two abstract methods.

}

