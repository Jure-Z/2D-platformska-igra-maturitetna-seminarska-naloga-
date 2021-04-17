package igra;

public class Jezik {
	public static final String [] jeziki = {"Slovenšèina","English","Deutsch"};
	public static final String [] naslov = {"PRECEJ VREDU IGRA","PRECEJ VREDU IGRA","PRECEJ VREDU IGRA"};
	public static final String [] igraj = {"igraj","play","spiel"};
	public static final String [] nastavitve = {"nastavitve","settings","einstellungen"};
	public static final String [] izhod = {"izhod","exit","ausgang"};
	public static final String [] nazaj = {"nazaj","back","zurück"};
	public static final String [] zacni_znova = {"zaèni znova","restart","wieder anfang"};
	public static final String [] izbira_nivoja = {"izbira nivoja","level selection","Niveau Auswahl"};
	public static final String [] glavni_meni = {"glavni meni","main menu","Hauptmenü"};
	public static final String [] shrani = {"shrani","save","speicher"};
	public static final String [] glasnost = {"glasnost","volume","Lautstärkeregler"};
	public static final String [] jezik = {"jezik","language","Sprach"};
	public static final String [] naslednji_nivo = {"naslednji nivo","next level","nächste Niveau"};
	
	public static final String [] vprasaj = {"?","?","?"};
	public static final String [] _1 = {"1","1","1"};
	public static final String [] _2 = {"2","2","2"};
	public static final String [] _3 = {"3","3","3"};
	public static final String [] _4 = {"4","4","4"};
	public static final String [] _5 = {"5","5","5"};
	public static final String [] _6 = {"6","6","6"};
	public static final String [] _7 = {"7","7","7"};
	public static final String [] _8 = {"8","8","8"};
	public static final String [] _9 = {"9","9","9"};
	public static final String [] _10 = {"10","10","10"};
	
	
	private int trenutniJezik;
	
	public Jezik(Igra igra) {
		trenutniJezik = igra.getNastavitve().getJezik(); 
	}
	
	public void setJezik(int x) {
		trenutniJezik = x;
	}
	
	public int getTrenutni() {
		return trenutniJezik;
	}
	
}
