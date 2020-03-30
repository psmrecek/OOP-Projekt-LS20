package main;

import java.io.*;

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
  	  		
  	  		poistovna.evidujLekara("Arnost Vseobecny", "1. Maja 1 Trencin", "000312/6871", 'm');
 			poistovna.evidujLekara("Beata Vseobecna", "1. Maja 2 Trencin", "945728/5343", 'z');
 			poistovna.evidujLekara("Cyril Specialny", "1. Maja 3 Trencin ", "990113/8863", 'm' , "Ocny lekar");
 			
 	  		poistovna.lekar.get(0).evidujPacienta(new Pacient("Dorota Chripkova", "Osloboditelov 4 Trencin", "025204/5464", 'z'));
 	 		poistovna.lekar.get(0).evidujPacienta(new Pacient("Emilia Chripkova", "Osloboditelov 4 Trencin", "965608/5076", 'z'));
 	 		poistovna.lekar.get(0).evidujPacienta(new Pacient("Frederika Chripkova", "Osloboditelov 4 Trencin", "935201/1922", 'z'));
 			
 	  		poistovna.lekar.get(1).evidujPacienta(new Pacient("Gertruda Soplikova", "Osloboditelov 5 Trencin", "985825/4274", 'z'));
 	  		poistovna.lekar.get(1).evidujPacienta(new Pacient("Hyacinta Soplikova", "Osloboditelov 5 Trencin", "976121/8544", 'z'));
 	  		poistovna.lekar.get(1).evidujPacienta(new Pacient("Ilona Soplikova", "Osloboditelov 5 Trencin", "026023/1257", 'z'));
 	  		
 			poistovna.lekar.get(2).evidujPacienta(new Pacient("Jan Poloslepy", "Osloboditelov 6 Trencin", "970523/0678", 'm', true));
 	  		poistovna.lekar.get(2).evidujPacienta(new Pacient("Kristian Poloslepy", "Osloboditelov 6 Trencin", "021222/9930", 'm'));
 	  		poistovna.lekar.get(2).evidujPacienta(new Pacient("Ladislav Poloslepy", "Osloboditelov 6 Trencin", "930618/6461", 'm', true));
		}
  		else {
  	  		try {
  				poistovna.nacitaj();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
		}

		poistovna.vypisLekarov();
		for (Lekar l : poistovna.lekar) {
			System.out.println("----------------------");
			l.vypisPacientov();
		}
			
		try {
			poistovna.uloz();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
