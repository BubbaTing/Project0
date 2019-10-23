package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.dao.VaultHunterDao;
import com.borderlands.scannerOnly.UserInput;

public class Login {

	public static int oldVaultHunter() {
		
		boolean status;
		String userNameVH;
		String userPassVH;
		int userAccNum;
		
		VaultHunterDao number = new VaultHunterDao();
		
		do {
			System.out.println("Enter your Username: ");
			userNameVH = UserInput.getStringInput();
			System.out.println("Enter your Password: ");
			userPassVH = UserInput.getStringInput();
			status = verify(userNameVH, userPassVH);
			if(status == false)
				System.out.println("Incorrect Username/Password");
		}while(!status);
		
		userAccNum = number.searchAccountName(userNameVH);
						
		return userAccNum;
	}
	
	//VERIFYING USERNAME AND PASSWORD
	public static boolean verify(String username, String password) {
		try(Connection con = ConnectToSQL.getConnection()) {
			String command = "Select user_name, pass_word from vault_hunter where user_name = ?";
			PreparedStatement s = con.prepareStatement(command);
			s.setString(1, username);
			
			ResultSet resultSet = s.executeQuery();
			while(resultSet.next()) {
				if(password.compareTo(resultSet.getString("pass_word")) == 0); return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false; 
	}
	
}
