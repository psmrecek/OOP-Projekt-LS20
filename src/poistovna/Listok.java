package poistovna;

import java.io.Serializable;

import osoby.*;

public class Listok extends Predpis implements Serializable{
	private static final long serialVersionUID = 0;
	
//	private String cielovaSpecializacia;
	
	public Listok(String meno, String rodnec, String text, Lekar lekar) {
		super(meno, rodnec, text, lekar);
		// TODO Auto-generated constructor stub
	}

	public String zistiSpecializaciu() {
		return this.text;
	}
	
	
}
