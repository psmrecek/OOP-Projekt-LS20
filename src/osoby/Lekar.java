package osoby;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import poistovna.*;


public class Lekar extends Lekarnik{
	private static final long serialVersionUID = 0;
	
	// List pacientov v lekarovej evidencii
	protected List<Pacient> lekaroviPacienti = new ArrayList<>();
	
//	protected String specializacia;
	
	public Lekar(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		super(meno, adresa, rodnec, pohlavie, nick, heslo);
//		this.specializacia = "Vseobecny lekar";
	}
	
	public String evidujPacienta(Pacient pacient) {
		if (!pacient.skontrolujVseobecnehoLekara()) {
			this.lekaroviPacienti.add(pacient);
			pacient.registrovanyPacientVseobecnymLekarom(true);
			return (pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno() + "\n.");
		} else {
			return (pacient.zistiMeno()+" uz ma vseobecneho lekara. Nie je mozne navstevovat 2 vseobecnych lekarov sucasne.\n");
		}
	}
	
	public Pacient vratPacienta(int n) {
		try {
			return lekaroviPacienti.get(n);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Takyto pacient neexistuje.");
		}
		return null;
	}
	
	public void vydajVymennyListok(ListView<String> zoznamPacientov, TextArea log, ChoiceBox<String> specializacia) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else if (specializacia.getValue() == null) {
			log.appendText("Vyber specializaciu cieloveho lekara zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			Lekar lekar = this;
			String textSpecializacie = specializacia.getValue();
			pacient.pridajListok(new Listok(pacient.zistiMeno(), pacient.zistiRodneCislo(), textSpecializacie, lekar));
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany vymenny listok k "+ textSpecializacie +"-ovi.\n");
		}
	}
	
	public void vypisVsetkychPacientov(ListView<String> zoznamPacientov, TextArea log) {
		zoznamPacientov.getItems().clear();
		for (Pacient pacient : this.lekaroviPacienti) {
			zoznamPacientov.getItems().add(pacient.zistiMeno());
		}
		log.appendText("Zoznam pacientov bol vypisany/aktualizovany.\n");
	}
	
	public void vytvorPredpis(ListView<String> zoznamPacientov, TextArea log, TextArea textPredpisu) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			String text = textPredpisu.getText();
			textPredpisu.clear();
			Lekar lekar = this;
			pacient.pridajPredpis(new Predpis(pacient.zistiMeno(), pacient.zistiRodneCislo(), text, lekar));
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany predpis s textom \""+ text + "\"\n");
		}			
	}
	
	public String zistiSpecializaciu() {
		return "Vseobecny lekar";
	}
}
