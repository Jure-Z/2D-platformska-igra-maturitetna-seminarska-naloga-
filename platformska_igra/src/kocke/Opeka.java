package kocke;

import java.awt.image.BufferedImage;

import igra.Igra;
import viri.Slike;

public class Opeka extends Kocka{
	
	public Opeka(Igra igra,int id) {
		super(igra,id,Slike.opeka);
	}
	
	public boolean jeTrdno() {
		return true;
	}
}
