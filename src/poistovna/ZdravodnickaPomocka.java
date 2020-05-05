package poistovna;

import osoby.Lekar;

public class ZdravodnickaPomocka extends Predpis {
	private static final long serialVersionUID = 0;
	
	private String typPomocky;
	
	public ZdravodnickaPomocka(String meno, String rodnec, String text, Lekar lekar, String typPomocky) {
		super(meno, rodnec, text, lekar);
		// TODO Auto-generated constructor stub
		this.typPomocky = typPomocky;
	}
	
	public String zistiTyp() {
		return this.typPomocky;
	}
}
