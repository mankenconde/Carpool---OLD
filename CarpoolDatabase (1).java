package edu.cuny.csi.csc330.carpool;

import java.sql.*; 
import java.util.*;

public class CarpoolDatabase {
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String isDriver;
	protected String departureLocation;
	protected String destinationLocation;
	protected String time;
	protected String phone;
	
	
	public CarpoolDatabase() {
		// TODO Auto-generated constructor stub
	}
	
	public static void mainMenu()
	{
		System.out.println("******************************************");
		System.out.println(" ");
	
		System.out.println("1: Log in.");
		System.out.println("2: Register a new user.");
		System.out.println("3: Exit.");
	
		System.out.println(" ");
		System.out.println("******************************************");
	}
	
	public static void driverMenu()
	{
		System.out.println("Please enter the number of your choice:");
		
		System.out.println("******************************************");
		System.out.println(" ");
	
		System.out.println("1: Enter a trip.");
		System.out.println("2: Edit a trip.");
		System.out.println("3: Delete a trip.");
		System.out.println("4: Search for passengers.");
		System.out.println("5: Display your info.");
		System.out.println("6: Log out.");
	
		System.out.println(" ");
		System.out.println("******************************************");
	}
	
	public static void passengerMenu()
	{
		System.out.println("Please enter the number of your choice:");
		
		System.out.println("******************************************");
		System.out.println(" ");
	
		System.out.println("1: Enter a trip.");
		System.out.println("2: Edit a trip.");
		System.out.println("3: Delete a trip.");
		System.out.println("4: Search for drivers.");
		System.out.println("5: Display your info.");
		System.out.println("6: Log out.");
	
		System.out.println(" ");
		System.out.println("******************************************");
	}
	
	public static void main(String[] args) {
		 Connection connection = null;  
	     ResultSet resultSet = null;  
	     Statement statement = null;  
	     CarpoolDatabase newUser = new CarpoolDatabase();
	     Scanner reader = new Scanner(System.in);  // Reading from System.in
	     String status = null;
	     int choice;
	     int ecode = 0;

	     try   {  
	    	 
	         Class.forName("org.sqlite.JDBC");  
	        String dbURL = "jdbc:sqlite:C:\\data\\sqlite\\Carpool.db";  
	         connection = DriverManager.getConnection(dbURL);  
	         statement = connection.createStatement();  

	         System.out.println(connection.getClass().getName());
	         System.out.println(statement.getClass().getName() + "\n\n");
	         System.out.println("\t\t***** Database content *****\n");
	         System.out.printf("%-10s %-10s %-10s %-10s %-5s \n","UserName", "Password", "FirstName", "LastName", "isDriver");
	         // Perform a SELECT on one of the tables ... 
	         
			
			statement = connection.createStatement() ;
			
			resultSet = statement.executeQuery( " SELECT UserName, Password, FirstName, LastName, isDriver FROM Users");
			while(resultSet.next() ) {
				String userName = resultSet.getString("UserName"); // ID
				String password = resultSet.getString("Password");   // Name
				String firstName = resultSet.getString("FirstName"); // ID
				String lastName = resultSet.getString("LastName");
				String isDriver = resultSet.getString("isDriver");
				
				System.out.printf("%-10s %-10s %-10s %-10s %-5s \n",userName, password, firstName, lastName, isDriver);
			}
			
			System.out.println();
	     } 
	     catch (Exception e) 
	     {  
	         e.printStackTrace();  
	         ecode = 9;
	     }
	     
	     
	   do
	   {
		   mainMenu();
		   choice = (char) reader.nextInt();
	     if(choice==2)
	     {
				//New user registration
			 try{
				 System.out.print("\t\t***** New User Registration *****\n");
				 String userName1 = null;
				 PreparedStatement statement2;
				 System.out.print("\nEnter a UserName of your choice: ");
			     newUser.userName = reader.next();
			     
			     //Check if userName is not already taken
				 String queryLogin = "SELECT * FROM USERS where UserName = (?)";
				 statement2 = connection.prepareStatement(queryLogin);
				 statement2.setString(1, newUser.userName);
				 resultSet = statement2.executeQuery();
					
					
				while(resultSet.next() ) {
					userName1 = resultSet.getString("UserName"); // ID			 
				}
				if(userName1!=null)
				{
					System.out.print("\nSorry this UserName is already taken, please enter another one! ");
					System.out.print("\nEnter a UserName of your choice: ");
					newUser.userName = reader.next();
				}
				String conPassword;
				System.out.print("\nNew Password: ");
				newUser.password = reader.next();
				System.out.print("\nConfirm Password: ");
				conPassword = reader.next();
				if(newUser.password != conPassword);
				{
				    System.out.print("\nError confirming the new password! ");
				    System.out.print("\nNew Password: ");
				    newUser.password = reader.next();
				    System.out.print("\nConfirm Password: ");
				    conPassword = reader.next();
				};
				System.out.print("\nEnter your First Name: ");
				newUser.firstName = reader.next();
				System.out.print("\nEnter your Last Name: ");
				newUser.lastName = reader.next();
				System.out.print("\nAre you a driver(YES/NO): ");
				newUser.isDriver = reader.next();
				String queryInsert = " INSERT INTO Users (UserName, Password, FirstName, LastName, isDriver)"	     									
					     									+ "VALUES (?, ?, ?, ?, ?)";
				statement2 = connection.prepareStatement(queryInsert);
				    	 
				statement2.setString(1, newUser.userName);
				statement2.setString(2, newUser.password);
				statement2.setString(3, newUser.firstName);
				statement2.setString(4, newUser.lastName);
				statement2.setString(5, newUser.isDriver.toUpperCase());
						 
				statement2.executeUpdate();	
			     
			 }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     }
	     else if(choice==1) 
	     {   //Log in process
			//Prompt user for UserName and Password
			try{
				System.out.print("\t\t***** Log in *****\n");
		         System.out.print("\nEnter your UserName: ");
			     newUser.userName = reader.next();
			     System.out.print("\nEnter your Password: ");
			     newUser.password = reader.next();
				
				//Check if userName and password match with database content
				String queryLogin = "SELECT * FROM USERS where UserName = (?) AND Password = (?)";
				PreparedStatement statement2 = connection.prepareStatement(queryLogin);
				statement2.setString(1, newUser.userName);
				statement2.setString(2, newUser.password);
				resultSet = statement2.executeQuery();
				
				String userName2 = null;
				String password2 = null;
				while(resultSet.next() ) {
					 userName2 = resultSet.getString("UserName"); // ID
					 password2 = resultSet.getString("Password");   // Name				 
				}
				
				if( userName2==null || password2==null)
					System.out.print("\nSorry, your credentials don't match!\n");
				else 
				{	System.out.print("\nLogin successful, welcome " + userName2 + "\n\n");
					
					queryLogin = " SELECT FirstName, isDriver FROM Users WHERE UserName= (?)";
					statement2 = connection.prepareStatement(queryLogin);
					statement2.setString(1, userName2);
					resultSet = statement2.executeQuery();
					while(resultSet.next() ) {
						newUser.firstName = resultSet.getString("FirstName");
						newUser.isDriver = resultSet.getString("isDriver"); // ID
					}
				
					if(newUser.isDriver.equalsIgnoreCase("yes"))
					{
						do{
						driverMenu();
						choice = reader.nextInt();
						//Driver Menu
						if(choice == 1)
						{   //1: Add a trip.	 
							try {
								 System.out.print("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
						    	 newUser.time=reader.next();
						    	 System.out.print("\nEnter your phone number witn no space: ");
						    	 newUser.phone=reader.next();
								 System.out.print("\nEnter the ZIP code for your departure location: ");
								 newUser.departureLocation = reader.next();
						    	 System.out.print("\nEnter the ZIP code for your destination location: ");
						    	 newUser.destinationLocation = reader.next(); 
						    	 
						    	 
						    	 String queryAdd = " INSERT INTO Schedule (UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone)"	     									
											+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
						    	 PreparedStatement statement3 = connection.prepareStatement(queryAdd);
								
								 statement3.setString(1, newUser.userName);
						    	 statement3.setString(2, newUser.firstName);
						    	 statement3.setString(3, newUser.isDriver.toUpperCase());
						    	 statement3.setString(4, newUser.departureLocation);
						    	 statement3.setString(5, newUser.destinationLocation);
						    	 statement3.setString(6, newUser.time);
						    	 statement3.setString(7, newUser.phone);		    	 

						    	 statement3.executeUpdate();
								} catch (SQLException e) {
								// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
						else if(choice == 2)
						{
							//2: Edit a trip.
							try{
								String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
					        	statement2 = connection.prepareStatement(querySchedule);
								statement2.setString(1, newUser.userName);
								resultSet = statement2.executeQuery();
								System.out.println("\t\t***** Your registered trips *****\n");
								System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
					        	while(resultSet.next() ) {
									String userName = resultSet.getString("UserName"); // ID
									String firstName = resultSet.getString("FirstName");   // Name
									String isDriver = resultSet.getString("isDriver"); // ID
									String departure = resultSet.getString("DepartureLocation");
									String destination = resultSet.getString("DestinationLocation");
									String time = resultSet.getString("Time1");
									String phone = resultSet.getString("Phone");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
									status = userName;
					        	}
					        	if(status==null)
					        		System.out.print("\nSorry, there is no registered trip to edit!\n");
					        	else
					        	{
					        		System.out.print("\nEnter the time for the trip you want to update in the fromat(HH:MM): ");
						        	String update = reader.next();
									System.out.print("\nEnter the ZIP code for your departure location: ");
									newUser.departureLocation = reader.next();
							    	System.out.print("\nEnter the ZIP code for your destination location: ");
							    	newUser.destinationLocation = reader.next();
							    	System.out.print("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
							    	newUser.time=reader.next();
							    	System.out.print("\nEnter your phone number witn no space: ");
							    	newUser.phone=reader.next();
						        	System.out.println();
						        	String sql = "UPDATE Schedule SET DepartureLocation=(?),DestinationLocation=(?), Time1=(?), Phone=(?) WHERE Time1 = (?) AND UserName = (?)";
					                PreparedStatement pstmt = connection.prepareStatement(sql);
					                pstmt.setString(1, newUser.departureLocation);
					                pstmt.setString(2, newUser.destinationLocation);
					                pstmt.setString(3, newUser.time);
					                pstmt.setString(4, newUser.phone);
					                pstmt.setString(5, update);
					                pstmt.setString(6, newUser.userName);
					                pstmt.executeUpdate();
					        	}
					        	
							}catch(SQLException e){
								e.printStackTrace();
							}
						}
						else if(choice == 3)
						{
							//3: Delete a trip				 
					        try{					        						        	
					        	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
					        	statement2 = connection.prepareStatement(querySchedule);
								statement2.setString(1, newUser.userName);
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
									status=userName;
					        	}
								if(status==null)
									System.out.println("Sorry, there is no registered trip to delete!\n");
								else
								{
									System.out.print("\nEnter the time for the trip you want to remove in the fromat(HH:MM): ");
						        	String delete = reader.next();
						        	System.out.println();
						        	String sql = "DELETE FROM Schedule WHERE Time1 = (?) AND UserName = (?)";
					                PreparedStatement pstmt = connection.prepareStatement(sql);
					                pstmt.setString(1, delete);
					                pstmt.setString(2,newUser.userName);
					                pstmt.executeUpdate();
								}	
								
					        } catch (SQLException e) {
					        	e.printStackTrace();
					        }
						}
						else if(choice == 4)
						{
							//4: Search for passengers
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
						}
						else if(choice == 5)
						{
							//5: Display your info
							String info = " SELECT UserName, Password, FirstName, LastName, isDriver FROM Users WHERE UserName = (?)";
				        	statement2 = connection.prepareStatement(info);
							statement2.setString(1, newUser.userName);
							resultSet = statement2.executeQuery();
							System.out.println("\t\t***** Your Profil *****\n");
							System.out.printf("%-10s %-10s %-10s %-10s %-10s\n","UserName", "Password", "FirstName", "LastName", "isDriver");
				        	while(resultSet.next() ) {
								String userName = resultSet.getString("UserName"); // ID
								String password = resultSet.getString("Password");   // Name
								String firstName = resultSet.getString("FirstName"); // ID
								String lastName = resultSet.getString("LastName");
								String isDriver = resultSet.getString("isDriver");
								System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",userName, password, firstName, lastName, isDriver);
							}
				        	System.out.println();
				        	
				        	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
				        	statement2 = connection.prepareStatement(querySchedule);
							statement2.setString(1, newUser.userName);
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
						}
						
					  }while(choice<6);
						if(choice==6)
							newUser = null;
					}	
					else
					{
						//Passenger menu
						do{
							passengerMenu();
							choice = reader.nextInt();
							if(choice == 1)
							{   //1: Add a trip.	 
								try {
									 System.out.print("\nEnter the ZIP code for your departure location: ");
									 newUser.departureLocation = reader.next();
							    	 System.out.print("\nEnter the ZIP code for your destination location: ");
							    	 newUser.destinationLocation = reader.next(); 
							    	 System.out.print("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
							    	 newUser.time=reader.next();
							    	 System.out.print("\nEnter your phone number witn no space: ");
							    	 newUser.phone=reader.next();
							    	 
							    	 String queryAdd = " INSERT INTO Schedule (UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone)"	     									
												+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
							    	 PreparedStatement statement3 = connection.prepareStatement(queryAdd);
									
									 statement3.setString(1, newUser.userName);
							    	 statement3.setString(2, newUser.firstName);
							    	 statement3.setString(3, newUser.isDriver.toUpperCase());
							    	 statement3.setString(4, newUser.departureLocation);
							    	 statement3.setString(5, newUser.destinationLocation);
							    	 statement3.setString(6, newUser.time);
							    	 statement3.setString(7, newUser.phone);		    	 

							    	 statement3.executeUpdate();
									} catch (SQLException e) {
									// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
							else if(choice == 2)
							{
								//2: Edit a trip.
								try{
									String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
						        	statement2 = connection.prepareStatement(querySchedule);
									statement2.setString(1, newUser.userName);
									resultSet = statement2.executeQuery();
									System.out.println("\t\t***** Your registered trips *****\n");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
						        	while(resultSet.next() ) {
										String userName = resultSet.getString("UserName"); // ID
										String firstName = resultSet.getString("FirstName");   // Name
										String isDriver = resultSet.getString("isDriver"); // ID
										String departure = resultSet.getString("DepartureLocation");
										String destination = resultSet.getString("DestinationLocation");
										String time = resultSet.getString("Time1");
										String phone = resultSet.getString("Phone");
										System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
										status = userName;
						        	}
						        	
						        	if(status == null)
						        		System.out.print("\nSorry, there is no registered trip to edit!\n");
						        	else
						        	{
						        		System.out.print("\nEnter the time for the trip you want to update in the fromat(HH:MM): ");
							        	String update = reader.next();
										System.out.print("\nEnter the ZIP code for your departure location: ");
										newUser.departureLocation = reader.next();
								    	System.out.print("\nEnter the ZIP code for your destination location: ");
								    	newUser.destinationLocation = reader.next();
								    	System.out.print("\nEnter time when you wish to be at destination in the format (HH:MM) \n: ");
								    	newUser.time=reader.next();
								    	System.out.print("\nEnter your phone number witn no space: ");
								    	newUser.phone=reader.next();
							        	System.out.println();
							        	String sql = "UPDATE Schedule SET DepartureLocation=(?),DestinationLocation=(?), Time1=(?), Phone=(?) WHERE Time1 = (?) AND UserName = (?)";
						                PreparedStatement pstmt = connection.prepareStatement(sql);
						                pstmt.setString(1, newUser.departureLocation);
						                pstmt.setString(2, newUser.destinationLocation);
						                pstmt.setString(3, newUser.time);
						                pstmt.setString(4, newUser.phone);
						                pstmt.setString(5, update);
						                pstmt.setString(6,newUser.userName);
						                pstmt.executeUpdate();
						        	}
						        	
								}catch(SQLException e){
									e.printStackTrace();
								}
							}
							else if(choice == 3)
							{
								//3: Delete a trip	
						        try{					        						        	
						        	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
						        	statement2 = connection.prepareStatement(querySchedule);
									statement2.setString(1, newUser.userName);
									resultSet = statement2.executeQuery();
									System.out.println("\t\t***** Your registered trips *****\n");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
						        	while(resultSet.next() ) {
										String userName = resultSet.getString("UserName"); // ID
										String firstName = resultSet.getString("FirstName");   // Name
										String isDriver = resultSet.getString("isDriver"); // ID
										String departure = resultSet.getString("DepartureLocation");
										String destination = resultSet.getString("DestinationLocation");
										String time = resultSet.getString("Time1");
										String phone = resultSet.getString("Phone");
										System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
										status = userName;
									}
									if(status==null)
										System.out.print("\nSorry, there is no registered trip to delete! \n");
									else{	
										System.out.print("\nEnter the time for the trip you want to remove in the fromat(HH:MM): ");
							        	String delete = reader.next();
							        	System.out.println();
							        	String sql = "DELETE FROM Schedule WHERE Time1 = (?) AND UserName = (?)";
						                PreparedStatement pstmt = connection.prepareStatement(sql);
						                pstmt.setString(1, delete);
						                pstmt.setString(2,newUser.userName);
						                pstmt.executeUpdate();
									}
						        } catch (SQLException e) {
						        	e.printStackTrace();
						        }
							}
							else if(choice == 4)
							{
								//4: Search for drivers
								String passengerSearch = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE isDriver = (?)";
					        	statement2 = connection.prepareStatement(passengerSearch);
								statement2.setString(1, "YES");
								resultSet = statement2.executeQuery();
								System.out.println("\t\t***** Registered Drivers *****\n");
								System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
					        	while(resultSet.next() ) {
									String userName = resultSet.getString("UserName"); // ID
									String firstName = resultSet.getString("FirstName");   // Name
									String isDriver = resultSet.getString("isDriver"); // ID
									String departure = resultSet.getString("DepartureLocation");
									String destination = resultSet.getString("DestinationLocation");
									String time = resultSet.getString("Time1");
									String phone = resultSet.getString("Phone");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
								}
					        	System.out.println();
							}
							else if(choice == 5)
							{
								//5: Display your info
								String info = " SELECT UserName, Password, FirstName, LastName, isDriver FROM Users WHERE UserName = (?)";
					        	statement2 = connection.prepareStatement(info);
								statement2.setString(1, newUser.userName);
								resultSet = statement2.executeQuery();
								System.out.println("\t\t***** Your Profil *****\n");
								System.out.printf("%-10s %-10s %-10s %-10s %-10s\n","UserName", "Password", "FirstName", "LastName", "isDriver");
					        	while(resultSet.next() ) {
									String userName = resultSet.getString("UserName"); // ID
									String password = resultSet.getString("Password");   // Name
									String firstName = resultSet.getString("FirstName"); // ID
									String lastName = resultSet.getString("LastName");
									String isDriver = resultSet.getString("isDriver");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",userName, password, firstName, lastName, isDriver);
								}
					        	System.out.println();
					        	
					        	String querySchedule = " SELECT UserName, FirstName, isDriver, DepartureLocation, DestinationLocation, Time1, Phone FROM Schedule WHERE UserName = (?)";
					        	statement2 = connection.prepareStatement(querySchedule);
								statement2.setString(1, newUser.userName);
								resultSet = statement2.executeQuery();
								System.out.println("\t\t***** Your registered trips *****\n");
								System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","UserName", "FirstName", "isDriver", "Departure", "Destination", "Time", "Phone");
					        	while(resultSet.next() ) {
									String userName = resultSet.getString("UserName"); // ID
									String firstName = resultSet.getString("FirstName");   // Name
									String isDriver = resultSet.getString("isDriver"); // ID
									String departure = resultSet.getString("DepartureLocation");
									String destination = resultSet.getString("DestinationLocation");
									String time = resultSet.getString("Time1");
									String phone = resultSet.getString("Phone");
									System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",userName, firstName, isDriver, departure, destination, time, phone);
								}
					        	System.out.println();
							}
								
						}while(choice<6);
						if(choice==6)
							newUser = null;
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
	     }
	   }while(choice!=3);
	     if(choice==3)
	    	 System.out.println("\nThank you for using our services! ");
	     reader.close();
	     		System.exit(ecode);
		
	}

}
