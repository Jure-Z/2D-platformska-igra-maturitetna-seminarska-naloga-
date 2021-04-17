package igra;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		Igra igra = new Igra(1200,600);
	}

}
