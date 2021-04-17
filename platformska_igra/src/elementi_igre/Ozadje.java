package elementi_igre;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import igra.Igra;
import igra.Podatki;

public class Ozadje {
	private Igra igra;
	private Podatki podatki;
	
	private BufferedImage slika;
	
	private double x=0,y=0;
	private double faktor = 0.5;
	
	public Ozadje(BufferedImage slika,Igra igra){
		this.slika = slika;
		this.podatki = igra.getPodatki();
		this.igra = igra;
	}
	
	public void posodobiStanje() {
		this.x = (podatki.getKamera().getXOdmik()*faktor*-1)%igra.getDolzina();
		this.y = (podatki.getKamera().getYOdmik()*faktor*-1)%igra.getVisina();
		
	}
	
	public void narisi(Graphics g) {
		g.drawImage(slika, (int)x, (int)y, slika.getWidth(), slika.getHeight(), null);
		if(x<0) {
			g.drawImage(slika, (int)x+slika.getWidth(), (int)y, slika.getWidth(), slika.getHeight(), null);
			if(y<0) {
				g.drawImage(slika, (int)x, (int)y+slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
				g.drawImage(slika, (int)x+slika.getWidth(), (int)y+slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
			}
			else {
				g.drawImage(slika, (int)x, (int)y-slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
				g.drawImage(slika, (int)x+slika.getWidth(), (int)y-slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
			}
		}
		else {
			g.drawImage(slika, (int)x-slika.getWidth(), (int)y, slika.getWidth(), slika.getHeight(), null);
			if(y<0) {
				g.drawImage(slika, (int)x, (int)y+slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
				g.drawImage(slika, (int)x-slika.getWidth(), (int)y+slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
			}
			else {
				g.drawImage(slika, (int)x, (int)y-slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
				g.drawImage(slika, (int)x-slika.getWidth(), (int)y-slika.getHeight(), slika.getWidth(), slika.getHeight(), null);
			}
		}
	}
	
}
