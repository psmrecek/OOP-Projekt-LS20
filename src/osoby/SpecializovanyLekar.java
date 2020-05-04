package osoby;

import java.util.ArrayList;

import poistovna.*;

public class SpecializovanyLekar extends Lekar{
	private static final long serialVersionUID = 0;
	// Pouzite dedenie
	
	public String specializacia;
	
	public SpecializovanyLekar(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo, String specializacia) {
		super(meno, adresa, rodnec, pohlavie, nick, heslo);
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
	public String evidujPacienta(Pacient pacient) {
		ArrayList<Listok> vymenneListky = pacient.vratListky();
		for (Listok listok : vymenneListky) {
			if(listok.zistiSpecializaciu().contentEquals(this.specializacia)) {
				this.lekaroviPacienti.add(pacient);
				pacient.odstranListok(listok);
				return (pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno() + ".\n");
			} else {
				return (pacient.zistiMeno()+" nema vymenny listok k " + this.zistiSpecializaciu() + "-ovi.\n");
			}
		}
		return "Ina chyba";
	}
	
	// Prekonanie metody
	@Override
	public String zistiSpecializaciu() {
		return this.specializacia;
	}
}
