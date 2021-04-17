package igra;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import viri.Slike;

public class Okno{
	
	private Igra igra;
	private int dolzina;
	private int visina;
	
	private JFrame frame;
	
	public Okno(Igra igra) {
		this.igra = igra;
		this.dolzina = igra.getDolzina();
		this.visina = igra.getVisina();
		
		ustvariOkno();
		
	}
	
	public void ustvariOkno() {
		
		frame = new JFrame();
		
		igra.setPreferredSize(new Dimension(dolzina,visina));
		igra.setMaximumSize(new Dimension(dolzina,visina));
		igra.setMinimumSize(new Dimension(dolzina,visina));
		frame.add(igra);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Slike.opeka);
		frame.setVisible(true);
		igra.setFocusable(true);
		igra.requestFocus();
	}

	

	
	

}
