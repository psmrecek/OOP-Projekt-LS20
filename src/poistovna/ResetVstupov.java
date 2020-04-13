package poistovna;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import osoby.Lekar;
import osoby.Pacient;

public class ResetVstupov implements Serializable{
	private static final long serialVersionUID = 0;
	
	public ResetVstupov() {
		
	}
	
	public void reset() {
		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();
		

		// Pomocny if na naplnenie serializovaneho outputu
		boolean b = true;
  		if (b) {
  	  		try {
  				poistovna.vymazOutput();
  			} catch (ClassNotFoundException | IOException e1 ) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  	  		
  			List<Pacient> mojiPacienti = new ArrayList<>();
  			
  			poistovna.evidujPacienta("Dorota Chripkova", "Osloboditelov 4 Trencin", "025204/5464", 'Z', "d", "a");
  			poistovna.evidujPacienta("Emilia Chripkova", "Osloboditelov 4 Trencin", "965608/5076", 'Z', "e", "a");
  			poistovna.evidujPacienta("Frederika Chripkova", "Osloboditelov 4 Trencin", "935201/1922", 'Z', "f", "a");
  			poistovna.evidujPacienta("Gertruda Soplikova", "Osloboditelov 5 Trencin", "985825/4274", 'Z', "g", "a");
  			poistovna.evidujPacienta("Hyacinta Soplikova", "Osloboditelov 5 Trencin", "976121/8544", 'Z', "h", "a");
  			poistovna.evidujPacienta("Ilona Soplikova", "Osloboditelov 5 Trencin", "026023/1257", 'Z', "i", "a");
  			poistovna.evidujPacienta("Jan Poloslepy", "Osloboditelov 6 Trencin", "970523/0678", 'M', "j", "a");
  			poistovna.evidujPacienta("Kristian Poloslepy", "Osloboditelov 6 Trencin", "021222/9930", 'M', "k", "a");
  			poistovna.evidujPacienta("Ladislav Poloslepy", "Osloboditelov 6 Trencin", "930618/6461", 'M', "l", "a");
  			
  			
  			for (Pacient pacient : poistovna.pacienti) {
				mojiPacienti.add(pacient);
			}
  			
  	  		poistovna.evidujLekara("Arnost Vseobecny", "1. Maja 1 Trencin", "000312/6871", 'M', "arnost", "aaa");
 			poistovna.evidujLekara("Beata Vseobecna", "1. Maja 2 Trencin", "945728/5343", 'Z', "beata", "bbb");
 			poistovna.evidujLekara("Cyril Specialny", "1. Maja 3 Trencin ", "990113/8863", 'M' , "cyril", "ccc", "Oftamolog");
 			
 	  		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(0));
 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(1));
 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(2));
 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(6));
 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(7));
 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(8));
 	 		
 	 		poistovna.lekari.get(0).vydajVymennyListok(mojiPacienti.get(6));
 	 		poistovna.lekari.get(0).vydajVymennyListok(mojiPacienti.get(7));
 	 		poistovna.lekari.get(0).vydajVymennyListok(mojiPacienti.get(8));
 	 		
 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(3));
 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(4));
 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(5));
 	  		
 			poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(6));
 	  		poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(7));
 	  		poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(8));
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
		for (Lekar l : poistovna.lekari) {
			System.out.println("---------------------- Vypis pacientov lekara");
			l.vypisPacientov();
		}
		
		System.out.println("---------------------- Predpisovanie liekov");
		for (int i = 0; i < 3; i++) {
			for (int m = 0; m < 3; m++) {
				poistovna.lekari.get(i).vytvorPredpis(poistovna.lekari.get(i).vratPacienta(m));
			}
		}
		System.out.println("---------------------- Vypis predpisov");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Pacient pacient = poistovna.lekari.get(i).vratPacienta(j);
				if (pacient instanceof Pacient) 
					System.out.println(pacient.citajPredpis(0));
				
				
			}
		}
		
		try {
			poistovna.uloz();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();
//		
//
//		// Pomocny if na naplnenie serializovaneho outputu
//		boolean b = false;
//  		if (b) {
//  	  		try {
//  				poistovna.vymazOutput();
//  			} catch (ClassNotFoundException | IOException e1 ) {
//  				// TODO Auto-generated catch block
//  				e1.printStackTrace();
//  			}
//  	  		
//  			List<Pacient> mojiPacienti = new ArrayList<>();
//  			mojiPacienti.add(new Pacient("Dorota Chripkova", "Osloboditelov 4 Trencin", "025204/5464", 'z'));
//  			mojiPacienti.add(new Pacient("Emilia Chripkova", "Osloboditelov 4 Trencin", "965608/5076", 'z'));
//  			mojiPacienti.add(new Pacient("Frederika Chripkova", "Osloboditelov 4 Trencin", "935201/1922", 'z'));
//  			mojiPacienti.add(new Pacient("Gertruda Soplikova", "Osloboditelov 5 Trencin", "985825/4274", 'z'));
//  			mojiPacienti.add(new Pacient("Hyacinta Soplikova", "Osloboditelov 5 Trencin", "976121/8544", 'z'));
//  			mojiPacienti.add(new Pacient("Ilona Soplikova", "Osloboditelov 5 Trencin", "026023/1257", 'z'));
//  			mojiPacienti.add(new Pacient("Jan Poloslepy", "Osloboditelov 6 Trencin", "970523/0678", 'm', true));
//  			mojiPacienti.add(new Pacient("Kristian Poloslepy", "Osloboditelov 6 Trencin", "021222/9930", 'm'));
//  			mojiPacienti.add(new Pacient("Ladislav Poloslepy", "Osloboditelov 6 Trencin", "930618/6461", 'm', true));
////  		volniPacienti.add();
//  			
//  	  		poistovna.evidujLekara("Arnost Vseobecny", "1. Maja 1 Trencin", "000312/6871", 'm');
// 			poistovna.evidujLekara("Beata Vseobecna", "1. Maja 2 Trencin", "945728/5343", 'z');
// 			poistovna.evidujLekara("Cyril Specialny", "1. Maja 3 Trencin ", "990113/8863", 'm' , "Ocny lekar");
// 			
// 	  		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(0));
// 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(1));
// 	 		poistovna.lekari.get(0).evidujPacienta(mojiPacienti.get(2));
// 			
// 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(3));
// 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(4));
// 	  		poistovna.lekari.get(1).evidujPacienta(mojiPacienti.get(5));
// 	  		
// 			poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(6));
// 	  		poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(7));
// 	  		poistovna.lekari.get(2).evidujPacienta(mojiPacienti.get(8));
//		}
//  		else {
//  	  		try {
//  				poistovna.nacitaj();
//  			} catch (ClassNotFoundException | IOException e1 ) {
//  				// TODO Auto-generated catch block
//  				e1.printStackTrace();
//  			}
//		}
//  		System.out.println("---------------------- Vypis lekarov");
//		poistovna.vypisLekarov();
//		for (Lekar l : poistovna.lekari) {
//			System.out.println("---------------------- Vypis pacientov lekara");
//			l.vypisPacientov();
//		}
//		
//		System.out.println("---------------------- Predpisovanie liekov");
//		for (int i = 0; i < 3; i++) {
//			for (int m = 0; m < 3; m++) {
//				poistovna.lekari.get(i).vytvorPredpis(poistovna.lekari.get(i).vratPacienta(m));
//			}
//		}
//		System.out.println("---------------------- Vypis predpisov");
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				Pacient pacient = poistovna.lekari.get(i).vratPacienta(j);
//				if (pacient instanceof Pacient) 
//					System.out.println(pacient.citajPredpis());
//				
//				
//			}
//		}
//		
//		try {
//			poistovna.uloz();
//		} catch (ClassNotFoundException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//
	
}
