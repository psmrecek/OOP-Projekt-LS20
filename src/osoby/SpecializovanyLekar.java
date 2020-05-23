package osoby;

import java.util.ArrayList;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import poistovna.*;

/**
 * Lekar so specializaciou - specializovany lekar. Dedi strukturu udajov z triedy Lekar, ktory ju zdedil z triedy lekarnik. Dokaze pridat pacienta 
 * do svojej osobnej evidencie. Vydava vymenne listky, vydava predpisy a dokaze vypisat zoznam svojich pacientov. Na rozdiel od vseobecneho lekara dokaze
 * vydat predpis aj na zdravotnicku pomocku.
 * @author PeterSmrecek
 *
 */
public class SpecializovanyLekar extends VseobecnyLekar{
	private static final long serialVersionUID = 0;
	// Pouzite dedenie
	
	public String specializacia;
	
	public SpecializovanyLekar(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo, String specializacia) {
		super(meno, adresa, rodnec, pohlavie, nick, heslo);
		// TODO Auto-generated constructor stub
		this.specializacia = specializacia;
	}
	
	/**
	 * {@inheritDoc}
	 * Pacient sa do evidencie specializovaneho lekara nedostane bez vymenneho listka urceneho pre konkretneho specialistu.
	 */
	// Prekonanie metody, pacient je evidovany iba ak ma vymenny listok pre spravneho specialistu
	@Override
	public String evidujPacienta(Pacient pacient) {
		ArrayList<Listok> vymenneListky = pacient.vratListky();
		for (Listok listok : vymenneListky) {
//			System.out.println(listok.zistiText());
			if(listok.zistiSpecializaciu().contentEquals(this.specializacia)) {
				this.lekaroviPacienti.add(pacient);
				pacient.odstranListok(listok);
				return (pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno() + ".\n");
			} 
		}
		return (pacient.zistiMeno()+" nema vymenny listok k " + this.zistiSpecializaciu() + "-ovi.\n");
	}
	
	/**
	 * Vytvorenie predpisu na zdravotnicku pomocku konkretnemu pacientovi. 
	 * Predpis obsahuje text a informacie o lekarovi a pacientovi, ako aj typ zdravotnickej pomocky.
	 * @param zoznamPacientov		zoznam pacientovv
	 * @param log					prvok, do ktoreho sa vypise sprava o vykonanej akcii
	 * @param textPredpisu			text, ktory bude zahrnuty v predpise
	 * @param typPomocky			typ zdravotnickej pomocky
	 */
	public void vytvorPomocku(ListView<String> zoznamPacientov, TextArea log, TextArea textPredpisu, String typPomocky) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			String text = textPredpisu.getText();
			textPredpisu.clear();
			VseobecnyLekar lekar = this;
			pacient.pridajPredpis(new ZdravotnickaPomocka(pacient.zistiMeno(), pacient.zistiRodneCislo(), text, lekar, typPomocky));
					// Do predpisov sa nepridava predpis ako od vseobecneho lekara, ale zdravotna pomocka
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany predpis na \"" + typPomocky + "\" s textom \""+ text + "\"\n");
		}			
	}
	
	/**
	 * Zistuje specializaciu lekara.
	 * @return specializacia lekara
	 */
	@Override
	public String zistiSpecializaciu() {
		return this.specializacia;
	}
}
