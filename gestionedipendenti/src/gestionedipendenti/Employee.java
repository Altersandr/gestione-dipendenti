package gestionedipendenti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
	
	int id;
	String nome;
	String cognome;
	double stipendioBase;
	
	
	public Employee (int id, String nome, String cognome, double stipendioBase) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.stipendioBase = stipendioBase;
	}
	
	   public static void readAllDipendenti(Connection conn) {
	        String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente, stipendiobase, ruolo FROM dipendenti";
	        try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco prodotti:");
	            while (rs.next()) {
	                int id = rs.getInt("iddipendenti");
	                String name = rs.getString("nomedipendente");
	                String cognome = rs.getString("cognomedipendente");
	                double stipendio = rs.getDouble("stipendiobase");
	                String ruolo = rs.getString("ruolo");
	               

	                System.out.printf("ID: %d | Nome: %s | Cognome: %s | Stipendio: %.2f | Ruolo: %s \n",
	                        id, name, cognome, stipendio, ruolo);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
