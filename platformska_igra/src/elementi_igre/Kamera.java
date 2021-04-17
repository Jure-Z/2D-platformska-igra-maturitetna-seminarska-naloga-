package elementi_igre;

import igra.Igra;
import igra.Podatki;

public class Kamera {
	
	private Igralec igralec;
	private Igra igra;
	
	private int xOdmik;
	private int yOdmik;
	
	public Kamera(Igra igra) {
		this.igra = igra;
		this.igralec = igra.getPodatki().getIgralec();
	}
	
	public void posodobiStanje() {
		xOdmik = (int) (igralec.getXPozicija()-(igra.getDolzina()/2)+ igralec.getDolzina()/2);
		yOdmik = (int) (igralec.getYPozicija()-(igra.getVisina()/2)+ igralec.getVisina()/2);
		
	}
	
	public int getXOdmik() {
		return xOdmik;
	}
	public int getYOdmik() {
		return yOdmik;
	}
}
