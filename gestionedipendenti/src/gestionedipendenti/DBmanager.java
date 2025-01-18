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
	            System.out.println("Connessione gia presente. \n");
	            return connection;
	        }

	        try {

	            connection = DriverManager.getConnection(URL, USER, PW);
	            System.out.println("Connessione eseguita.\n");
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
	                System.out.println("Chiusura connessione\n");
	            } catch (SQLException e) {
	                System.err.println("Errore, non e stato possibile chiudere la connessione: " + e.getMessage());
	            }
	        } else {
	            System.out.println("Nessuna connessione da chiudere");
	        }
	    }

	}
