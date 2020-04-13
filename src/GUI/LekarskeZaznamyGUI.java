package GUI;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import javafx.geometry.*;
import osoby.*;
import poistovna.*;

public class LekarskeZaznamyGUI extends Stage{
	
	private Button vypisPacientov = new Button("Vypisat zoznam pacientov");
	private Button vydajVymennyListok = new Button("Vydat pacientovi vymenny listok");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vydajPredpis = new Button("Vydat predpis");
	
	private Label zoznamPacientovOzn = new Label("Zoznam lekarovych pacientov:");
	private Label vypisy = new Label("Akcie:");
	private Label textPredpisuOzn = new Label("Text predpisu:");
	
	private TextArea log = new TextArea();
	private TextArea textPredpisu = new TextArea();
	
	private ListView<String> zoznamPacientov = new ListView<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	
	public LekarskeZaznamyGUI(ZdravotnaPoistovna poistovna, Lekar lekar) {
		
		super();
		
		setTitle("Prihlaseny lekar: " + lekar.zistiMeno());
		
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 10, 10, 10));
		
		GridPane pane2 = new GridPane();
		pane2.setVgap(10);
		pane2.setHgap(10);
		pane2.setPadding(new Insets(10, 10, 10, 10));
		int height = 350;
		log.setPrefSize(450, height/2);
		log.setEditable(false);
		log.setStyle("-fx-control-inner-background: transparent; -fx-text-inner-color: black;");

		textPredpisu.setPrefSize(450, height/2);
		int row = 0;
		pane2.add(vypisy, 0, row++);
		pane2.add(log, 0, row++);
		pane2.add(textPredpisuOzn, 0, row++);
		pane2.add(textPredpisu, 0, row++);

		
		HBox box = new HBox();
		box.setSpacing(10);
		box.setPadding(new Insets(10, 10, 10, 10));
		box.getChildren().addAll(vypisPacientov, vydajVymennyListok, vydajPredpis, uloz);
		
		GridPane pane4 = new GridPane();
		pane4.setVgap(10);
		pane4.setHgap(10);
		pane4.setPadding(new Insets(10, 10, 10, 10));
		pane4.add(zoznamPacientovOzn, 0, 0);
		zoznamPacientov.setPrefHeight(height + 40);
		pane4.add(zoznamPacientov, 0, 1);
		
		border.setLeft(pane2);
		border.setRight(pane4);
		border.setBottom(box);
		
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
				lekar.vydajVymennyListok(pacient);
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
		
		vydajPredpis.setOnAction(e->{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Oops");
			alert.setContentText("Tato funkcionalita este nebola implementovana.");
			alert.showAndWait();
		});
		
		
		skrol.setContent(border);
		setScene(new Scene(skrol, 770, 520));
		show();
		vypisPacientov.requestFocus();
	}
}
