package poistovna;

import osoby.*;

public class Listok extends Predpis {
	private static final long serialVersionUID = 0;
	
	public Listok(String meno, String rodnec, String text, Lekar lekar) {
		super(meno, rodnec, text, lekar);
		// TODO Auto-generated constructor stub
	}

	public String zistiSpecializaciu() {
		return this.text;
	}
}
