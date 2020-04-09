package osoby;

import java.io.*;

public class Predpis implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	String meno, rodnec, text;
	
	public Predpis(String meno, String rodnec, String text) {
		// TODO Auto-generated constructor stub
		this.meno = meno;
		this.rodnec = rodnec;
		this.text = text;
	}
}
