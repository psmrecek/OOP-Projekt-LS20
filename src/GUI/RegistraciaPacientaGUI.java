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
import osoby.EvidenciaPacientov;
import osoby.Lekar;
import osoby.ZdravotnaPoistovna;

public class RegistraciaPacientaGUI extends Stage{

	private Button pridajPacienta = new Button("Zaregistrovat sa");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vycisTF = new Button("Vycisti textfieldy");
	
	private TextField meno = new TextField();
	private TextField adresa = new TextField();
	private TextField rodnec = new TextField();
	private TextField nick = new TextField();
	private TextField heslo = new TextField();
	
	
	private Label menoOzn = new Label("Meno pacienta");
	private Label adresaOzn = new Label("Adresa pacienta");
	private Label rodnecOzn = new Label("Rodne cislo pacienta");
	private Label pohlavieOzn = new Label("Pohlavie pacienta");
	private Label nickOzn = new Label("Prihlasovacie meno");
	private Label hesloOzn = new Label("Heslo");
	
	private ChoiceBox<String> pohlavie = new ChoiceBox<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	public RegistraciaPacientaGUI(ZdravotnaPoistovna poistovna) {
		super();
		
		setTitle("Registracia pacienta");
		
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

		pane2.add(pridajPacienta, 0, row);
		pane2.add(vycisTF, 1, row++);
		
		pane2.add(uloz, 0, row);
	
		pane.add(pane2, 0, 0);
		skrol.setContent(pane);
		
		pridajPacienta.setOnAction(e->{
			poistovna.evidujPacienta(meno.getText(), adresa.getText(), rodnec.getText(), pohlavie.getValue().charAt(0), nick.getText(), heslo.getText());	
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
		});
		
		setScene(new Scene(skrol, 420, 360));
		show();
	}
}
