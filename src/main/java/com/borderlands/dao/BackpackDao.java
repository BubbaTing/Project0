package com.borderlands.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.borderlands.connectionOnly.ConnectToSQL;
import com.borderlands.models.VaultHuntersBank;

public class BackpackDao {
	Connection bp;
	
	public void setConnection(Connection bp) {
		try {
			if(this.bp != null && !this.bp.isClosed()) {
				System.out.println("Closing connection");
				this.bp.close();
			}
			this.bp = bp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BackpackDao() {
		this.bp = ConnectToSQL.getConnection();
	}
	
	//SEARCH THE BANK ACCORDING TO BACKPACK NUMBER
	public boolean searchBackpackNumber(int backnumber){
		try {
			String command = "select backpack_number from vault_hunter_bank";
			PreparedStatement statement = bp.prepareStatement(command);
			ResultSet r = statement.executeQuery();
			
			while(r.next()) {
				int old = r.getInt("backpack_number");
				if(old == backnumber) return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//DISPLAY USER ACCOUNTS
	public List<VaultHuntersBank> displayBackpackNumber(int backnumber) {
		try {
			String command = "select * from vault_hunter_bank where account_number = ?";
			PreparedStatement statement = bp.prepareStatement(command);
			statement.setInt(1, backnumber);
			ResultSet r = statement.executeQuery();
			List<VaultHuntersBank> users = new ArrayList<>();
			
			while(r.next()) {
				VaultHuntersBank user = extractUser(r);
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private VaultHuntersBank extractUser(ResultSet resultSet) throws SQLException {
		int account_number = resultSet.getInt("account_number");
		int backpack_number = resultSet.getInt("backpack_number");
		int numberOfRifles = resultSet.getInt("rifle");
		int numberOfShotguns = resultSet.getInt("shotgun");
		int numberOfPistols = resultSet.getInt("pistol");
		int numberOfSubmachineGuns = resultSet.getInt("smg");
		int numberOfRockets = resultSet.getInt("rocket");
		int numberOfSnipers = resultSet.getInt("sniper");

		VaultHuntersBank user = new VaultHuntersBank(account_number, backpack_number,
				numberOfRifles, numberOfShotguns, numberOfPistols, numberOfSubmachineGuns,
				numberOfRockets, numberOfSnipers);
		return user;
	}
}
