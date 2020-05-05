package GUI;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;
import javafx.geometry.*;
import osoby.*;
import poistovna.*;

public class PrihlasenyLekarnikGUI extends Stage{
	
	private Button najdiPacienta = new Button("Najst pacienta");
	private Button uloz = new Button("Ulozit zmeny");
	private Button vydatLiek = new Button("Vydat liek");
	private Button vymazat = new Button("Iny pacient");
	
	private Label menoPacienta = new Label("Zadaj meno pacienta:");
	private Label vypisy = new Label("Akcie:");
	private Label predpisyOzn = new Label("Predpisy");
	
	private TextArea log = new TextArea();
	
	private TextField meno = new TextField();
	
	private ListView<String> predpisy = new ListView<String>();
	
	private ScrollPane skrol = new ScrollPane();
	
	
	public PrihlasenyLekarnikGUI(ZdravotnaPoistovna poistovna, Lekarnik lekarnik) {
		
		super();
		
		setTitle("Prihlaseny lekarnik: " + lekarnik.zistiMeno());
		
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		
		GridPane pane2 = new GridPane();
		pane2.setVgap(10);
		pane2.setHgap(10);
		pane2.setPadding(new Insets(10, 10, 10, 10));
		int height = 350;
		log.setPrefSize(450, height/2);
		log.setEditable(false);
		log.setStyle("-fx-control-inner-background: transparent; -fx-text-inner-color: black;");
		
		predpisy.setPrefSize(450, height/2);
		
		int row = 0;
		pane2.add(predpisyOzn, 0, row++);
		pane2.add(predpisy, 0, row++);
		pane2.add(vypisy, 0, row++);
		pane2.add(log, 0, row++);

		
		HBox box = new HBox();
		box.setSpacing(10);
		box.setPadding(new Insets(10, 10, 10, 10));
		box.getChildren().addAll(menoPacienta, meno);
		meno.setPrefWidth(290);
		
		HBox box2 = new HBox();
		box2.setSpacing(10);
		box2.setPadding(new Insets(10, 10, 10, 10));
		box2.getChildren().addAll(najdiPacienta, vydatLiek, vymazat, uloz);
		
		pane.getChildren().add(pane2);
		pane.getChildren().add(box);
		pane.getChildren().add(box2);
		
		
		najdiPacienta.setOnAction(e->{
			log.appendText(lekarnik.nacitajPredpisy(poistovna, meno.getText(), predpisy));
			meno.setEditable(false);
		});
		
		vydatLiek.setOnAction(e->{
			log.appendText(lekarnik.vydatPredpis(poistovna, meno.getText(), predpisy));
		});
		
		vymazat.setOnAction(e->{
			meno.setText("");
			meno.setEditable(true);
			predpisy.getItems().clear();
		});
		
		uloz.setOnAction(e->{
			try {
				poistovna.uloz();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		skrol.setContent(pane);
		setScene(new Scene(skrol, 500, 600));
		show();
		najdiPacienta.requestFocus();
	}
}
