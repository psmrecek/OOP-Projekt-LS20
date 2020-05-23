package GUI;

import java.util.*;
import javafx.scene.control.*;
import osoby.*;
import poistovna.*;

/*
 * Element ListView pouzivany v navrhovom vzore Observer. Udrzuje aktualny zoznam lekarov evidovanych zdravotnou poistovnou.
 * @author PeterSmrecek
 *
 */
public class VypisLekarovGUI extends ListView<String> implements SledovatelLekarov{					// Element GUI aplikujuci navrhovy vzor Observer
	private ZdravotnaPoistovna poistovna;
	public List<VseobecnyLekar> lekari = new ArrayList<>();
	
	public VypisLekarovGUI(ZdravotnaPoistovna poistovna) {
		// TODO Auto-generated constructor stub
		super();
		this.setMinWidth(300);
		this.poistovna = poistovna;
	}
	
	@Override
	public void upovedom() {
		// TODO Auto-generated method stub
		lekari = poistovna.vratZoznamLekarov();
		this.getItems().clear();
		for (VseobecnyLekar lekar : lekari) {
			this.getItems().add(lekar.zistiMeno() +" - "+ lekar.zistiSpecializaciu());
		}
	}
	
	public void vymazListView() {
		this.getItems().clear();
	}

}
