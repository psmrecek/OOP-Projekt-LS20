package poistovna;

import java.util.*;
import osoby.*;

import java.io.*;

/**
 * Hlavna trieda programu. Poistovna agreguje objekty pacientov, lekarov aj lekarnikov, pricom vseobecnych lekarov a specializovanych lekarov
 * drzi v jednom spolocnom zozname. Trieda zabezpecujuca prihlasovanie a evidovanie lekarov, pacientov a lekarnikov.
 * @author PeterSmrecek
 *
 */
public class ZdravotnaPoistovna implements Serializable, ZistiPrihlasovacieUdaje{
	private static final long serialVersionUID = 0;
	
	private List<Lekarnik> lekarnici = new ArrayList<>();
	private List<VseobecnyLekar> lekari = new ArrayList<>();
	private List<Pacient> pacienti = new ArrayList<>();
	
	transient private List<SledovatelLekarov> sledovatelia = new ArrayList<>();
	
	private PrihlasovacieUdaje priudaje;
	
	public ZdravotnaPoistovna() {
		this.priudaje = new PrihlasovacieUdaje("admin", "123");
	}
	
	/**
	 * Pridava sledovatelov lekarov
	 * @param sledovatelLekarov
	 */
	public void pridajSledovatela(SledovatelLekarov sledovatelLekarov) {
		sledovatelia.add(sledovatelLekarov);
	}
	
	/**
	 * Upovedomi vsetkych sledovatelov lekarov v zozname.
	 */
	public void upovedomSledovatelov() {
		for (SledovatelLekarov s : sledovatelia)
			s.upovedom();
	}
	
	/**
	 * Overuje prihlasovacie udaje krajskeho manazera poistovne.
	 * @param nick		prihlasovacie meno
	 * @param heslo		prihlasovacie heslo
	 * @return true, ak sa prihlasovanie podarilo
	 * @throws NenajdenyUzivatelException ak sa prihlasovacie udaje nezhoduju s prihlasovacimi udajmi manazera poistovne
	 */
	public boolean autentifikaciaPoistovne(String nick, String heslo) throws NenajdenyUzivatelException {
		if (nick.equals(priudaje.zistiNick())) {
			if (heslo.equals(priudaje.zistiHeslo())) {
				return true;
			}
		}
		throw new NenajdenyUzivatelException();
	}
	
	/**
	 * Overuje prihlasovacie udaje lekara pokusajuceho sa prihlasit. Prejde cely zoznam evidovanych lekakarov.
	 * @param nick		prihlasovacie meno
	 * @param heslo		prihlasovacie heslo
	 * @return lekar, ktory sa prave prihlasil
	 * @throws NenajdenyUzivatelException ak sa prihlasovacie udaje nezhoduju s prihlasovacimi udajmi ziadneho lekara
	 */
	public VseobecnyLekar autentifikaciaLekara(String nick, String heslo) throws NenajdenyUzivatelException {
		for (VseobecnyLekar lekar : lekari) {
			if (lekar.zistiNick().equals(nick)) {
				if (lekar.zistiHeslo().equals(heslo)) {
				return lekar;
				}
			}
		}
		throw new NenajdenyUzivatelException();
	}
	
	/**
	 * Overuje prihlasovacie udaje pacienta pokusajuceho sa prihlasit. Prejde cely zoznam evidovanych pacientov.
	 * @param nick		prihlasovacie meno
	 * @param heslo		prihlasovacie heslo
	 * @return pacient, ktory sa prave prihlasil
	 * @throws NenajdenyUzivatelException ak sa prihlasovacie udaje nezhoduju s prihlasovacimi udajmi ziadneho pacienta
	 */
	public Pacient autentifikaciaPacienta(String nick, String heslo) throws NenajdenyUzivatelException {
		for (Pacient pacient : pacienti) {
			if (pacient.zistiNick().equals(nick)) {
				if (pacient.zistiHeslo().equals(heslo)) {
				return pacient;
				}
			}
		}
		throw new NenajdenyUzivatelException();
	}
	
	/**
	 * Overuje prihlasovacie udaje lekarnika pokusajuceho sa prihlasit. Prejde cely zoznam evidovanych lekarnikov.
	 * @param nick			prihlasovacie meno
	 * @param heslo			prihlasovacie heslo
	 * @return lekarnik, 	ktory sa prave prihlasil
	 * @throws NenajdenyUzivatelException ak sa prihlasovacie udaje nezhoduju s prihlasovacimi udajmi ziadneho lekarnika
	 */
	public Lekarnik autentifikaciaLekarnika(String nick, String heslo) throws NenajdenyUzivatelException {
		for (Lekarnik lekarnik : lekarnici) {
			if (lekarnik.zistiNick().equals(nick)) {
				if (lekarnik.zistiHeslo().equals(heslo)) {
				return lekarnik;
				}
			}
		}
		throw new NenajdenyUzivatelException();
	}
	
	/**
	 * Na zaklade parametrom zadanej hodnoty boolean rozhoduje, ci bude do evidencie lekara pridany novy vseobecny lekar alebo novy specializovany lekar.
	 * @param b					true, ak ma leka r specializaciu, false ak nema
	 * @param meno				meno a priezvisko lekara
	 * @param adresa			adresa lekara
	 * @param rodnec			rodne cislo lekara
	 * @param pohlavie			pohlavie lekara
	 * @param nick				prihlasovacie meno lekara
	 * @param heslo				prihlasovacie heslo lekara
	 * @param specializacia		pripadna specializacia lekara
	 */
	public void urciTypLekara(boolean b, String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo, String specializacia) {
		if (b) {
			this.evidujLekara(meno, adresa, rodnec, pohlavie, nick, heslo, specializacia);
		} else {
			this.evidujLekara(meno, adresa, rodnec, pohlavie, nick, heslo);
		}
	}
	
	/**
	 * Zaraduje do zoznamu lekarov evidovanych poistovnou novy objekt vseobecneho lekara.
	 * @param meno				meno a priezvisko lekara
	 * @param adresa			adresa lekara
	 * @param rodnec			rodne cislo lekara
	 * @param pohlavie			pohlavie lekara
	 * @param nick				prihlasovacie meno lekara
	 * @param heslo				prihlasovacie heslo lekara
	 */
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		lekari.add(new VseobecnyLekar(meno, adresa, rodnec, pohlavie, nick, heslo));
		upovedomSledovatelov();
	}
	
	/**
	 * Zaraduje do zoznamu lekarov evidovanych poistovnou novy objekt specializovaneho lekara.
	 * @param meno				meno a priezvisko lekara
	 * @param adresa			adresa lekara
	 * @param rodnec			rodne cislo lekara
	 * @param pohlavie			pohlavie lekara
	 * @param nick				prihlasovacie meno lekara
	 * @param heslo				prihlasovacie heslo lekara
	 * @param specializacia		specializacia lekara
	 */
	// Pretazenie
	public void evidujLekara(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo, String specializacia) {
		lekari.add(new SpecializovanyLekar(meno, adresa, rodnec, pohlavie, nick, heslo, specializacia));
		upovedomSledovatelov();
	}
	
	/**
	 * Zaraduje do zoznamu lekarnikov evidovanych poistovnou novy objekt lekarnika.
	 * @param meno				meno a priezvisko lekarnika
	 * @param adresa			adresa lekarnika
	 * @param rodnec			rodne cislo lekarnika
	 * @param pohlavie			pohlavie lekarnika
	 * @param nick				prihlasovacie meno lekarnika
	 * @param heslo				prihlasovacie heslo lekarnika
	 */
	public void evidujLekarnika(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		lekarnici.add(new Lekarnik(meno, adresa, rodnec, pohlavie, nick, heslo));
		System.out.println("Lekarnik evidovany");
	}
	
	/**
	 * Zaraduje do zoznamu pacientov evidovanych poistovnou novy objekt pacienta.
	 * @param meno				meno a priezvisko pacienta
	 * @param adresa			adresa pacienta
	 * @param rodnec			rodne cislo pacienta
	 * @param pohlavie			pohlavie pacienta
	 * @param nick				prihlasovacie meno pacienta
	 * @param heslo				prihlasovacie heslo pacienta
	 */
	public void evidujPacienta(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		pacienti.add(new Pacient(meno, adresa, rodnec, pohlavie, nick, heslo));
	}
	
	/**
	 * Upovedomi sledovatelov lekarov, ked su lekari vypisovani
	 */
	public void vypisLekarov() {
		upovedomSledovatelov();
	}
	
	/**
	 * Vrati vybraneho lekara zo zoznamu
	 * @param n		poradove cislo lekara v zozname
	 * @return		lekar ak je lekar najdeny, inak null
	 */
	public VseobecnyLekar vratLekara(int n) {
		try {
			return lekari.get(n);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Takyto lekar v evidencii poistovne neexistuje.");
		}
		return null;
	}
	
	/**
	 * Vrati pacienta podla mena. Prehlada cely zoznam pacientov a vrati prveho s danym menom. Program nepodporuje moznost 2 osob rovnakeho mena.
	 * @param meno		meno pacienta
	 * @return			pacient ak je pacient najdeny, inak null
	 */
	public Pacient najdiPacienta(String meno) {
		for (Pacient pacient : pacienti) {
			if (pacient.zistiMeno().equals(meno)) {
				return pacient;
			}
		}
		return null;
	}
	
	/**
	 * Vrati zoznam pacientov evidovanych zdravotnou poistovnou.
	 * @return zoznam pacientov
	 */
	public List<Pacient> vratZoznamPacientov() {
		return this.pacienti;
	}
	
	/**
	 * Vrati zoznam lekarov evidovanych zdravotnou poistovnou.
	 * @return zoznam lekarov
	 */
	public List<VseobecnyLekar> vratZoznamLekarov(){
		return this.lekari;
	}

	/**
	 * Serializacia objektu poistovne do vypisu.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void uloz() throws ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("evidencia.out"));
		out.writeObject(this);
		out.close();
	}
	
	/**
	 * Nacitanie serializovanych zaznamov.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void nacitaj() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("evidencia.out"));
		ZdravotnaPoistovna nacitanaPoistovna = (ZdravotnaPoistovna) in.readObject();
		in.close();
		
		lekari = nacitanaPoistovna.lekari;
		pacienti = nacitanaPoistovna.pacienti;
		lekarnici = nacitanaPoistovna.lekarnici;
	}
	
	/**
	 * Pomocna metoda na vymazanie suboru s outputom.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void vymazOutput() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("evidencia.out"));
		in.close();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("evidencia.out"));
		out.close();
	}
	
	/**
	 * {@inheritDoc}
	 * Pre manazera positovne.
	 */
	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiNick();
	}

	/**
	 * {@inheritDoc}
	 * Pre manazera poistovne.
	 */
	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiHeslo();
	}

	/**
	 * {@inheritDoc}
	 * Pre manazera poistovne.
	 */
	@Override
	public void nastavPrihlasovacieUdaje(String nick, String heslo) {
		// TODO Auto-generated method stub
		
	}
	
}

