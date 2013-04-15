import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class SoundHandler {
	
	public AudioHandler audioHandler;
	
	public SoundHandler(AudioHandler a) {
		audioHandler = a;
	}

	public void playSound(String fileName) throws Exception {
		if(audioHandler.soundIsMuted()) {
			return;
		}
		// this will load and play the sound associated with the
		// file name used as an argument to this function
		try {
		    File file = new File(fileName);
		    AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		    // load the sound into memory (a Clip)
		    DataLine.Info info1 = new DataLine.Info(Clip.class, sound.getFormat());
		    Clip clip = (Clip) AudioSystem.getLine(info1);
		    clip.open(sound);
		    clip.start();
		}
	    catch (UnsupportedAudioFileException e1) { }
		catch (IOException e2) { } 
		catch (LineUnavailableException e3) { }
	}
}