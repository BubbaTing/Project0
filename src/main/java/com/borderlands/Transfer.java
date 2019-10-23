package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.scannerOnly.UserInput;

public class Transfer {
	static String withdraw = null;
	static String update = null;
	static String ret = null;
	static String update1 = null;
	int take, give;
	Balance user = new Balance();
	Balance other = new Balance();
	
	public void swapWeapsons(int account) {
		int response;
		boolean state = true;
		
		do {
			System.out.println("Enter an account you would like to transfer to?");
			response = UserInput.getIntMenu(9999);
			
			//check if the account exist.
			boolean check = checkAnotherAccount(response);
			if(response == account) {
				System.out.println("Do you really want to transfer to you same account?");
			} else if (check == false) {
				System.out.println("That account does not exist. Please try again!");
			} else {
				state = false;
			}
			
		}while(state);
		
		//display user account
		user.getBalanceByAccount(account);
		//display transferee account
		other.getBalanceByAccount(response);
		
		processingTransfer(account, response);
		
		System.out.println("Transfer SUCCESS!");
		
		//display user account
		user.getBalanceByAccount(account);
		//display transferee account
		other.getBalanceByAccount(response);
	}

	private void processingTransfer(int account, int response) {
		try (Connection transfering = ConnectToSQL.getConnection()){
			int response1, response2;
			boolean state = true;
			//withdraw user account
			do {
				printOption();
				response1 = UserInput.getIntMenu(6);
				switch(response1) {
				case 1:{
					withdraw = "select rifle from vault_hunter_bank where account_number = ?";
					state = false;
					break;
					}
				case 2:{
					withdraw = "select shotgun from vault_hunter_bank where account_number = ?"; 
					state = false;
					break;
					}
				case 3:{
					withdraw = "select smg from vault_hunter_bank where account_number = ?"; 
					state = false;
					break;
					}
				case 4:{
					withdraw = "select pistol from vault_hunter_bank where account_number = ?";
					state = false;
					break;
					}
				case 5:{
					withdraw = "select rocket from vault_hunter_bank where account_number = ?";
					state = false;
					break;
					}
				case 6:{
					withdraw = "select sniper from vault_hunter_bank where account_number = ?"; 
					state = false;
					break;
					}
				default: System.out.println("Enter valid number");
				}
			}while(state);
			System.out.println("How many would you like to transfer?");
			response2 = UserInput.getIntMenu(9999);
			
			PreparedStatement statement = transfering.prepareStatement(withdraw);
			statement.setInt(1, account);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				take = r.getInt(1);
			}
			
			give = take - response2;
			switch(response1) {
			case 1:{
				update = "update vault_hunter_bank set rifle = ? where account_number = ?";
				break;
			}
			case 2 :{
				update = "update vault_hunter_bank set shotgun = ? where account_number = ?";
				break;
			}
			case 3:{
				update = "update vault_hunter_bank set smg = ? where account_number = ?";
				break;
			}
			case 4:{
				update = "update vault_hunter_bank set pistol = ? where account_number = ?";
				break;
			}
			case 5:{
				update = "update vault_hunter_bank set rocket = ? where account_number = ?";
				break;
			}
			case 6:{
				update = "update vault_hunter_bank set sniper = ? where account_number = ?";
				break;
			}
			default: System.out.println("DOES THIS WORK?");
			}
			PreparedStatement s2 = transfering.prepareStatement(update);
			s2.setInt(1, give);
			s2.setInt(2, account);
			int a = s2.executeUpdate();
			if(a > 0) System.out.println("WEAPSON REMOVED");
			
		//Deposit transferee account
			switch(response1) {
			case 1:{
				ret = "select rifle from vault_hunter_bank where account_number = ?";
				break;
			}
			case 2:{
				ret = "select shotgun from vault_hunter_bank where account_number = ?";
				break;
			}
			case 3:{
				ret = "select smg from vault_hunter_bank where account_number = ?";
				break;
			}
			case 4:{
				ret = "select pistol from vault_hunter_bank where account_number = ?";
				break;
			}
			case 5:{
				ret = "select rocket from vault_hunter_bank where account_number = ?";
				break;
			}				
			case 6:{
				ret = "select sniper from vault_hunter_bank where account_number = ?";
				break;
			}
			default: System.out.println("THIS SHOULD WORK!");
			}
			PreparedStatement s3 = transfering.prepareStatement(ret);
			s3.setInt(1, response);
			ResultSet b = s3.executeQuery();

			while(b.next()) {
				take = b.getInt(1);
			}
				take = take + response2;
			
			switch(response1) {
			case 1:{
				update1 = "update vault_hunter_bank set rifle = ? where account_number = ?";
				break;
			}
			case 2:{
				update1 = "update vault_hunter_bank set shotgun = ? where account_number = ?";
				break;
			}
			case 3:{
				update1 = "update vault_hunter_bank set smg = ? where account_number = ?";
				break;
			}
			case 4:{
				update1 = "update vault_hunter_bank set pistol = ? where account_number = ?";
				break;
			}
			case 5:{
				update1 = "update vault_hunter_bank set rocket = ? where account_number = ?";
				break;
			}
			case 6:{
				update1 = "update vault_hunter_bank set sniper = ? where account_number = ?";
				break;
			}
			default: System.out.println("FINALLY SWITCH CASE!");
			}
			PreparedStatement s4 = transfering.prepareStatement(update1);
			s4.setInt(1, take);
			s4.setInt(2, response);
			int c = s4.executeUpdate();
			if(c> 0) System.out.println("Transfer Success 2");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void printOption() {
		System.out.println("----Select Which Weapson----");
		System.out.println("Rifle\t press 1");
		System.out.println("Shotgun\t press 2");
		System.out.println("SMG\t press 3");
		System.out.println("Pistol\t press 4");
		System.out.println("Rocket\t press 5");
		System.out.println("Sniper\t press 6");	
		System.out.println("----------------------------");
	}

	private boolean checkAnotherAccount(int response) {
		try (Connection con = ConnectToSQL.getConnection()){
			String sql = "select count(account_number) from vault_hunter_bank where account_number = ? ";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, response);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				if(r.getInt("count")== 0) return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
