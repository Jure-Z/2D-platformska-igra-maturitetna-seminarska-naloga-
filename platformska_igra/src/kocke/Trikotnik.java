package kocke;

public class Trikotnik {
	public double x1;
	public double y1;
	public double x2;
	public double y2;
	public double x3;
	public double y3;
	
	public Trikotnik(double tocka1x,double tocka1y,double tocka2x,double tocka2y,double tocka3x,double tocka3y){
		this.x1 = tocka1x;
		this.y1 = tocka1y;
		this.x2 = tocka2x;
		this.y2 = tocka2y;
		this.x3 = tocka3x;
		this.y3 = tocka3y;
	}
	
	public static double ploscina(double tx1,double ty1,double tx2,double ty2,double tx3,double ty3) {
		return 0.5*Math.abs((tx2-tx1)*(ty3-ty1)-(tx3-tx1)*(ty2-ty1));
	}
	
}
