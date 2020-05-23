package osoby;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.*;
import poistovna.*;

/**
 * Lekar bez specializacie - vseobecny lekar. Dedi strukturu udajov z triedy Lekarnik. Dokaze pridat pacienta 
 * do svojej osobnej evidencie. Vydava vymenne listky, vydava predpisy a dokaze vypisat zoznam svojich pacientov.
 * @author PeterSmrecek
 *
 */
public class VseobecnyLekar extends Lekarnik{
	private static final long serialVersionUID = 0;
	
	// List pacientov v lekarovej evidencii
	protected List<Pacient> lekaroviPacienti = new ArrayList<>();
	
	public VseobecnyLekar(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		super(meno, adresa, rodnec, pohlavie, nick, heslo);
	}
	
	/**
	 * Pridanie pacienta do osobnej evidencie lekara.
	 * @param pacient		pacient urceny na zaradenie do osobnej evidencie lekara
	 * @return				sprava, ci sa zaradenie do osobnej evidencie podarilo, alebo nie
	 */
	public String evidujPacienta(Pacient pacient) {
		if (!pacient.skontrolujVseobecnehoLekara()) {
			this.lekaroviPacienti.add(pacient);
			pacient.registrovanyPacientVseobecnymLekarom(true);
			return (pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno() + ".\n");
		} else {
			return (pacient.zistiMeno()+" uz ma vseobecneho lekara. Nie je mozne navstevovat 2 vseobecnych lekarov sucasne.\n");
		}
	}
	
	/**
	 * Vrati pacienta s poradovym cislom zadanym parametrom.
	 * @param n		poradove cislo pacienta v evidencii
	 * @return		pacient
	 */
	public Pacient vratPacienta(int n) {
		try {
			return lekaroviPacienti.get(n);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Zlyhanie: Takyto pacient neexistuje.");
		}
		return null;
	}
	
	/**
	 * Vyda konkretnemu pacientovi konkretny vymenny listok obsahujuci udaje o pacientovi, lekarovi aj text a typ vymenneho listku.
	 * @param zoznamPacientov		zoznam pacientov konkretneho lekara
	 * @param log					miesto na vypisovanie hlaseni o prave vykonanych akciach
	 * @param specializacia			specializacia lekara ktoreho navsteva je umoznena vydanim vymenneho listka pacientovi
	 */
	public void vydajVymennyListok(ListView<String> zoznamPacientov, TextArea log, ChoiceBox<String> specializacia) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else if (specializacia.getValue() == null) {
			log.appendText("Vyber specializaciu cieloveho lekara zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			VseobecnyLekar lekar = this;
			String textSpecializacie = specializacia.getValue();
			pacient.pridajListok(new Listok(pacient.zistiMeno(), pacient.zistiRodneCislo(), textSpecializacie, lekar));
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany vymenny listok k "+ textSpecializacie +"-ovi.\n");
		}
	}
	
	/**
	 * Vypisanie celeho zoznamu lekarovych pacientov.
	 * @param zoznamPacientov		prvok, kam sa zoznam vypise
	 * @param log					prvok, do ktoreho sa vypise sprava o vykonanej akcii
	 */
	public void vypisVsetkychPacientov(ListView<String> zoznamPacientov, TextArea log) {
		zoznamPacientov.getItems().clear();
		for (Pacient pacient : this.lekaroviPacienti) {
			zoznamPacientov.getItems().add(pacient.zistiMeno());
		}
		log.appendText("Zoznam pacientov bol vypisany/aktualizovany.\n");
	}
	
	/**
	 * Vytvorenie predpisu konkretnemu pacientovi. Predpis obsahuje text a informacie o lekarovi a pacientovi.
	 * @param zoznamPacientov		zoznam pacientovv
	 * @param log					prvok, do ktoreho sa vypise sprava o vykonanej akcii
	 * @param textPredpisu			text, ktory bude zahrnuty v predpise
	 */
	public void vytvorPredpis(ListView<String> zoznamPacientov, TextArea log, TextArea textPredpisu) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			String text = textPredpisu.getText();
			textPredpisu.clear();
			VseobecnyLekar lekar = this;
			pacient.pridajPredpis(new Predpis(pacient.zistiMeno(), pacient.zistiRodneCislo(), text, lekar));
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany predpis s textom \""+ text + "\"\n");
		}			
	}
	
	/**
	 * Zistuje specializaciu lekara.
	 * @return pre vseobecneho lekara vzdy vrati retazec s textom "Vseobecny lekar"
	 */
	public String zistiSpecializaciu() {
		return "Vseobecny lekar";
	}
}
