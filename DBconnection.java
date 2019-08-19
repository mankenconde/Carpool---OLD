package carpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnection {
	
	public DBconnection() {

	 Connection connection = null;  
     ResultSet resultSet = null;  
     Statement statement = null; 
     
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
     
	}
	
}
