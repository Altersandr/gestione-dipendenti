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
				+ "\n1. Azione su dipendente "
				+ "\n2. Azione su manager"
				+ "\n3. Azione su developer"
				+ "\n0. Uscire dal menu");
				
		
		int scelta = scan.nextInt();
		scan.nextLine();
		
		
		
		switch(scelta) {
		case 1:
			System.out.println("Lista azioni che si possono svolgere su dipendente ");
			switch (scelta) {
				case 1:
					Employee.insertEmployee(conn);
					break;
				case 2:
					Employee.updateEmployeeSalary(conn);
					break;
				case 3: 
					Employee.deleteEmployee(conn);
					break;
				case 4: 
					Employee.readAllEmployees(conn);
					break;
					
			}

		case 2:
			Employee.updateEmployeeRole(conn);
			break;
		case 3:
			Employee.updateEmployeeSalary(conn);
			break;
		case 4:
			Employee.deleteEmployee(conn);
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
			Employee.readAllEmployees(conn);
			break;
		}
		
		
		
		 DBmanager.closeConnection();
		
		
//		Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus).
		

	
	}	
	
}
	


