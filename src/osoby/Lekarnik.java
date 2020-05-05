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
		
		Sprava sprava = text -> (meno + ": " + text + "\n");
		
		if (pacient != null) {
			ArrayList<Predpis> pacientovePredpisy = pacient.vratPredpisy();
			predpisy.getItems().clear();
			for (Predpis predpis : pacientovePredpisy) {
				predpisy.getItems().add(predpis.citaniePredpisu());
			}
//			return (meno+": Predpisy boli vypisane.\n");
			return sprava.pridaj("Predpisy boli vypisane.");
		} else {
			return sprava.pridaj("Nie je v evidencii.");
		}
	}
	
	public String vydatPredpis(ZdravotnaPoistovna poistovna, String meno, ListView<String> predpisy) {
		Pacient pacient = poistovna.najdiPacienta(meno);
		Sprava sprava = text -> ("Vydavanie predpisu: " + text + "\n");
		if (predpisy.getSelectionModel().isEmpty()) {
			return sprava.pridaj("Zo zoznamu nebol vybraty ziaden predpis.");
		} else {
			int index = predpisy.getSelectionModel().getSelectedIndex();
			
			class NovaNit extends Thread{											// Vnorena trieda
				
			    public void run() 
			    { 
			    	for (int j = 0; j < 100000; j++) {
			    		System.out.println("Prebieha hladanie lieku " + j);
					}
			    	
			    }
			    
			}
			
			NovaNit nit = new NovaNit();
			nit.start();
			try {
				nit.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String text = sprava.pridaj(("Predpis \"" + predpisy.getSelectionModel().getSelectedItem() + "\" bol vydany."));
			pacient.odstranPredpis(index);
			return (text + this.nacitajPredpisy(poistovna, meno, predpisy));
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
