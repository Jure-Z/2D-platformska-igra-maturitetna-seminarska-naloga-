package meni;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import igra.Igra;

//https://www.baeldung.com/java-add-text-to-image#:~:text=%20Adding%20Text%20to%20an%20Image%20in%20Java,left%20of%20an%20image%20let's%20see...%20More

public class Besedilo extends KomponentaMenija {
	
	private String [] vsebina;
	private AttributedString text;
	private Font font;
	private Color barva;
	
	public Besedilo(String [] vsebina, int x, int y, int velikost,Color barva, Igra igra){
		super(x,y,igra);
		
		this.vsebina = vsebina;
		this.barva = barva;
		
		font = new Font("Arial",Font.BOLD, velikost);
	}

	@Override
	public void posodobiStanje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void narisi(Graphics g) {
		text = new AttributedString(vsebina[igra.getJezik().getTrenutni()]);
		text.addAttribute(TextAttribute.FONT, font);
		
		g.setColor(barva);
		g.drawString(text.getIterator(), x, y);
		
	}
	
}
