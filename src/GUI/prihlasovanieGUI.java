package GUI;

import java.io.IOException;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import osoby.ZdravotnaPoistovna;
import osoby.*;


public class prihlasovanieGUI extends Application {
	private Button prihlasenie = new Button("Prihlasit sa");
	private Button registracia = new Button("Chcem sa zaregistrovat");
	private TextField nick = new TextField();
	private TextField heslo = new TextField();
	private Label nickOzn = new Label("Prihlasovacie meno");
	private Label hesloOzn = new Label("Heslo");
//	private TextArea vypis = new TextArea();
//	private ScrollPane skrol = new ScrollPane();
	
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
		FlowPane pane = new FlowPane();
		
		pane.setVgap(10);
		pane.setHgap(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		 
		Region p1 = new Region();
		p1.setPrefSize(Double.MAX_VALUE, 0.0);
		Region p2 = new Region();
		p2.setPrefSize(Double.MAX_VALUE, 0.0);
		
		nickOzn.setPrefWidth(175);
		hesloOzn.setPrefWidth(175);

		pane.getChildren().add(nickOzn);
		pane.getChildren().add(nick);
		pane.getChildren().add(p1);
		pane.getChildren().add(hesloOzn);
		pane.getChildren().add(heslo);
		pane.getChildren().add(p2);
		pane.getChildren().add(prihlasenie);
		pane.getChildren().add(registracia);
		
		
		prihlasenie.setOnAction(e-> {
			if (poistovna.autentifikacia(nick.getText(), heslo.getText())) {
				System.out.println("Prihlaseny ako poistovna.");
				new poistovnaGUI(poistovna);
			} else {
				System.out.println("Meno a heslo sa nezhoduje.");
			}
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

		
		hlavneOkno.setScene(new Scene(pane, 300, 220));
//		hlavneOkno.setScene(new Scene(skrol, 500, 300)); // so scrollbarmi
		hlavneOkno.show();
	}

	// metoda main() pomoze v pripade zlyhania standardneho sposobu aktivacie JavaFX aplikacie
	// ale inak nie je potrebna
	public static void main(String[] args) {
		launch(args);
	}
}
