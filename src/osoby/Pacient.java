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
	public Predpis predpis;
	public boolean vymennyListok = false;
	
	
	public String citajPredpis() {
		return this.predpis.meno +" ma evidovany predpis s textom: "+ this.predpis.text;
	}
	
	public Pacient(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		nastavPrihlasovacieUdaje(nick, heslo);
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
		return this.priudaje.heslo;
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
