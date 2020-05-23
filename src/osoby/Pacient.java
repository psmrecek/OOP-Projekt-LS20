package osoby;

import java.io.*;
import java.util.ArrayList;

import javafx.scene.control.ListView;
import poistovna.*;

/**
 * Pacient si dokaze vybrat a zacat navstevovat lekara zo zoznamu lekarov, ktory dokaze vypisat. Moze navstevovat vzdy iba jedneho vsobecneho lekara.
 * Navstivit specializovaneho lekara moze iba ak ma k nemu vymenny listok. Dokaze vypisat svoje predpisy a vymenne listky.
 * @author PeterSmrecek
 *
 */
public class Pacient implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje{
	private static final long serialVersionUID = 0;
	
	private OsobneUdaje osudaje;
	private PrihlasovacieUdaje priudaje;
	private ArrayList<Predpis> predpisy = new ArrayList<>();
	private ArrayList<Listok> vymenneListky = new ArrayList<>();
	private boolean uzMaVseobecnehoLekara = false;
	
	public Pacient(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		nastavPrihlasovacieUdaje(nick, heslo);
	}
	
	/**
	 * Vypise zoznam predpisov do daneho prvku. 
	 * Vypis pre potreby plynuleho behu programu prebieha v samostatnej niti vytvorenej vo vnorenej triede.
	 * @param predpisyLW		prvok, do ktoreho budu predpisy vypisane
	 */
	public void citajPredpisy(ListView<String> predpisyLW) {
		Pacient pacient = this;
		class NovaNit extends Thread{											// Vnorena trieda
			
		    public void run() 
		    { 
				predpisyLW.getItems().clear();
				for (Predpis predpis : pacient.predpisy) {
					predpisyLW.getItems().add(predpis.citaniePredpisu());
				}
		    }
		    
		}
		NovaNit nit = new NovaNit();
		nit.start();
	}
	
	/**
	 * Vypise zoznam vymennych listkov do daneho prvku. 
	 * @param vymenneListky			prvok, do ktoreho budu vymenne listky vypisane
	 */
	public void citajListky(ListView<String> vymenneListky) {
		vymenneListky.getItems().clear();
		for (Listok listok : this.vymenneListky) {
			vymenneListky.getItems().add("Vymenny listok k " + listok.zistiText()+"-ovi");
		}
	}
	
	/**
	 * Getter pre zoznam predpisov konkretneho pacienta.
	 * @return zoznam predpisov
	 */
	public ArrayList<Predpis> vratPredpisy() {
		return this.predpisy;
	}
	
	/**
	 * Getter pre zoznam vymennych listkov konkretneho pacienta.
	 * @return zoznam vymennych listkov
	 */
	public ArrayList<Listok> vratListky() {
		return this.vymenneListky;
	}
	
	/**
	 * Setter pre pridanie vymenneho listku vydaneho lekarom do zoznamu vymennych listkov pacienta
	 * @param listok		vymenny listok vytvoreny lekarom
	 */
	public void pridajListok(Listok listok) {
		this.vymenneListky.add(listok);
	}
	
	/**
	 * Setter pre pridanie predpisu vydaneho lekarom do zoznamu predpisov pacienta.
	 * @param 				predpis vytvoreny lekarom
	 */
	public void pridajPredpis(Predpis predpis) {
		this.predpisy.add(predpis);
	}
	
	/**
	 * Metoda na odstranenie vymenneho listka zo zoznamu vymennych listkov pacienta.
	 * @param listok		vymenny listok na odstranenie
	 */
	public void odstranListok(Listok listok) {
		this.vymenneListky.remove(listok);
	}
	
	
	/**
	 * Metoda na odstranenie predpisu zo zoznamu predpisov pacienta.
	 * @param predpis		predpis na odstranenie
	 */
	public void odstranPredpis(Predpis predpis) {
		this.predpisy.remove(predpis);
	}
	
	/**
	 * Metoda na odstranenie predpisu zo zoznamu predpisov pacienta.
	 * @param index			Index - poradove cislo predpisu na odstranenie
	 */
	// Pretazenie
	public void odstranPredpis(int index) {
		this.predpisy.remove(index);
	}
	
	/**
	 * Setter na nastavenie hodnoty boolean urcujucej, ci uz pacient ma vseobecneho lekara.
	 * @param b				hodnota urcujuca, ci uz lekara ma, alebo nie
	 */
	public void registrovanyPacientVseobecnymLekarom(boolean b) {
		this.uzMaVseobecnehoLekara = b;
	}
	
	/**
	 * Getter na zistenie, ci je dany pacient uz evidovany u nejakeho vseobecneho lekara.
	 * @return	boolean hodnota true, ak uz ma vseobecneho lekara, false ak nie
	 */
	public boolean skontrolujVseobecnehoLekara() {
		return this.uzMaVseobecnehoLekara;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiNick();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiHeslo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nastavPrihlasovacieUdaje(String nick, String heslo) {
		// TODO Auto-generated method stub
		priudaje = new PrihlasovacieUdaje(nick, heslo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiMeno() {
		// TODO Auto-generated method stub
		return this.osudaje.meno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiAdresu() {
		// TODO Auto-generated method stub
		return this.osudaje.adresa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiRodneCislo() {
		// TODO Auto-generated method stub
		return this.osudaje.rodnec;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public char zistiPohlavie() {
		// TODO Auto-generated method stub
		return this.osudaje.pohlavie;
	}
	
}
