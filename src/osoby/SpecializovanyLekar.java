package osoby;

import java.util.ArrayList;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
	
	// Prekonanie metody, pacient je evidovany iba ak ma vymenny listok pre spravneho specialistu
	@Override
	public String evidujPacienta(Pacient pacient) {
		ArrayList<Listok> vymenneListky = pacient.vratListky();
		for (Listok listok : vymenneListky) {
			System.out.println(listok.zistiText());
			if(listok.zistiSpecializaciu().contentEquals(this.specializacia)) {
				this.lekaroviPacienti.add(pacient);
				pacient.odstranListok(listok);
				return (pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno() + ".\n");
			} 
		}
		return (pacient.zistiMeno()+" nema vymenny listok k " + this.zistiSpecializaciu() + "-ovi.\n");
	}
	
	public void vytvorPomocku(ListView<String> zoznamPacientov, TextArea log, TextArea textPredpisu, String typPomocky) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			String text = textPredpisu.getText();
			textPredpisu.clear();
			Lekar lekar = this;
			pacient.pridajPredpis(new ZdravodnickaPomocka(pacient.zistiMeno(), pacient.zistiRodneCislo(), text, lekar, typPomocky));
					// Do predpisov sa nepridava predpis ako od vseobecneho lekara, ale zdravotna pomocka
			String oslovenie = "Pacientovi ";
			if (pacient.zistiPohlavie() == 'Z') {
				oslovenie = "Pacientke ";
			}
			log.appendText(oslovenie + pacient.zistiMeno()+" bol vydany predpis na \"" + typPomocky + "\" s textom \""+ text + "\"\n");
		}			
	}
	
	@Override
	public String zistiSpecializaciu() {
		return this.specializacia;
	}
}
