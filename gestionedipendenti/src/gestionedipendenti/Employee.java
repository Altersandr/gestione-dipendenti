package gestionedipendenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
	
	
	
	//Stampa tutti i record presenti nella tabella DIPENDENTI
	
	   public static void readAllEmployees(Connection conn, Scanner scan) {
	        String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente, stipendiobase, ruolo, bonus FROM dipendenti LEFT JOIN manager ON dipendenti.iddipendenti = manager.idmanager";
	        try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco dipendenti:");
	            while (rs.next()) {
	                int id = rs.getInt("iddipendenti");
	                String name = rs.getString("nomedipendente");
	                String cognome = rs.getString("cognomedipendente");
	                String ruolo = rs.getString("ruolo");
	                double stipendioBase = rs.getDouble("stipendiobase");
	                double bonus = rs.getDouble("bonus");
	                double stipendio = 0;
	                if(ruolo.equals("Manager")) {
	                	Manager.addManager(conn, id);
	                	stipendio = stipendioBase * ((bonus/100)+1);
	                }else stipendio = stipendioBase;      
	                System.out.printf("ID: %d | Nome: %s | Cognome: %s | Stipendio: %.2f | Ruolo: %s \n",
	                        id, name, cognome, stipendio, ruolo);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	   
	   /**
	     * Inserisce un nuovo dipendente nella tabella DIPENDENTI.
	     *
	     * @param nome    			nome del dipendente
	     * @param cognome      		cognome del dipendente
	     * @param stipendio      	stipendio base del dipendente
	     * @param ruolo				ruolo del dipendente
	     */
		
	   
	   public static void insertEmployee(Connection conn, Scanner scan) {
		   
		   String ruolo = "";
		   int ruoloInt = 0;
		   double stipendio = 0;
		   boolean valid = false;

			System.out.println("Nome del dipendente: ");
			String nome = scan.next();			
			System.out.println("Cognome del dipendente: ");
			String cognome = scan.next();
			

			do {
				System.out.println("Stipendio del dipendente: ");
				if (scan.hasNextDouble()) {
					stipendio = scan.nextDouble();
					scan.nextLine();
					valid = true;
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);
			
			
			do {
				System.out.println("Ruolo del dipendente: \n1. Dipendente \n2. Sviluppatore \n3. Manager  ");
				if (scan.hasNextInt()) {
					ruoloInt = scan.nextInt();
					scan.nextLine();
					valid = true;
				} else {
					valid = false;
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);
			
			if (ruoloInt == 1)
				ruolo = "Dipendente";
			else if (ruoloInt == 2)
				ruolo = "Sviluppatore";
			else if (ruoloInt == 3)
				ruolo = "Manager";

			String query = "INSERT INTO dipendenti (nomedipendente, cognomedipendente, stipendiobase, ruolo) VALUES(?, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, nome);
				pstmt.setString(2, cognome);
				pstmt.setDouble(3, stipendio);
				pstmt.setString(4, ruolo);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Aggiunta dipendente fallita");
				} else {
					System.out.println("Dipendente aggiunto con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	   
	   
	   /**
	     * Modifica il ruolo del dipendente.
	     *
	     * @param id    			id del dipendente
	     * @param nuovoRuolo     	nuovo ruolo del dipendente
	     */

		public static void updateEmployeeRole(Connection conn, Scanner scan) {

			int id = 0;
			int nuovoRuoloInt = 0;
			String nuovoRuolo = "";
			boolean valid = false;
			
			
			do {
				System.out.println("ID del dipendente: ");
				if (scan.hasNextInt()) {
					id = scan.nextInt();
					scan.nextLine();
					valid = true;
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);
			
			
			do {
				System.out.println("Nuovo ruolo del dipendente: \n1. Dipendente \n2. Sviluppatore \n3. Manager  ");
				if (scan.hasNextInt()) {
					nuovoRuoloInt = scan.nextInt();
					scan.nextLine();
					valid = true;
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
					valid = false;
				}
			} while (!valid);
			

			
			if (nuovoRuoloInt == 1)
				nuovoRuolo = "Dipendente";
			else if (nuovoRuoloInt == 2)
				nuovoRuolo = "Sviluppatore";
			else if (nuovoRuoloInt == 3) {
				Manager.addManager(conn, id);
				nuovoRuolo = "Manager";}

			String query = "UPDATE dipendenti SET ruolo = ? WHERE iddipendenti = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, nuovoRuolo);
				pstmt.setInt(2, id);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Modifica dipendente fallita, nessuna riga aggiunta.");
				} else {
					System.out.println("Dipendente modificato con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		 /**
	     * Modifica lo stipendio del dipendente.
	     *
	     * @param id    			id del dipendente
	     * @param stipendio     	nuovo stipendio del dipendente
	     */

		public static void updateEmployeeSalary(Connection conn, Scanner scan) {
			
			int id = 0;
			double stipendio = 0;
			boolean valid = false;
			
			do {
				System.out.println("ID del dipendente: ");
				if (scan.hasNextInt()) {
					id = scan.nextInt();
					scan.nextLine();
					valid = true;
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);
			
			do {
				System.out.println("Nuovo stipendio del dipendente:  ");
				if (scan.hasNextDouble()) {
					stipendio = scan.nextDouble();
					scan.nextLine();
					valid = true;
				} else {
					valid = false;
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);

			String query = "UPDATE dipendenti SET stipendioBase = ? WHERE iddipendenti = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setDouble(1, stipendio);
				pstmt.setInt(2, id);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Modifica dipendente fallita, nessuna riga aggiunta.");
				} else {
					System.out.println("Dipendente modificato con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		
		 /**
	     * Elimina un dipendente dalla tabella DIPENDENTI.
	     *
	     * @param id    			id del dipendente
	     */

		public static void deleteEmployee(Connection conn, Scanner scan) {

			boolean valid = false;
			int id = 0;

			do {
				System.out.println("ID del dipendente: ");
				if (scan.hasNextInt()) {
					id = scan.nextInt();
					scan.nextLine();
					valid = true;
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un ID valido");
				}
			} while (!valid);

			String query = "DELETE from dipendenti WHERE iddipendenti = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setInt(1, id);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Eliminazione fallita, dipendente non presente \n");
				} else {
					System.out.println("Dipendente eliminato con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		
		

}
