package osoby;

public class SpecializovanyLekar extends Lekar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;
	// Pouzite dedenie
	
	public String specializacia;
	
	public SpecializovanyLekar(String meno, String adresa, String rodnec, char pohlavie, String specializacia) {
		super(meno, adresa, rodnec, pohlavie);
		// TODO Auto-generated constructor stub
		this.specializacia = specializacia;
	}
	
	// Prekonanie metody
	@Override
	public void vypisPacientov() {
		for (Pacient pacient2 : this.lekaroviPacienti) {
			System.out.println("Evidovany pacient lekara '" + this.osudaje.meno + "' - "+ this.specializacia +": " + pacient2.zistiMeno() +" "
								+ pacient2.zistiAdresu() +" "+ pacient2.zistiRodneCislo() +" "+ pacient2.zistiPohlavie());
		}
	}
	
	// Prekonanie metody
	@Override
	public void evidujPacienta(Pacient pacient) {
		if(pacient.vymennyListok)
			this.lekaroviPacienti.add(pacient);
		else
			System.out.println("Pacient nema vymenny listok: " + pacient.zistiMeno() +" "
								+ pacient.zistiAdresu() +" "+ pacient.zistiRodneCislo() +" "+ pacient.zistiPohlavie());
	}
}
