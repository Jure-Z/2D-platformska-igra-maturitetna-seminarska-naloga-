package stanja;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import igra.Igra;
import igra.Jezik;
import meni.Gumb;
import viri.Slike;

public class Stanje_IzbiraNivoja extends Stanje {
	
	private Igra igra;
	
	private Gumb nazaj;
	private Gumb nivo0;
	private Gumb nivo1;
	private Gumb nivo2;
	private Gumb nivo3;
	private Gumb nivo4;
	private Gumb nivo5;
	private Gumb nivo6;
	private Gumb nivo7;
	private Gumb nivo8;
	private Gumb nivo9;
	
	public Stanje_IzbiraNivoja(Igra igra) {
		this.igra = igra;
		
		nazaj = new Gumb(Jezik.nazaj, 10, 10, 200, 60, 50,Slike.gumb, igra);
		nivo0 = new Gumb(Jezik._1, 145, 130, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo1 = new Gumb(Jezik._2, 335, 130, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo2 = new Gumb(Jezik._3, 525, 130, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo3 = new Gumb(Jezik._4, 715, 130, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo4 = new Gumb(Jezik._5, 905, 130, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo5 = new Gumb(Jezik._6, 145, 320, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo6 = new Gumb(Jezik._7, 335, 320, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo7 = new Gumb(Jezik._8, 525, 320, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo8 = new Gumb(Jezik._9, 715, 320, 150, 150, 100,Slike.gumb_kvadrat, igra);
		nivo9 = new Gumb(Jezik._10, 905, 320, 150, 150, 100,Slike.gumb_kvadrat, igra);
	}

	@Override
	public void posodobiStanje() throws IOException {
		if(igra.getTipke().getEsc())
			igra.spremeniStanjeNaPredhodno();
		
		nazaj.posodobiStanje();
		nivo0.posodobiStanje();
		nivo1.posodobiStanje();
		nivo2.posodobiStanje();
		nivo3.posodobiStanje();
		nivo4.posodobiStanje();
		nivo5.posodobiStanje();
		nivo6.posodobiStanje();
		nivo7.posodobiStanje();
		nivo8.posodobiStanje();
		nivo9.posodobiStanje();
		
		if(nazaj.jePritisnjen()) {
			igra.spremeniStanjeNaMeni();
		}
		if(nivo0.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(0);
		}
		if(nivo1.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(1);
		}
		if(nivo2.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(2);
		}
		if(nivo3.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(3);
		}
		if(nivo4.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(4);
		}
		if(nivo5.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(5);
		}
		if(nivo6.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(6);
		}
		if(nivo7.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(7);
		}
		if(nivo8.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(8);
		}
		if(nivo9.jePritisnjen()) {
			igra.spremeniStanjeNaNivo(9);
		}
	}

	@Override
	public void narisi(Graphics g) {
		g.setColor(new Color(39,41,37));
		g.fillRect(0,0, igra.getDolzina(),igra.getVisina());
		nazaj.narisi(g);
		nivo0.narisi(g);
		nivo1.narisi(g);
		nivo2.narisi(g);
		nivo3.narisi(g);
		nivo4.narisi(g);
		nivo5.narisi(g);
		nivo6.narisi(g);
		nivo7.narisi(g);
		nivo8.narisi(g);
		nivo9.narisi(g);
	}

}
