package GestioneDipendentiSQL;
import java.sql.*;
public class DBmanager {
	
		private static final String DB_URL = "jdbc:mysql://localhost:3306/dbgestionedipendenti";
		private static final String DB_USER = "root";
		private static final String DB_PASSWORD = "159654897wdM";
		private Connection connection;
		
		// Costruttore
		public DBmanager() {
			this.connection = null;
		}
		
		//metodo per aprire la connessione
		public Connection openConnection() {
			if (connection != null) {
				System.out.println("Connessione stabilita.");
				return connection;
			}
			
			try {
				
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				System.out.println("Connection stabilita");
			} catch (SQLException e) {
				System.out.println("SQL Exception: " + e.getMessage());
			}
			return connection;
		}
		
		// metodo per chiudere la connessione
		public void closeConnection() {
			if(connection != null) {
				try {
					connection.close();
					connection = null;
					System.out.println("Connessione chiusa.");
				} catch (SQLException e) {
					System.out.println("Errore chiudendo connessione: " + e.getMessage());
				}
			} else {
				System.out.println("Nessuna connessione da chiudere.");
			}
		}
}
