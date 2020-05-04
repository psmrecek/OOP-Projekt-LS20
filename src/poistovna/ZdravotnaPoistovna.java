package poistovna;

import java.util.*;
import osoby.*;

import java.io.*;

public class ZdravotnaPoistovna implements Serializable, ZistiPrihlasovacieUdaje{
	private static final long serialVersionUID = 0;
	
	private List<Lekarnik> lekarnici = new ArrayList<>();
	private List<Lekar> lekari = new ArrayList<>();
	private List<Pacient> pacienti = new ArrayList<>();
	
	
	transient private List<SledovatelLekarov> sledovatelia = new ArrayList<>();
	
	public void pridajSledovatela(SledovatelLekarov sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	public void upovedomSledovatelov() {
		for (SledovatelLekarov s : sledovatelia)
			s.upovedom();
	}
	
	private PrihlasovacieUdaje priudaje = new PrihlasovacieUdaje("admin", "123");
	
	public boolean autentifikaciaPoistovne(String nick, String heslo) {
		if (nick.equals(priudaje.zistiNick())) {
			if (heslo.equals(priudaje.zistiHeslo())) {
				return true;
			}
		}
		return false;
	}
	
	public Lekar autentifikaciaLekara(String nick, String heslo) {
		for (Lekar lekar : lekari) {
			if (lekar.zistiNick().equals(nick)) {
				if (lekar.zistiHeslo().equals(heslo)) {
				return lekar;
				}
			}
		}
		return null;
	}
	
	public Pacient autentifikaciaPacienta(String nick, String heslo) {
		for (Pacient pacient : pacienti) {
			if (pacient.zistiNick().equals(nick)) {
				if (pacient.zistiHeslo().equals(heslo)) {
				return pacient;
				}
			}
		}
		return null;
	}
	
	public Lekarnik autentifikaciaLekarnika(String nick, String heslo) {
		for (Lekarnik lekarnik : lekarnici) {
			System.out.println(lekarnik.zistiMeno() +" " + lekarnik.zistiHeslo());
			if (lekarnik.zistiNick().equals(nick)) {
				if (lekarnik.zistiHeslo().equals(heslo)) {
				return lekarnik;
				}
			}
		}
		return null;
	}
	
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		lekari.add(new Lekar(meno, adresa, rodnec, pohlavie, nick, heslo));
		System.out.println("Bezny lekar evidovany");
		upovedomSledovatelov();
	}
	
	// Pretazenie
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo, String specializacia) {
		lekari.add(new SpecializovanyLekar(meno, adresa, rodnec, pohlavie, nick, heslo, specializacia));
		System.out.println("Specializovany lekar evidovany");
		upovedomSledovatelov();
	}
	
	public void evidujLekarnika(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		lekarnici.add(new Lekarnik(meno, adresa, rodnec, pohlavie, nick, heslo));
		System.out.println("Lekarnik evidovany");
	}
	
	
	public void vypisLekarov() {
//		for (Lekar lekar2 : lekari) {
//			// Kontrola, ktorej triedy je instancia lekar
//			if (lekar2 instanceof SpecializovanyLekar) {
//				System.out.println("Zazmluvneny lekar: " + lekar2.zistiMeno() +" "+ lekar2.zistiAdresu() +" "
//						+ lekar2.zistiRodneCislo() +" "+ lekar2.zistiPohlavie() + " " + ((SpecializovanyLekar) lekar2).specializacia + " " + 
//						lekar2.zistiNick() + " " + lekar2.zistiHeslo());
//			}
//			else {
//				System.out.println("Zazmluvneny lekar: " + lekar2.zistiMeno() +" "+ lekar2.zistiAdresu() +" "
//						+ lekar2.zistiRodneCislo() +" "+ lekar2.zistiPohlavie()+ " " + 
//								lekar2.zistiNick() + " " + lekar2.zistiHeslo());
//			}
//		}
		upovedomSledovatelov();
	}
	
	public Lekar vratLekara(int n) {
		try {
			return lekari.get(n);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Takyto lekar v evidencii poistovne neexistuje.");
		}
		return null;
	}
	
	public void evidujPacienta(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		pacienti.add(new Pacient(meno, adresa, rodnec, pohlavie, nick, heslo));
		System.out.println("Pacient zaevidovany");
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
		
		lekari = nacitanaPoistovna.lekari;
		pacienti = nacitanaPoistovna.pacienti;
		lekarnici = nacitanaPoistovna.lekarnici;
	}
	
	// Pomocna metoda na vymazanie suboru s outputom
	public void vymazOutput() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("evidencia.out"));
		in.close();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("evidencia.out"));
		out.close();
	}
	
	public List<Pacient> vratZoznamPacientov() {
		return this.pacienti;
	}
	
	public List<Lekar> vratZoznamLekarov(){
		return this.lekari;
	}
	
	public Pacient najdiPacienta(String meno) {
		for (Pacient pacient : pacienti) {
			if (pacient.zistiMeno().equals(meno)) {
				return pacient;
			}
		}
		return null;
	}


	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiNick();
	}


	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiHeslo();
	}


	@Override
	public void nastavPrihlasovacieUdaje(String nick, String heslo) {
		// TODO Auto-generated method stub
		
	}
	
}

