package GestioneDipendentiSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
public class main {
	 
	public static void main(String[] args) {
		
		DBmanager DBmanager = new DBmanager();
		Connection conn = DBmanager.openConnection();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Che azione vuoi eseguire? "
				+ "\n1. Azione su dipendente "//
				+ "\n2. Azione su manager " //modifiche al team e al progetto
				+ "\n3. Azione su developer "
				+ "\n4. uscita dal programma ");	
		int scelta = scan.nextInt();
		scan.nextLine();
		
		switch(scelta) {
			case 1:
				System.out.println("Lista azione da svolgere su dipendente");
				int scelta1 = scan.nextInt();
				scan.nextLine();
				switch(scelta1){
				case 1:
					System.out.println("Per aggiungere un dipendente premi 1! \n Per aggiornare lo stipendio base di un dipendente premi 2 \n Per aggionare il ruolo di un dipendente premi 3! \n Per vedere la lista dei dipendenti premi 4!");
					
					employee.insertEmployee(conn);
					break;
				case 2:
					System.out.println("Per aggiornare lo stipendio base di un dipendente premi 2");
					
					employee.updateEmployeeSalary(conn);
					break;
				case 3:
					System.out.println("Per aggionare il ruolo di un dipendente premi 3!");
					
					employee.updateEmployeeRole(conn);
					break;
				case 4:
					System.out.println("Per vedere la lista dei dipendenti premi 4!");
					
					employee.readAllDipendenti(conn);
					break;
				}
			
			case 2:
				System.out.println("Lista azione da svolgere su manager");
				int scelta2 = scan.nextInt();
				scan.nextLine();
				switch(scelta2) {
					case 1:
						System.out.println("Per vedere la lista dei team premi 1! \n Per inserire un team premi 2! \n Per assegnare un team ad un progetto premi 3! \n Per assegnare i dipendenti al team premi 4!");
						employee.getTeams(conn);
						break;
					case 2:
						System.out.println("Per inserire un team premi 2!");
						employee.insertTeam(conn);
						break;
					case 3:
						System.out.println("Per assegnare un team ad un progetto premi 3!");
						employee.assignTeam(conn);
						break;
					case 4:
						System.out.println("Per assegnare i dipendenti al team premi 4!");
						employee.getAssignedEmployees(conn);
						break;
					
					}
			case 3:
				System.out.println("Lista di azioni da svolgere su developer");
				int scelta3 = scan.nextInt();
				scan.nextLine();
				switch(scelta3) {
					case 1:
						System.out.println("Per vedere la lista dei developer premi 1!");
						
										developer.readAllDevelopers(conn);
						break;
					case 2:
						System.out.println("Per aggiungere un linguaggio conosciuto ad un developer premi 2!");
						int scelta2 = scan.nextInt();
						scan.nextLine();
						developer.addLanguage();
						break;
					case 3:
						System.out.println("Per vedere tutti i tipi di linguaggi conosciuti premi 3!");
						int scelta3 = scan.nextInt();
						scan.nextLine();
						developer.getAllLanguages();
					case 4:
						System.out.println("Per vedere la lista dei linguaggi che si possono conoscere premi 4!");
						int scelta4 = scan.nextInt();
						scan.nextLine();
						developer.readAllLanguages();										
				}
			case 4:
				do {
					System.out.println("Per uscire dal programma premere 4!");
					int scelta1 = scan.nextInt();
					scan.nextLine();
					System.exit(0);
				
		
		
		
		
		
				DBmanager.closeConnection();
		
		
//		Deve essere possibile aggiungere, modificare, eliminare dipendenti, assegnarli a progetti e calcolare gli stipendi (considerando eventuali bonus).
		
			
		}
	}
}
