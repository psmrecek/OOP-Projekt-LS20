package osoby;

/**
 * Funkcne rozhranie potrebne pre generovanie sprav pomocou lambda vyrazov.
 * @author PeterSmrecek
 *
 */
public interface Sprava {
	/**
	 * Metoda na generovanie sprav pomocou lambda vyrazov.
	 * @param text
	 * @return upraveny text
	 */
	String pridaj(String text);
}
