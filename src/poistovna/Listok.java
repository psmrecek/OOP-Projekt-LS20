package poistovna;

import osoby.*;

/**
 * Cita vymenny listok
 * @author PeterSmrecek
 */
public class Listok extends Predpis {
	private static final long serialVersionUID = 0;
	
	public Listok(String meno, String rodnec, String text, VseobecnyLekar lekar) {
		super(meno, rodnec, text, lekar);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Getter pre text specializacie uvedenej na vymennom listku
     * @return text vymenneho listku
     */
	public String zistiSpecializaciu() {
		return this.text;
	}
}
