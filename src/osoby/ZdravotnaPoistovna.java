package osoby;

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
	
	// Pretazenie
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie, String specializacia) {
		lekar.add(new SpecializovanyLekar(meno, adresa, rodnec, pohlavie, specializacia));
	}
	
	
	public void vypisLekarov() {
		for (Lekar lekar2 : lekar) {
			// Kontrola, ktorej triedy je instancia lekar
			if (lekar2 instanceof SpecializovanyLekar) {
				System.out.println("Zazmluvneny lekar: " + lekar2.zistiMeno() +" "+ lekar2.zistiAdresu() +" "
						+ lekar2.zistiRodneCislo() +" "+ lekar2.zistiPohlavie() + " " + ((SpecializovanyLekar) lekar2).specializacia);
			}
			else {
				System.out.println("Zazmluvneny lekar: " + lekar2.zistiMeno() +" "+ lekar2.zistiAdresu() +" "
						+ lekar2.zistiRodneCislo() +" "+ lekar2.zistiPohlavie());
			}
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

