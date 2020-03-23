package aPackage;

public class Worker extends Adult{
	double salary;
	double bonus;
	public Worker(String aPersonName, String dateString){
		super(aPersonName, new MyDate(dateString));
		this.salary = salary;     
	}

	// income and is_rich is defined here!!

	public  double income(){
		return (salary + bonus);
	}
	public void setBonus(double currentBonus){
		bonus = currentBonus;
	}

	//A worker is rich if his income is high

	public boolean isRich(){
		if (income() > 100000.00) return true;
		else return false;
	}

}
