package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import osoby.Lekar;
import osoby.Pacient;
import osoby.ZdravotnaPoistovna;

public class Main implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();
		

		// Pomocny if na naplnenie serializovaneho outputu
		boolean b = false;
  		if (b) {
  	  		try {
  				poistovna.vymazOutput();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  	  		
  			List<Pacient> mojiPacienti = new ArrayList<>();
  			mojiPacienti.add(new Pacient("Dorota Chripkova", "Osloboditelov 4 Trencin", "025204/5464", 'z'));
  			mojiPacienti.add(new Pacient("Emilia Chripkova", "Osloboditelov 4 Trencin", "965608/5076", 'z'));
  			mojiPacienti.add(new Pacient("Frederika Chripkova", "Osloboditelov 4 Trencin", "935201/1922", 'z'));
  			mojiPacienti.add(new Pacient("Gertruda Soplikova", "Osloboditelov 5 Trencin", "985825/4274", 'z'));
  			mojiPacienti.add(new Pacient("Hyacinta Soplikova", "Osloboditelov 5 Trencin", "976121/8544", 'z'));
  			mojiPacienti.add(new Pacient("Ilona Soplikova", "Osloboditelov 5 Trencin", "026023/1257", 'z'));
  			mojiPacienti.add(new Pacient("Jan Poloslepy", "Osloboditelov 6 Trencin", "970523/0678", 'm', true));
  			mojiPacienti.add(new Pacient("Kristian Poloslepy", "Osloboditelov 6 Trencin", "021222/9930", 'm'));
  			mojiPacienti.add(new Pacient("Ladislav Poloslepy", "Osloboditelov 6 Trencin", "930618/6461", 'm', true));
//  		volniPacienti.add();
  			
  	  		poistovna.evidujLekara("Arnost Vseobecny", "1. Maja 1 Trencin", "000312/6871", 'm');
 			poistovna.evidujLekara("Beata Vseobecna", "1. Maja 2 Trencin", "945728/5343", 'z');
 			poistovna.evidujLekara("Cyril Specialny", "1. Maja 3 Trencin ", "990113/8863", 'm' , "Ocny lekar");
 			
 	  		poistovna.lekar.get(0).evidujPacienta(mojiPacienti.get(0));
 	 		poistovna.lekar.get(0).evidujPacienta(mojiPacienti.get(1));
 	 		poistovna.lekar.get(0).evidujPacienta(mojiPacienti.get(2));
 			
 	  		poistovna.lekar.get(1).evidujPacienta(mojiPacienti.get(3));
 	  		poistovna.lekar.get(1).evidujPacienta(mojiPacienti.get(4));
 	  		poistovna.lekar.get(1).evidujPacienta(mojiPacienti.get(5));
 	  		
 			poistovna.lekar.get(2).evidujPacienta(mojiPacienti.get(6));
 	  		poistovna.lekar.get(2).evidujPacienta(mojiPacienti.get(7));
 	  		poistovna.lekar.get(2).evidujPacienta(mojiPacienti.get(8));
		}
  		else {
  	  		try {
  				poistovna.nacitaj();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
		}
  		System.out.println("---------------------- Vypis lekarov");
		poistovna.vypisLekarov();
		for (Lekar l : poistovna.lekar) {
			System.out.println("---------------------- Vypis pacientov lekara");
			l.vypisPacientov();
		}
		
		System.out.println("---------------------- Predpisovanie liekov");
		for (int i = 0; i < 3; i++) {
			for (int m = 0; m < 3; m++) {
				poistovna.lekar.get(i).vytvorPredpis(poistovna.lekar.get(i).vratPacienta(m));
			}
		}
		System.out.println("---------------------- Vypis predpisov");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Pacient pacient = poistovna.lekar.get(i).vratPacienta(j);
				if (pacient instanceof Pacient) 
					System.out.println(pacient.citajPredpis());
				
				
			}
		}
		
		try {
			poistovna.uloz();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}