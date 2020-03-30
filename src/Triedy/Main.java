package Triedy;

import java.io.*;

public class Main implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();

		// Pomocny if na naplnenie serializovaneho outputu
  		if (false) {
  	  		try {
  				poistovna.vymazOutput();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  	  		
  	  		poistovna.evidujLekara("Arnost Kabel", "1. Maja 1 Trencin", "781111/1234", 'm');
 			poistovna.evidujLekara("Beata Mala", "1. Maja 2 Trencin", "786111/1234", 'z');
 			
 	  		poistovna.lekar.get(0).evidujPacienta("Cecilia Laskava", "Osloboditelov 4 Trencin", "875202/5421", 'z');
 	  		poistovna.lekar.get(0).evidujPacienta("Dorota Laskava", "Osloboditelov 4 Trencin", "975202/5421", 'z');
 	 		poistovna.lekar.get(0).evidujPacienta("Emilia Laskava", "Osloboditelov 4 Trencin", "075202/5421", 'z');
 			
 			poistovna.lekar.get(1).evidujPacienta("Faramira Maslova", "Osloboditelov 4 Trencin", "875202/5421", 'z');
 	  		poistovna.lekar.get(1).evidujPacienta("Gertruda Maslova", "Osloboditelov 4 Trencin", "975202/5421", 'z');
 	  		poistovna.lekar.get(1).evidujPacienta("Hyacinta Maslova", "Osloboditelov 4 Trencin", "075202/5421", 'z');
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
