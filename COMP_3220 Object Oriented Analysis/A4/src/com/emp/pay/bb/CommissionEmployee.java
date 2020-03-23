package com.emp.pay.bb;

public class CommissionEmployee extends Employee {

	private double commissionRate;
	private double grossSales;
	
	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public double getGrossSales() {
		return grossSales;
	}

	public void setGrossSales(double grossSales) {
		this.grossSales = grossSales;
	}
	
	public double commissionEmployeeEarnings() {
		return commissionRate*grossSales;
	}
}