package elementi_igre;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import igra.Igra;
import igra.Podatki;
import kocke.Cilj;
import kocke.Kocka;
import kocke.Opeka;
import kocke.Spica;
import kocke.Zrak;

public class Svet {
	
	private Igra igra;
	private Podatki podatki;
	
	private Kocka [] kocke = new Kocka[20];
	private Integer[][] svet;
	
	private int xVelikost;
	private int yVelikost;
	
	private SkupinaDelcev1 delci;
	
	
	public Svet(Igra igra, int stNivoja) throws IOException {
		
		this.igra = igra;
		this.podatki = igra.getPodatki();
		
		svet = igra.getNivoji().getNivo(stNivoja);
		
		//Izracunaj velikost sveta
		xVelikost = svet.length;
		yVelikost = svet[0].length;
		
		System.out.println(xVelikost);
		System.out.println(yVelikost);
		
		
		//Inicializiraj razlicne vrste kock
		kocke[0] = new Zrak(igra,0);
		kocke[1] = new Opeka(igra,1);
		kocke[2] = new Spica(igra,2);
		kocke[3] = new Spica(igra,3);
		kocke[4] = new Spica(igra,4);
		kocke[5] = new Spica(igra,5);
		kocke[6] = new Spica(igra,6);
		kocke[7] = new Spica(igra,7);
		kocke[8] = new Spica(igra,8);
		kocke[9] = new Spica(igra,9);
		kocke[10] = new Cilj(igra,10);
		
		int xCilj = 0;
		int yCilj = 0;
		for(int i=0; i<xVelikost; i++) {
			for(int e=0; e<yVelikost; e++) {
				if(svet[i][e]==10) {
					xCilj = i*Kocka.dolzina;
					yCilj = e*Kocka.visina;
				}
			}
		}
		
		delci = new SkupinaDelcev1(xCilj+Kocka.dolzina/2,yCilj+Kocka.visina/2,80,350,podatki);
	}
	public void posodobiStanje() {
		delci.posodobiStanje();
	}
	
	public void narisi(Graphics g) {
		int xZacetni = (int) (podatki.getIgralec().getXPozicija() - (igra.getDolzina()/2)) / Kocka.dolzina;
		int yZacetni = (int) (podatki.getIgralec().getYPozicija() - (igra.getVisina()/2)) / Kocka.visina;
		
		int xKoncni = (int) (podatki.getIgralec().getXPozicija() + (igra.getDolzina()/2)) / Kocka.dolzina;
		int yKoncni = (int) (podatki.getIgralec().getYPozicija() + (igra.getVisina()/2)) / Kocka.visina;
		
		if(xZacetni<0)
			xZacetni=0;
		if(yZacetni<0)
			yZacetni=0;
		if(xKoncni>xVelikost)
			xKoncni=xVelikost;
		if(yKoncni>yVelikost)
			yKoncni=yVelikost;
		
		for(int i=xZacetni; i<xVelikost; i++) {
			for(int e=yZacetni; e<yVelikost; e++) {
				kocke[svet[i][e]].narisi(g,i,e);
			}
		}
		
		delci.narisi(g);
	}
	public Kocka getKocka(double xPiksli, double yPiksli) {
		double xd=(xPiksli/Kocka.dolzina);
		double yd=(yPiksli/Kocka.visina);
		
		int x = (int)xd;
		int y = (int)yd;
		
		if(x<0 ||y<0||x>=xVelikost|| y>=yVelikost)
			return kocke[0];
		
		return kocke[svet[x][y]];
	}

	public int getXVelikost() {
		return xVelikost;
	}
	public int getYVelikost() {
		return yVelikost;
	}
	
}
