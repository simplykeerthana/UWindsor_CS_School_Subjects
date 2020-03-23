package com.emp.pay.bb;
public class PieceWorkerEmployee extends Employee {

	private double wagePerPiece;
	private int numberOfPiecesOfMerchandise;

	public void setWagePerPieces(double wagePerPiece) {
		this.wagePerPiece = wagePerPiece;
	}
	
	public double getWagePerPieces() {
		return wagePerPiece;
	}

	public void setNumberOfPiecesOfMerchandise(int numberOfPiecesOfMerchandise) {
		this.numberOfPiecesOfMerchandise = numberOfPiecesOfMerchandise;
	}

	public int getNumberOfPiecesOfMerchandise() {
		return numberOfPiecesOfMerchandise;
	}
	
	public double pieceWorkerEmployeeEarnings() {
		return (getWagePerPieces()+getWagePerPieces()*.01)*getNumberOfPiecesOfMerchandise();
	}
}