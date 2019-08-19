package carpool;

import java.util.*;

public class Controller {
	
	// The main menu.
	// The first menu the user interacts with.
	public void mainMenu(Menu menu, User user) throws CarPoolException
	{
		String choice = "x";
		
		Scanner scan = new Scanner(System.in);
		
		while (choice != "0")
		{
			// Output main menu
			menu.mainMenu();
			
			choice = scan.next();
			System.out.println();
		
			switch (choice)
			{
				case "1":
					logIn(menu, user);
					break;
				case "2":
					register(menu, user);
					break;
				default:
					choice = "0";
					break;
			}
			
			loggedInMenu(menu, user);
			System.out.println();
			System.out.println();
		}
		
		scan.close();
	} // end mainMenu
	
	// Logging in.
	// The user enters a username and password and
	// they are compared with the information stored
	// in the database.
	// If successful, the user is now logged in.
	public void logIn(Menu menu, User user) throws CarPoolException
	{
		Scanner scan= new Scanner(System.in);
		
		//"\nEnter your username:\t"
		menu.prompt(1);

		user.setUserName(scan.nextLine());
		
		//"\nEnter your password:\t"
		menu.prompt(2);
		user.setPassword(scan.nextLine());
		
		scan.close();
		
		if(!user.logIn())
			//"\nLogin unsuccessful. Username/Password do not match.\n"
			menu.errorMessage(1);
	}
	
	// Registering.
	// The user fills in their information.
	// If the username is not already taken, the
	// register will be successful and the user
	// will be logged in.
	public void register(Menu menu, User user) throws CarPoolException
	{
		Scanner scan = new Scanner(System.in);
		
		//"\nEnter your username:\t"
		//menu.prompt(1);
		//user.setUserName(scan.nextLine());
		
		//"\nEnter your password:\t"
		//menu.prompt(2);
		//user.setPassword(scan.nextLine());
		
		//"\nEnter your first name:\t"
		//menu.prompt(3);
		//user.setFirstName(scan.nextLine());
		
		//"\nEnter your last name:\t"
		//menu.prompt(4);
		//user.setLastName(scan.nextLine());
		
		//"\nEnter your phone number:\t"
		//menu.prompt(5);
		//user.setPhoneNumber(scan.nextLine());
		
		//String driver = "x";
		
		//while (driver != "y" && driver != "n")
	//	{
			//"\nAre you registering as a driver?(y/n):\t"
			//menu.prompt(6);
			//driver = scan.nextLine();
		//}
		
		//if(driver == "y")
			//user.setDriver(true);
		
		//else
			//user.setDriver(false);
		
		//if(!user.register())
			//"\nRegister unsuccessful. Username is already taken."
			//menu.errorMessage(2);
		
		//scan.close();
		user.register();
	}// end register
	
	// Logged-In menu. The menu that the user
	// interacts with while logged in.
	public void loggedInMenu(Menu menu, User user) throws CarPoolException
	{
		char choice = 'x';
		
		Scanner scan = new Scanner(System.in);
		
		while (user.getLoggedIn())
		{
			// Outpt logged-in menu
			menu.loggedInMenu();
			
			choice = (char) scan.nextByte();
			System.out.println();
			
			switch (choice)
			{
			case '1':
				enterTrip(menu, user);
				break;
			case '2':
				editTrip(menu, user);
				break;
			case '3':
				deleteTrip(menu, user);
				break;
			case '4':
				searchTrip(menu, user);
				break;
			case '5':
				displayUser(menu, user);
				break;
			case '6':
				logOut(menu, user);
				break;
			}
			
			System.out.println();
			System.out.println();
		}
		
		scan.close();
	}// end loggedInMenu
	
	// Adding a trip to the database.
	// The user enters a destination, the desired
	// time of arrival and the starting location of
	// this trip.
	// If the user already does not have a trip at the
	// entered time, the new trip is entered into the database.
	public void enterTrip(Menu menu, User user) throws CarPoolException
	{
		Scanner scan = new Scanner(System.in);
		
		//"\nEnter a destination(zip code):\t"
		menu.prompt(7);
		user.setDestinationLocation(scan.nextLine());
		
		//"\nEnter the desired time of arrival:\t"
		menu.prompt(8);
		//set time
		
		//"\nEnter the starting location(zipcode) for this trip:\t"
		menu.prompt(9);
		user.setDepartureLocation(scan.nextLine());
		
		user.add();
		
		scan.close();
	}
	
	// Editing a trip in the database.
	// The user's trips are displayed.
	// The user chooses a trip to edit.
	// The user then changes the destination/departure
	// location of the chosen trip.
	// The chosen trip will be altered in the database.
	public void editTrip(Menu menu, User user) throws CarPoolException
	{
		Scanner scan = new Scanner(System.in);
		char choice = 'x';
		
		displayUser(menu, user);
		
		//"\nEnter the time of the trip to edit:\t"
		menu.prompt(10);
		user.setTime1(scan.nextLine());
		
		while (choice != '1' && choice != '2')
		{
			//"\nWhat would you like to change?"
			menu.prompt(11);
			
			//Display choices: Destination or Departure Location
			menu.editMenu();
		
			choice = (char) scan.nextByte();
		}
		
		//Enter new destination prompt.
		menu.prompt(7);
		
		//if (choice == '1')
		//{
			//user.setDestinationLocation(scan.nextLine());
			
			// When 1 passed as parameter, user.edit() changes
			// destinationLocation from this user at time1
			// in the database to this.destinationLocation.
			user.edit();			
		//}
		
		/*
		else
		{
			user.setDepartureLocation(scan.nextLine());
			
			// When 2 passed as parameter, user.edit() changes
			// departureLocation from this user at time1
			// in the database to this.departureLocation.			
			user.edit(2);
		}
*/
		scan.close();
	}// end editTrip
	
	// Deleting a trip in the database.
	// The user's trips are displayed.
	// The user chooses a trip to delete.
	// The trip is then deleted from the database.
	public void deleteTrip(Menu menu, User user) throws CarPoolException
	{
		Scanner scan = new Scanner(System.in);
		
		displayUser(menu, user);
		
		//"\nEnter the time of the trip to delete:\t"
		menu.prompt(13);
		user.setTime1(scan.nextLine());
		
		menu.errorMessage(4);
		
		user.delete();		
		scan.close();
	}
	
	// Searching for trips in the database.
	// The user enters a destination and time frame
	// as parameters for the search.
	// The program outputs the information of all
	// users of the opposite type (driver/passenger) who
	// are making that trip within the time frame.
	public void searchTrip(Menu menu, User user) throws CarPoolException
	{
		Scanner scan = new Scanner(System.in);
		
		//"\nEnter a destination:\t"
		menu.prompt(7);
		
		//Set destination
		user.setDestinationLocation(scan.nextLine());
		
		//"\n\nEnter a time frame for the search."
		menu.prompt(16);
		
		//"\nEarliest time:\t");
		menu.prompt(17);
		
		//Set time1 to earliest time
		user.setTime1(scan.nextLine());
		
		//"\nLatest time:\t"
		menu.prompt(18);
		
		//Set time2 to latest time
		user.setTime2(scan.nextLine());
		
		user.search();		
		scan.close();
	}
	
	// The user's trips are displayed.
	public void displayUser(Menu menu, User user)
	{
		user.displayUser();
	}
	
	// The user logs out of the program.
	// All fields of User instance are set
	// to null and loggedIn is set to false.
	public void logOut(Menu menu, User user)
	{
		user.logOut();
	}
	
	// Entry point of the program.
	public static void main(String[] args)
	{
		
		
		
		try {
			User user = new User();
			Menu menu = new Menu();
			Controller controller = new Controller();
			controller.mainMenu(menu, user);
		}
		catch (CarPoolException ex)
		{
			// this will catch 3 exceptions:
			// user class cannot connect to database
			// menu class cannot find property file
			// property file is missing information
			System.out.println("Ex: " + ex);
		}
	}
}
