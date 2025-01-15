package GestioneDipendentiSQL;
import java.sql.*;
import java.util.Scanner;
public class employee {
	
	int id;
	String nome;
	String cognome;
	double stipendioBase;
	
	public employee (int id, String nome, String cognome, double stipendioBase) {
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
	 DBmanager DBmanager = new DBmanager();
		Connection conn = DBmanager.openConnection();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Che azione vuoi eseguire? "
				+ "\n1. Aggiungere dipendente "
				+ "\n2. Modificare ruolo del dipendente "
				+ "\n3. Modificare stipendio del dipendente "
				+ "\n4. Eliminare dipendente "
				+ "\n5. Mostra la lista dei team "
				+ "\n6. Aggiungere team "
				+ "\n7. Assegna dipendente a un team"
				+ "\n8. Visualizza dipendenti assegnati"
				+ "\n99. Mostra lista dipendenti ");
		
		int scelta = scan.nextInt();
		scan.nextLine();
	 switch(scelta) {
		case 1:
			employee.insertEmployee(conn);
			break;
		case 2:
			employee.updateEmployeeRole(conn);
			break;
		case 3:
			employee.updateEmployeeSalary(conn);
			break;
		case 4:
			employee.deleteEmployee(conn);
			break;
		case 5:
			employee.getTeams(conn);
			break;
		case 6:
			employee.insertTeam(conn);
			break;
		case 7:
			employee.assignTeam(conn);
			break;
		case 8:
			employee.getAssignedEmployees(conn);
			break;
		case 99:
			employee.readAllDipendenti(conn);
			break;
		}
	

}
