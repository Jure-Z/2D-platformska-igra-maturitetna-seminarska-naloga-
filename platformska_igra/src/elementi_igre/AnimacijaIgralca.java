package elementi_igre;

import java.awt.image.BufferedImage;
import viri.Slike;

public class AnimacijaIgralca {
	
	private BufferedImage [] slike;
	
	private int index;
	private long interval;
	public long stevec;
	private long prejsnjiCas;
	
	public AnimacijaIgralca(double interval,BufferedImage[] slike) {
		this.slike = slike;
		double intervalNano = 1000000000*interval;
		this.interval = (long) intervalNano;
		index = 0;
		stevec = 0;
	}
	
	public void posodobiStanje() {
		stevec += System.nanoTime()-prejsnjiCas;
		prejsnjiCas = System.nanoTime();
		
		if(stevec>interval) {
			index ++;
			stevec = 0;
			if(index>=slike.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getTrenutnaSlika() {
		return slike[index];
	}
}
