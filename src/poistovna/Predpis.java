package poistovna;

import java.io.*;
import java.util.Random;

import osoby.*;

/**
 * Predpis vydany lekarom. Obsahuje informacie o pacientovi a meno lekara, ktory predpis vydal.
 * @author PeterSmrecek
 */
public class Predpis implements Serializable{
	private static final long serialVersionUID = 0;
	
	String meno, rodnec, text;
	VseobecnyLekar autorLekar;
	
	public Predpis(String meno, String rodnec, String text, VseobecnyLekar lekar) {
		// TODO Auto-generated constructor stub
		this.meno = meno;
		this.rodnec = rodnec;
		this.text = text;
		this.autorLekar = lekar;
	}
	
	/**
	 * Citanie predpisu. Vrati retazec s menom lekara a textom predpisu.
	 * @return meno lekara, ktory vydal predpis a text predpisu
	 */
	public String citaniePredpisu() {
		return ("Predpis lieku: " + this.zistiMenoLekara() + " - " + this.zistiText()); 
	}
	
	/**
	 * Overovanie platnosti predpisu. Nie je implementovane inak, vracia len nahodne true a false
	 * @return nahodne vygenerovany boolean urcujuci, ci je predpis v tomto momente este platny
	 */
	public boolean overPlatnost() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
	 * Getter pre meno pacienta, ktoremu bol predpis vydany.
	 * @return meno pacienta
	 */
	public String zistiMeno() {
		return this.meno;
	}
	
	/**
	 * Getter pre rodne cislo pacienta, ktoremu bol predpis vydany
	 * @return rodne cislo pacienta
	 */
	public String zistiRodnec() {
		return this.rodnec;
	}
	
	/**
	 * Getter pre text predpisu obsahujuci nazov predpisaneho lieku.
	 * @return text predpisu
	 */
	public String zistiText() {
		return this.text;
	}

	/**
	 * Getter pre meno lekara, ktory predpis vydal.
	 * @return meno lekara
	 */
	public String zistiMenoLekara() {
		return this.autorLekar.zistiMeno();
	}
}
