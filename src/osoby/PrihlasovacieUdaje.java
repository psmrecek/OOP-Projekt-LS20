package osoby;

import java.io.*;

public class PrihlasovacieUdaje implements Serializable{
	String nick, heslo;
	
	public PrihlasovacieUdaje(String nick, String heslo) {
		this.nick = nick;
		this.heslo = heslo;
	}
}
