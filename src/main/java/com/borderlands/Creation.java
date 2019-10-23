package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.dao.BackpackDao;
import com.borderlands.dao.VaultHunterDao;
import com.borderlands.models.VaultHunters;
import com.borderlands.models.VaultHuntersBank;
import com.borderlands.scannerOnly.UserInput;

public class Creation {
/**
 * Creation Class is to create new methods
 * 
 */
	
	public static void InitializeTable() {
		try(Connection scooter = ConnectToSQL.getConnection()) {
			Statement statement = scooter.createStatement();
			String create = "create table if not exists vaults_hunters(" + 
					"	id serial primary key," + 
					"	user_name varchar(20)," + 
					"	pass_word varchar(20)," + 
					"	name char(20)," + 
					"	account_number int unique" + 
					"	);";
			boolean test = statement.execute(create);
			if (test) {
				System.out.println("DATABASE CREATED");
			} else {
				System.out.println("SOMETHING MIGHT WENT WRONG");
			}
			
				
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	public static int newVaultHunter() {
		boolean status = true;
		int userAccNum;
		int userPakNum;
		System.out.println("Create a new Username: ");
		String userNameVH = UserInput.getStringInput();
		System.out.println("Create a new Password: ");
		String userPassVH = UserInput.getStringInput();
		System.out.println("Enter your first name");
		String userFullName = UserInput.getStringInput();
		VaultHunterDao number = new VaultHunterDao();
		BackpackDao digit = new BackpackDao();
		
		do{
			//create a unique random account number
			userAccNum = generateMasterAccount();
			//check if master account exist
			status = number.searchAccountNumber(userAccNum);
			
		}while(status);
		
		status = true;
		
		do{
			//create a unique random backpack number
			userPakNum = generateMasterAccount();
			//check if master account exist
			status = digit.searchBackpackNumber(userPakNum);
			
		}while(status);
				
		VaultHunters noob = new VaultHunters(0, userNameVH, userPassVH, userFullName, userAccNum);
		VaultHuntersBank noob2 = new VaultHuntersBank(userAccNum, userPakNum, 0,0,0,0,0,0);
		insertNewUser(noob, noob2);
		return userAccNum;
	}
	
	//INSERTING NEW ACCOUNT TO THE DATABASE
	public static void insertNewUser(VaultHunters noob, VaultHuntersBank noob2) {
		try(Connection scooter = ConnectToSQL.getConnection()){
			String command = "insert into vault_hunter_bank(account_number, backpack_number, rifle, shotgun, smg, pistol, rocket, sniper)\r\n" + 
					"values (?, ?, 0, 0, 0, 0, 0, 0)";
			String command1 = "insert into joint_table_one(user_name, account_number, backpack_number) values (?, ?, ?);";
			String command2 = "insert into vault_hunter(user_name, pass_word, real_name, account_number) values (?, ?, ?, ?);";
			PreparedStatement statement = scooter.prepareStatement(command);
			PreparedStatement statement1 = scooter.prepareStatement(command1);
			PreparedStatement statement2 = scooter.prepareStatement(command2);
			
			statement.setInt(1, noob.getAccount_number());
			statement.setInt(2, noob2.getBackpack_number());
			
			statement1.setString(1, noob.getUsername());
			statement1.setInt(2, noob.getAccount_number());
			statement1.setInt(3, noob2.getBackpack_number());
			
			statement2.setString(1, noob.getUsername());
			statement2.setString(2, noob.getPassword());
			statement2.setString(3, noob.getName());
			statement2.setInt(4, noob.getAccount_number());
			
			int a = statement.executeUpdate();
			int b = statement1.executeUpdate(); //handle same username
			int c = statement2.executeUpdate();

			if((a > 0)&&(b > 0)&&(c > 0)) {
				System.out.println("New User has been created."+ noob2.getAccount_number() + " " + noob2.getBackpack_number());
			}
			
		} catch  (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int generateMasterAccount() {
		Random rand = new Random();
		int number = rand.nextInt(9999);
		return number;
	}
}
