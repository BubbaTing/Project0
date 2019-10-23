package com.borderlands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.scannerOnly.UserInput;

public class JointAccount {

	public void developJointaccount(int account) {
		int response;
		boolean state = true;
		
		do {
			System.out.println("Enter an account you would like to join together?");
			response = UserInput.getIntMenu(9999);
			
			//check if the account exist.
			boolean check = checkAnotherAccount(response);
			if(response == account) {
				System.out.println("You cannot join the same account!");
			} else if (check == false) {
				System.out.println("That account does not exist. Please try again!");
			} else {
				state = false;
			}
			
		}while(state);
		
		try(Connection joint = ConnectToSQL.getConnection()){
			String share = "update vault_hunter set account_number = ? where account_number = ?";
			String share1 = "update joint_table_one set account_number = ? where account_number = ?";
			String share2 = "update vault_hunter_bank set account_number = ? where account_number = ?";
			PreparedStatement statement = joint.prepareStatement(share);
			PreparedStatement statement1 = joint.prepareStatement(share1);
			PreparedStatement statement2 = joint.prepareStatement(share2);
			statement.setInt(2, response);
			statement.setInt(1, account);
			statement1.setInt(2, response);
			statement1.setInt(1, account);
			statement2.setInt(2, response);
			statement2.setInt(1, account);
			int r = statement.executeUpdate();
			int s = statement1.executeUpdate();
			int t = statement2.executeUpdate();
			if((r>0) && (s>0) &&(t>0)) {
				Balance display = new Balance();
				display.getBalanceByAccount(account);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
