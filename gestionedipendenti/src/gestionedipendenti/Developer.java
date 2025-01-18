package gestionedipendenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Developer extends Employee{
	ArrayList<String> linguaggiConosciuti;
	ArrayList<String> progettiAssegnati;
	
	
	public Developer(ArrayList<String> linguaggiConosciuti, ArrayList<String> progettiAssegnati, int id, String nome, String cognome, double stipendioBase) {
		
		super(id, nome, cognome, stipendioBase);
		this.linguaggiConosciuti = linguaggiConosciuti;
		this.progettiAssegnati = progettiAssegnati;
	}
	
	public static void readAllDevelopers(Connection conn) {
		
		String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente FROM dipendenti WHERE ruolo = 'Developer' ";
	        try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco sviluppatori:");
	            while (rs.next()) {
	                int id = rs.getInt("iddipendenti");
	                String nome = rs.getString("nomedipendente");
	                String cognome = rs.getString("cognomedipendente");

	                System.out.printf("ID: %d | Nome: %s %s\n",
	                        id, nome, cognome);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public static void getLanguageList(Connection conn) {
		
		String sql = "SELECT idlinguaggio, nome FROM listalinguaggi";
	        try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco linguaggi:");
	            while (rs.next()) {
	                int id = rs.getInt("idlinguaggio");
	                String name = rs.getString("nome");

	                System.out.printf("ID: %d | Linguaggio: %s\n",
	                        id, name);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void insertNewLanguage(Connection conn, Scanner scan) {
		
		System.out.println("Nome linguaggio: ");
		String nome = scan.nextLine();
		
		String query = "INSERT INTO listalinguaggi (nome) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, nome);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Aggiunta linguaggio fallita");
			} else {
				System.out.println("Linguaggio aggiunto con successo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public static void getDevAndLang(Connection conn) {
		String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente, listalinguaggi.nome "
				+ "FROM dipendenti "
				+ "INNER JOIN linguaggiconosciutti "
				+ "ON dipendenti.iddipendenti = linguaggiconosciutti.iddipendente "
				+ "INNER JOIN listalinguaggi "
				+ "ON listalinguaggi.idlinguaggio = linguaggiconosciutti.idlinguaggio ";
		
		try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco sviluppatori e linguaggi conosciutti:");
	            while (rs.next()) {
	                int id = rs.getInt("iddipendenti");
	                String name = rs.getString("nomedipendente");
	                String cognome = rs.getString("cognomedipendente");
	                String lista = rs.getString("listalinguaggi.nome");
	                System.out.printf("ID: %d | Nome: %s %s | Linguaggio: %s  \n",
	                        id, name, cognome, lista);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void learnLanguage(Connection conn, Scanner scan) {
		boolean valid = false;
		int idDip = 0;
		int idLang = 0;
		
		do {
			System.out.println("ID Dipendente: ");
			if (scan.hasNextInt()) {
				idDip = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);
		
		do {
			System.out.println("ID Linguaggio: ");
			if (scan.hasNextInt()) {
				idLang = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				valid = false;
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);
		
		
		
		String query = "INSERT INTO linguaggiconosciutti (iddipendente, idlinguaggio) VALUES(?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, idDip);
			pstmt.setInt(2, idLang);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Aggiunta linguaggio fallita, nessuna riga aggiunta.");
			} else {
				System.out.println("Linguaggio imparato con successo");
			}

		} catch (SQLException e) {
			System.out.println("Input errati, riprova");
		}
		
	}

}
