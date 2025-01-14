package gestionedipendenti;

import java.sql.*;
import java.util.Scanner;

public class Manager extends Employee {

	double bonus;
	String teamGestito;
	static Scanner scan = new Scanner(System.in);

	public Manager(double bonus, String teamGestito, int id, String nome, String cognome, double stipendioBase) {

		super(id, nome, cognome, stipendioBase);
		this.bonus = bonus;
		this.teamGestito = teamGestito;
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
