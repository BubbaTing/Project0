package com.borderlands.models;

public class VaultHuntersBank {
	private int account_number;
	private int backpack_number;
	private int numberOfRifles;
	private int numberoOfShotguns;
	private int numberOfPistols;
	private int numberOfSubmachineGuns;
	private int numberOfRockets;
	private int numberOfSnipers;
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public int getBackpack_number() {
		return backpack_number;
	}
	public void setBackpack_number(int backpack_number) {
		this.backpack_number = backpack_number;
	}
	public int getNumberOfAsualtRifles() {
		return numberOfRifles;
	}
	public void setNumberOfAsualtRifles(int numberOfAsualtRifles) {
		this.numberOfRifles = numberOfAsualtRifles;
	}
	public int getNumberoOfShotguns() {
		return numberoOfShotguns;
	}
	public void setNumberoOfShotguns(int numberoOfShotguns) {
		this.numberoOfShotguns = numberoOfShotguns;
	}
	public int getNumberOfPistols() {
		return numberOfPistols;
	}
	public void setNumberOfPistols(int numberOfPistols) {
		this.numberOfPistols = numberOfPistols;
	}
	public int getNumberOfSubmachineGuns() {
		return numberOfSubmachineGuns;
	}
	public void setNumberOfSubmachineGuns(int numberOfSubmachineGuns) {
		this.numberOfSubmachineGuns = numberOfSubmachineGuns;
	}
	public int getNumberOfRockets() {
		return numberOfRockets;
	}
	public void setNumberOfRockets(int numberOfRockets) {
		this.numberOfRockets = numberOfRockets;
	}
	public int getNumberOfSnipers() {
		return numberOfSnipers;
	}
	public void setNumberOfSnipers(int numberOfSnipers) {
		this.numberOfSnipers = numberOfSnipers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_number;
		result = prime * result + backpack_number;
		result = prime * result + numberOfRifles;
		result = prime * result + numberOfPistols;
		result = prime * result + numberOfRockets;
		result = prime * result + numberOfSnipers;
		result = prime * result + numberOfSubmachineGuns;
		result = prime * result + numberoOfShotguns;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VaultHuntersBank other = (VaultHuntersBank) obj;
		if (account_number != other.account_number)
			return false;
		if (backpack_number != other.backpack_number)
			return false;
		if (numberOfRifles != other.numberOfRifles)
			return false;
		if (numberOfPistols != other.numberOfPistols)
			return false;
		if (numberOfRockets != other.numberOfRockets)
			return false;
		if (numberOfSnipers != other.numberOfSnipers)
			return false;
		if (numberOfSubmachineGuns != other.numberOfSubmachineGuns)
			return false;
		if (numberoOfShotguns != other.numberoOfShotguns)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VaultHuntersBank [account_number=" + account_number + ", backpack_number=" + backpack_number
				+ ", numberOfAsualtRifles=" + numberOfRifles + ", numberoOfShotguns=" + numberoOfShotguns
				+ ", numberOfPistols=" + numberOfPistols + ", numberOfSubmachineGuns=" + numberOfSubmachineGuns
				+ ", numberOfRockets=" + numberOfRockets + ", numberOfSnipers=" + numberOfSnipers + "]";
	}
	public VaultHuntersBank(int account_number, int backpack_number, int numberOfAsualtRifles, int numberoOfShotguns,
			int numberOfPistols, int numberOfSubmachineGuns, int numberOfRockets, int numberOfSnipers) {
		super();
		this.account_number = account_number;
		this.backpack_number = backpack_number;
		this.numberOfRifles = numberOfAsualtRifles;
		this.numberoOfShotguns = numberoOfShotguns;
		this.numberOfPistols = numberOfPistols;
		this.numberOfSubmachineGuns = numberOfSubmachineGuns;
		this.numberOfRockets = numberOfRockets;
		this.numberOfSnipers = numberOfSnipers;
	}
	public VaultHuntersBank() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
