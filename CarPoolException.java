package carpool;

public class CarPoolException extends Exception {

	  // static publicly defined error codes 
	  public static int	FILE_NOT_FOUND = 0; 
	  public static int	MISSING_PROPERTY = 1;
	  public static int CONNECTION_NOT_ESTABLISHED = 2;
	    
	  // static publicly defined exception messages  
	  public static String[]  MESSAGE = { 
	  		"File not found", 
	  		"One or more missing/incorrect properties in file", 
	  		"Connection could not be established"
	  } ;
	
		protected int code;
		
	    private CarPoolException() { ; } 
	    
	    // Throw message only.
	    public CarPoolException(String m) { 
	    	super(m); 
	    } 
	    
	    // Throw message and code. 
	    public CarPoolException(String message, int code) { 
	    	super(message);
	    	this.code = code;
	    } 
	    
		public int getCode() { 
			return code;
		}
		
	    @Override
		public String toString() {
			return "CarPoolException [code=" + code + ", toString()="
					+ super.toString() + "]\n" + MESSAGE[code] ;
		}
}
