package osoby;

import java.io.*;
import java.util.ArrayList;

import javafx.scene.control.*;
import poistovna.*;

public class Lekarnik implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje{
	private static final long serialVersionUID = 0;
	
	protected OsobneUdaje osudaje;
	protected PrihlasovacieUdaje priudaje;
	
	public Lekarnik(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		nastavPrihlasovacieUdaje(nick, heslo);
	}
	
	public String nacitajPredpisy(ZdravotnaPoistovna poistovna, String meno, ListView<String> predpisy) {
		Pacient pacient = poistovna.najdiPacienta(meno);
		if (pacient != null) {
			ArrayList<Predpis> pacientovePredpisy = pacient.vratPredpisy();
			predpisy.getItems().clear();
			for (Predpis predpis : pacientovePredpisy) {
				predpisy.getItems().add(predpis.zistiText() +" predpisal " + predpis.zistiMenoLekara());
			}
			return (meno+": Recepty boli vypisane.\n");
		} else {
			return (meno + " sa v evidencii nenachadza.\n");
		}
	}
	
	public String vydatPredpis(ZdravotnaPoistovna poistovna, String meno, ListView<String> predpisy) {
		Pacient pacient = poistovna.najdiPacienta(meno);
		if (predpisy.getSelectionModel().isEmpty()) {
			return ("Vyber predpis zo zoznamu.\n");
		} else {
			int index = predpisy.getSelectionModel().getSelectedIndex();
			
			String sprava = "Predpis \"" + predpisy.getSelectionModel().getSelectedItem() + "\" bol vydany.\n";
			pacient.odstranPredpis(index);
			return (sprava + this.nacitajPredpisy(poistovna, meno, predpisy));
		}	
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

	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiNick();
	}

	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiHeslo();
	}
	
	@Override
	public void nastavPrihlasovacieUdaje(String nick, String heslo) {
		// TODO Auto-generated method stub
		priudaje = new PrihlasovacieUdaje(nick, heslo);
	}
}
