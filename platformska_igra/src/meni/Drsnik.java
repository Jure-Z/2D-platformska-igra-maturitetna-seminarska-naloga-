package meni;

import java.awt.Color;
import java.awt.Graphics;

import igra.Igra;

public class Drsnik extends KomponentaMenija{
	
	int dolzina,visina,dolzinaGumb,visinaGumb;
	double vrednost;
	double pozicija;
	
	boolean hover = false;
	boolean pritisnjen = false;
	boolean slediMiski = false;
	
	int miskaXPrejsnji;
	
	public Drsnik(int x, int y, int dolzina, int visina, double vrednost, Igra igra) {
		super (x,y,igra);
		
		this.dolzina = dolzina;
		this.visina = visina;
		this.dolzinaGumb = dolzina/20;
		this.visinaGumb = visina*6/5;
		
		this.vrednost = vrednost;
		this.pozicija = (dolzina-dolzinaGumb)*vrednost;
	}

	@Override
	public void posodobiStanje() {
		if(igra.getTipke().getX() > x+pozicija && igra.getTipke().getX() < x+pozicija+dolzinaGumb && igra.getTipke().getY() > y-(visinaGumb-visina)/2 && igra.getTipke().getY() < y-(visinaGumb-visina)/2+visinaGumb) {
			hover = true;
		}
		else {
			hover = false;
		}
		
		if(hover && igra.getTipke().getMiska1()) {
			//�e mi�ka v prej�njem krogu �e ni bila pritisnjena zabele�imo njen polo�aj
			if(!pritisnjen) {
				miskaXPrejsnji = igra.getTipke().getX();
				slediMiski = true;
				pritisnjen = true;
			}
		}
		else
			pritisnjen = false;
		
		if(!igra.getTipke().getMiska1())
			slediMiski = false;
		
		if(slediMiski) {
			pozicija += igra.getTipke().getX() - miskaXPrejsnji;
			miskaXPrejsnji = igra.getTipke().getX();
		}
		
		if(pozicija<0)
			pozicija = 0;
		
		if(pozicija>dolzina-dolzinaGumb)
			pozicija = dolzina-dolzinaGumb;
		
		//izra�unamo vrednost drsnika
		vrednost = pozicija / (dolzina-dolzinaGumb);
	}

	@Override
	public void narisi(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, dolzina, visina);
		g.setColor(new Color(173,166,158));
		g.fillRect((int) (x+pozicija), y-(visinaGumb-visina)/2, dolzinaGumb,visinaGumb);
	}
	
	public double getVrednost() {
		return vrednost;
	}
}
