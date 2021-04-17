package igra;

import elementi_igre.Igralec;
import elementi_igre.Kamera;
import elementi_igre.Svet;

public class Podatki {
	private Svet svet;
	private Igralec igralec;
	private Kamera kamera;
	
	public void setSvet(Svet svet) {
		this.svet = svet;
	}
	public Svet getSvet() {
		return svet;
	}
	public void setIgralec(Igralec igralec) {
		this.igralec = igralec;
	}
	public Igralec getIgralec() {
		return igralec;
	}
	public void setKamera(Kamera kamera) {
		this.kamera = kamera;
	}
	public Kamera getKamera() {
		return kamera;
	}
}
