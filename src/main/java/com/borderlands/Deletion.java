package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.scannerOnly.UserInput;

public class Deletion {

	public void activateDeleteAccount(int account) {
		boolean state = true;
		
		// prompt user
		do {
			System.out.println("Are you sure you want to detele this account?");
			String answer = UserInput.getStringInput();
			
			if (answer.compareToIgnoreCase("yes") == 0) {
				deleteSQL(account);
				state = false;
			}else if(answer.compareToIgnoreCase("no") == 0) {
				System.out.println("Better luck next time");
				state = false;
			}else {
				System.out.println("UNKOWN VALUE?");
			}
		}while(state);
			
	}

	private void deleteSQL(int account) {
		boolean state = true;
		// prompt user if this is a joint account
		System.out.println("Is this a joint account?");
		String answer = UserInput.getStringInput();
		
		do {
			if (answer.compareToIgnoreCase("yes") == 0) {
				deleteSQLjoint(account);
				state = false;
			}else if(answer.compareToIgnoreCase("no") == 0) {
				try(Connection d = ConnectToSQL.getConnection()) {
					String command = "delete from vault_hunter where account_number = ?";
					String command1 = "delete from joint_table_one where account_number = ?";
					String command2 = "delete from vault_hunter_bank where account_number = ?";
					PreparedStatement s = d.prepareStatement(command);
					PreparedStatement s1 = d.prepareStatement(command1);
					PreparedStatement s2= d.prepareStatement(command2);
					s.setInt(1,account);
					s1.setInt(1,account);
					s2.setInt(1,account);
					int a = s.executeUpdate();
					int b = s1.executeUpdate();
					int c = s2.executeUpdate();
					
					if((a > 0)&&(b > 0)&&(c > 0)) {
						System.out.println("User account is deleted");
					}
					state = false;
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("UNKOWN VALUE?");
			}
		}while(state);
	}

	private void deleteSQLjoint(int account) {
		// TODO Auto-generated method stub
		
	}

}
