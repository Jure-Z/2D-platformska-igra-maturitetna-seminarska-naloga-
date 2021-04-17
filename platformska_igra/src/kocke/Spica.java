package kocke;

import java.awt.image.BufferedImage;

import elementi_igre.Igralec;
import igra.Igra;
import viri.Slike;

public class Spica extends Kocka{
	
	private Trikotnik trikotnik;

	public Spica(Igra igra,int id) {
		super(igra,id,Slike.spica1);
		
		switch(id) {
		case 2: this.slika = Slike.spica1;break;
		case 3: this.slika = Slike.spica2;break;
		case 4: this.slika = Slike.spica3;break;
		case 5: this.slika = Slike.spica4;break;
		case 6: this.slika = Slike.spica5;break;
		case 7: this.slika = Slike.spica6;break;
		case 8: this.slika = Slike.spica7;break;
		case 9: this.slika = Slike.spica8;break;
		}
			
		switch(id) {
		case 2: trikotnik = new Trikotnik(0,Kocka.visina,Kocka.dolzina,Kocka.visina,(double)Kocka.dolzina/2,0);break;
		case 3: trikotnik = new Trikotnik(0,0,0,Kocka.visina,Kocka.dolzina,(double)Kocka.visina/2);break;
		case 4: trikotnik = new Trikotnik(0,0,(double)Kocka.dolzina/2,Kocka.visina,Kocka.dolzina,0);break;
		case 5: trikotnik = new Trikotnik(0,(double)Kocka.visina/2,Kocka.dolzina,Kocka.visina,Kocka.dolzina,0);break;
		case 6: trikotnik = new Trikotnik(0,Kocka.visina,Kocka.dolzina,Kocka.visina,(double)Kocka.dolzina/2,(double)Kocka.visina/2);break;
		case 7: trikotnik = new Trikotnik(0,0,0,Kocka.visina,(double)Kocka.dolzina/2,(double)Kocka.visina/2);break;
		case 8: trikotnik = new Trikotnik(0,0,(double)Kocka.dolzina/2,(double)Kocka.visina/2,Kocka.dolzina,0); break;
		case 9: trikotnik = new Trikotnik((double)Kocka.dolzina/2,(double)Kocka.visina/2,Kocka.dolzina,Kocka.visina,Kocka.dolzina,0);
		}
	}
	
	public boolean jeNevarno() {
		return true;
	}
	
	//preveri trk med igralcem in spico
	public boolean preveriTrk(Igralec igralec, double ix, double iy) {
		double x=(int)((ix)/Kocka.dolzina)*Kocka.dolzina;
		double y=(int)((iy)/Kocka.visina)*Kocka.visina;
		
		//preverimo ali se skrajna toèka trikotnika nahaja v igralcu
		switch(id){
		case 2: case 6:
			if(igralec.getXPozicija()<trikotnik.x3+x && igralec.getXPozicija()+igralec.getDolzina()>trikotnik.x3+x) {
				if(igralec.getYPozicija()+igralec.getVisina()>trikotnik.y3+y)
					return true;
			}break;
		case 3: case 7:
			if(igralec.getYPozicija()<trikotnik.y3+y && igralec.getYPozicija()+igralec.getVisina()>trikotnik.y3+y) {
				if(igralec.getXPozicija()<trikotnik.x3+x)
					return true;
			}break;
		case 4: case 8:
			if(igralec.getXPozicija()<trikotnik.x2+x && igralec.getXPozicija()+igralec.getDolzina()>trikotnik.x2+x) {
				if(igralec.getYPozicija()<trikotnik.y2+y)
					return true;
			}break;
		case 5: case 9:
			if(igralec.getYPozicija()<trikotnik.y1+y && igralec.getYPozicija()+igralec.getVisina()>trikotnik.y1+y) {
				if(igralec.getXPozicija()+igralec.getDolzina()>trikotnik.x1+x)
					return true;
			}break;
		
		}
		
		//preverimo ali se toèka nahaja v trikotniku
		double P = Trikotnik.ploscina(trikotnik.x1+x,trikotnik.y1+y,trikotnik.x2+x,trikotnik.y2+y,trikotnik.x3+x,trikotnik.y3+y);
		double P1 = Trikotnik.ploscina(ix,iy,trikotnik.x2+x,trikotnik.y2+y,trikotnik.x3+x,trikotnik.y3+y);
		double P2 = Trikotnik.ploscina(trikotnik.x1+x,trikotnik.y1+y,ix,iy,trikotnik.x3+x,trikotnik.y3+y);
		double P3 = Trikotnik.ploscina(trikotnik.x1+x,trikotnik.y1+y,trikotnik.x2+x,trikotnik.y2+y,ix,iy);
		
		if(P1+P2+P3 <= P)
			return true;
		
		return false;
	}

}
