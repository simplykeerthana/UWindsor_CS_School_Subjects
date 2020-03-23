package com.emp.pay.bb;

public class BaseSalariedCommissionEmployee extends CommissionEmployee {

	private double baseSalary;

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public double getBaseSalary() {
		return baseSalary;
	}
	
	public double baseSalariedCommissionEmployeeEarnings() {
		return (getCommissionRate()*getGrossSales())+getBaseSalary();
	}
}