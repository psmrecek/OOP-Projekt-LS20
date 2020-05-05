package poistovna;

import java.io.*;
import java.util.List;

import osoby.*;

public class ResetVstupov implements Serializable{
	private static final long serialVersionUID = 0;
	
	public ResetVstupov() {
		
	}
	
	public void reset() {
		ZdravotnaPoistovna poistovna = new ZdravotnaPoistovna();

		try {
			poistovna.vymazOutput();
		} catch (ClassNotFoundException | IOException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		poistovna.evidujPacienta("Dorota Chripkova", "Osloboditelov 4 Trencin", "025204/5464", 'Z', "d", "a");
		poistovna.evidujPacienta("Emilia Chripkova", "Osloboditelov 4 Trencin", "965608/5076", 'Z', "e", "a");
		poistovna.evidujPacienta("Frederika Chripkova", "Osloboditelov 4 Trencin", "935201/1922", 'Z', "f", "a");
		poistovna.evidujPacienta("Gertruda Soplikova", "Osloboditelov 5 Trencin", "985825/4274", 'Z', "g", "a");
		poistovna.evidujPacienta("Hyacinta Soplikova", "Osloboditelov 5 Trencin", "976121/8544", 'Z', "h", "a");
		poistovna.evidujPacienta("Ilona Soplikova", "Osloboditelov 5 Trencin", "026023/1257", 'Z', "i", "a");
		poistovna.evidujPacienta("Jan Poloslepy", "Osloboditelov 6 Trencin", "970523/0678", 'M', "j", "a");
		poistovna.evidujPacienta("Kristian Poloslepy", "Osloboditelov 6 Trencin", "021222/9930", 'M', "k", "a");
		poistovna.evidujPacienta("Ladislav Poloslepy", "Osloboditelov 6 Trencin", "930618/6461", 'M', "l", "a");
		
		List<Pacient> mojiPacienti = poistovna.vratZoznamPacientov();
		
  		poistovna.evidujLekara("Arnost Vseobecny", "1. Maja 1 Trencin", "000312/6871", 'M', "arnost", "aaa");
		poistovna.evidujLekara("Beata Vseobecna", "1. Maja 2 Trencin", "945728/5343", 'Z', "beata", "bbb");
		poistovna.evidujLekara("Cyril Specialny", "1. Maja 3 Trencin ", "990113/8863", 'M' , "cyril", "ccc", "Oftamolog");
		poistovna.evidujLekara("Zoltan Vybusny", "1. Maja 4 Trencin ", "654123/9874", 'M' , "zoltan", "zzz", "Neurolog");
		
  		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(0));
 		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(1));
 		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(2));
 		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(6));
 		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(7));
 		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(8));
 		
  		poistovna.vratLekara(0).evidujPacienta(mojiPacienti.get(3));
  		poistovna.vratLekara(1).evidujPacienta(mojiPacienti.get(4));
  		poistovna.vratLekara(1).evidujPacienta(mojiPacienti.get(5));
  		
		poistovna.vratLekara(2).evidujPacienta(mojiPacienti.get(6));
  		poistovna.vratLekara(2).evidujPacienta(mojiPacienti.get(7));
  		poistovna.vratLekara(2).evidujPacienta(mojiPacienti.get(8));
  		
		poistovna.evidujLekarnika("Tomas Maly", "1. Maja 5 Trencin", "562314/1456", 'M', "tomas", "ttt");
		poistovna.evidujLekarnika("Ulrich Velky", "1. Maja 6 Trencin", "562314/1456", 'M', "ulrich", "uuu");	  		
 			
		
		try {
			poistovna.uloz();
			System.out.println("Zaznamy boli serializovane");
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
