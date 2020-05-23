package GUI;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import javafx.geometry.*;
import poistovna.*;

/**
 * Okno manazera poistovne. Manazer poistovne moze vypisat zoznam lekarov a zaevidovat noveho vseobecneho, 
 * alebo specializovaneho lekara do zaznamov poistovne.
 * @author PeterSmrecek
 *
 */
public class ManazerPoistovneGUI extends Stage{
	private Button pridajLekara = new Button("Pridat lekara");
	private Button reset = new Button("Reset");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vypisAktualnychLekarov = new Button("Vypisat lekarov");
	private Button vymazPole = new Button("Vymazat vypis lekarov");
	private Button vycisTF = new Button("Vycistit polia");
	
	private TextField meno = new TextField();
	private TextField adresa = new TextField();
	private TextField rodnec = new TextField();
	private TextField nick = new TextField();
	private TextField heslo = new TextField();
	
	private Label menoOzn = new Label("Meno lekara");
	private Label adresaOzn = new Label("Adresa lekara");
	private Label rodnecOzn = new Label("Rodne cislo lekara");
	private Label pohlavieOzn = new Label("Zadaj pohlavie lekara");
	private Label specializaciaOzn = new Label("Zadaj specializaciu lekara");
	private Label specializaciaPoznamkaOzn = new Label("Zaskrtni, ak ma\nlekar specializaciu:");
	private Label vypisOzn = new Label("Vypis lekarov");
	private Label nickOzn = new Label("Prihlasovacie meno");
	private Label hesloOzn = new Label("Heslo");
	
	private VypisLekarovGUI lwVypisLekarov;
	
	private CheckBox specializaciaAktivna = new CheckBox("Lekar ma specializaciu");
	
	private ChoiceBox<String> pohlavie = new ChoiceBox<String>();
	private ChoiceBox<String> specializacia = new ChoiceBox<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	public ManazerPoistovneGUI(ZdravotnaPoistovna poistovna) {
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
		
		int row = 0;
		
		pane2.add(menoOzn, 0, row);
		pane2.add(meno, 1, row++);
		
		pane2.add(nickOzn, 0, row);
		pane2.add(nick, 1, row++);
		
		pane2.add(hesloOzn, 0, row);
		pane2.add(heslo, 1, row++);
		
		pane2.add(adresaOzn, 0, row);
		pane2.add(adresa, 1, row++);
		
		pane2.add(rodnecOzn, 0, row);
		pane2.add(rodnec, 1, row++);
		
		pane2.add(pohlavieOzn, 0, row);
		pohlavie.getItems().addAll("Muz", "Zena", "Ine");
		pane2.add(pohlavie, 1, row++);

		pane2.add(specializaciaPoznamkaOzn, 0, row);
		pane2.add(specializaciaAktivna, 1, row++);
		
		pane2.add(specializaciaOzn, 0, row);
		specializacia.getItems().addAll("Oftamolog", "Dietolog", "Neurolog", "Gastroenterolog");
		specializacia.setDisable(true);
		pane2.add(specializacia, 1, row++);
		
		pane2.add(vypisAktualnychLekarov, 0, row);
		pane2.add(pridajLekara, 1, row++);
		pane2.add(vymazPole, 0, row);
		pane2.add(vycisTF, 1, row++);
		pane2.add(uloz, 0, row);
		
//		pane2.add(reset, 1, row++);				// Tlacidlo resetovania vstupov.
												// Vo finalnej verzii programu nie je pristupne. Bolo pouzivane ako debugovaci prvok	
		
		int velkostButton = 200;
		vypisAktualnychLekarov.setMinWidth(velkostButton);
		pridajLekara.setMinWidth(velkostButton);
		vymazPole.setMinWidth(velkostButton);
		vycisTF.setMinWidth(velkostButton);
		uloz.setMinWidth(velkostButton);
		reset.setMinWidth(velkostButton);
		pohlavie.setMinWidth(velkostButton);
		specializacia.setMinWidth(velkostButton);
		
		GridPane pane3 = new GridPane();
		pane3.setVgap(10);
		pane3.setHgap(10);
		pane3.setPadding(new Insets(10, 10, 10, 10));
		
		pane3.add(vypisOzn, 0, 0);
		
		lwVypisLekarov = new VypisLekarovGUI(poistovna);
		poistovna.pridajSledovatela(lwVypisLekarov);
		pane3.add(lwVypisLekarov, 0, 1);
		
		pane.add(pane2, 0, 0);
		pane.add(pane3, 1, 0);
		skrol.setContent(pane);
		
		pridajLekara.setOnAction(e->{
			poistovna.urciTypLekara(specializaciaAktivna.isSelected(), meno.getText(), adresa.getText(), rodnec.getText(), 
						pohlavie.getValue().charAt(0), nick.getText(), heslo.getText(), specializacia.getValue());
		});
		
		reset.setOnAction(e->{
			ResetVstupov main = new ResetVstupov();
			main.reset();
			
  	  		try {
  				poistovna.nacitaj();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
		});
		
		vymazPole.setOnAction(e->{
			lwVypisLekarov.vymazListView();
		});
		
		uloz.setOnAction(e->{
			try {
				poistovna.uloz();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		vycisTF.setOnAction(e->{
			meno.clear();
			nick.clear();
			heslo.clear();
			adresa.clear();
			rodnec.clear();
			pohlavie.getSelectionModel().clearSelection();
			specializacia.getSelectionModel().clearSelection();
			specializaciaAktivna.setSelected(false);
		});
		
		vypisAktualnychLekarov.setOnAction(e->{
			poistovna.vypisLekarov();
		});
		
		specializaciaAktivna.setOnAction(e->{
			if (!specializaciaAktivna.isSelected()) {
				specializacia.getSelectionModel().clearSelection();
				specializacia.setDisable(true);
			} else {
				specializacia.setDisable(false);;
			}
		});
		
		setScene(new Scene(skrol, 800, 500));
		show();
	}
}
