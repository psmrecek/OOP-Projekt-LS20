package GUI;

import java.io.IOException;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import osoby.*;


public class PrihlasovacieOknoGUI extends Application {
	private Button prihlasenie = new Button("Prihlasit sa");
	private Button registracia = new Button("Chcem sa zaregistrovat");
	private TextField nick = new TextField();
	private TextField heslo = new TextField();
	private Label nickOzn = new Label("Prihlasovacie meno");
	private Label hesloOzn = new Label("Heslo");
	
	private RadioButton po = new RadioButton("Poistovna");
	private RadioButton le = new RadioButton("Lekar");
	private RadioButton pa = new RadioButton("Pacient");
	final ToggleGroup gr = new ToggleGroup();
	
//	private TextArea vypis = new TextArea();
	
	
	@Override
	public void start(Stage hlavneOkno) {
		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();
		
	  	try {
			poistovna.nacitaj();
		} catch (ClassNotFoundException | IOException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		hlavneOkno.setTitle("Prihlasenie");
		
		FlowPane flowp = new FlowPane();
		flowp.setVgap(10);
		flowp.setHgap(10);
		flowp.setPadding(new Insets(10, 10, 10, 10));
		flowp.setAlignment(Pos.CENTER);
		
		GridPane pane = new GridPane();
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		GridPane pane2 = new GridPane();
		pane2.setVgap(10);
		pane2.setHgap(10);
		pane2.setPadding(new Insets(10, 10, 10, 10));
	
		nickOzn.setPrefWidth(175);
		hesloOzn.setPrefWidth(175);

		pane.add(nickOzn, 0, 0);
		pane.add(nick, 1, 0);
		pane.add(hesloOzn, 0, 1);
		pane.add(heslo, 1, 1);
		
		flowp.getChildren().add(pane);
		
		po.setToggleGroup(gr);
		po.setSelected(true);
		le.setToggleGroup(gr);
		pa.setToggleGroup(gr);
		flowp.getChildren().addAll(po, le, pa);
		
		pane2.add(prihlasenie, 0, 0);
		pane2.add(registracia, 1, 0);
//		pane.add(pane2, 0, 2);
		
		flowp.getChildren().add(pane2);
		prihlasenie.setOnAction(e-> {
			if (po.isSelected()) {
				if (poistovna.autentifikaciaPoistovne(nick.getText(), heslo.getText())) {
					System.out.println("Prihlaseny ako poistovna");
					new ManazerPoistovneGUI(poistovna);
				}
			} else if (le.isSelected()) {
				Lekar lekar = poistovna.autentifikaciaLekara(nick.getText(), heslo.getText());
				if (lekar != null) {
					System.out.println("Prihlaseny ako lekar");
					new LekarskeZaznamyGUI(poistovna, lekar);
				} 
			} else {
				Pacient pacient = poistovna.autentifikaciaPacienta(nick.getText(), heslo.getText());
				if (pacient != null) {
					System.out.println("Prihlaseny ako pacient");
					new PrihlasenyPacientGUI(poistovna, pacient);
				}
			}
		});
		
		registracia.setOnAction(e->{
			new RegistraciaPacientaGUI(poistovna);
		});
		
// 		vytvorBojovnikov.setOnAction(e -> { // lambda vyraz s odvodenim typu z kontextu
//			stret.vytvorBojovnikov(Integer.parseInt(rytieri.getText()),
//					Integer.parseInt(statocniRytieri.getText()),
//					Integer.parseInt(zliObri.getText()));
//
//			vypis.appendText("Bojovnici vytvoreni.\n");
//			}
//		);
//
// 		spustStret.setOnAction(e -> vypis.appendText(stret.stret()));
// 		vymazText.setOnAction(e -> vypis.clear());
//
//		energiaBojovnikov = new EnergiaBojovnikov(stret);
//		stret.pridajSledovatela(energiaBojovnikov);
//		pane.getChildren().add(energiaBojovnikov);

		
		hlavneOkno.setScene(new Scene(flowp, 400, 200));
//		hlavneOkno.setScene(new Scene(skrol, 500, 300)); // so scrollbarmi
		hlavneOkno.show();
	}

	// metoda main() pomoze v pripade zlyhania standardneho sposobu aktivacie JavaFX aplikacie
	// ale inak nie je potrebna
	public static void main(String[] args) {
		launch(args);
	}
}
