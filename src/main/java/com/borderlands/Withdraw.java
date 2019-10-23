package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.scannerOnly.UserInput;

public class Withdraw {
	static int numberOfAsualtRifles;
	static int numberoOfShotguns;
	static int numberOfPistols;
	static int numberOfSubmachineGuns;
	static int numberOfRockets;
	static int numberOfSnipers;
	static int rifle;
	static int shotgun;
	static int pistol;
	static int smg;
	static int rocket;
	static int sniper;
	Balance test = new Balance();
	
	public void takeWeapsons(int number) {
		int answer2;
		boolean state = true;
		
		System.out.println("Is this a joint account? Enter yes or no");
		String answer1 = UserInput.getStringInput();
		//display old balance
		test.getBalanceByAccount(number);
		
		do {
						
			if(answer1.compareToIgnoreCase("yes") == 0) {
				System.out.println("Which backpack account would you like to deposite to?");
				answer2 = UserInput.getIntMenu(9999);
				withdrawWeapsonJoint(answer2);
				state = false;
			} else if (answer1.compareToIgnoreCase("no") == 0) {
				withdrawWeapson(number);
				state = false;
			} else {
				System.out.println("Incorrect Input");

			}
		}while(state);
		
		//display new balance
		test.getBalanceByAccount(number);
	}
	
	public void withdrawWeapsonJoint(int number) {
		System.out.println("Withdraw how many rifles?");
		numberOfAsualtRifles = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many shotgun?");
		numberoOfShotguns = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many smg?");
		numberOfSubmachineGuns  = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many pistols?");
		numberOfPistols = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many launcher?");
		numberOfRockets = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many sniper?");
		numberOfSnipers = UserInput.getIntMenu(9999);
		
		try(Connection con = ConnectToSQL.getConnection()){
			String retrieve = "select * from vault_hunter_bank where backpack_number = ?";
			PreparedStatement statement1 = con.prepareStatement(retrieve);
			statement1.setInt(1, number);
			ResultSet r = statement1.executeQuery();
			
			while(r.next()) {
				rifle = r.getInt("rifle");
				shotgun = r.getInt("shotgun");
				pistol = r.getInt("pistol");
				smg = r.getInt("smg");
				rocket = r.getInt("rocket");
				sniper = r.getInt("sniper");
			}
			
			numberOfAsualtRifles = rifle - numberOfAsualtRifles;
			numberoOfShotguns = shotgun - numberoOfShotguns; 
			numberOfSubmachineGuns = smg - numberOfSubmachineGuns;
			numberOfPistols = pistol - numberOfPistols;
			numberOfRockets = rocket - numberOfRockets;
			numberOfSnipers = sniper - numberOfSnipers;
			
			
			String command = "update vault_hunter_bank set rifle = ?, shotgun = ?, smg  = ?,"
					+ "pistol = ?, rocket = ?,sniper = ? where backpack_number = ?";
			PreparedStatement statement = con.prepareStatement(command);
			statement.setInt(1, numberOfAsualtRifles);
			statement.setInt(2, numberoOfShotguns);
			statement.setInt(3, numberOfSubmachineGuns );
			statement.setInt(4, numberOfPistols);
			statement.setInt(5, numberOfRockets);
			statement.setInt(6, numberOfSnipers);
			statement.setInt(7, number);
			int a = statement.executeUpdate();
			
			if(a > 0) System.out.println("Update Complete");
			
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	public void withdrawWeapson(int number){
		System.out.println("Withdraw how many rifles?");
		numberOfAsualtRifles = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many shotgun?");
		numberoOfShotguns = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many smg?");
		numberOfSubmachineGuns  = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many pistols?");
		numberOfPistols = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many launcher?");
		numberOfRockets = UserInput.getIntMenu(9999);
		System.out.println("Withdraw how many sniper?");
		numberOfSnipers = UserInput.getIntMenu(9999);
		
		try(Connection con = ConnectToSQL.getConnection()){
			String retrieve = "select * from vault_hunter_bank where account_number = ?";
			PreparedStatement statement1 = con.prepareStatement(retrieve);
			statement1.setInt(1, number);
			ResultSet r = statement1.executeQuery();
			
			while(r.next()) {
				rifle = r.getInt("rifle");
				shotgun = r.getInt("shotgun");
				pistol = r.getInt("pistol");
				smg = r.getInt("smg");
				rocket = r.getInt("rocket");
				sniper = r.getInt("sniper");
			}
			
			numberOfAsualtRifles = rifle - numberOfAsualtRifles;
			numberoOfShotguns = shotgun - numberoOfShotguns; 
			numberOfSubmachineGuns = smg - numberOfSubmachineGuns;
			numberOfPistols = pistol - numberOfPistols;
			numberOfRockets = rocket - numberOfRockets;
			numberOfSnipers = sniper - numberOfSnipers; 
			
			String command = "update vault_hunter_bank set rifle = ?, shotgun = ?, smg  = ?,"
					+ "pistol = ?, rocket = ?,sniper = ? where account_number = ?";
			PreparedStatement statement = con.prepareStatement(command);
			statement.setInt(1, numberOfAsualtRifles);
			statement.setInt(2, numberoOfShotguns);
			statement.setInt(3, numberOfSubmachineGuns );
			statement.setInt(4, numberOfPistols);
			statement.setInt(5, numberOfRockets);
			statement.setInt(6, numberOfSnipers);
			statement.setInt(7, number);
			int a = statement.executeUpdate();
			
			if(a > 0) System.out.println("Update Complete");
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	

}
