package gestionedipendenti;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
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
			Manager.insertEmployee(conn);
			break;
		case 2:
			Manager.updateEmployeeRole(conn);
			break;
		case 3:
			Manager.updateEmployeeSalary(conn);
			break;
		case 4:
			Manager.deleteEmployee(conn);
			break;
		case 5:
			Team.getTeams(conn);
			break;
		case 6:
			Team.insertTeam(conn);
			break;
		case 7:
			Team.assignTeam(conn);
			break;
		case 8:
			Team.getAssignedEmployees(conn);
			break;
		case 99:
			Employee.readAllDipendenti(conn);
			break;
		}
		
		
		
		 DBmanager.closeConnection();
		
		
//		Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus).
		

	
	}	
	
}
	


