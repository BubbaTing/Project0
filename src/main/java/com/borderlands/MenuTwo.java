package com.borderlands;

import com.borderlands.scannerOnly.UserInput;

public class MenuTwo {
	
	
	public static void MenuTwoInterface(int account) {
		boolean state = true;
		Deposite add = new Deposite();
		Withdraw sub = new Withdraw();
		Transfer trade = new Transfer();
		JointAccount share = new JointAccount();
		Deletion remove = new Deletion();
		Balance display = new Balance();
				
		do {			
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Would you like to:");
			System.out.println("Deposite Weapons\t\t\t PRESS 1");
			System.out.println("Withdraw Weapons\t\t\t PRESS 2");
			System.out.println("Transfer Weapons\t\t\t PRESS 3");	
			System.out.println("Check The Balance of Your Weapons\t PRESS 4");
			System.out.println("Make This a Joint Account?\t\t PRESS 5");
			System.out.println("Delete account\t\t\t\t PRESS 6");
			System.out.println("Quit the Program\t\t\t PRESS 0");	
			System.out.println("Please enter your selection: ");
			System.out.println("--------------------------------------------------------------------------------------------");
			int userInput2 = UserInput.getIntMenu(6);
			
			System.out.println(userInput2);
			switch(userInput2) {
				case 1: {
					add.storeWeapons(account);
					break;
				}
				case 2: {
					sub.takeWeapsons(account);
					break;
				}
				case 3: {
					trade.swapWeapsons(account);
					break;
				}
				case 4: {
					System.out.println("Here are the BALANCE of your weapons:");
					display.getBalanceByAccount(account);
					break;
				}
				case 5: {
					System.out.println("Creating a JOINT account");
					share.developJointaccount(account);
					break;
				}
				case 6: {
					System.out.println("Delete this account?");
					remove.activateDeleteAccount(account);
					state = false;
					break;
				}
				case 0: {
					System.out.println("Logging Out");
					state = false;
					break;
				}
				default :{
					System.out.println("Incorrect Input");
				}
			}
			continue; // continues asking the user to select an option
		} while (state);
	}

}
