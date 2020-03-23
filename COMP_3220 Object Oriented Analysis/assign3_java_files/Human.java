package aPackage;


public abstract class Human implements Sortable{
	private String name;
	protected MyDate birthDate;
	public Human(String aPersonName, MyDate d){
		name = aPersonName;
		birthDate = new MyDate(d);
	}
	// The toString() method simply returns a string giving the
	// name, age and income of the person.
	public String toString(){
		return ("Name : " + name 
				+ ", birth Date = " 
				+ birthDate.toString() + ", income = " 
				+ income() + "\n");
	}
	public abstract double income();
	//The class is abstract due to this abstract method 

	public boolean lessThan(Sortable x){
		Human  anotherHuman;
		anotherHuman =  (Human) x;
		return birthDate.lessThan(anotherHuman.birthDate);
	}


}


