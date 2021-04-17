package stanja;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import igra.Igra;
import igra.Jezik;
import meni.Besedilo;
import meni.Gumb;
import meni.KomponentaMenija;
import viri.Slike;

public class Stanje_Meni extends Stanje {
	
	private Igra igra;
	
	private BufferedImage ozadje;
	private Gumb igraj;
	private Gumb nastavitve;
	private Gumb izhod;
	private Gumb pomoc;
	
	public Stanje_Meni(Igra igra){
		this.igra = igra;
		ozadje = Slike.ozadje_meni;
		igraj = new Gumb(Jezik.igraj,390,240,420,70,50,Slike.gumb,igra);
		nastavitve = new Gumb(Jezik.nastavitve,390,350,420,70,50,Slike.gumb,igra);
		izhod = new Gumb(Jezik.izhod,390,460,420,70,50,Slike.gumb,igra);
		pomoc = new Gumb(Jezik.vprasaj,1100,20,80,80,60,Slike.gumb_kvadrat,igra);
	}

	@Override
	public void posodobiStanje() {
		
		igraj.posodobiStanje();
		nastavitve.posodobiStanje();
		izhod.posodobiStanje();
		pomoc.posodobiStanje();
		
		if(igraj.jePritisnjen())
			igra.spremeniStanjeNaIzbiraNivoja();
		if(izhod.jePritisnjen())
			igra.ustaviIgro();
		if(nastavitve.jePritisnjen())
			igra.spremeniStanjeNaNastavitve();
		if(pomoc.jePritisnjen())
			igra.spremeniStanjeNaPomoc();
	}

	@Override
	public void narisi(Graphics g) {
		g.drawImage(ozadje, 0, 0, igra.getDolzina(), igra.getVisina(),null);
		g.setColor(Color.black);
		
		igraj.narisi(g);
		nastavitve.narisi(g);
		izhod.narisi(g);
		pomoc.narisi(g);
	}

}
