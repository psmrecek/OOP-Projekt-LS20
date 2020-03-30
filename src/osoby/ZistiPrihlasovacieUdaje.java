package osoby;

public interface ZistiPrihlasovacieUdaje {
	String zistiNick();
	String zistiHeslo();
	void nastavPrihlasovacieUdaje(String nick, String heslo);
}
