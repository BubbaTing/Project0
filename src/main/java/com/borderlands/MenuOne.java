package com.borderlands;

import com.borderlands.scannerOnly.UserInput;

public class MenuOne {
	
	public static void main(String[] args) {
		boolean state = true;
		int account;

		//INITALIZE DATABASE
		//Creation.InitializeTable();
		
		do {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Welcome to Marco Ammontion");
			System.out.println("Create A New Account\t\t PRESS 1");
			System.out.println("Log into Existing Account\t PRESS 2");
			System.out.println("Quit\t\t\t\t PRESS 3");
			System.out.println("Please enter your selection: ");
			System.out.println("--------------------------------------------------------------------------------------------");
			int userInput = UserInput.getIntMenu(3);
			

			switch(userInput) {
				case 1:{
					account = Creation.newVaultHunter(); // create a new user
					MenuTwo.MenuTwoInterface(account);
					break;
				}
				case 2:{
					
					account = Login.oldVaultHunter(); // verify existing user
					MenuTwo.MenuTwoInterface(account);
					break;
				}
				case 3:{
					state = false;
					break;
				}
				default:{
					System.out.println("Unknown Value!");
				}
			}
			//UserInput.nextLine();
			continue;	
		}while(state);

	}

}
