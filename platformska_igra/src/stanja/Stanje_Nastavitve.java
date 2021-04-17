package stanja;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import igra.Igra;
import igra.Jezik;
import meni.Besedilo;
import meni.Drsnik;
import meni.Gumb;
import meni.SpustniMeni;
import viri.Slike;

public class Stanje_Nastavitve extends Stanje{
	
	private Besedilo besedilo1;
	private Besedilo besedilo2;
	private Drsnik glasnost;
	private Gumb nazaj;
	private Gumb shrani;
	private SpustniMeni jezik;
	
	private Igra igra;
	
	public Stanje_Nastavitve(Igra igra) {
		this.igra = igra;
		
		nazaj = new Gumb(Jezik.nazaj, 10, 10, 180, 50, 30,Slike.gumb,igra);
		besedilo1 = new Besedilo(Jezik.glasnost,20,150,40,new Color(173,166,158),igra);
		glasnost = new Drsnik(20,180,400,20,igra.getNastavitve().getGlasnost(),igra);
		besedilo2 = new Besedilo(Jezik.jezik,20,260,40,new Color(173,166,158),igra);
		jezik = new SpustniMeni(20,300,400,40,30,Jezik.jeziki,igra.getNastavitve().getJezik(),igra);
		shrani = new Gumb(Jezik.shrani,20,500,200,60,40,Slike.gumb,igra);
	}

	@Override
	public void posodobiStanje() throws IOException {
		if(igra.getTipke().getEsc())
			igra.spremeniStanjeNaPredhodno();
		
		glasnost.posodobiStanje();
		nazaj.posodobiStanje();
		shrani.posodobiStanje();
		jezik.posodobiStanje();
		
		if(nazaj.jePritisnjen())
			igra.spremeniStanjeNaMeni();
		
		if(shrani.jePritisnjen())
			shraniNastavitve();
	}

	@Override
	public void narisi(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0,0, igra.getDolzina(),igra.getVisina());
		g.drawImage(Slike.ozadje_nastavitve,0,0,igra.getDolzina(),igra.getVisina(),null);
		g.setColor(Color.black);
		
		glasnost.narisi(g);
		nazaj.narisi(g);
		shrani.narisi(g);
		besedilo1.narisi(g);
		besedilo2.narisi(g);
		jezik.narisi(g);
	}
	
	public void shraniNastavitve() throws IOException {
		igra.getNastavitve().shraniGlasnost(glasnost.getVrednost());
		igra.getNastavitve().shraniJezik(jezik.getVrednost());
		
	}

}
