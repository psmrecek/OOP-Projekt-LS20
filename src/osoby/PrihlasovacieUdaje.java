package osoby;

import java.io.*;

/**
 * Obsahuje prihlasovacie udaje pouzivatela. Instancie tejto triedy su agregovane lekarmi, lekarnikmi, pacientami aj manazerom poistovne.
 * @author PeterSmrecek
 *
 */
public class PrihlasovacieUdaje implements Serializable{
	private static final long serialVersionUID = 0;
	
	String nick, heslo;
	
	public PrihlasovacieUdaje(String nick, String heslo) {
		this.nick = nick;
		this.heslo = heslo;
	}
	
	/**
	 * Getter na zistenie prihlasovacieho mena
	 * @return		prihlasovacie meno
	 */
	public String zistiNick() {
		return this.nick;
	}
	
	/**
	 * Getter na zistenie prihlasovacieho hesla
	 * @return		prihlasovacie heslo
	 */
	public String zistiHeslo() {
		return this.heslo;
	}
}
