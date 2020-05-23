package osoby;

/**
 * Rozhranie na zistovanie a nastavovanie osobnych udajov.
 * @author PeterSmrecek
 *
 */
public interface ZistiOsobneUdaje {
	
	/**
	 * Getter pre meno pouzivatela.
	 * @return meno pouzivatela
	 */
	String zistiMeno();
	
	/**
	 * Getter pre adresu pouzivatela.
	 * @return adresa pouzivatela
	 */
	String zistiAdresu();
	
	/**
	 * Getter pre rodne cislo pouzivatela.
	 * @return rodne cislo pouzivatela
	 */
	String zistiRodneCislo();
	
	/**
	 * Getter pre pohlavie pouzivatela.
	 * @return pohlavie pouzivatela
	 */
	char zistiPohlavie();
	
	/**
	 * Predvolena metoda na vratenie celeho retazca obsahujuceho vsetky udaje.
	 * @param meno 			meno pouzivatela
	 * @param adresa		adresa pouzivatela
	 * @param rodnec		rodne cislo pouzivatela
	 * @param pohlavie		pohlavie pouzivatela
	 * @return				retazec s textom vsetkych udajov o pouzivatelovi
	 */
	default String vsetkyUdaje(String meno, String adresa, String rodnec, char pohlavie) {
		return ("Prihlaseny " + meno + ", " + adresa + ", " + rodnec + ", " + pohlavie + ".\n");
	}
}
