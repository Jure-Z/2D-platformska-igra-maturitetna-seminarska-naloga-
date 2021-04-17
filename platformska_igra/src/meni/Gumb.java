package meni;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import igra.Igra;
import viri.Slike;

public class Gumb extends KomponentaMenija{
	
	private int dolzina,visina,velikost;
	private Color barvaBesedila = new Color(39,41,37),barvaBesedilaAlt = new Color(173,166,158);
	private String [] vsebina;
	
	AttributedString text;
	Font font;
	BufferedImage slika [];
	
	private boolean hover = false;
	private boolean miskaPrej = false;
	
	private boolean pritisnjen = false;
	
	private boolean zvokIgran = false;

	public Gumb(String [] vsebina, int x, int y, int dolzina, int visina,int velikost,BufferedImage []slika,Igra igra) {
		super(x, y, igra);
		this.dolzina = dolzina;
		this.visina = visina;
		this.velikost = velikost;
		this.vsebina = vsebina;
		this.slika = slika;
	}

	@Override
	public void posodobiStanje() {
		if(igra.getTipke().getX() > x && igra.getTipke().getX() < x+dolzina && igra.getTipke().getY() > y && igra.getTipke().getY() < y+visina) {
			hover = true;
		}
		else {
			hover = false;
		}
		
		if(hover)
			if(miskaPrej == true) {
				if(igra.getTipke().getMiska1() == false) {
					pritisnjen = true;
				}
				else
					pritisnjen = false;
			}
			else
				pritisnjen = false;
		else
			pritisnjen = false;
		
		miskaPrej = igra.getTipke().getMiska1();
		
		zvok();	
	}

	@Override
	public void narisi(Graphics g) {
		if(!hover) {
			g.drawImage(slika[0],x,y,dolzina,visina,null);
			g.setColor(barvaBesedila);
		}
		else {
			g.drawImage(slika[1],x,y,dolzina,visina,null);
			g.setColor(barvaBesedilaAlt);
		}
		
		//Izraèunamo pozicijo texta
		text = new AttributedString(vsebina[igra.getJezik().getTrenutni()]);
		font = new Font("Arial",Font.BOLD, velikost);
		text.addAttribute(TextAttribute.FONT, font);
		
		FontMetrics m = g.getFontMetrics(font);
		int xText = x + (dolzina - m.stringWidth(vsebina[igra.getJezik().getTrenutni()]))/2;
		int yText = y + (visina - m.getHeight())/2 + m.getAscent();
		
		g.drawString(text.getIterator(), xText, yText);
		
	}
	
	public boolean jePritisnjen() {
		return pritisnjen;
	}
	
	public void zvok() {
		if(hover) {
			if(!zvokIgran) {
				igra.getZvok().zvok_Hover();
				zvokIgran = true;
			}
		}
		else
			zvokIgran = false;
		
		if(pritisnjen) {
			igra.getZvok().zvok_Klik();
		}
	}
	
	public void setY(int novY) {
		this.y = novY;
	}
	
}
