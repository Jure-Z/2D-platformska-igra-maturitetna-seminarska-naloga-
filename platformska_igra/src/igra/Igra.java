package igra;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import stanja.Stanje;
import stanja.Stanje_IzbiraNivoja;
import stanja.Stanje_Meni;
import stanja.Stanje_Nastavitve;
import stanja.Stanje_Nivo;
import stanja.Stanje_Pomoc;
import viri.Nivoji;
import viri.Slike;
import viri.Zvok;

public class Igra extends JPanel implements Runnable{
	
	private Okno okno;
	private int dolzina;
	private int visina;
	
	private volatile boolean igra_poteka = false;
	
	private Thread nit;
	
	private Stanje stanje_nivo;
	private Stanje stanje_meni;
	private Stanje stanje_izbiraNivoja;
	private Stanje stanje_nastavitve;
	private Stanje stanje_pomoc;
	
	private Podatki podatki;
	private Nivoji nivoji;
	private Nastavitve nastavitve;
	private Jezik jezik;
	private Tipke tipke;
	private Zvok zvok;
	private Slike slike;
	
	Igra(int dolzina, int visina) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		this.dolzina = dolzina;
		this.visina = visina;
		
		//inicializira vse lastnosti igre
		inicializiraj();
		okno = new Okno(this);
		zacniIgro();
	}
	
	private void inicializiraj() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
		setFocusable(true);
		requestFocus();
		
		//dodamo keylistener in mouselistener
		tipke = new Tipke(this);
		
		//inicializiramo objekte 
		slike = new Slike();
		nivoji = new Nivoji();
		podatki = new Podatki();
		nastavitve = new Nastavitve(this);
		zvok = new Zvok(this);
		jezik = new Jezik(this);
		
		//inicializiramo razlièna stanja igre in stanje nastavimo na meni
		stanje_nivo = new Stanje_Nivo(this,0);
		stanje_meni = new Stanje_Meni(this);
		stanje_izbiraNivoja = new Stanje_IzbiraNivoja(this);
		stanje_nastavitve = new Stanje_Nastavitve(this);
		stanje_pomoc = new Stanje_Pomoc(this);
		Stanje.setStanje(stanje_meni);
	}
	
	private synchronized void zacniIgro(){
		if(nit == null || igra_poteka == false) {
			nit = new Thread(this);
			nit.start();
		}
	}
	
	public void ustaviIgro() {
		igra_poteka = false;
	}

	public void run() {/*
		igra_poteka = true;
		
		int fps = 80;
		double dovoljen_cas = 1000000000/fps;
		double porabljen_cas = 0;
		long trenutni_cas;
		long prejsnji_cas = System.nanoTime();
		
		long timer = 0;
		int ticks = 0;
		
		while(igra_poteka) {
			trenutni_cas = System.nanoTime();
			porabljen_cas += trenutni_cas-prejsnji_cas;
			timer += trenutni_cas - prejsnji_cas;
			prejsnji_cas = trenutni_cas;
			if(porabljen_cas > dovoljen_cas) {
				try {
					posodobiStanje();
				} catch (IOException e) {
					e.printStackTrace();
				}
				narisi();
				pokaziSliko();
				ticks ++;
				porabljen_cas -= dovoljen_cas;
			}
			else {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			if(timer>=1000000000) {
				System.out.println("FPS: "+ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		ustaviIgro();
		*/
		igra_poteka = true;
		
		int fps = 80;
		long perioda = 1000000000/fps;
		
		long casPrej,casPotem,razlikaCasa,casSpanja;
		long prespanCas = 0l;
		
		casPrej = System.nanoTime();
		
		while(igra_poteka) {
			try {
				posodobiStanje();
			} catch (IOException e) {
				e.printStackTrace();
			}
			narisi();
			pokaziSliko();
			
			casPotem = System.nanoTime();
			razlikaCasa = casPotem - casPrej;
			casSpanja = (perioda - razlikaCasa) - prespanCas;
			
			if(casSpanja > 0) {
				try {
					Thread.sleep(casSpanja/1000000l);
				}catch(InterruptedException e){
					prespanCas = (System.nanoTime() - casPotem) - casSpanja;
				}
			}
			else {
				prespanCas  = 0l;
			}
			casPrej = System.nanoTime();
			
		}
		System.exit(0);
	}
	
	private void posodobiStanje() throws IOException{
		if(Stanje.getStanje() != null)
			Stanje.getStanje().posodobiStanje();
		
		
	}
	
	private Graphics grafika;
	private BufferedImage slika = null;
	
	private void narisi() {
		
			if(slika == null)
				slika = new BufferedImage(dolzina,visina,BufferedImage.TYPE_INT_RGB);
			if(slika == null) {
				System.out.println("slika = null");
				return;
			}
			else {
				grafika = slika.getGraphics();
			}
			if(Stanje.getStanje() != null)
				Stanje.getStanje().narisi(grafika);
			
			Toolkit.getDefaultToolkit().sync();
			grafika.dispose();

	}
	
	private void pokaziSliko() {
		Graphics g;
		
		try {
			g = this.getGraphics();
			
			if((g != null ) && (slika != null)) {
				g.drawImage(slika,0,0,dolzina,visina,null);
				Toolkit.getDefaultToolkit().sync();
				g.dispose();
			}
		}
		catch(Exception e){
			System.out.println("Napaka pri grafiki: "+ e);
		}
	}
	
	//spreminajnje stanja igre
	public void spremeniStanjeNaNivo(int nivo) throws IOException {
		stanje_nivo = new Stanje_Nivo(this, nivo);
		Stanje.setStanje(stanje_nivo);
	}
	public void spremeniStanjeNaMeni() {
		Stanje.setStanje(stanje_meni);
	}
	public void spremeniStanjeNaIzbiraNivoja() {
		Stanje.setStanje(stanje_izbiraNivoja);
	}
	public void spremeniStanjeNaNastavitve() {
		Stanje.setStanje(stanje_nastavitve);
	}
	public void spremeniStanjeNaPomoc() {
		Stanje.setStanje(stanje_pomoc);
	}
	public void spremeniStanjeNaPredhodno() {
		Stanje.setStanje(Stanje.getPredhodnoStanje());
	}
	
	//get metode za objekte
	public int getDolzina() {
		return this.dolzina;
	}
	public int getVisina() {
		return this.visina;
	}
	public Tipke getTipke() {
		return tipke;
	}
	public Stanje getStanje() {
		return Stanje.getStanje();
	}
	public Podatki getPodatki() {
		return podatki;
	}
	public Nivoji getNivoji() {
		return nivoji;
	}
	public Zvok getZvok() {
		return zvok;
	}
	public Nastavitve getNastavitve() {
		return nastavitve;
	}
	public Jezik getJezik() {
		return jezik;
	}
	
	
}
