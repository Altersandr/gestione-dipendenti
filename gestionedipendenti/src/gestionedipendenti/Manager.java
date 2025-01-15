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


	public static void getTeams(Connection conn) {
		        String sql = "SELECT idteam, nometeam FROM team";
		        try (
		             Statement stmt = conn.createStatement();
		             ResultSet rs = stmt.executeQuery(sql)) {

		            System.out.println("Elenco team:");
		            while (rs.next()) {
		                int id = rs.getInt("idteam");
		                String name = rs.getString("nometeam");

		                System.out.printf("ID: %d | Nome Team: %s\n",
		                        id, name);
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

	}
	
	public static void getAssignedEmployees(Connection conn) {
		String sql = "SELECT iddipendenti, nomedipendente, cognomedipendente, team.nometeam, ruolo "
				+ "FROM dipendenti "
				+ "LEFT JOIN gestioneteam "
				+ "ON dipendenti.iddipendenti = gestioneteam.iddipendente1 "
				+ "LEFT JOIN team "
				+ "ON team.idteam = gestioneteam.idteam1 "
				+ "WHERE team.nometeam IS NOT NULL "
				+ "ORDER BY nometeam "
				
				;
		
		try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco dipendenti assegnati al team:");
	            while (rs.next()) {
	                int id = rs.getInt("iddipendenti");
	                String name = rs.getString("nomedipendente");
	                String cognome = rs.getString("cognomedipendente");
	                String team = rs.getString("nometeam");
	                String ruolo = rs.getString("ruolo");
	                
	               

	                System.out.printf("ID: %d | Nome: %s %s | Team: %s | Ruolo: %s \n",
	                        id, name, cognome, team, ruolo);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public static void insertTeam(Connection conn) {


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
	
	public static void assignTeam(Connection conn) {
		
		System.out.println("Inserisci l'ID del dipendente che vuoi assegnare a un team: ");
		Employee.readAllEmployees(conn);
		int idDipendente = scan.nextInt();
		
		System.out.println("Inserisci l'ID del team: ");
		getTeams(conn);
		int idTeam = scan.nextInt();
		
		String query = "INSERT INTO gestioneteam (iddipendente1, idteam1) VALUES(?, ?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, idDipendente);
			pstmt.setInt(2, idTeam);
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creazione team fallita, nessuna riga aggiunta.");
			} else {
				System.out.println("Dipendente assegnato con successo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
