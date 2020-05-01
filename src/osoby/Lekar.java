package osoby;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import poistovna.Predpis;


public class Lekar implements Serializable, ZistiPrihlasovacieUdaje, ZistiOsobneUdaje {
	private static final long serialVersionUID = 0;
	
	// Agregacia a enkapsulacia (udaje pristupne len cez metodu)
	protected OsobneUdaje osudaje;
	protected PrihlasovacieUdaje priudaje;
	
	// List pacientov v lekarovej evidencii
	public List<Pacient> lekaroviPacienti = new ArrayList<>();
	
	public boolean evidujPacienta(Pacient pacient) {
		this.lekaroviPacienti.add(pacient);
		System.out.println(pacient.zistiMeno()+" sa stal pacientom lekara "+this.zistiMeno());
		return true;
	}
	
	public void vypisPacientov() {
		for (Pacient pacient2 : this.lekaroviPacienti) {
			System.out.println("Evidovany pacient lekara '" + this.zistiMeno() + "': " + pacient2.zistiMeno() +" "
								+ pacient2.zistiAdresu() +" "+ pacient2.zistiRodneCislo() +" "+ pacient2.zistiPohlavie());
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
	
	public void vydajVymennyListok(ListView<String> zoznamPacientov, TextArea log) {
		if (zoznamPacientov.getSelectionModel().isEmpty()) {
			log.appendText("Vyber pacienta zo zoznamu.\n");
		} else {
			int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
			Pacient pacient = this.vratPacienta(index);
			pacient.vymennyListok = true;
			log.appendText("Pacientovi "+pacient.zistiMeno()+" bol vydany vymenny listok.\n");
			vypisVsetkychPacientov(zoznamPacientov, log);
		}
	}
	
	public void vypisVsetkychPacientov(ListView<String> zoznamPacientov, TextArea log) {
		zoznamPacientov.getItems().clear();
		for (Pacient pacient : this.lekaroviPacienti) {
			zoznamPacientov.getItems().add(pacient.zistiMeno() +" "+ pacient.vymennyListok);
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
			pacient.predpisy.add(new Predpis(pacient.zistiMeno(), pacient.zistiRodneCislo(), text, lekar));
			log.appendText("Pacientovi "+pacient.zistiMeno()+" bol vydany predpis s textom \""+ text + "\"");
		}			
	}
	
	public Lekar(String meno, String adresa, String rodnec, char pohlavie, String nick, String heslo) {
		osudaje = new OsobneUdaje(meno, adresa, rodnec, pohlavie);
		nastavPrihlasovacieUdaje(nick, heslo);
	}
	
	public String zistiSpecializaciu() {
		return "Vseobecny lekar";
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
