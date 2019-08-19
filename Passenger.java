package carpool;

import carpool.User;

public class Passenger extends User {

	public void menu(Boolean loggedIn)
	{
		System.out.println("Please enter the number of your choice:");
		
		System.out.println("******************************************");
		System.out.println(" ");
	
		System.out.println("1: Enter a trip.");
		System.out.println("2: Edit a trip.");
		System.out.println("3: Delete a trip.");
		System.out.println("4: Search for Drivers.");
		System.out.println("5: Display your info.");
		System.out.println("6: Log out.");
	
		System.out.println(" ");
		System.out.println("******************************************");
	}
	
}
