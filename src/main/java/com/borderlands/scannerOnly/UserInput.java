package com.borderlands.scannerOnly;

import java.util.Scanner;

public class UserInput {
	
	static Scanner keyboard = new Scanner(System.in);
	
	public static int getIntMenu(int max) {
		int input = -1;
		
		while(input < 0 || input > max) {
			
			if (!keyboard.hasNextInt()) {
				keyboard.nextLine();
				continue;
			}
			input = keyboard.nextInt();			
		}
		
		return input;
	}
	
	public static String getStringInput() {
		String text = "";
		while(text.isEmpty()) {
			text = keyboard.nextLine();
		}
		return text;
	}
	
	
	

}
