package GestioneDipendentiSQL;
import java.sql.*;
import java.util.ArrayList;
public class developer extends employee {
	ArrayList<String> linguaggiConosciuti;
	ArrayList<String> progettiAssegnati;
	
	public developer(ArrayList<String> linguaggiConosciuti,ArrayList<String> progettiAssegnati,int id, String nome, String cognome, double stipendioBase) {
		super (id, nome, cognome, stipendioBase);
		this.linguaggiConosciuti = linguaggiConosciuti;
		this.progettiAssegnati = progettiAssegnati;
	}
}
