package stanja;

import java.awt.Graphics;
import java.io.IOException;

import elementi_igre.Svet;

public abstract class Stanje {
	
	public abstract void posodobiStanje() throws IOException;
	
	public abstract void narisi(Graphics g);
	
	
	private static Stanje trenutnoStanje = null;
	private static Stanje predhodnoStanje = null;
	
	public static void setStanje(Stanje stanje) {
		predhodnoStanje = trenutnoStanje;
		trenutnoStanje = stanje;
	}
	
	public static Stanje getStanje() {
		return trenutnoStanje;
	}
	
	public static Stanje getPredhodnoStanje(){
		return predhodnoStanje;
	}
	
	
}
