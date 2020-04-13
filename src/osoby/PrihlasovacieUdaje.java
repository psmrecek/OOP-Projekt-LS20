package osoby;

import java.io.*;

public class PrihlasovacieUdaje implements Serializable{
	private static final long serialVersionUID = 0;
	
	String nick, heslo;
	
	public PrihlasovacieUdaje(String nick, String heslo) {
		this.nick = nick;
		this.heslo = heslo;
	}
	
	public String zistiNick() {
		return this.nick;
	}
	
	public String zistiHeslo() {
		return this.heslo;
	}
}
