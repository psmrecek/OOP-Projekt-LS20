package osoby;

import java.io.*;

public class Pacient implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	
	// Agregacia a enkapsulacia (udaje pristupne len cez metodu)
	private OsobneUdaje osudaje;
	private PrihlasovacieUdaje priudaje;
	public boolean vymennyListok = false;
	
	public Pacient(String meno, String adresa, String rodnec, char pohlavie, boolean listok) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		this.vymennyListok = listok;
	}
	
	// Pretazenie metody, no len docasne riesenie na otestovanie programu
	public Pacient(String meno, String adresa, String rodnec, char pohlavie) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
	}
	
	public void pridajVymennyListok(boolean b) {
		this.vymennyListok = b;
	}

	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.nick;
	}

	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.nick;
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
