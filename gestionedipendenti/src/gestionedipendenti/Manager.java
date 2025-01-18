package gestionedipendenti;

import java.sql.*;
import java.util.Scanner;

public class Manager extends Employee {

	double bonus;
	String teamGestito;

	public Manager(double bonus, String teamGestito, int id, String nome, String cognome, double stipendioBase) {

		super(id, nome, cognome, stipendioBase);
		this.bonus = bonus;
		this.teamGestito = teamGestito;
	}

	public static void getTeams(Connection conn) {
		String sql = "SELECT idteam, nometeam FROM team";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Elenco team:");
			while (rs.next()) {
				int id = rs.getInt("idteam");
				String name = rs.getString("nometeam");

				System.out.printf("ID: %d | Nome Team: %s\n", id, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void getAssignedEmployees(Connection conn) {
		String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente, team.nometeam, ruolo "
				+ "FROM dipendenti " + "LEFT JOIN gestioneteam "
				+ "ON dipendenti.iddipendenti = gestioneteam.iddipendente1 " + "LEFT JOIN team "
				+ "ON team.idteam = gestioneteam.idteam1 " + "WHERE team.nometeam IS NOT NULL " + "ORDER BY nometeam "

		;

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Elenco dipendenti assegnati al team:");
			while (rs.next()) {
				int id = rs.getInt("iddipendenti");
				String name = rs.getString("nomedipendente");
				String cognome = rs.getString("cognomedipendente");
				String team = rs.getString("nometeam");
				String ruolo = rs.getString("ruolo");

				System.out.printf("ID: %d | Nome: %s %s | Team: %s | Ruolo: %s \n", id, name, cognome, team, ruolo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertTeam(Connection conn, Scanner scan) {

		System.out.println("Nome del nuovo team: ");
		String nome = scan.nextLine();

		String query = "INSERT INTO team (nometeam) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, nome);
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creazione team fallita, nessuna riga aggiunta.");
			} else {
				System.out.println("Team aggiunto con successo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void assignTeam(Connection conn, Scanner scan) {
		int idDipendente = -1;
		int idTeam = -1;
		boolean valid = false;
		
		do {
			System.out.println("Inserisci l'ID del dipendente che vuoi assegnare a un team: ");
			if (scan.hasNextInt()) {
				idDipendente = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);
		
		do {
			System.out.println("Inserisci l'ID del team: ");
			if (scan.hasNextInt()) {
				idTeam = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
				valid = false;
			}
		} while (!valid);
		
		String query = "INSERT INTO gestioneteam (iddipendente1, idteam1) VALUES(?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, idDipendente);
			pstmt.setInt(2, idTeam);
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Dipendente non assegnato, verifica input");
			} else {
				System.out.println("Dipendente assegnato con successo");
			}

		} catch (SQLException e) {
			System.out.println("Sono stati inseriti dati sbagliati, riprovare\n");
			e.printStackTrace();
		}

	}

	public static void removeFromTeam(Connection conn, Scanner scan) {
		int idDipendente = -1;
		boolean valid = false;
		
		do {
			System.out.println("Inserisci l'ID del dipendente che vuoi rimuovere dal team: ");
			if (scan.hasNextInt()) {
				idDipendente = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);

		String query = "DELETE FROM gestioneteam WHERE iddipendente1 = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, idDipendente);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Rimosso fallito, il dipendente non e assegnato a un team\n");
			} else {
				System.out.println("Dipendente rimosso con successo\n");
			}

		} catch (SQLException e) {
			System.out.println("Sono stati inseriti dati sbagliati, riprovare\n");
			e.printStackTrace();
		}

	}

	public static void readAllProjects(Connection conn) {

		String sql = "SELECT idprogetto, nomeprogetto FROM progetti";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			System.out.println("Elenco progetti:");
			while (rs.next()) {
				int id = rs.getInt("idprogetto");
				String name = rs.getString("nomeprogetto");

				System.out.printf("ID: %d | Nome Progetto: %s\n", id, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addProject(Connection conn, Scanner scan) {

		System.out.println("Nome progetto: ");
		String nomeprog = scan.nextLine();

		String query = "INSERT INTO progetti (nomeprogetto) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, nomeprog);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Aggiunta progetto fallita");
			} else {
				System.out.println("Progetto inserito con successo");
			}

		} catch (SQLException e) {
			System.out.println("Sono stati inseriti dati sbagliati, riprovare\n");
			e.printStackTrace();
		}

	}

	public static void removeProject(Connection conn, Scanner scan) {
		boolean valid = false;
		int id = -1;

		do {
			System.out.println("ID progetto da rimuovere: ");
			if (scan.hasNextInt()) {
				id = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);
		

		String query = "DELETE FROM progetti WHERE idprogetto = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, id);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Rimosso fallito");
			} else {
				System.out.println("Progetto rimosso con successo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Rimosso fallito, riprova inserendo un ID valido\n");
		}

	}

	public static void assignTeamToProject(Connection conn, Scanner scan) {

		boolean valid = false;
		int idTeam = 0;
		int idProject = 0;

		do {
			System.out.println("Inserisci l'ID del team da assegnare a un progetto: ");
			if (scan.hasNextInt()) {
				idTeam = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
			}
		} while (!valid);

		do {
			System.out.println("Inserisci l'ID del progetto: ");
			if (scan.hasNextInt()) {
				idProject = scan.nextInt();
				scan.nextLine();
				valid = true;
			} else {
				scan.nextLine();
				System.out.println("Errore input, inserire un ID valido");
				valid = false;
			}
		} while (!valid);

		String query = "INSERT INTO progettiassegnati (idteam, idprogetto) VALUES(?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, idTeam);
			pstmt.setInt(2, idProject);
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Assegnazione team fallita, nessuna riga aggiunta.");
			} else {
				System.out.println("Team assegnato con successo");
			}

		} catch (SQLException e) {
			System.out.println("Sono stati inseriti dati sbagliati, riprovare\n");
			e.printStackTrace();

		}

	}
}
