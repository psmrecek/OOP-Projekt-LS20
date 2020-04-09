package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import osoby.Lekar;
import osoby.SledovatelLekarov;
import osoby.ZdravotnaPoistovna;

public class VypisLekarovGUI extends ListView implements SledovatelLekarov{
	private ZdravotnaPoistovna poistovna;
	public List<Lekar> lekari = new ArrayList<>();
	
	public VypisLekarovGUI(ZdravotnaPoistovna poistovna) {
		// TODO Auto-generated constructor stub
		super();
		this.poistovna = poistovna;
	}
	
	@Override
	public void upovedom() {
		// TODO Auto-generated method stub
		lekari = poistovna.lekari;
		this.getItems().clear();
		for (Lekar lekar : lekari) {
			this.getItems().add(lekar.zistiMeno());
		}
	}
	
	public void vymazListView() {
		this.getItems().clear();
	}

}
