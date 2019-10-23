package com.borderlands;

import java.util.List;

import com.borderlands.dao.BackpackDao;
import com.borderlands.dao.VaultHunterDao;
import com.borderlands.models.VaultHunters;
import com.borderlands.models.VaultHuntersBank;

public class Balance {
	
	private VaultHunterDao vaultHunter = new VaultHunterDao();
	private BackpackDao weapson = new BackpackDao();

	public void getBalanceByAccount(int accountNumber) {
		
		
		//List<VaultHunters> users = vaultHunter.getVaultHunterAccount(accountNumber);
		//printUserList(users);
		
		List<VaultHuntersBank> users = weapson.displayBackpackNumber(accountNumber);
		printUserBankList(users);
	
	}

	private void printUserList(List<VaultHunters> users) {
		System.out.println("-------------------------- Users -------------------------");
		System.out.println("| id |   USERNAME   |  PASSWORD |  REAL NAME  | ACCOUNT NUMBER |");
		for(VaultHunters index : users) {
			System.out.printf("| %2d | %13s | %11s | %13s | %16d |%n", 
					index.getId(), index.getUsername(), index.getPassword(),
					index.getName(), index.getAccount_number());
		}
		System.out.println("----------------------------------------------------------");
	}
	
	private void printUserBankList(List<VaultHuntersBank> users) {
		System.out.println("------------------------------------------------------------------------------ Users-----------------------------------------------------------------------------");
		System.out.println("|\taccount\t|\t backpack\t|\trifle\t|\tshotgun\t|\tsmg\t|\tpistol\t|\tlauncher\t|\tsniper\t|");
		for(VaultHuntersBank index : users) {
			System.out.printf("|\t%d\t|\t%8d\t|\t%d\t|\t%d\t|\t%d\t|\t%d\t|\t%d\t\t|\t%d\t|%n", 
					index.getAccount_number(), index.getBackpack_number(), index.getNumberOfAsualtRifles(), index.getNumberoOfShotguns(), 
					index.getNumberOfSubmachineGuns(), index.getNumberOfPistols(), index.getNumberOfRockets(), index.getNumberOfSnipers()); 
		}
		System.out.println("-------------------------- ----------------------------------------------------------------------------------------------------------------------------------------");
	}

}
