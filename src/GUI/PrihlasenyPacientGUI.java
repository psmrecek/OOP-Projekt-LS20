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

public class PrihlasenyPacientGUI extends Stage{
	private Button navsteva = new Button("Zapisat sa k vybranemu lekarovi");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vypisAktualnychLekarov = new Button("Vypisat vsetkych lekarov");
	private Button vymazPole = new Button("Vymazat pole");
	private Button vypisPredpisy = new Button("Vypis moje predpisy");
	
	private TextField meno = new TextField();
	private TextField adresa = new TextField();
	private TextField rodnec = new TextField();
	private TextField pohlavie = new TextField();
	private TextField vymennyListok = new TextField();
	
	private Label menoOzn = new Label("Meno pacienta");
	private Label adresaOzn = new Label("Adresa pacienta");
	private Label rodnecOzn = new Label("Rodne cislo pacienta");
	private Label pohlavieOzn = new Label("Pohlavie pacienta");
	private Label vypisOzn = new Label("Vypis lekarov");
	private Label vymennListokOzn = new Label("Vymenny listok");
	private Label predpisyOzn = new Label("Moje predpisy");
	
	private VypisLekarovGUI lwVypisLekarov;
	private ListView<String> predpisy = new ListView<String>();
	
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
		
		pane2.add(vymennListokOzn, 0, row);
		pane2.add(vymennyListok, 1, row++);
		vymennyListok.setText(String.valueOf(pacient.vymennyListok));
		vymennyListok.setEditable(false);
		
		pane2.add(vypisAktualnychLekarov, 0, row);
		pane2.add(vymazPole, 1, row++);
		
		pane2.add(navsteva, 0, row);
		pane2.add(uloz, 1, row++);
		
		pane2.add(vypisPredpisy, 0, row);
		
		int velkostButton = 260;
		int velkostButton2 = 200;
		vypisAktualnychLekarov.setMinWidth(velkostButton);
		vymazPole.setMinWidth(velkostButton2);
		navsteva.setMinWidth(velkostButton);
		uloz.setMinWidth(velkostButton2);
		vypisPredpisy.setMinWidth(velkostButton);
		
		GridPane pane3 = new GridPane();
		pane3.setVgap(10);
		pane3.setHgap(10);
		pane3.setPadding(new Insets(10, 10, 10, 10));
		
		pane3.add(vypisOzn, 0, 0);
		
		lwVypisLekarov = new VypisLekarovGUI(poistovna);
		poistovna.pridajSledovatela(lwVypisLekarov);
		pane3.add(lwVypisLekarov, 0, 1);
		
		pane3.add(predpisyOzn, 1, 0);
		predpisy.setMinWidth(300);
		pane3.add(predpisy, 1, 1);
		
		pane.add(pane2, 0, 0);
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
				if(!poistovna.vratLekara(index).evidujPacienta(pacient)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Vymenny listok nebol najdeny");
					alert.setContentText("K specializovanemu lekarovi mozes ist len s vymennym listkom.\n"
							+ "Najskor navstiv vseobecneho lekara a poziadaj ho o vydanie vymenneho listka.");
					alert.showAndWait();
				}
				
			}
		});
		
		vymazPole.setOnAction(e->{
			lwVypisLekarov.vymazListView();
		});
		
		vypisPredpisy.setOnAction(e->{
			pacient.citajPredpisy(predpisy);
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
		
		
		setScene(new Scene(skrol, 1150, 500));
		show();
		vypisAktualnychLekarov.requestFocus();
	}
}
