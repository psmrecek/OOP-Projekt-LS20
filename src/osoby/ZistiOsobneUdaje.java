package osoby;

public interface ZistiOsobneUdaje {
	String zistiMeno();
	String zistiAdresu();
	String zistiRodneCislo();
	char zistiPohlavie();
	
	default String vsetkyUdaje(String meno, String adresa, String rodnec, char pohlavie) {
		return ("Prihlaseny " + meno + ", " + adresa + ", " + rodnec + ", " + pohlavie + ".\n");
	}
}
