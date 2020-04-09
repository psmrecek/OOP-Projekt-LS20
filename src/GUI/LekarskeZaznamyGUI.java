package GUI;

import javafx.application.*;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import javafx.concurrent.*;
import java.io.*;
import java.util.*;

import javafx.geometry.Insets;
import osoby.*;
import main.*;

public class LekarskeZaznamyGUI extends Stage{
	
	private Button vypisPacientov = new Button("Vypis pacientov");
	private Button vydajVymennyListok = new Button("Vydaj pacientovi vymenny listok");
	private Button uloz = new Button("Ulozit");
	
	private Label zoznamPacientovOzn = new Label("Zoznam lekarovych pacientov");
	
	private TextArea log = new TextArea();
	
	private ListView<String> zoznamPacientov = new ListView<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	
	public LekarskeZaznamyGUI(ZdravotnaPoistovna poistovna, Lekar lekar) {
		
		super();
		
		setTitle("Lekarske zaznamy");
		
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		GridPane pane2 = new GridPane();
		pane2.setVgap(10);
		pane2.setHgap(10);
		pane2.setPadding(new Insets(10, 10, 10, 10));
		pane2.add(vypisPacientov, 0, 0);
		pane2.add(vydajVymennyListok, 1, 0);
		pane2.add(uloz, 0, 1);
		
		GridPane pane3 = new GridPane();
		pane3.setVgap(10);
		pane3.setHgap(10);
		pane3.setPadding(new Insets(10, 10, 10, 10));
		log.setPrefWidth(450);
		pane3.add(log, 0, 0);
		
		GridPane pane4 = new GridPane();
		pane4.setVgap(10);
		pane4.setHgap(10);
		pane4.setPadding(new Insets(10, 10, 10, 10));
		pane4.add(pane3, 0, 0);
		pane4.add(pane2, 0, 1);
		
		pane.add(pane4, 0, 0);
		pane4.add(zoznamPacientov, 1, 0);
		
		vypisPacientov.setOnAction(e->{
			zoznamPacientov.getItems().clear();
			for (Pacient pacient : lekar.lekaroviPacienti) {
				zoznamPacientov.getItems().add(pacient.zistiMeno() +" "+ pacient.vymennyListok);
			}
			log.appendText("Zoznam pacientov bol vypisany.\n");
		});
		
		vydajVymennyListok.setOnAction(e->{
			if (zoznamPacientov.getSelectionModel().isEmpty()) {
				log.appendText("Vyber pacienta zo zoznamu.\n");
			} else {
				int index = zoznamPacientov.getSelectionModel().getSelectedIndex();
				Pacient pacient = lekar.vratPacienta(index);
				pacient.vymennyListok = true;
				log.appendText("Pacientovi "+pacient.zistiMeno()+" bol vydany vymenny listok.\n");
			}
			
		});
		
		uloz.setOnAction(e->{
			try {
				poistovna.uloz();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		skrol.setContent(pane);
		setScene(new Scene(skrol, 850, 550));
		show();
	}
}
