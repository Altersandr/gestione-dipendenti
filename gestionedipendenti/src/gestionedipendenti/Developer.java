package gestionedipendenti;

import java.util.ArrayList;

public class Developer extends Employee{
	ArrayList<String> linguaggiConosciuti;
	ArrayList<String> progettiAssegnati;
	
	
	public Developer(ArrayList<String> linguaggiConosciuti, ArrayList<String> progettiAssegnati, int id, String nome, String cognome, double stipendioBase) {
		
		super(id, nome, cognome, stipendioBase);
		this.linguaggiConosciuti = linguaggiConosciuti;
		this.progettiAssegnati = progettiAssegnati;
	}


}
