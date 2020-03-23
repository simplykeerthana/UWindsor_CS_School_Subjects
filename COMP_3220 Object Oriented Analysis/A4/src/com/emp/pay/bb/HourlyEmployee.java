package com.emp.pay.bb;
public class HourlyEmployee extends Employee{
	
	private double wage;
	private double hours;
	
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	public double getWage() {
		return wage;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public double getHours() {
		return hours;
	}

	public double hourlyEmployeeEarnings() {
		double hSalary = 0;
		if(getHours() <= 40)
			hSalary = getWage()*getHours();
		else if (getHours() >= 40) {
			hSalary = 40*getWage()+(getHours()-40)*getWage()*1.5;
		}
		return hSalary;
	}
}