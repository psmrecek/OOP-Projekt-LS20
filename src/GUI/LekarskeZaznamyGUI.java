package GUI;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import javafx.geometry.*;
import osoby.*;
import poistovna.*;

public class LekarskeZaznamyGUI extends Stage{
	
	private Button vypisPacientov = new Button("Vypisat zoznam pacientov");
	private Button vydajVymennyListok = new Button("Vydat vymenny listok");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vydajPredpis = new Button("Vydat predpis");
	private Button vydajPomocku = new Button("Vydat pomocku");
	
	private Label zoznamPacientovOzn = new Label("Zoznam lekarovych pacientov:");
	private Label vypisy = new Label("Akcie:");
	private Label textPredpisuOzn = new Label("Text predpisu:");
	private Label specializaciaOzn = new Label("Vymenny listok k lekarovi:");
	private Label pomockaOzn = new Label("Typ zdravnotnickej pomocky:");
	
	private TextField typPomocky = new TextField();
	
	private TextArea log = new TextArea();
	private TextArea textPredpisu = new TextArea();
	
	private ListView<String> zoznamPacientov = new ListView<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	private ChoiceBox<String> specializacia = new ChoiceBox<String>();
	
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
		
		HBox box2 = new HBox();
		box2.setSpacing(10);
		box2.setPadding(new Insets(10, 10, 10, 10));
		specializacia.getItems().addAll("Oftamolog", "Dietolog", "Neurolog", "Gastroenterolog");
		box2.getChildren().addAll(specializaciaOzn, specializacia);
		
		BorderPane pane5 = new BorderPane();
		pane5.setPadding(new Insets(10, 10, 10, 10));
		
		pane5.setTop(box2);
		pane5.setBottom(box);
		
		GridPane pane4 = new GridPane();
		pane4.setVgap(10);
		pane4.setHgap(10);
		pane4.setPadding(new Insets(10, 10, 10, 10));
		pane4.add(zoznamPacientovOzn, 0, 0);
		zoznamPacientov.setPrefHeight(height + 40);
		pane4.add(zoznamPacientov, 0, 1);
		
		if (lekar instanceof SpecializovanyLekar) {				// Toto sem nepatri
			
			box2.getChildren().addAll(pomockaOzn, typPomocky);
			box.getChildren().add(vydajPomocku);
		}
		
		border.setLeft(pane2);
		border.setRight(pane4);
		border.setBottom(pane5);
		
		log.appendText(lekar.vsetkyUdaje(lekar.zistiMeno(), lekar.zistiAdresu(), lekar.zistiRodneCislo(), lekar.zistiPohlavie()));

		vypisPacientov.setOnAction(e->{
			lekar.vypisVsetkychPacientov(zoznamPacientov, log);
		});
		
		vydajVymennyListok.setOnAction(e->{
			lekar.vydajVymennyListok(zoznamPacientov, log, specializacia);
		});
		
		vydajPomocku.setOnAction(e->{
			SpecializovanyLekar specialista = (SpecializovanyLekar) lekar;
			specialista.vytvorPomocku(zoznamPacientov, log, textPredpisu, typPomocky.getText());
			typPomocky.clear();
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
			lekar.vytvorPredpis(zoznamPacientov, log, textPredpisu);
		});
		
		
		skrol.setContent(border);
		setScene(new Scene(skrol, 800, 600));
		show();
		vypisPacientov.requestFocus();
	}
}
