package poistovna;

import osoby.VseobecnyLekar;

/**
 * Specialny typ predpisu je zdravotnicka pomocka, ktoru moze vydat len specializovany lekar. Lisi sa tym, ze okrem  textu predpisu 
 * ma aj informacie o type pomocky. Text predpisu specifikuje zdravotnicku pomocku. Napriklad v texte sa nachadza "+2D na obe oci"
 * a typ pomocky su okuliare.
 * @author PeterSmrecek
 */
public class ZdravotnickaPomocka extends Predpis {
	private static final long serialVersionUID = 0;
	
	private String typPomocky;
	
	public ZdravotnickaPomocka(String meno, String rodnec, String text, VseobecnyLekar lekar, String typPomocky) {
		super(meno, rodnec, text, lekar);
		// TODO Auto-generated constructor stub
		this.typPomocky = typPomocky;
	}
	
	/**
	 * Getter pre typ zdravotnickej pomocky.
	 * @return typ pomocky
	 */
	public String zistiTyp() {
		return this.typPomocky;
	}
	
	/**
	 * Metoda na citanie predpisu zdravotnickej pomocky. Pri zdravotnickej pomocke sa cita aj jej typ nerozdiel od predpisu.
	 * @return meno lekara, ktory predpisal pomocku, typ pomocky a text predpisu pomocky v retazci
	 */
	@Override
	public String citaniePredpisu() {
		return ("Zdravotnicka pomocka: " + this.zistiMenoLekara() + " - " + this.zistiTyp() + " - " + this.zistiText()); 
	}
}
