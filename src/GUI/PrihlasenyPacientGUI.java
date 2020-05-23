package GUI;

import java.io.IOException;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;
import osoby.*;
import poistovna.*;

/**
 * Okno prihlaseneho pacienta. Pacient vidi svoje osobne udaje aktualne evidovane zdravotnou poistovnou. Moze vypisat
 * zoznam lekarov, zoznam predpisov na lieky a zdravotnicke pomocky, ako aj zoznam vymennych listkov prenho vydanych.
 * Pacient si moze zo zoznamu vybrat lekara a zapisat sa k nemu.
 * @author PeterSmrecek
 *
 */
public class PrihlasenyPacientGUI extends Stage{
	private Button navsteva = new Button("Zapisat sa k vybranemu lekarovi");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vypisAktualnychLekarov = new Button("Vypisat vsetkych lekarov");
	private Button vymazPole = new Button("Vymazat pole");
	private Button vypisPredpisy = new Button("Vypis moje predpisy");
	private Button vypisListky = new Button("Vypis moje vymenne listky");
	
	private TextField meno = new TextField();
	private TextField adresa = new TextField();
	private TextField rodnec = new TextField();
	private TextField pohlavie = new TextField();
	
	private Label menoOzn = new Label("Meno pacienta");
	private Label adresaOzn = new Label("Adresa pacienta");
	private Label rodnecOzn = new Label("Rodne cislo pacienta");
	private Label pohlavieOzn = new Label("Pohlavie pacienta");
	private Label vypisOzn = new Label("Vypis lekarov");
	private Label vymennListokOzn = new Label("Vymenne listky");
	private Label predpisyOzn = new Label("Moje predpisy");
	
	private VypisLekarovGUI lwVypisLekarov;
	private ListView<String> predpisy = new ListView<String>();
	private ListView<String> vymenneListky = new ListView<String>();
	
	private TextArea log = new TextArea();
	
	private ScrollPane skrol = new ScrollPane();
	
	public PrihlasenyPacientGUI(ZdravotnaPoistovna poistovna, Pacient pacient) {
		super();
		
		setTitle("Prihlaseny pacient "+pacient.zistiMeno());
		
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
		
		int row = 0;
		
		pane2.add(menoOzn, 0, row);
		pane2.add(meno, 1, row++);
		meno.setText(pacient.zistiMeno());
		meno.setEditable(false);
		
		pane2.add(adresaOzn, 0, row);
		pane2.add(adresa, 1, row++);
		adresa.setText(pacient.zistiAdresu());
		adresa.setEditable(false);
		
		pane2.add(rodnecOzn, 0, row);
		pane2.add(rodnec, 1, row++);
		rodnec.setText(pacient.zistiRodneCislo());
		rodnec.setEditable(false);
		
		pane2.add(pohlavieOzn, 0, row);
		pane2.add(pohlavie, 1, row++);
		pohlavie.setText(String.valueOf(pacient.zistiPohlavie()));
		pohlavie.setEditable(false);
		
		pane2.add(vypisAktualnychLekarov, 0, row);
		pane2.add(navsteva, 1, row++);
		
		pane2.add(vypisPredpisy, 0, row);
		pane2.add(vymazPole, 1, row++);
		
		pane2.add(vypisListky, 0, row);
		pane2.add(uloz, 1, row++);
		
		int velkostButton = 260;
		int velkostButton2 = 200;
		
		vypisAktualnychLekarov.setMinWidth(velkostButton2);
		vymazPole.setMinWidth(velkostButton);
		navsteva.setMinWidth(velkostButton);
		uloz.setMinWidth(velkostButton);
		vypisPredpisy.setMinWidth(velkostButton2);
		vypisListky.setMinWidth(velkostButton2);
		
		FlowPane pane4 = new FlowPane();
		pane4.setPadding(new Insets(10, 10, 10, 10));
		pane4.getChildren().add(log);
		log.setPrefWidth(velkostButton + velkostButton2 + 10); 
		log.setEditable(false);
		log.setStyle("-fx-control-inner-background: transparent; -fx-text-inner-color: black;");
		
		GridPane pane3 = new GridPane();
		pane3.setVgap(10);
		pane3.setHgap(10);
		pane3.setPadding(new Insets(10, 10, 10, 10));
		
		pane3.add(vypisOzn, 0, 0);
		
		lwVypisLekarov = new VypisLekarovGUI(poistovna);
		poistovna.pridajSledovatela(lwVypisLekarov);
		
		int width = 300;
		int height = 500;

		lwVypisLekarov.setMinSize(width, height);
		pane3.add(lwVypisLekarov, 0, 1);
		
		pane3.add(predpisyOzn, 1, 0);
		predpisy.setMinSize(width, height);
		pane3.add(predpisy, 1, 1);
		
		pane3.add(vymennListokOzn, 2, 0);
		vymenneListky.setMinSize(width, height);
		pane3.add(vymenneListky, 2, 1);
		
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 10, 10, 10));
		border.setTop(pane2);
		border.setBottom(pane4);

		pane.add(border, 0, 0);
		pane.add(pane3, 1, 0);
		
		skrol.setContent(pane);
		
		navsteva.setOnAction(e->{
			if (lwVypisLekarov.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Nevybrany lekar");
				alert.setContentText("Ziaden lekar zo zoznamu nebol zvoleny.\n"
						+ "Najskor vyber lekara.");
				alert.showAndWait();
			} else {
				int index = lwVypisLekarov.getSelectionModel().getSelectedIndex();
				log.appendText(poistovna.vratLekara(index).evidujPacienta(pacient));
			}
		});
		
		vymazPole.setOnAction(e->{
			lwVypisLekarov.vymazListView();
			vymenneListky.getItems().clear();
			predpisy.getItems().clear();
		});
		
		vypisPredpisy.setOnAction(e->{
			pacient.citajPredpisy(predpisy);
		});
		
		
		vypisListky.setOnAction(e->{
			pacient.citajListky(vymenneListky);
		});
		
		uloz.setOnAction(e->{
			try {
				poistovna.uloz();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		vypisAktualnychLekarov.setOnAction(e->{
			poistovna.vypisLekarov();
		});
		
		
		setScene(new Scene(skrol, 1500, 600));
		show();
		vypisAktualnychLekarov.requestFocus();
	}
}
