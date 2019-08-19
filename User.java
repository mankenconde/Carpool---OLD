//package edu.cuny.csi.csc330.carpool;
package carpool;

import java.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Scanner;

//import carpool.DBconnection;

public class User {
	
    Scanner reader = new Scanner(System.in);  // Reading from System.in

	public User() {
		
		//DBconnection dbconnection() = new DBconnection();
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     Statement statement = null; 
	     
	     int ecode =0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;
	     }
	}




	private String userName;
	private String password;	
	private String firstName;
	private String lastName;
	private boolean isDriver;
	private String phoneNumber;
	protected static boolean loggedIn = false;	
	
	
	private String destinationLocation;
	private String departureLocation;
	private String time1;
	private String time2;


	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}
	
	public boolean getLoggedIn()
	{
		return loggedIn;
	}
	
	public void setLoggedIn(boolean log)
	{
		loggedIn = log;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isDriver() {
		return isDriver;
	}

	public void setDriver(boolean isDriver) {
		this.isDriver = isDriver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}
	
	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}
	
	public void search()
	{
		//sql code to call all with destination
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     PreparedStatement statement2;
	     
	     int ecode = 0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;

	     }
		
	     
	     if(isDriver) {
	    	 //Search for passengers
	    	 try {
				String passengerSearch = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE isDriver = (?)";
	        	statement2 = connection.prepareStatement(passengerSearch);
				statement2.setString(1, "NO");
				resultSet = statement2.executeQuery();
				System.out.println("\t\t***** Registered Passengers *****\n");
				System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
	        	while(resultSet.next() ) {
					String userName = resultSet.getString("UserName"); // ID
					String firstName = resultSet.getString("FirstName");   // Name
					String isDriver = resultSet.getString("isDriver"); // ID
					String departure = resultSet.getString("DepartureLocation");
					String destination = resultSet.getString("DestinationLocation");
					String time = resultSet.getString("Time1");
					String phone = resultSet.getString("Phone");
					System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
				}
	        	System.out.println();
	    	 }catch (Exception e) {
	    		 e.printStackTrace();
	    	 }
	    	 
	     }
	     
	     else {
	    	 //Search for drivers
	    	 try {
	    	 String passengerSearch = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE isDriver = (?)";
	        	statement2 = connection.prepareStatement(passengerSearch);
				statement2.setString(1, "YES");
				resultSet = statement2.executeQuery();
				System.out.println("\t\t***** Registered Drivers *****\n");
				System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
	        	while(resultSet.next() ) {
					String userName = resultSet.getString("UserName"); // ID
					String firstName = resultSet.getString("FirstName");   // Name
					String isDriver = resultSet.getString("isDriver"); // ID
					String departure = resultSet.getString("DepartureLocation");
					String destination = resultSet.getString("DestinationLocation");
					String time = resultSet.getString("Time1");
					String phone = resultSet.getString("Phone");
					System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
				}
	        	System.out.println();
	    	 }catch (Exception e){
	    		 e.printStackTrace();
	    	 }
	     }
		
	}
	
	/*
	public void search(LocalTime from, LocalTime to, String destinationLocation)
	{
		// sql call all with destination within timeframe
		
	}
	*/
	
	public boolean delete()
	{
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     PreparedStatement statement2;
	     
	     int ecode = 0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;

	     }
		
	     String status = null;
		
		
		try{					        						        	
        	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
        	statement2 = connection.prepareStatement(querySchedule);
			statement2.setString(1, this.getUserName());
			resultSet = statement2.executeQuery();
			System.out.println("\t\t***** Your registered trips *****\n");
			System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
        	while(resultSet.next() ) {
				String userName = resultSet.getString("UserName"); // ID
				String firstName = resultSet.getString("FirstName");   // Name
				String isDriver = resultSet.getString("isDriver"); // ID
				String departure = resultSet.getString("DepartureLocation");
				String destination = resultSet.getString("DestinationLocation");
				String time = resultSet.getString("Time1");
				String phone = resultSet.getString("Phone");
				System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
				status = userName;
			}
			if(status==null)
				System.out.println("\nSorry, there is no registered trip to delete! \n");
			else{	
				System.out.println("\nEnter the time for the trip you want to remove in the fromat(HH:MM): ");
	        	String delete = reader.next();
	        	System.out.println();
	        	String sql = "DELETE FROM Schedule WHERE Time1 = (?) AND UserName = (?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, delete);
                pstmt.setString(2,this.getUserName());
                pstmt.executeUpdate();
			}
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		
		return false;
	}
	
	public void edit() {
		
		//Edit Trip
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	    // Statement statement2 = null;
	     String status = null;
	    
		//String queryLogin = "SELECT * FROM USERS where UserName = (?) AND Password = (?)";
		PreparedStatement statement2;
 
	     int ecode =0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	        // statement2 = connection.createStatement();  

	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;
	     }
	     
	     
		
		try{
			String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
        	statement2 = connection.prepareStatement(querySchedule);
			statement2.setString(1, this.userName);
			resultSet = statement2.executeQuery();
			System.out.println("\t\t***** Your registered trips *****\n");
			System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
        	while(resultSet.next() ) {
				String userName = resultSet.getString("UserName"); // ID
				String firstName = resultSet.getString("FirstName");   // Name
				String isDriver = resultSet.getString("isDriver"); // ID
				String departure = resultSet.getString("DepartureLocation");
				String destination = resultSet.getString("DestinationLocation");
				String time = resultSet.getString("Time1");
				String phone = resultSet.getString("Phone");
				System.out.printf("%-10s %-10s %-10s %-10s %-10s %-5s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
				status = userName;
        	}
        	if(status==null)
        		System.out.println("\nSorry, there is no registered trip to edit!\n");
        	else
        	{
        		System.out.println("\nEnter the time for the trip you want to update in the fromat(HH:MM): ");
	        	String update = reader.next();
				System.out.println("\nEnter the ZIP code for your departure location: ");
				this.departureLocation = reader.next();
		    	System.out.println("\nEnter the ZIP code for your destination location: ");
		    	this.destinationLocation = reader.next();
		    	System.out.println("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
		    	this.setTime1(reader.next());
		    	System.out.println("\nEnter your phone number witn no space: ");
		    	this.setPhoneNumber(reader.next());
	        	System.out.println();
	        	String sql = "UPDATE Schedule SET DepartureLocation=(?),DestinationLocation=(?), Time1=(?), Phone=(?) WHERE Time1 = (?) AND UserName = (?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, this.departureLocation);
                pstmt.setString(2, this.destinationLocation);
                pstmt.setString(3, this.getTime1() );
                pstmt.setString(4, this.getPhoneNumber());
                pstmt.setString(5, update);
                pstmt.setString(6, this.userName);
                pstmt.executeUpdate();
        	}
        	
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void displayUser()
	{
		
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     PreparedStatement statement2;
	     
	     int ecode = 0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;

	     }
		
		try {
			
		
		String info = " SELECT UserName, Password, FirstName, LastName, isDriver FROM Users WHERE UserName = (?)";
    	statement2 = connection.prepareStatement(info);
		statement2.setString(1, this.getUserName());
		resultSet = statement2.executeQuery();
		System.out.println("\t\t***** Your Profil *****\n");
		System.out.printf("%-10s %-10s %-10s %-10s %-5s\n","UserName", "Password", "FirstName", "LastName", "isDriver");
    	while(resultSet.next() ) {
			String userName = resultSet.getString("UserName"); // ID
			String password = resultSet.getString("Password");   // Name
			String firstName = resultSet.getString("FirstName"); // ID
			String lastName = resultSet.getString("LastName");
			String isDriver = resultSet.getString("isDriver");
			System.out.printf("%-10s %-10s %-10s %-10s %-5s\n",userName, password, firstName, lastName, isDriver);
		}
    	System.out.println();
    	
    	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
    	statement2 = connection.prepareStatement(querySchedule);
		statement2.setString(1, this.getUserName());
		resultSet = statement2.executeQuery();
		System.out.println("\t\t***** Your registered trips *****\n");
		System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
    	while(resultSet.next() ) {
			String userName = resultSet.getString("UserName"); // ID
			String firstName = resultSet.getString("FirstName");   // Name
			String isDriver = resultSet.getString("isDriver"); // ID
			String departure = resultSet.getString("DepartureLocation");
			String destination = resultSet.getString("DestinationLocation");
			String time = resultSet.getString("Time1");
			String phone = resultSet.getString("Phone");
			System.out.printf("%-10s %-10s %-10s %-10s %-5s %-5s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
		}
    	System.out.println();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean logOut()
	{
		// set user's data variables to null
		setUserName(null);
		setFirstName(null);
		setLastName(null);
		setDriver(false);
		setPassword(null);
		setDestinationLocation(null);
		setDepartureLocation(null);
		this.time1 = null;
		this.time2 = null;
		
		return false;
	}
	
	public boolean register()
	{
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	    // PreparedStatement statement2;
	     
	     int ecode = 0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;

	     }
		
		 try{
			 System.out.println("\t\t***** New User Registration *****\n");
			 System.out.println("\nEnter a UserName of your choice: ");
		     this.setUserName(reader.next());
		     String conPassword;
		     System.out.println("\nNew Password: ");
	    	 this.setPassword( reader.next());
	    	 System.out.println("\nConfirm Password: ");
	    	 conPassword = reader.next();
//		     while(newUser.password != conPassword);
//		     {
//		    	 System.out.println("Error, please confirm the new password! ");
//		    	 System.out.println("\nNew Password: ");
//		    	 newUser.password = reader.next();
//		    	 System.out.println("\nConfirm Password: ");
//		    	 conPassword = reader.next();
//		       };
		     System.out.println("\nEnter your First Name: ");
	    	 //newUser.firstName = reader.next();
	    	 this.setFirstName(reader.next());
	    	 System.out.println("\nEnter your Last Name: ");
	    	// newUser.lastName = reader.next();
	    	 this.setLastName(reader.next());
	    	 System.out.println("\nAre you a driver(YES/NO): ");
	    	// newUser.isDriver = reader.next();
	    	 if(reader.next().toUpperCase() == "YES") 
	    		 this.isDriver = true;
	    	 
	    	 else
	    		 this.isDriver = false;

	    	 String queryInsert = " INSERT INTO Users (UserName, Password, FirstName, LastName, isDriver)"	     									
		     									+ "VALUES (?, ?, ?, ?, ?)";
	    	 PreparedStatement statement2 = connection.prepareStatement(queryInsert);
	    	 
	    	 statement2.setString(1, this.getUserName());
			 statement2.setString(2, this.getPassword());
			 statement2.setString(3, this.getFirstName());
			 statement2.setString(4, this.getLastName());
			 if(isDriver) 
				 statement2.setString(5, "YES");
			 
			 else
				 statement2.setString(5, "NO");
			 
			 statement2.executeUpdate();
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return true;
	}
	
	protected void menu()
	{
		char choice = '0';
		
		System.out.println("Welcome to the Carpool Managmennt App.");		
		
		while (choice != '3')
		{

			System.out.println("Please enter the number of your choice:");
		
			System.out.println("******************************************");
			System.out.println(" ");
		
			System.out.println("1: Log in.");
			System.out.println("2: Register a new user.");
			System.out.println("3: Exit.");
		
			System.out.println(" ");
			System.out.println("******************************************");
			
			try {
				choice = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if (choice == '1')
			{
				boolean loggedIn = logIn();
				
				if (loggedIn && isDriver)
				{
					Driver driver = new Driver();//(userName, password); // pass parameters to create driver instance.
					driver.menu(this.loggedIn);
					driver = null;
				}
				
				if (loggedIn && !isDriver)
				{
					Passenger passenger = new Passenger();//(userName, password);// pass parameters
					passenger.menu(this.loggedIn);
					passenger = null;
				}
				
				System.gc(); // Give hint to Garbage Collector to act. Hopefully cleans up de-referenced driver/passenger.
				loggedIn = logOut();
			}
			
			else if (choice == '2')
			{
				boolean loggedIn = register();
				
				if (loggedIn && isDriver)
				{
					Driver driver = new Driver();//(userName, password, firstName, lastName, isDriver); // pass parameters to create driver instance.
					driver.menu();
				}
				
				if (loggedIn && !isDriver)
				{
					Passenger passenger = new Passenger();//(userName, password, firstName, lastName, isDriver);// pass parameters
					passenger.menu();
				}
				
				System.gc(); // Give hint to Garbage Collector to act. Hopefully cleans up de-referenced driver/passenger.
				loggedIn = logOut();
				
			}
			
			else if (choice == '3')
			{
				System.out.println("Exiting..."); //exit message
			}
			
			else
			{
				choice = '0';
			}
			
			System.out.println("");			
			
			
		}

		
	}
	
	/*************
	
	private static boolean registering()
	{
		// search database to see if any username there matches with this class's Username.
		
		
		// if there is no match
		// write a row in the database that includes:
		// this class's userName, password, firstName; lastName; isDriver; phoneNumber;
		 
		return true;
		
		// if false
		
		
		
		// return false;
	}
	***********/
	
	protected boolean logIn()
	{
		
		
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	    // PreparedStatement statement2;
	     
	     int ecode = 0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;

	     }
		
		int choice;
		
		try{
			System.out.println("\t\t***** Log in *****\n");
	         System.out.println("\nEnter your UserName: ");
		     this.setUserName(reader.next());
		     System.out.println("\nEnter your Password: ");
		     this.setPassword(reader.next());
			
			//Check if userName and password match with database content
			String queryLogin = "SELECT * FROM USERS where UserName = (?) AND Password = (?)";
			PreparedStatement statement2 = connection.prepareStatement(queryLogin);
			statement2.setString(1, this.userName);
			statement2.setString(2, this.password);
			resultSet = statement2.executeQuery();
			
			String userName2 = null;
			String password2 = null;
			while(resultSet.next() ) {
				 userName2 = resultSet.getString("UserName"); // ID
				 password2 = resultSet.getString("Password");   // Name				 
			}
			
			if( userName2==null || password2==null)
				System.out.println("\nSorry, your credentials don't match!\n");
			else 
			{	System.out.println("\nWelcome " + userName2 + "\n");
				
				queryLogin = " SELECT FirstName, isDriver FROM Users WHERE UserName= (?)";
				statement2 = connection.prepareStatement(queryLogin);
				statement2.setString(1, userName2);
				resultSet = statement2.executeQuery();
				while(resultSet.next() ) {
					this.setFirstName(resultSet.getString("FirstName"));
					//newUser.isDriver = resultSet.getString("isDriver"); 
					if(resultSet.getString("isDriver") == "YES")
						this.isDriver=true;
				}
			
				//if(newUser.isDriver.equalsIgnoreCase("yes"))
				if(this.isDriver == true)
				{
					do{
					//driverMenu();
					Driver driver = new Driver();
					driver.menu();
					choice = reader.nextInt();
					//Driver Menu
					if(choice == 1)
					{   //1: Add a trip.	 
						try {
							 System.out.println("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
					    	// newUser.time=reader.next();
							 this.setTime1(reader.next());
					    	 System.out.println("\nEnter your phone number witn no space: ");
					    	 //newUser.phone=reader.next();
					    	 this.setPhoneNumber(reader.next());
							 System.out.println("\nEnter the ZIP code for your departure location: ");
							 //newUser.departureLocation = reader.next();
							 this.setDepartureLocation(reader.next());
					    	 System.out.println("\nEnter the ZIP code for your destination location: ");
					    	 //newUser.destinationLocation = reader.next(); 
					    	 this.setDestinationLocation(reader.next());
					    	 
					    	 
					    	 String queryAdd = " INSERT INTO Schedule (UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone)"	     									
										+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
					    	 PreparedStatement statement3 = connection.prepareStatement(queryAdd);
							
							 statement3.setString(1, this.getUserName());
					    	 statement3.setString(2, this.getFirstName());
					    	 if(this.isDriver == true)
					    		 statement3.setString(3, "YES");
					    	 
					    	 else
					    		statement3.setString(3, "no");
					    	 
					    	 statement3.setString(4, this.getDepartureLocation());
					    	 statement3.setString(5, this.getDestinationLocation());
					    	 statement3.setString(6, this.getTime1());
					    	 statement3.setString(7, this.getPhoneNumber());		    	 

					    	 statement3.executeUpdate();
							} catch (SQLException e) {
							// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
					}while(choice<=6);
					
				}
				
			}
			
		}
		
	
					catch(SQLException e){
						e.printStackTrace();
					}
					
	
						
		loggedIn = true;
		
		return true;
	}
	
	
	
	
	
/*
//************ MAIN *****************************
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		User user = new User();
		user.menu();
	// calls to the menu classes	
	
		
	} // end main
*/
	public void add() {
		// TODO Auto-generated method stub
		
		
		//Establishing connection to database
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     Statement statement = null; 
	     
	     int ecode =0;
	     
	     try {
	     Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL); 
	         
	     }  catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;
	     }
		
		
		
		Scanner reader = new Scanner(System.in);
		try {
			 System.out.println("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
	    	 this.setTime1(reader.next());
	    	 System.out.println("\nEnter your phone number witn no space: ");
	    	 this.setPhoneNumber(reader.next());
			 System.out.println("\nEnter your departure location: ");
			 this.setDepartureLocation (reader.nextLine());
	    	 System.out.println("\nEnter your destination location: ");
	    	 this.setDestinationLocation(reader.nextLine()); 
	    	 
	    	 
	    	 String queryAdd = " INSERT INTO Schedule (UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone)"	     									
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    	 PreparedStatement statement3 =  connection.prepareStatement(queryAdd);
			
			 statement3.setString(1, this.userName);
	    	 statement3.setString(2, this.firstName);
	    	 
	    	 if(this.isDriver) 
	    	 statement3.setString(3, "YES");
	    	 else
	    		statement3.setString(3, "NO");
	    	 
	    	 statement3.setString(4, this.departureLocation);
	    	 statement3.setString(5, this.destinationLocation);
	    	 statement3.setString(6, this.time1);
	    	 statement3.setString(7, this.phoneNumber);		    	 

	    	 statement3.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


} // end User
