package elementi_igre;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import igra.Igra;
import igra.Podatki;
import kocke.Kocka;
import viri.Slike;

public class Igralec{
	private Igra igra;
	private Podatki podatki;
	
	private final  static int dolzina = 24;
	private final  static int visina = 40;
	
	final private static double gravitacija = 0.3;
	final private static double hitrostSkoka = 9;
	final private static double pospesek = 0.1;
	final private static double pojemek = 0.1;
	final private static double maxHitrostX = 2.5;
	final private static double maxHitrostY = 14;
	
	//Slika je veèja od dejanske velikosti, zato jo moramo zamakniti
	private final int odmikSlikeX = 6;
	private final int odmikSlikeY = 8;
	
	private double zacetniX,zacetniY;
	private double x,y;
	
	private double hitrostX = 0;
	private double hitrostY = 0;
	
	private boolean ustaviSkok = false;
	
	private boolean zmaga = false;
	private boolean smrt = false;
	
	private SkupinaDelcev2 delci;
	
	private boolean jeObrnjenDesno = true;
	private AnimacijaIgralca mirovanjeD;
	private AnimacijaIgralca hojaD;
	private AnimacijaIgralca mirovanjeL;
	private AnimacijaIgralca hojaL;
	private AnimacijaIgralca skokD;
	private AnimacijaIgralca skokL;

	public Igralec(int x, int y, Igra igra) {
		this.igra = igra;
		this.podatki = igra.getPodatki();
		this.zacetniX = x*Kocka.dolzina;
		this.zacetniY = y*Kocka.visina;
		this.x = zacetniX;
		this.y = zacetniY;
		
		mirovanjeD = new AnimacijaIgralca(1,Slike.mirovanjeD);
		hojaD = new AnimacijaIgralca(0.15,Slike.hojaD);
		mirovanjeL = new AnimacijaIgralca(1,Slike.mirovanjeL);
		hojaL = new AnimacijaIgralca(0.15,Slike.hojaL);
		skokD = new AnimacijaIgralca(1,Slike.skokD);
		skokL = new AnimacijaIgralca(1,Slike.skokL);
	}
	
	public void posodobiStanje() {
		
		if(!smrt && !zmaga) {
			izracunajHitrostX();
			izracunajHitrostY();
			premakniX();
			premakniY();
			orientacija();
		}
		
		if(pogojZaSmrt()) {
			this.smrt = true;
			if(delci == null) {
				delci = new SkupinaDelcev2((int)x+dolzina/2, (int)y+visina/2, 200,800,podatki);
			}
		}
		if(delci != null) {
			delci.posodobiStanje();
		}
			
		
		if(pogojZaZmago())
			this.zmaga = true;
			
		dotikTal();
		
		//animacija
		mirovanjeD.posodobiStanje();
		hojaD.posodobiStanje();
		mirovanjeL.posodobiStanje();
		hojaL.posodobiStanje();
		skokD.posodobiStanje();
		skokL.posodobiStanje();
	}
	
	public void izracunajHitrostX() {
		if(igra.getTipke().getDesno())
			hitrostX += pospesek;
		else {
			if(hitrostX > 0) {
				hitrostX -= pojemek;
				if((Math.abs(hitrostX)) < pojemek)
					hitrostX = 0;
				}
		}
		
		if(igra.getTipke().getLevo())
			hitrostX -= pospesek;
		else {
			if(hitrostX < 0) {
				hitrostX += pojemek;
				if((Math.abs(hitrostX)) < pojemek)
					hitrostX = 0;
			}
				
		}
		
		if(hitrostX>maxHitrostX)
			hitrostX = maxHitrostX;
		
		if(hitrostX < (-1*maxHitrostX))
			hitrostX = -1*maxHitrostX;
		
	}
	
	public void izracunajHitrostY() {
		
		hitrostY += gravitacija;
		
		
		if(seDotikaTal) {
			if(igra.getTipke().getGor()) {
				if(!ustaviSkok) {
					hitrostY = -1*hitrostSkoka;
					//igraj zvok Jump
					igra.getZvok().zvok_Skok();
					ustaviSkok = true;
				}
			}
			else {
				ustaviSkok = false;
			}
		}
		
		if(hitrostY>maxHitrostY)
			hitrostY = maxHitrostY;
		
		if(hitrostY < (-1*maxHitrostY))
			hitrostY = -1*maxHitrostY;
		
	}
	
	public void premakniX() {
		
		if(hitrostX < 0)
			if(!preveriTrk(x+hitrostX,y) && !preveriTrk(x+hitrostX,y+visina-1))
				x = (x + hitrostX);
			else {
				x = (int)(((x)/Kocka.dolzina))*Kocka.dolzina;
				hitrostX = 0;
			}

		if(hitrostX > 0) {
			if(!preveriTrk(x+dolzina+hitrostX,y) && !preveriTrk(x+dolzina+hitrostX,y+visina-1))
				x = (x + hitrostX);
			else {
				x = (int)((x+dolzina+hitrostX)/Kocka.dolzina)*Kocka.dolzina - dolzina;
				hitrostX = 0;
			}
			
		}
	}
	public void premakniY() {
		
		if(hitrostY < 0)
			if(!preveriTrk(x,y+hitrostY) && !preveriTrk(x+dolzina-1,y+hitrostY))
				y = (y + hitrostY);
			else {
				y = (int)(((y)/Kocka.visina))*Kocka.visina;
				hitrostY = 0;
			}
		
		if(hitrostY > 0)
			if(!preveriTrk(x,y+visina+hitrostY) && !preveriTrk(x+dolzina-1,y+visina+hitrostY))
				y = (y + hitrostY);
			else {
				y = (int)((y+visina+hitrostY)/Kocka.visina)*Kocka.visina - visina;
				hitrostY = 0;
			}
		
	}
	//preveri èe se toèka igralca nahaja v kocki
	public boolean preveriTrk(double novaXPozicija,double novaYPozicija) {
		return podatki.getSvet().getKocka(novaXPozicija, novaYPozicija).jeTrdno();
	}
	
	public boolean pogojZaSmrt() {
		//igralec je padel z nivoja
		if(y > podatki.getSvet().getYVelikost()*Kocka.visina)
			return true;
		
		//igralec se je dotaknil spice
		boolean dotik = false;
		if(podatki.getSvet().getKocka(x,y).jeNevarno())
			dotik =  (podatki.getSvet().getKocka(x,y).preveriTrk(this,x,y));
		if(podatki.getSvet().getKocka(x+dolzina-1,y).jeNevarno())
			dotik =  (podatki.getSvet().getKocka(x+dolzina-1,y).preveriTrk(this,x+dolzina-1,y));
		if(podatki.getSvet().getKocka(x+dolzina-1,y+visina-1).jeNevarno())
			dotik =  (podatki.getSvet().getKocka(x+dolzina-1,y+visina-1).preveriTrk(this,x+dolzina-1,y+visina-1));
		if(podatki.getSvet().getKocka(x,y+visina-1).jeNevarno())
			dotik =  (podatki.getSvet().getKocka(x,y+visina-1).preveriTrk(this,x,y+visina-1));
		
		return dotik;
	}
	
	public boolean pogojZaZmago() {
		if(podatki.getSvet().getKocka(x,y).jeCilj())
			return true;
		if(podatki.getSvet().getKocka(x+dolzina-1,y).jeCilj())
			return true;
		if(podatki.getSvet().getKocka(x+dolzina-1,y+visina-1).jeCilj())
			return true;
		if(podatki.getSvet().getKocka(x,y+visina-1).jeCilj())
			return true;
		
		return false;
	}
	
	private boolean seDotikaTal = false;
	public void dotikTal() {
		if(preveriTrk(x,y+visina+1)||preveriTrk(x+dolzina-1,y+visina+1)) {
			if(!seDotikaTal) {
				seDotikaTal = true;
				igra.getZvok().zvok_Padec();
			}
		}
		else
			seDotikaTal = false;
		
	}
	public void orientacija() {
		if(hitrostX>0)
			jeObrnjenDesno = true;
		else if(hitrostX<0)
			jeObrnjenDesno = false;
	}
	
	public void narisi(Graphics grafika) {
		if(!smrt && !zmaga) {
			BufferedImage slika = trenutnaSlika();
			grafika.drawImage(slika,(igra.getDolzina()/2-dolzina/2-odmikSlikeX), (igra.getVisina()/2-visina/2-odmikSlikeY), slika.getWidth(), slika.getHeight(),null);
		}
		if(delci != null) {
			delci.narisi(grafika);
		}
		
	}
	
	public BufferedImage trenutnaSlika() {
		if(!seDotikaTal) {
			if(jeObrnjenDesno)
				return skokD.getTrenutnaSlika();
			else
				return skokL.getTrenutnaSlika();
		}
		else if(hitrostX>0)
			return hojaD.getTrenutnaSlika();
		else if(hitrostX<0)
			return hojaL.getTrenutnaSlika();
		else if(jeObrnjenDesno)
			return mirovanjeD.getTrenutnaSlika();
		else
			return mirovanjeL.getTrenutnaSlika();
	}
	
	public void ponastavi() {
		x = zacetniX;
		y = zacetniY;
		hitrostX = 0;
		hitrostY = 0;
		smrt = false;
		zmaga = false;
	}
	
	public boolean getZmaga() {
		return zmaga;
	}
	public void setSmrt(boolean novoStanje) {
		this.smrt = novoStanje;
	}
	public boolean getSmrt() {
		return smrt;
	}
	
	public double getXPozicija() {
		return x;
	}
	public double getYPozicija() {
		return y;
	}
	public int getDolzina() {
		return dolzina;
	}
	public int getVisina() {
		return visina;
	}
	public void setXPozicija(int novaPozicija) {
		this.x = novaPozicija;
	}
	public void setYPozicija(int novaPozicija) {
		this.y = novaPozicija;
	}

}
