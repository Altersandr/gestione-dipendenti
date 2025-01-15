package gestionedipendenti;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		DBmanager DBmanager = new DBmanager();
		Connection conn = DBmanager.openConnection();

		Scanner scan = new Scanner(System.in);

//		Developer.getDevAndLang(conn);
//		Developer.readAllProjects(conn);
//		Manager.getTeams(conn);
//		
//		Developer.assignTeamToProject(conn);
//		Developer.getLanguageList(conn);

//		Developer.learnLanguage(conn);

//		Developer.readAllDevelopers(conn);

//		Developer.addProject(conn);

//		Employee.readAllEmployees(conn);
		int scelta;

		do {
			System.out.println("Che azione vuoi eseguire? " + "\n1. Azione su dipendente " + "\n2. Azione su manager"
					+ "\n3. Azione su developer" + "\n4. Uscire dal programma");
			scelta = scan.nextInt();
			scan.nextLine();

//			int scelta = scan.nextInt();
//			scan.nextLine();
			switch (scelta) {
			case 1:
				
				int scelta1;
				
				do {
				System.out.println("Per aggiungere un dipendente premi 1! \n"
						+ "Per aggiornare lo stipendio base di un dipendente premi 2 \n"
						+ "Per aggionare il ruolo di un dipendente premi 3! \n"
						+ "Per vedere la lista dei dipendenti premi 4! \n" 
						+ "Per cancellare un dipendente premi 5");
				
				scelta1 = scan.nextInt();
				scan.nextLine();
				
				switch (scelta1) {
				case 1:
					Employee.insertEmployee(conn);
					break;
				case 2:
					Employee.updateEmployeeSalary(conn);
					break;
				case 3:
					Employee.updateEmployeeRole(conn);
					break;
				case 4:
					Employee.readAllEmployees(conn);
					break;
				case 5:
					Employee.deleteEmployee(conn);
					break;
				default:
					Employee.closeScanner();

				}
			}while (scelta1 > 5 || scelta1 < 1);

			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
//		case 5:
//			Team.getTeams(conn);
//			break;
//		case 6:
//			Team.insertTeam(conn);
//			break;
//		case 7:
//			Team.assignTeam(conn);
//			break;
//		case 8:
//			Team.getAssignedEmployees(conn);
//			break;
//		case 99:
//			Employee.readAllEmployees(conn);
//			break;

//			default:
//				scelta = -1;
//				continue;
			}
		} while (scelta > 4 || scelta < 1);

		DBmanager.closeConnection();

//		Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus).

	}

}
