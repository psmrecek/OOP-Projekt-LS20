package GUI;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.Main;
import osoby.Pacient;
import osoby.ZdravotnaPoistovna;

public class PrihlasenyPacientGUI extends Stage{
	private Button navsteva = new Button("Stat sa pacientom vybraneho lekara");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vypisAktualnychLekarov = new Button("Vypisat lekarov");
	private Button vymazPole = new Button("Vymazat pole");
	
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
	
	private VypisLekarovGUI lwVypisLekarov;
	
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
		pane2.add(uloz, 1, row);
		
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
		
		navsteva.setOnAction(e->{
			if (lwVypisLekarov.getSelectionModel().isEmpty()) {
				System.out.println("Vyber si lekara.");
			} else {
				poistovna.vratLekara(lwVypisLekarov.getSelectionModel().getSelectedIndex()).evidujPacienta(pacient);
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
		
		vypisAktualnychLekarov.setOnAction(e->{
			poistovna.vypisLekarov();
		});
		
		
		setScene(new Scene(skrol, 800, 500));
		show();
	}
}
