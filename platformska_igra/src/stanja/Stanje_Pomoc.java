package stanja;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import igra.Igra;
import igra.Jezik;
import meni.Gumb;
import viri.Slike;

public class Stanje_Pomoc extends Stanje {
	
	Igra igra;
	BufferedImage slike [];
	Gumb nazaj;
	
	public Stanje_Pomoc(Igra igra) {
		this.igra = igra;
		slike = Slike.pomoc;
		nazaj = new Gumb(Jezik.nazaj, 20, 20, 200, 60, 50,Slike.gumb,igra);
	}

	@Override
	public void posodobiStanje() throws IOException {
		nazaj.posodobiStanje();
		if(nazaj.jePritisnjen()) {
			igra.spremeniStanjeNaPredhodno();
		}
	}

	@Override
	public void narisi(Graphics g) {
		g.drawImage(slike[igra.getJezik().getTrenutni()], 0, 0, igra.getDolzina(), igra.getVisina(),null);
		nazaj.narisi(g);
	}
	

}
