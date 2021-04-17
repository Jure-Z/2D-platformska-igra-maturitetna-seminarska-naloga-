package elementi_igre;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import igra.Podatki;

public class SkupinaDelcev2 {
	private Podatki podatki;
	private ArrayList<Delec> delci = new ArrayList<Delec>();
	
	private int x,y,radij,maxStevilo;
	
	private final int maxVelikost = 5;
	private final int minVelikost = 2;
	
	private final double premik = 16;
	
	public SkupinaDelcev2(int x,int y, int radij, int maxStevilo,Podatki podatki) {
		this.podatki = podatki;
		this.x = x;
		this.y = y;
		this.radij = radij;
		this.maxStevilo = maxStevilo;
		
		for(int i=0; i<maxStevilo; i++) {
			//generiramo nakljuène koordinate v krogu
			//https://programming.guide/random-point-within-circle.html
			double a = Math.random() * 2 * Math.PI;
			double r = radij * Math.sqrt(Math.random());

			double xDelca = r * Math.cos(a) + x;
			double yDelca = r * Math.sin(a) + y;
			
			int velikost =(int) (Math.random() * (maxVelikost - minVelikost)) + minVelikost; 
			
			int steviloBarve = (int) (Math.random() * (6)) + 1; 
			Color barva = Color.white;
			switch(steviloBarve) {
			case 1:barva = Color.black;break;
			case 2:barva = new Color(0,162,232);break;
			case 3:barva = new Color(253,202,168);break;
			case 4:barva = new Color(189,149,69);break;
			case 5:barva = new Color(88,46,19);break;
			case 6:barva = Color.red;break;
			}
			
			Delec d = new Delec(xDelca,yDelca,velikost,barva,podatki);
			delci.add(d);
			
		}
		
	}
	
	public void posodobiStanje() {
		for(int i=0; i<delci.size(); i++) {
			double razdaljaX = x - delci.get(i).getX();
			double razdaljaY = y - delci.get(i).getY();
			double predznakX = -razdaljaX/Math.abs(razdaljaX);
			double predznakY = -razdaljaY/Math.abs(razdaljaY);
			double razmerje = razdaljaX / razdaljaY;
			double premikX = Math.sqrt(Math.pow(premik, 2)/(1+Math.pow(razmerje, -2)));
			double premikY = Math.sqrt(Math.pow(premik,2)/(1+Math.pow(razmerje, 2)));
			delci.get(i).premakni(premikX*predznakX, premikY*predznakY);
		}
	}
	
	public void narisi(Graphics g) {
		for(int i=0; i<delci.size(); i++) {
			delci.get(i).narisi(g);
		}
	}

}
