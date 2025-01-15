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
	static Scanner scan = new Scanner(System.in);
	
	
	public Employee (int id, String nome, String cognome, double stipendioBase) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.stipendioBase = stipendioBase;
	}
	
	   public static void readAllEmployees(Connection conn) {
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
	   
	   public static void insertEmployee(Connection conn) {

			System.out.println("Nome del dipendente: ");
			String nome = scan.nextLine();
			System.out.println("Cognome del dipendente: ");
			String cognome = scan.nextLine();
			System.out.println("Stipendio del dipendente: ");
			double stipendio = scan.nextDouble();
			System.out.println("Ruolo del dipendente: \n1. Dipendente \n2. Sviluppatore \n3. Manager  ");
			int ruoloInt = scan.nextInt();

			String ruolo = "";

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
					throw new SQLException("Creazione dipendente fallita, nessuna riga aggiunta.");
				} else {
					System.out.println("Dipendente aggiunto con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public static void updateEmployeeRole(Connection conn) {

			System.out.println("ID del dipendente: ");
			int id = scan.nextInt();
			scan.nextLine();
			System.out.println("Nuovo ruolo del dipendente: \n1. Dipendente \n2. Sviluppatore \n3. Manager  ");
			int nuovoRuoloInt = scan.nextInt();
			scan.nextLine();
			String nuovoRuolo = "";
			if (nuovoRuoloInt == 1)
				nuovoRuolo = "Dipendente";
			else if (nuovoRuoloInt == 2)
				nuovoRuolo = "Sviluppatore";
			else if (nuovoRuoloInt == 3)
				nuovoRuolo = "Manager";

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

		public static void updateEmployeeSalary(Connection conn) {

			System.out.println("ID del dipendente: ");
			int id = scan.nextInt();
			scan.nextLine();
			System.out.println("Nuovo stipendio del dipendente:  ");
			double stipendio = scan.nextDouble();

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

		public static void deleteEmployee(Connection conn) {

			System.out.println("ID del dipendente: ");
			int id = scan.nextInt();
			scan.nextLine();

			String query = "DELETE from dipendenti WHERE iddipendenti = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setInt(1, id);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					throw new SQLException("Modifica dipendente fallita, nessuna riga aggiunta.");
				} else {
					System.out.println("Dipendente eliminato con successo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

}
