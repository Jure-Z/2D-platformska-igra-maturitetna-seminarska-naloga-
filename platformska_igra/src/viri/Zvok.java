package viri;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import igra.Igra;

public class Zvok {
	
	private Igra igra;
	
	private AudioInputStream input;
	private Clip klik;
	private Clip hover;
	private Clip skok;
	private Clip smrt;
	private Clip padec;
	private Clip zmaga;
	
	private final String pot1 = "res\\Zvoki\\klik.wav";
	private final String pot2 = "res\\Zvoki\\hover.wav";
	//https://freesound.org/people/Lefty_Studios/sounds/369515/
	private final String pot3 = "res\\Zvoki\\skok.wav";
	//https://freesound.org/people/likeclockwork/sounds/168313/
	private final String pot4 = "res\\Zvoki\\smrt.wav";
	private final String pot5 = "res\\\\Zvoki\\\\padec.wav";
	//https://freesound.org/people/xtrgamr/sounds/277441/
	private final String pot6 = "res\\Zvoki\\zmaga.wav";
	
	public Zvok(Igra igra) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		this.igra = igra;
		
		input = AudioSystem.getAudioInputStream(new File(pot1).getAbsoluteFile());
		klik = AudioSystem.getClip();
		klik.open(input);
		
		input = AudioSystem.getAudioInputStream(new File(pot2).getAbsoluteFile());
		hover = AudioSystem.getClip();
		hover.open(input);
		
		input = AudioSystem.getAudioInputStream(new File(pot3).getAbsoluteFile());
		skok = AudioSystem.getClip();
		skok.open(input);
		
		input = AudioSystem.getAudioInputStream(new File(pot4).getAbsoluteFile());
		smrt = AudioSystem.getClip();
		smrt.open(input);
		
		input = AudioSystem.getAudioInputStream(new File(pot5).getAbsoluteFile());
		padec = AudioSystem.getClip();
		padec.open(input);
		
		input = AudioSystem.getAudioInputStream(new File(pot6).getAbsoluteFile());
		zmaga = AudioSystem.getClip();
		zmaga.open(input);
		
		setGlasnost(igra.getNastavitve().getGlasnost());
		
	}
	
	public void zvok_Klik() {
		klik.setFramePosition(0);
		klik.loop(0);
		klik.start();
	}
	
	public void zvok_Hover() {
		hover.setFramePosition(0);
		hover.loop(0);
		hover.start();
	}
	
	public void zvok_Skok() {
		skok.setFramePosition(0);
		skok.loop(0);
		skok.start();
	}
	
	public void zvok_Smrt() {
		smrt.setFramePosition(0);
		smrt.loop(0);
		smrt.start();
	}
	
	public void zvok_Padec() {
		padec.setFramePosition(0);
		padec.loop(0);
		padec.start();
	}
	
	public void zvok_Zmaga() {
		zmaga.setFramePosition(0);
		zmaga.loop(0);
		zmaga.start();
	}
	
	public void setGlasnost(double gain) {
		//https://jvalentino2.tripod.com/dft/index.html
		//http://www.java2s.com/Code/Java/Development-Class/SettingtheVolumeofaSampledAudioPlayer.htm
		float dB = (float) (Math.log(gain == 0.0 ? 0.0001 :gain) / Math.log(10.0) * 20.0);
		System.out.println(dB);
		
		FloatControl glasnost = (FloatControl) klik.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		glasnost = (FloatControl) hover.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		glasnost = (FloatControl) skok.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		glasnost = (FloatControl) smrt.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		glasnost = (FloatControl) padec.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		glasnost = (FloatControl) zmaga.getControl(FloatControl.Type.MASTER_GAIN);
		glasnost.setValue(dB);
		
		
	}
	
	
}
