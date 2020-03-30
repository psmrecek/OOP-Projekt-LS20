package Triedy;

import java.io.*;

public class Pacient implements Udaje, Serializable, PrihlasovacieUdaje{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	String meno;
	String adresa;
	String rodnec;
	char pohlavie;
	String nick;
	String heslo;
	
	@Override
	public void nastavMeno(String meno) {
		// TODO Auto-generated method stub
		this.meno = meno;
	}

	@Override
	public void nastavAdresu(String adresa) {
		// TODO Auto-generated method stub
		this.adresa = adresa;
	}

	@Override
	public void nastavRodneCislo(String rodnec) {
		// TODO Auto-generated method stub
		this.rodnec = rodnec;
	}

	@Override
	public void nastavPohlavie(char pohlavie) {
		// TODO Auto-generated method stub
		this.pohlavie = pohlavie;
	}
	
	public Pacient(String meno, String adresa, String rodnec, char pohlavie) {
		this.nastavMeno(meno);
		this.nastavAdresu(adresa);
		this.nastavRodneCislo(rodnec);
		this.nastavPohlavie(pohlavie);
	}

	@Override
	public void nastavNick(String nick) {
		// TODO Auto-generated method stub
		this.nick = nick;
	}

	@Override
	public void nastavHeslo(String heslo) {
		// TODO Auto-generated method stub
		this.heslo = heslo;
	}

	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.nick;
	}

	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.heslo;
	}
	
}
