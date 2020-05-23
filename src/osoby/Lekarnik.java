package osoby;

import java.io.*;
import java.util.ArrayList;

import javafx.scene.control.*;
import poistovna.*;

/**
 * Lekarnik dokaze pre konkretneho pacienta nacitavat predpisy a vypisat ich, vydat konkretny predpis 
 * konkretneho pacienta a vymazat ho z jeho zoznamu predpisov. 
 * @author PeterSmrecek
 *
 */
public class Lekarnik implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje{
	private static final long serialVersionUID = 0;
	
	protected OsobneUdaje osudaje;
	protected PrihlasovacieUdaje priudaje;
	
	public Lekarnik(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		nastavPrihlasovacieUdaje(nick, heslo);
	}
	
	/**
	 * Nacitavanie predpisov konkretneho pacienta do listu.
	 * @param poistovna		poistovna, do ktorej dany pacient spada
	 * @param meno			meno pacienta
	 * @param predpisy		list, do ktoreho sa zapisu predpisy pacienta
	 * @return				sprava o akcii (ci boli predpisy vypisane alebo pacient nebol najdeny v evidencii poistovne)
	 */
	public String nacitajPredpisy(ZdravotnaPoistovna poistovna, String meno, ListView<String> predpisy) {
		Pacient pacient = poistovna.najdiPacienta(meno);
		
		Sprava sprava = text -> (meno + ": " + text + "\n");
		
		if (pacient != null) {
			ArrayList<Predpis> pacientovePredpisy = pacient.vratPredpisy();
			predpisy.getItems().clear();
			for (Predpis predpis : pacientovePredpisy) {
				predpisy.getItems().add(predpis.citaniePredpisu());
			}
			return sprava.pridaj("Predpisy boli vypisane.");
		} else {
			return sprava.pridaj("Nie je v evidencii.");
		}
	}
	
	/**
	 * Vydavanie konkretneho vybrateho predpisu zo zoznamu.
	 * @param poistovna		poistovna, do ktorej dany pacient spada
	 * @param meno			meno pacienta
	 * @param predpisy		list, v ktorom su zapisane predpisy pacienta
	 * @return				sprava o akcii (ci bol predpis vydany alebo ze zo zoznamu nebol vybraty ziaden predpis)
	 */
	public String vydatPredpis(ZdravotnaPoistovna poistovna, String meno, ListView<String> predpisy) {
		Pacient pacient = poistovna.najdiPacienta(meno);
		Sprava sprava = text -> ("Vydavanie predpisu: " + text + "\n");
		if (predpisy.getSelectionModel().isEmpty()) {
			return sprava.pridaj("Zo zoznamu nebol vybraty ziaden predpis.");
		} else {
			int index = predpisy.getSelectionModel().getSelectedIndex();
			String text = sprava.pridaj(("Predpis \"" + predpisy.getSelectionModel().getSelectedItem() + "\" bol vydany."));
			pacient.odstranPredpis(index);
			return (text + this.nacitajPredpisy(poistovna, meno, predpisy));
		}	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiMeno() {
		// TODO Auto-generated method stub
		return this.osudaje.meno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiAdresu() {
		// TODO Auto-generated method stub
		return this.osudaje.adresa;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiRodneCislo() {
		// TODO Auto-generated method stub
		return this.osudaje.rodnec;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public char zistiPohlavie() {
		// TODO Auto-generated method stub
		return this.osudaje.pohlavie;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiNick() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiNick();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String zistiHeslo() {
		// TODO Auto-generated method stub
		return this.priudaje.zistiHeslo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nastavPrihlasovacieUdaje(String nick, String heslo) {
		// TODO Auto-generated method stub
		priudaje = new PrihlasovacieUdaje(nick, heslo);
	}
}
