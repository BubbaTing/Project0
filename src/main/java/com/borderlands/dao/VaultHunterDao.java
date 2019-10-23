package com.borderlands.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.models.VaultHunters;

public class VaultHunterDao {
	Connection conn;
	
	public void setConnection(Connection conn) {
		try {
			if(this.conn != null && !this.conn.isClosed()) {
				System.out.println("Closing connection");
				this.conn.close();
			}
			this.conn = conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public VaultHunterDao() {
		this.conn = ConnectToSQL.getConnection();
	}
	
	
	//SEARCH THE BANK BY USERNAME
	public int searchAccountName(String username){
		try {
			String command = "select account_number from vault_hunter where user_name = ?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1, username);
			ResultSet r = statement.executeQuery();
			r.next();
			return r.getInt("account_number");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}	
	
	//SEARCH THE BANK BY ACCOUNT NUMBER
	public boolean searchAccountNumber(int account){
		try {
			String command = "select account_number from vault_hunter where account_number = ?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setInt(1, account);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				int old = r.getInt("account_number");
				if(old == account) return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<VaultHunters> getVaultHunterAccount(int account_number) {
		try{
			String query = "SELECT * FROM vault_hunter WHERE account_number = ?";
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setInt(1, account_number);
			ResultSet resultSet = statement.executeQuery();
			List<VaultHunters> users = new ArrayList<>();

			while (resultSet.next()) {
				VaultHunters user = extractUser(resultSet);
				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private VaultHunters extractUser(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String username = resultSet.getString("user_name");
		String password = resultSet.getString("pass_word");
		String name = resultSet.getString("real_name");
		int account_number = resultSet.getInt("account_number");

		VaultHunters user = new VaultHunters(id, username, password, name, account_number);
		return user;
	}
	
}
