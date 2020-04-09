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

public class poistovnaGUI extends Stage{
	private Button pridajLekara = new Button("Pridat lekara");
	private Button vypisLekarov = new Button("Vypis lekarov");
	private Button uloz = new Button("Ulozit zmeny");
	private Button znovaNacitaj = new Button("Znova nacitat udaje");
//	private Button registracia = new Button("Chcem sa zaregistrovat");
	private TextField meno = new TextField();
	private TextField adresa = new TextField();
	private TextField rodnec = new TextField();
	private TextField pohlavie = new TextField();
	private TextField specializacia = new TextField();
	
	private Label menoOzn = new Label("Meno lekara");
	private Label adresaOzn = new Label("Adresa lekara");
	private Label rodnecOzn = new Label("Rodne cislo lekara");
	private Label pohlavieOzn = new Label("Zadaj pohlavie lekara");
	private Label specializaciaOzn = new Label("Zadaj specializaciu lekara");
	private Label specializaciaPoznamkaOzn = new Label("Nechaj prazdne ak\nlekar je vseobecny.");
	
	private Label vypisOzn = new Label("Vypis lekarov");
	private ListView vypis = new ListView();
	private ScrollPane skrol = new ScrollPane();
	
	public poistovnaGUI(ZdravotnaPoistovna poistovna) {
		super();
		
		setTitle("Manazer poistovne");
		
		GridPane pane = new GridPane();
		
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		GridPane pane2 = new GridPane();
		
		pane2.setVgap(10);
		pane2.setHgap(10);
		pane2.setPadding(new Insets(10, 10, 10, 10));
		 
		menoOzn.setPrefWidth(175);
		adresaOzn.setPrefWidth(175);
		rodnecOzn.setPrefWidth(175);
		pohlavieOzn.setPrefWidth(175);
		specializaciaOzn.setPrefWidth(175);
		
		pane2.add(menoOzn, 0, 0);
		pane2.add(meno, 1, 0);
		
		pane2.add(adresaOzn, 0, 1);
		pane2.add(adresa, 1, 1);
		
		pane2.add(rodnecOzn, 0, 2);
		pane2.add(rodnec, 1, 2);
		
		pane2.add(pohlavieOzn, 0, 3);
		pane2.add(pohlavie, 1, 3);

		pane2.add(specializaciaOzn, 0, 4);
		pane2.add(specializacia, 1, 4);
		pane2.add(specializaciaPoznamkaOzn, 0, 5);
		
		pane2.add(pridajLekara, 0, 6);
		pane2.add(vypisLekarov, 1, 6);
		pane2.add(uloz, 0, 7);
		pane2.add(znovaNacitaj, 1, 7);
		
		pane.add(pane2, 0, 0);
		
		GridPane pane3 = new GridPane();
		
		pane3.setVgap(10);
		pane3.setHgap(10);
		pane3.setPadding(new Insets(10, 10, 10, 10));
		
		pane3.add(vypisOzn, 0, 0);
		pane3.add(vypis, 0, 1);
		
		
		pane.add(pane3, 1, 0);
		skrol.setContent(pane);
		
		pridajLekara.setOnAction(e->{
			poistovna.evidujLekara(meno.getText(), adresa.getText(), rodnec.getText(), pohlavie.getText().charAt(0));
		});
		
		vypisLekarov.setOnAction(e->{
			vypis.getItems().clear();
			for (Lekar lekar : poistovna.lekar) {
				vypis.getItems().add(lekar.zistiMeno());
			}
			
		});
		
		
		
		setScene(new Scene(skrol, 700, 500));
		show();
	}
}
