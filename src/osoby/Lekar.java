package osoby;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Lekar implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje {
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	
	// Agregacia a enkapsulacia (udaje pristupne len cez metodu)
	protected OsobneUdaje osudaje;
	protected PrihlasovacieUdaje priudaje;
	
	// List pacientov v lekarovej evidencii
	protected List<Pacient> lekaroviPacienti = new ArrayList<>();
	
	public void evidujPacienta(Pacient pacient) {
		this.lekaroviPacienti.add(pacient);
	}
	
	public void vypisPacientov() {
		for (Pacient pacient2 : this.lekaroviPacienti) {
			System.out.println("Evidovany pacient lekara '" + this.osudaje.meno + "': " + pacient2.zistiMeno() +" "
								+ pacient2.zistiAdresu() +" "+ pacient2.zistiRodneCislo() +" "+ pacient2.zistiPohlavie());
		}
	}
	
	public Lekar(String meno, String adresa, String rodnec, char pohlavie) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
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
