package poistovna;

import java.io.*;

import osoby.*;

public class Predpis implements Serializable{
	private static final long serialVersionUID = 0;
	
	String meno, rodnec, text;
	Lekar lekar;
	
	public Predpis(String meno, String rodnec, String text, Lekar lekar) {
		// TODO Auto-generated constructor stub
		this.meno = meno;
		this.rodnec = rodnec;
		this.text = text;
		this.lekar = lekar;
	}
	
	public String zistiMeno() {
		return this.meno;
	}
	
	public String zistiRodnec() {
		return this.rodnec;
	}
	
	public String zistiText() {
		return this.text;
	}
	
	public String zistiMenoLekara() {
		return this.lekar.zistiMeno();
	}
}
