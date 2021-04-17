package igra;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tipke {
	
	private Igra igra;
	
	private boolean gor = false;
	private boolean dol = false;
	private boolean levo = false;
	private boolean desno = false;
	
	private boolean esc = false;
	
	private int x = 0;
	private int y = 0;
	private boolean miska1 = false;
	
	public Tipke(Igra igra) {
		this.igra = igra;
		
		igra.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tipkaPritisnjena(e);
			}
			public void keyReleased(KeyEvent e) {
				tipkaSpuscena(e);
			}
		});
		
		igra.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				miskaPritisnjena(e);
			}
			public void mouseReleased(MouseEvent e) {
				miskaSpuscena(e);
			}
		});
		
		igra.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				miskaPremaknjena(e);
			}
			public void mouseDragged(MouseEvent e) {
				miskaPremaknjena(e);
			}
		});
	}
	
	public void tipkaPritisnjena(KeyEvent e) {
		int tipka = e.getKeyCode();
		
		if(tipka==KeyEvent.VK_W)
			gor = true;
		if(tipka==KeyEvent.VK_S)
			dol = true;
		if(tipka==KeyEvent.VK_A)
			levo = true;
		if(tipka==KeyEvent.VK_D)
			desno = true;
		if(tipka==KeyEvent.VK_ESCAPE)
			esc = true;
	}
	public void tipkaSpuscena(KeyEvent e) {
		int tipka = e.getKeyCode();
		
		if(tipka==KeyEvent.VK_W)
			gor = false;
		if(tipka==KeyEvent.VK_S)
			dol = false;
		if(tipka==KeyEvent.VK_A)
			levo = false;
		if(tipka==KeyEvent.VK_D)
			desno = false;
		if(tipka==KeyEvent.VK_ESCAPE)
			esc = false;
	}
	
	public void miskaPremaknjena(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public void miskaPritisnjena(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			miska1 = true;
	}
	public void miskaSpuscena(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			miska1 = false;
	}

	public boolean getGor() {
		return gor;
	}

	public boolean getDol() {
		return dol;
	}

	public boolean getLevo() {
		return levo;
	}

	public boolean getDesno() {
		return desno;
	}
	public boolean getEsc() {
		return esc;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getMiska1() {
		return miska1;
	}
	
}
