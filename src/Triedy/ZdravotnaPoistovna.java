package Triedy;

import java.util.*;
import java.io.*;

public class ZdravotnaPoistovna implements Serializable{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = 0;
	
	public List<Lekar> lekar = new ArrayList<>();
	public List<Pacient> pacient = new ArrayList<>();
	
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie) {
		lekar.add(new Lekar(meno, adresa, rodnec, pohlavie));
	}
	
	public void vypisLekarov() {
		for (Lekar lekar2 : lekar) {
			System.out.println("Zazmluvneny lekar: " + lekar2.meno);
		}
	}
	

	public void uloz() throws ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("evidencia.out"));
		out.writeObject(this);
		out.close();
	}
	
	public void nacitaj() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("evidencia.out"));
		ZdravotnaPoistovna nacitanaPoistovna = (ZdravotnaPoistovna) in.readObject();
		in.close();
		
		lekar = nacitanaPoistovna.lekar;
		pacient = nacitanaPoistovna.pacient;
	}
	
	// Pomocna metoda na vymazanie suboru s outputom
	public void vymazOutput() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("evidencia.out"));
		in.close();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("evidencia.out"));
		out.close();
	
	}
	
}

