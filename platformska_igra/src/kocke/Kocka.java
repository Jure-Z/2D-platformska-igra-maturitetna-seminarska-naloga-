package kocke;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import elementi_igre.Igralec;
import igra.Igra;
import igra.Podatki;

public class Kocka {
	
	protected BufferedImage slika;
	protected int id;
	
	public final static int dolzina = 40;
	public final static int visina = 40;
	
	private Igra igra;
	protected Podatki podatki;
	
	Kocka(Igra igra, int id,BufferedImage slika){
		this.id = id;
		this.slika = slika;
		this.igra = igra;
		this.podatki = igra.getPodatki();
	}
	
	public void narisi(Graphics g, int x, int y) {
		g.drawImage(this.slika,(x*dolzina)-podatki.getKamera().getXOdmik(),y*visina-podatki.getKamera().getYOdmik(),dolzina,visina,null);
	}
	
	public boolean jeTrdno() {
		return false;
	}
	
	public boolean jeNevarno() {
		return false;
	}
	
	public boolean jeCilj() {
		return false;
	}
	public int getId() {
		return id;
	}
	
	public boolean preveriTrk(Igralec igralec, double ix, double iy) {
		return false;
	}

}
