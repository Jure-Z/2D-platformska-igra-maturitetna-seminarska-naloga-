package elementi_igre;

import java.awt.Color;
import java.awt.Graphics;

import igra.Podatki;

public class Delec {
	private double x,y;
	private int velikost;
	private Podatki podatki;
	private Color barva;
	
	public Delec(double x,double y, int velikost,Color barva,Podatki podatki) {
		this.x = x;
		this.y = y;
		this.velikost = velikost;
		this.podatki = podatki;
		this.barva = barva;
	}
	
	public void premakni(double premikX, double premikY) {
		this.x += premikX;
		this.y += premikY;
	}
	
	public void narisi(Graphics g) {
		g.setColor(barva);
		g.fillRect((int)x - podatki.getKamera().getXOdmik(), (int)y-podatki.getKamera().getYOdmik(), velikost, velikost);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
