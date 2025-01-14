package gestionedipendenti;

import java.sql.*;

public class DBmanager {

	    private static final String URL = "jdbc:mysql://localhost:3306/gestionedipendenti"; 
	    private static final String USER = "root";  
	    private static final String PW = "WC@[O-I[WJ^RK^.4TDZI"; 
	    private Connection connection;
	    
	    // Construttore
	    public DBmanager() {
	        this.connection = null;
	    }
	    
	    // metodo per aprire la connessione
	    public Connection openConnection() {
	        if (connection != null) {
	            System.out.println("Connection is already established.");
	            return connection;
	        }

	        try {

	            connection = DriverManager.getConnection(URL, USER, PW);
	            System.out.println("Connection established.\n");
	        } catch (SQLException e) {
	            System.err.println("SQL Exception: " + e.getMessage());
	        }
	        return connection;
	    }

	    // metodo per chiudere la connessione
	    public void closeConnection() {
	        if (connection != null) {
	            try {
	                connection.close();
	                connection = null;
	                System.out.println("Connection closed.");
	            } catch (SQLException e) {
	                System.err.println("Error closing connection: " + e.getMessage());
	            }
	        } else {
	            System.out.println("No connection to close.");
	        }
	    }

	}
