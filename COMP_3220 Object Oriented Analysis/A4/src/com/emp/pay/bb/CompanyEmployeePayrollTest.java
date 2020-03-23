package com.emp.pay.bb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompanyEmployeePayrollTest {


	
	

	    //Test Method for GetBaseSalary class
	
	    @Test
	    public void testGetBaseSalary() {
	        BaseSalariedCommissionEmployee bsce = new BaseSalariedCommissionEmployee();
	        bsce.setBaseSalary(1000);
	        assertEquals(bsce.getBaseSalary(), 1000, 0.0);
	    }

	    //Test Method for SetBaseSalary
	    @Test
	    public void testSetBaseSalary() {
	        BaseSalariedCommissionEmployee bsce= new BaseSalariedCommissionEmployee();
	        bsce.setBaseSalary(500);
	        assertEquals(bsce.getBaseSalary(),500, 0.0);
	    }



	    //Test Method for BaseSalariedCommissionEmployeeEarnings
	    @Test
	    public void testBaseSalariedCommissionEmployeeEarnings() {
	        BaseSalariedCommissionEmployee bsce= new BaseSalariedCommissionEmployee();
	        bsce.setGrossSales(500);
	        bsce.setCommissionRate(2.5);
	        bsce.setBaseSalary(1000);
	        assertEquals(bsce.baseSalariedCommissionEmployeeEarnings(),2250 , 0.0);
	    }

	    //Test Method for GetCommissionRate
	    @Test
	    public void testGetCommissionRate() {
	        CommissionEmployee ce = new CommissionEmployee();
	        ce.setCommissionRate(2);
	        assertEquals(ce.getCommissionRate(), 2, 0.0);
	    }

	    //Test Method for SetCommissionRate
	    @Test
	    public void testSetCommissionRate() {
	        CommissionEmployee ce = new CommissionEmployee();
	        ce.setCommissionRate(5);
	        assertEquals(ce.getCommissionRate(), 5, 0.0);
	    }

	    //Test Method for GetGrossSales
	    @Test
	    public void testGetGrossSales() {
	        CommissionEmployee ce = new CommissionEmployee();
	        ce.setGrossSales(300);
	        assertEquals(ce.getGrossSales(), 300, 0.0);
	    }

	    //Test Method for SetGrossSales
	    @Test
	    public void testSetGrossSales() {
	        CommissionEmployee ce = new CommissionEmployee();
	        ce.setGrossSales(205.50);
	        assertEquals(ce.getGrossSales(), 205.50, 0.0);
	    }

	    //Test Method for CommissionEmployeeEarnings
	    @Test
	    public void testCommissionEmployeeEarnings() {
	        CommissionEmployee ce = new CommissionEmployee();
	        ce.setGrossSales(450);
	        ce.setCommissionRate(2.5);
	        assertEquals(ce.commissionEmployeeEarnings(), 1125, 0.0);
	    }

	    //Test Method for SetName
	    @Test
	    public void testSetName() {
	        Employee emp=new Employee();
	        emp.setName("Keerthana");
	        assertEquals(emp.getName(), "Keerthana");
	    }

	    //Test Method for GetName
	    @Test
	    public void testGetName() {
	        Employee emp=new Employee();
	        emp.setName("Madhavan");
	        assertEquals(emp.getName(), "Madhavan");
	    }

	    //Test Method for GetAddress
	    @Test

	    public void testGetAddress() {
	        Employee emp=new Employee();
	        emp.setAddress("1234 Jennifer Ave");
	        assertEquals(emp.getAddress(), "1234 Jennifer Ave");
	    }

	    //Test Method forSetAddress
	    @Test
	    public void testSetAddress() {
	        Employee emp=new Employee();
	        emp.setAddress("1234 Jennifer Ave");
	        assertEquals(emp.getAddress(), "1234 Jennifer Ave");
	    }

	    //Test Method for GetSIN
	    @Test
	    public void testGetSIN() {
	        Employee emp=new Employee();
	        emp.setSIN("123456789");
	        assertEquals(emp.getSIN(), "123456789");
	    }

	    //Test Method for SetSIN
	    @Test
	    public void testSetSIN() {
	        Employee emp=new Employee();
	        emp.setSIN("987654321");
	        assertEquals(emp.getSIN(), "987654321");
	    }

	    //Test Method for GetWage
	    @Test
	    public void testGetWage() {
	        HourlyEmployee he = new HourlyEmployee();
	        he.setWage(14.50);
	        assertEquals(he.getWage(), 14.50, 0.0);
	    }



	    //Test Method for SetWage
	    @Test
	    public void testSetWage() {
	        HourlyEmployee he = new HourlyEmployee();
	        he.setWage(14);
	        assertEquals(he.getWage(), 14, 0.0);
	    }

	    //Test Method for GetHours
	    @Test
	    public void testGetHours() {
	        HourlyEmployee he = new HourlyEmployee();
	        he.setHours(40);
	        assertEquals(he.getHours(), 40, 0.0);
	    }

	    //Test Method for SetHours
	    @Test
	    public void testSetHours() {
	        HourlyEmployee he = new HourlyEmployee();
	        he.setHours(50);
	        assertEquals(he.getHours(), 50, 0.0);
	    }

	    //Test Method for HourlyEmployeeEarnings
	    @Test
	    public void testHourlyEmployeeEarnings() {
	        HourlyEmployee he = new HourlyEmployee();
	        he.setWage(14);
	        he.setHours(40);
	        assertEquals(he.hourlyEmployeeEarnings(), 560, 0.0);
	    }

	    //Test Method for GetWagePerPieces
	    @Test
	    public void testGetWagePerPieces() {
	        PieceWorkerEmployee pwe = new PieceWorkerEmployee();
	        pwe.setWagePerPieces(11.5);
	        assertEquals(pwe.getWagePerPieces(), 11.5, 0.0);
	    }

	    //Test Method for SetWagePerPieces
	    @Test
	    public void testSetWagePerPieces() {
	        PieceWorkerEmployee pwe = new PieceWorkerEmployee();
	        pwe.setWagePerPieces(5.9);
	        assertEquals(pwe.getWagePerPieces(), 5.9, 0.0);
	    }

	    //Test Method for SetNumberOfPiecesOfMerchandise
	    @Test
	    public void testNumberOfPiecesOfMerchandise() {
	        PieceWorkerEmployee pwe = new PieceWorkerEmployee();
	        pwe.setNumberOfPiecesOfMerchandise(250);
	        assertEquals(pwe.getNumberOfPiecesOfMerchandise(), 250);
	    }

	    //Test Method for SetNumberOfPiecesOfMerchandise
	    @Test
	    public void testSetNumberOfPiecesOfMerchandise() {
	        PieceWorkerEmployee pwe = new PieceWorkerEmployee();
	        pwe.setNumberOfPiecesOfMerchandise(154);
	        assertEquals(pwe.getNumberOfPiecesOfMerchandise(), 154);
	    }




	    //Test Method for PieceWorkerEmployeeEarnings
	    @Test
	    public void testPieceWorkerEmployeeEarnings() {
	        PieceWorkerEmployee pwe = new PieceWorkerEmployee();
	        pwe.setWagePerPieces(10.2);
	        pwe.setNumberOfPiecesOfMerchandise(450);
	        assertEquals(pwe.pieceWorkerEmployeeEarnings(), 4635.9, 0.0);
	    }

	    //Test Method for SetWeeklySalary
	    @Test
	    public void testSetWeeklySalary() {
	        SalariedEmployee se = new SalariedEmployee();
	        se.setWeeklySalary(560);
	        assertEquals(se.getWeeklySalary(), 560, 0.0);
	    }

	    //Test Method for GetWeeklySalary
	    @Test
	    public void testGetWeeklySalary() {
	        SalariedEmployee se = new SalariedEmployee();
	        se.setWeeklySalary(760);
	        assertEquals(se.getWeeklySalary(), 760, 0.0);
	    }

	    //Test Method for SalariedEmployeeEarnings
	    @Test
	    public void testSalariedEmployeeEarnings() {
	        SalariedEmployee se = new SalariedEmployee();
	        se.setWeeklySalary(760);
	        assertEquals(se.salariedEmployeeEarnings(), 760, 0.0);
	    }
	}
	


