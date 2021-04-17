package meni;

import java.awt.Graphics;

import igra.Igra;

public abstract class KomponentaMenija {
	
	protected int x;
	protected int y;
	
	protected Igra igra;
	
	KomponentaMenija(int x,int y, Igra igra){
		this.x = x;
		this.y = y;
		this.igra = igra;
	}
	
	public abstract void posodobiStanje();
	
	public abstract void narisi(Graphics g);
}
