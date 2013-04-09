import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class SoundHandler {
	
	public AudioHandler audioHandler;
	
	public SoundHandler(AudioHandler a) {
		audioHandler = a;
	}

	public void playSound(String fileName) throws Exception {
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

/*import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundHandler {

	private Clip clip;
	
	public SoundHandler(String fileName) {
		try {
			// Open an audio input stream.
			URL url = this.getClass().getClassLoader().getResource(fileName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}		*/


/*import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
   
// UNFINISHED!!! THIS CODE WILL BE ALTERED TO ADD SOUND EFFECTS TO GAME,
// SWORD SWIPES, ZOMBIE GROANS, ETC.!!

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundHandler.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundHandler.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundHandler.volume to mute the sound.
 */
/*public enum SoundHandler {
   BITEDUST("res/Sounds/bitedust.wav"),		// lose round
   EXPLOSION("res/Sounds/explosion.wav"),	// explosion
   THUNK("res/Sounds/thunk.wav");			// button click?
   
   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundHandler(String soundFileName) {
      try {
         // Use URL (instead of File) to read from disk and JAR.
         URL url = this.getClass().getClassLoader().getResource(soundFileName);
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
      }
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}	*/