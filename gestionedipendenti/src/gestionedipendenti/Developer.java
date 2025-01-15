package gestionedipendenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public static void insertNewLanguage(Connection conn) {
		
		System.out.println("Nome linguaggio: ");
		String nome = scan.nextLine();
		
		String query = "INSERT INTO listalinguaggi (nome) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, nome);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Aggiunta linguaggio fallita, nessuna riga aggiunta.");
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
				+ "ON listalinguaggi.idlinguaggio = linguaggiconosciutti.idlinguaggio "

				
				;
		
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
	
	public static void learnLanguage(Connection conn) {
		
		System.out.println("ID Dipendente: ");
		getDevAndLang(conn);
		int idDip = scan.nextInt();
		scan.nextLine();
		
		System.out.println("ID Linguaggio: ");
		getLanguageList(conn);
		int idLang = scan.nextInt();
		scan.nextLine();
		
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
			e.printStackTrace();
		}
		
	}

	public static void readAllProjects(Connection conn) {
		
		  String sql = "SELECT idprogetto, nomeprogetto FROM progetti";
	        try (
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            System.out.println("Elenco progetti:");
	            while (rs.next()) {
	                int id = rs.getInt("idprogetto");
	                String name = rs.getString("nomeprogetto");

	                System.out.printf("ID: %d | Nome Progetto: %s\n",
	                        id, name);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void addProject(Connection conn) {
		
		System.out.println("Nome progetto: ");
		String nomeprog = scan.nextLine();
		
		String query = "INSERT INTO progetti (nomeprogetto) VALUES(?)";

		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, nomeprog);


			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Aggiunta progetto fallita, nessuna riga aggiunta.");
			} else {
				System.out.println("Progetto inserito con successo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void assignTeamToProject(Connection conn) {
		
		System.out.println("Inserisci l'ID del team da assegnare a un progetto: ");

		int idTeam = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Inserisci l'ID del progetto: ");
		readAllProjects(conn);
		int idProject = scan.nextInt();
		scan.nextLine();
		
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
			e.printStackTrace();
		}
	}


}
