package osoby;

import java.io.*;

public class OsobneUdaje implements Serializable{
	private static final long serialVersionUID = 0;
	
	String meno, adresa, rodnec;
	char pohlavie;
	
	public OsobneUdaje(String meno, String adresa, String rodnec, char pohlavie) {
		this.meno = meno;
		this.adresa = adresa;
		this.rodnec = rodnec;
		this.pohlavie = pohlavie;
	}
	
}
