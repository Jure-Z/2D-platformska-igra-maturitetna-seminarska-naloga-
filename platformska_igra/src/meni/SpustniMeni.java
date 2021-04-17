package meni;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import igra.Igra;
import viri.Slike;

public class SpustniMeni extends KomponentaMenija{
	
	private int visina,dolzina,velikost;
	private Color barva =new Color(39,41,37),ozadje = new Color(97,92,87);
	private String [] izbira;
	private Igra igra;
	private AttributedString [] text;
	private Font font;
	
	private boolean miskaPrej = false;
	private boolean hover = false;
	private boolean odprt = false;
	private int vrednost;

	public SpustniMeni(int x, int y,int dolzina, int visina,int velikost, String [] izbira,int vrednost, Igra igra) {
		super(x, y, igra);
		
		this.igra = igra;
		
		this.dolzina = dolzina;
		this.visina = visina;
		
		text = new AttributedString [izbira.length];
		for(int i=0; i<izbira.length; i++) {
			text[i]= new AttributedString(izbira[i]);
			font = new Font("Arial",Font.BOLD, velikost);
			text[i].addAttribute(TextAttribute.FONT, font);
		}
		
		this.vrednost = vrednost;
		this.izbira = izbira;
	}

	@Override
	public void posodobiStanje() {
		//preverimo èe je miška na gumbu
		if(igra.getTipke().getX() > x && igra.getTipke().getX() < x+dolzina && igra.getTipke().getY() > y && igra.getTipke().getY() < y+visina) {
			hover = true;
		}
		else {
			hover = false;
		}
		
		if(hover)
			if(miskaPrej == true)
				if(igra.getTipke().getMiska1() == false) {
					odprt = true;
					igra.getZvok().zvok_Hover();
				}
		
		if(odprt) {
			if(miskaPrej == true)
				if(igra.getTipke().getMiska1() == false)
					if(igra.getTipke().getX() > x && igra.getTipke().getX() < x+dolzina && igra.getTipke().getY() > y+visina && igra.getTipke().getY() < y+(izbira.length+1)*visina){
						vrednost = (igra.getTipke().getY()-y-visina)/visina;
						igra.getZvok().zvok_Klik();
					}
		}
		
		if(!hover && odprt)
			if(miskaPrej == true)
				if(igra.getTipke().getMiska1() == false) {
					odprt = false;
					igra.getZvok().zvok_Hover();
				}
					
				
		
		miskaPrej = igra.getTipke().getMiska1();
		
		
	}

	@Override
	public void narisi(Graphics g) {
		if(!odprt) {
			g.drawImage(Slike.spustni_meni[0],x,y,dolzina,visina,null);
			
			FontMetrics m = g.getFontMetrics(font);
			int xText = x + 10;
			int yText = y + (visina - m.getHeight())/2 + m.getAscent();
			
			g.setColor(barva);
			g.drawString(text[vrednost].getIterator(), xText, yText);
		}
		else {
			g.drawImage(Slike.spustni_meni[1],x,y,dolzina,visina,null);
			
			for(int i=0; i<izbira.length; i++) {
				g.setColor(ozadje);
				g.fillRect(x,y+(i+1)*visina, dolzina,visina);
				
				FontMetrics m = g.getFontMetrics(font);
				int xText = x + 10;
				int yText = y + (visina - m.getHeight())/2 + m.getAscent() + (i+1)*visina;
				
				g.setColor(barva);
				g.drawString(text[i].getIterator(), xText, yText);
			}
		}
		
	}
	
	public int getVrednost() {
		return vrednost;
	}

}
