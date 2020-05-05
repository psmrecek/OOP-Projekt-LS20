package osoby;

import java.io.*;
import java.util.ArrayList;

import javafx.scene.control.ListView;
import poistovna.*;

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
	
	public void citajPredpisy(ListView<String> predpisyLW) {
		predpisyLW.getItems().clear();
		for (Predpis predpis : this.predpisy) {
			predpisyLW.getItems().add(predpis.citaniePredpisu());
		}
	}
	
	public void citajListky(ListView<String> vymenneListky) {
		vymenneListky.getItems().clear();
		for (Listok listok : this.vymenneListky) {
			vymenneListky.getItems().add("Vymenny listok k " + listok.zistiText()+"-ovi");
		}
	}
	
	public ArrayList<Predpis> vratPredpisy() {
		return this.predpisy;
	}
	
	public ArrayList<Listok> vratListky() {
		return this.vymenneListky;
	}
	
	public void pridajListok(Listok listok) {
		this.vymenneListky.add(listok);
	}
	
	public void pridajPredpis(Predpis predpis) {
		this.predpisy.add(predpis);
	}
	
	public void odstranListok(Listok listok) {
		this.vymenneListky.remove(listok);
	}
	
	public void odstranPredpis(Predpis predpis) {
		this.predpisy.remove(predpis);
	}
	
	// Pretazenie
	public void odstranPredpis(int index) {
		this.predpisy.remove(index);
	}
	
	public void registrovanyPacientVseobecnymLekarom(boolean b) {
		this.uzMaVseobecnehoLekara = b;
	}
	
	public boolean skontrolujVseobecnehoLekara() {
		return this.uzMaVseobecnehoLekara;
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
		priudaje = new PrihlasovacieUdaje(nick, heslo);
	}
	
	@Override
	public String zistiMeno() {
		// TODO Auto-generated method stub
		return this.osudaje.meno;
	}

	@Override
	public String zistiAdresu() {
		// TODO Auto-generated method stub
		return this.osudaje.adresa;
	}

	@Override
	public String zistiRodneCislo() {
		// TODO Auto-generated method stub
		return this.osudaje.rodnec;
	}

	@Override
	public char zistiPohlavie() {
		// TODO Auto-generated method stub
		return this.osudaje.pohlavie;
	}
	
}
