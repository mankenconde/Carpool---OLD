package carpool;

import java.util.ResourceBundle;

public class Menu {
	
	private ResourceBundle bundle;
	
	public Menu() throws CarPoolException
	{
		init();
	}
	
	public void init() throws CarPoolException
	{
		// Check if the properties file exists
		try
		{
			bundle = ResourceBundle.getBundle("Output");			
		}
		catch(Exception ex)
		{
			bundle = null;
		}
		
		if (bundle == null)
			throw new CarPoolException("Property file not found", 0);
	}
	
	// Output prompt
	public void prompt(int index) throws CarPoolException
	{
		if (!bundle.containsKey("PROMPT_" + index))
			throw new CarPoolException("Property file does not contain prompt", 1);
		
		else
		System.out.println(bundle.getString("PROMPT_" + index));
	}
	
	// Output error message
	public void errorMessage(int index) throws CarPoolException
	{
		if (!bundle.containsKey("ERROR_" + index))
			throw new CarPoolException("Property file does not contain error statement", 1);
		
		else
		System.err.println(bundle.getString("ERROR_" + index));
	}
	
	// The main menu.
	// The first prompts the user sees when the program runs.
	public void mainMenu() throws CarPoolException
	{
		//"Please enter the number of your choice."
		prompt(0);
		
		if (!bundle.containsKey("MENU1.0"))
			throw new CarPoolException("Property file does not contain statement for main menu", 1);
		
		System.out.println(bundle.getString("MENU1.0"));
		System.out.println();
		
		// Output the main menu
		for (int i = 1; i < 4; i++)
		{
			if (!bundle.containsKey("MENU1." + i))
				throw new CarPoolException("Property file does not contain statement for main menu", 1);
			
			System.out.println(i + ": " + bundle.getString("MENU1." + i));
		}
		
		System.out.println();
		
		System.out.println(bundle.getString("MENU1.0"));
	}

	// The menu that is displayed to the user once logged in.
	public void loggedInMenu() throws CarPoolException
	{
		//Please enter the number of your choice."
		prompt(0);
		
		if (!bundle.containsKey("MENU2.0"))
			throw new CarPoolException("Property file does not contain statement for logged-in menu", 1);
		
		System.out.println(bundle.getString("MENU2.0"));
		System.out.println();
	
		// Output the logged-in menu
		for (int i = 1; i < 7; i++)
		{
			if (!bundle.containsKey("MENU2." + i))
				throw new CarPoolException("Property file does not contain statement for logged-in menu", 1);
			
			System.out.println(i + ": " + bundle.getString("MENU2." + i));
		}
	
		System.out.println();
		System.out.println(bundle.getString("MENU2.0"));
	}
	
	// The menu to edit a trip.
	public void editMenu() throws CarPoolException
	{
		//"What would you like to change?"
		prompt(19);
		
		// Output the edit menu
		for (int i = 1; i < 3; i++)
		{
			if (!bundle.containsKey("MENU3." + i))
				throw new CarPoolException("Property file does not contain statement for edit menu", 1);
			
			System.out.println(i + ": " + bundle.getString("MENU3." + i));
		}
		
		System.out.println();
	}
} // end class
