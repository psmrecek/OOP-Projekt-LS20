package osoby;

/**
 * Rozhranie s metodami na zistovanie a nastavovanie prihlasovacich udajov.
 * @author PeterSmrecek
 *
 */
public interface ZistiPrihlasovacieUdaje {

	/**
	 * Getter pre prihlasovacie meno pouzivatela.
	 * @return prihlasovacie meno pouzivatela
	 */
	String zistiNick();
	
	/**
	 * Getter pre prihlasovacie heslo pouzivatela.
	 * @return prihlasovacie heslo pouzivatela
	 */
	String zistiHeslo();
	
	/**
	 * Setter pre prihlasovacie meno a heslo pouzivatela.
	 * @param nick		prihlasovacie meno pouzivatela
	 * @param heslo		prihlasovacie heslo pouzivatela
	 */
	void nastavPrihlasovacieUdaje(String nick, String heslo);
}
