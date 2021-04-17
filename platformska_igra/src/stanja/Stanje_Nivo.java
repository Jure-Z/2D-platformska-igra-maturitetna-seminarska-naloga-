package stanja;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;

import elementi_igre.Igralec;
import elementi_igre.Kamera;
import elementi_igre.Ozadje;
import elementi_igre.Svet;
import igra.Igra;
import igra.Jezik;
import igra.Podatki;
import kocke.Kocka;
import meni.Gumb;
import meni.KomponentaMenija;
import viri.Slike;

public class Stanje_Nivo extends Stanje {
	
	private Igra igra;
	private Podatki podatki;
	private int stNivoja;
	
	private boolean pavza = false;
	private boolean dovoliSprememboPavze = false;
	private boolean meniZmaga = false;
	private boolean zmaga = false;
	private boolean smrt = false;
	private int odštevanje;
	
	private Svet svet;
	private Kocka kocka;
	private Igralec igralec1;
	private Kamera kamera;
	
	private Gumb glavniMeni;
	private Gumb izbiraNivoja;
	private Gumb ponastavitev;
	private Gumb pomoc;
	
	private Gumb naslednjiNivo;
	
	private Graphics tempG;
	
	private boolean zadnjiNivo;
	
	private Ozadje ozadje;
		
	public Stanje_Nivo(Igra igra, int stNivoja) throws IOException {
		this.igra = igra;
		this.stNivoja = stNivoja;
		
		//inicalizacija elementov igre
		this.podatki = igra.getPodatki();
		svet = new Svet(igra, stNivoja);
		podatki.setSvet(svet);
		igralec1 = new Igralec(igra.getNivoji().getIgralecX(stNivoja), igra.getNivoji().getIgralecY(stNivoja), igra);
		podatki.setIgralec(igralec1);
		kamera = new Kamera(igra);
		podatki.setKamera(kamera);
		this.ozadje = new Ozadje(Slike.ozadje,igra);
		
		//inicializacija gumbov
		ponastavitev = new Gumb(Jezik.zacni_znova, 390, 120, 420, 70, 50,Slike.gumb, igra);
		izbiraNivoja = new Gumb(Jezik.izbira_nivoja, 390, 230, 420, 70, 50,Slike.gumb, igra);
		glavniMeni = new Gumb(Jezik.glavni_meni, 390, 340, 420, 70, 50,Slike.gumb, igra);
		pomoc = new Gumb(Jezik.vprasaj,1100,20,80,80,60,Slike.gumb_kvadrat,igra);
		
		//èe nivo ni zadnji se inicializira gumb naslednjiNivo
		if(igra.getNivoji().getStNivojev()-1 == stNivoja) {
			zadnjiNivo = true;
		}
		else {
			naslednjiNivo = new Gumb(Jezik.naslednji_nivo, 390, 190, 420, 70, 50,Slike.gumb, igra);
			zadnjiNivo = false;
		}
	}

	@Override
	public void posodobiStanje() throws IOException {
		
		//preverimo ali je igralec ustavil igro
		if(igra.getTipke().getEsc()) {
			if(dovoliSprememboPavze) {
				pavza = !pavza;
				dovoliSprememboPavze = false;
				prekiniIgro();
			}
		}
		else {
			dovoliSprememboPavze = true;
		}
		
		if(meniZmaga) {// èe je igralec zmagal posodobi stanje menija ob zmagi
			if(!zadnjiNivo) {
				naslednjiNivo.posodobiStanje();
				if(naslednjiNivo.jePritisnjen())
					igra.spremeniStanjeNaNivo(stNivoja+1);
			}
			glavniMeni.posodobiStanje();
			if(glavniMeni.jePritisnjen())
				igra.spremeniStanjeNaMeni();
		}
		
		else if(!pavza) {// èe igra ni ustavljena posodobi vsebino igre
			igralec1.posodobiStanje();
			svet.posodobiStanje();
			kamera.posodobiStanje();
			ozadje.posodobiStanje();
			zmaga();
			smrt();
		}
		else {// èe je igra ustavljena posodobi gumbe menija v igri
			ponastavitev.posodobiStanje();
			izbiraNivoja.posodobiStanje();
			glavniMeni.posodobiStanje();
			pomoc.posodobiStanje();
			if(glavniMeni.jePritisnjen())
				igra.spremeniStanjeNaMeni();
			if(izbiraNivoja.jePritisnjen())
				igra.spremeniStanjeNaIzbiraNivoja();
			if(ponastavitev.jePritisnjen()) {
				igralec1.ponastavi();
				pavza = false;
			}
			if(pomoc.jePritisnjen())
				igra.spremeniStanjeNaPomoc();
		}
	}

	@Override
	public void narisi(Graphics g) {
		if(meniZmaga) {// èe je igralec zmagal nariši meni ob zmagi
			g.setColor(new Color(39,41,37));
			g.fillRect(0, 0, igra.getDolzina(), igra.getVisina());
			if(!zadnjiNivo)
				naslednjiNivo.narisi(g);
			glavniMeni.narisi(g);
		}
		else if(!pavza) {// èe igra ni usstavljena nariši vsebino igre
			g.setColor(Color.black);
			g.fillRect(0,0, igra.getDolzina(), igra.getVisina());
			ozadje.narisi(g);
			if(!smrt) {
				igralec1.narisi(g);
				svet.narisi(g);
			}
			else {
				svet.narisi(g);
				igralec1.narisi(g);
			}
		}
		else {//èe je igra ustavljena nariši meni
			g.drawImage(ustavljenaIgra, 0, 0, igra.getDolzina(), igra.getVisina(),null);
			ponastavitev.narisi(g);
			izbiraNivoja.narisi(g);
			glavniMeni.narisi(g);
			pomoc.narisi(g);
		}
	}
	
	//metoda pripravi sliko ustavljene igre
	private BufferedImage ustavljenaIgra;
	
	public void prekiniIgro() {
		if(ustavljenaIgra == null)
			ustavljenaIgra = new BufferedImage(igra.getDolzina(),igra.getVisina(), BufferedImage.TYPE_INT_RGB);
		tempG = ustavljenaIgra.getGraphics();
		tempG.setColor(Color.black);
		tempG.fillRect(0,0, igra.getDolzina(),igra.getVisina());
		ozadje.narisi(tempG);
		igralec1.narisi(tempG);
		svet.narisi(tempG);
		tempG.setColor(new Color(200,200,200,150));
		tempG.fillRect(0,0, igra.getDolzina(),igra.getVisina());
		tempG.dispose();
	}
	
	//ko metoda zazna da je igralec zmagal poèaka 120 klikov, nato se pokaže meni ob zmagi
	public void zmaga() {
		if(igralec1.getZmaga() && !zmaga) {
			odštevanje = 120;
			igra.getZvok().zvok_Zmaga();
		}
		if(zmaga) {
			if(odštevanje < 0) {
				meniZmaga = true;
			}
			odštevanje--;
		}
		zmaga = igralec1.getZmaga();
	}
	public void smrt() throws IOException {
		if(igralec1.getSmrt() && !smrt) {
			igra.getZvok().zvok_Smrt();
			odštevanje = 120;
		}
		if(smrt) {
			if(odštevanje < 0) {
				igra.spremeniStanjeNaNivo(stNivoja);
			}
			odštevanje --;
		}
		smrt = igralec1.getSmrt();
	}

}
