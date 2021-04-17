package elementi_igre;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import igra.Podatki;

public class SkupinaDelcev1 {
	private Podatki podatki;
	private ArrayList<Delec> delci = new ArrayList<Delec>();
	
	private int x,y,radij,maxStevilo;
	
	private final int maxVelikost = 5;
	private final int minVelikost = 2;
	
	private final double premik = 0.8;
	
	public SkupinaDelcev1(int x,int y, int radij, int maxStevilo,Podatki podatki) {
		this.podatki = podatki;
		this.x = x;
		this.y = y;
		this.radij = radij;
		this.maxStevilo = maxStevilo;
		
	}
	
	public void posodobiStanje() {
		//V arraylist dodamo nove delce
		int novi = maxStevilo - delci.size();
		for(int i=0; i<novi; i++) {
			//generiramo nakljuène koordinate v krogu
			//https://programming.guide/random-point-within-circle.html
			double a = Math.random() * 2 * Math.PI;
			double r = radij * Math.sqrt(Math.random());

			double xDelca = r * Math.cos(a) + x;
			double yDelca = r * Math.sin(a) + y;
			
			int velikost =(int) (Math.random() * (maxVelikost - minVelikost)) + minVelikost; 
			
			Delec d = new Delec(xDelca,yDelca,velikost,Color.white,podatki);
			delci.add(d);
			
		}
		
		for(int i=0; i<delci.size(); i++) {
			double razdaljaX = x - delci.get(i).getX();
			double razdaljaY = y - delci.get(i).getY();
			double predznakX = razdaljaX/Math.abs(razdaljaX);
			double predznakY = razdaljaY/Math.abs(razdaljaY);
			double razmerje = razdaljaX / razdaljaY;
			double premikX = Math.sqrt(Math.pow(premik, 2)/(1+Math.pow(razmerje, -2)));
			double premikY = Math.sqrt(Math.pow(premik,2)/(1+Math.pow(razmerje, 2)));
			delci.get(i).premakni(premikX*predznakX, premikY*predznakY);
		}
		for(int i=0; i<delci.size(); i++) {
			
			if(delci.get(i).getX()>x-2 && delci.get(i).getX()<x+2)
				if(delci.get(i).getY()>y-2 && delci.get(i).getY()<y+2)
					delci.remove(i);
		}
	}
	
	public void narisi(Graphics g) {
		for(int i=0; i<delci.size(); i++) {
			delci.get(i).narisi(g);
		}
	}
}
