package gestionedipendenti;

import java.sql.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		DBmanager DBmanager = new DBmanager();
		Connection conn = DBmanager.openConnection();
		Scanner scan = new Scanner(System.in);

		boolean continua = true;
		boolean continuaAction = true;
		int scelta = 0;
		int scelta1 = 0;
		while (continua) {

			boolean valid = false;

			// Do while che si ripete fin che non si inserisce un input valido
			do {
				System.out.println("Che azione vuoi eseguire? " + "\n1. Azione su dipendente "
						+ "\n2. Azione su manager" + "\n3. Azione su developer" + "\n4. Uscire dal programma");
				if (scan.hasNextInt()) {
					scelta = scan.nextInt();
					if (scelta < 1 || scelta > 4) {
						scan.nextLine();
						System.out.println("Errore input, inserire un input valido");
						valid = false;
					} else {
						scan.nextLine();
						valid = true;
					}
				} else {
					scan.nextLine();
					System.out.println("Errore input, inserire un input valido");
					valid = false;
				}
			} while (!valid);
			continuaAction = true;
			continua = scelta == 4 ? false: true;

			switch (scelta) {
			case 1:

				while (continuaAction) {
					// Do while che si ripete fin che non si inserisce un input valido
					do {
						System.out.println("1. Per aggiungere un dipendente\n"
								+ "2. Per aggiornare lo stipendio base di un dipendente\n"
								+ "3. Per aggionare il ruolo di un dipendente \n"
								+ "4. Per vedere la lista dei dipendenti \n" 
								+ "5. Per eliminare un dipendente\n"
								+ "0. Per tornare indietro");
						if (scan.hasNextInt()) {
							scelta1 = scan.nextInt();
							scan.nextLine();
							continuaAction = false;

						} else {
							scan.nextLine();
							System.out.println("Errore input, scegliere un azione valida");
						}
					} while (continuaAction);
					
					continuaAction = true;
			
					switch (scelta1) {
					
					// I vari casi e azioni dal menu del dipendente
					case 1:
						Employee.insertEmployee(conn, scan);
						break;
					case 2:
						Employee.updateEmployeeSalary(conn, scan);
						break;
					case 3:
						Employee.updateEmployeeRole(conn, scan);
						break;
					case 4:
						Employee.readAllEmployees(conn, scan);
						break;
					case 5:
						Employee.deleteEmployee(conn, scan);
						break;
					case 0:
						continua = true;
						continuaAction = false;
						break;

					}
				}

			case 2:

				while (continuaAction) {
					
					// Do while che si ripete fin che non si inserisce un input valido
					
					do {
						System.out.println("1. Per vedere elenco team \n" + "2. Per vedere elenco dipendenti assegnati \n"
								+ "3. Per vedere elenco progetti \n" + "4. Per aggiungere un team \n"
								+ "5. Per assegnare un dipendente al team \n" + "6. Per rimuovere un dipendente dal team \n"
								+ "7. Per aggiungere un progetto \n" + "8. Per rimuovere un progetto \n"
								+ "9. Per assegnare progetto a un team \n" + "10. Per assegnare manager a un team \n" + "0. Per tornare indietro \n");
						if (scan.hasNextInt()) {
							scelta1 = scan.nextInt();
							scan.nextLine();
							continuaAction = false;

						} else {
							scan.nextLine();
							System.out.println("Errore input, scegliere un azione valida");
						}
					} while (continuaAction);
					
					continuaAction = true;
					
					switch (scelta1) {
					
					// I vari casi e azioni dal menu del manager
					case 1:
						Manager.getTeams(conn);
						;
						break;
					case 2:
						Manager.getAssignedEmployees(conn);
						;
						break;
					case 3:
						Manager.readAllProjects(conn);
						;
						break;
					case 4:
						Manager.insertTeam(conn, scan);
						;
						break;
					case 5:
						Manager.assignTeam(conn, scan);
						;
						break;
					case 6:
						Manager.removeFromTeam(conn, scan);
						break;
					case 7:
						Manager.addProject(conn, scan);
						break;
					case 8:
						Manager.removeProject(conn, scan);
						break;
					case 9:
						Manager.assignTeamToProject(conn, scan);
						break;
					case 10:
						Manager.assignManagerToTeam(conn, scan);
						break;
					case 0:
						continua = true;
						continuaAction = false;
						break;
					default:
						continuaAction = true;
						continue;
					}
				}

			case 3:
					while (continuaAction) {
						
						// Do while che si ripete fin che non si inserisce un input valido
						do {
							System.out.println("1. Per vedere elenco sviluppatori \n" 
									+ "2. Per la lista dei linguaggi \n"
									+ "3. Per aggiungere linguaggio \n"
									+ "4. Per vedere elenco sviluppatori e i linguaggi che conoscono \n"
									+ "5. Per aggiungere linguaggio a un sviluppatore \n" + "0. Per tornare indietro");
							if (scan.hasNextInt()) {
								scelta1 = scan.nextInt();
								scan.nextLine();
								continuaAction = false;

							} else {
								scan.nextLine();
								System.out.println("Errore input, scegliere un azione valida");
							}
						} while (continuaAction);
						
						continuaAction = true;

						switch (scelta1) {
						
						// I vari casi e azioni dal menu del sviluppatore
						case 1:
							Developer.readAllDevelopers(conn);
							break;
						case 2:
							Developer.getLanguageList(conn);
							break;
						case 3:
							Developer.insertNewLanguage(conn, scan);
							break;
						case 4:
							Developer.getDevAndLang(conn);
							break;
						case 5:
							Developer.learnLanguage(conn, scan);
							break;
						case 0:
							continua = true;
							continuaAction = false;
							break;
						}
					}

			case 4:
				
				// Verifica se si vuole continuare a fare azioni
				if(!continua)break;
				else if(continuaAction) continue;
				else break;
				default: continue;
			}
		}
		System.out.println("Program has been terminated!");
		scan.close();
		DBmanager.closeConnection();
	}
}
