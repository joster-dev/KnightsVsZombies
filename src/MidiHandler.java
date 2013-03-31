import java.io.*;
import javax.sound.midi.*;

public class MidiHandler {

	public Sequencer midiPlayer;

	public void startMidi(String midFilename) {
		try {
			File midiFile = new File(midFilename);
			Sequence song = MidiSystem.getSequence(midiFile);
			midiPlayer = MidiSystem.getSequencer();
			midiPlayer.open();
			midiPlayer.setSequence(song);
			midiPlayer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); // infinite loop until interrupted
//			setVolume(1);
			midiPlayer.start();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopMidi() {
		if( !midiPlayer.isRunning() )
			midiPlayer.stop();
        midiPlayer.close();
	}

	public void setTempo(float tempo) {
		midiPlayer.setTempoFactor(tempo);
	}
	
	// currently does not work... need a way
	// to set the volume to a desired value...
/*	public void setVolume(int volume) {
	    try {
	    	ShortMessage volumeMessage = new ShortMessage();
	    	for (int i = 0; i < 16; i++) {
	    		volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, volume);
	    		MidiSystem.getReceiver().send(volumeMessage, -1);
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}	*/
}

/*import java.io.*;
import javax.sound.midi.*;

// UNFINISHED!!! THIS WILL BE USED FOR ADDING MIDI/CHIPTUNES...

public class MidiSoundTest {
   private static Sequencer midiPlayer;
   
   // testing main method
//   public static void main(String[] args) {
   public void changeBackToMain() {
      startMidi("res/Sounds/flourish.mid");     // start the midi player
      try {
         Thread.sleep(6000);   // delay
      } catch (InterruptedException e) { }
      System.out.println("faster");
      midiPlayer.setTempoFactor(2.0F);   // >1 to speed up the tempo
      try {
         Thread.sleep(2000);   // delay
      } catch (InterruptedException e) { }
      midiPlayer.stop();
   
      // Do this on every move step, you could change to another song
      if (!midiPlayer.isRunning()) {  // previous song finished
         // reset midi player and start a new song
         midiPlayer.stop();
         midiPlayer.close();
//         startMidi("song2.mid");
      }
   }
   
   public static void startMidi(String midFilename) {
      try {
         File midiFile = new File(midFilename);
         Sequence song = MidiSystem.getSequence(midiFile);
         midiPlayer = MidiSystem.getSequencer();
         midiPlayer.open();
         midiPlayer.setSequence(song);
         midiPlayer.setLoopCount(0); // repeat 0 times (play once)
         midiPlayer.start();
      } catch (MidiUnavailableException e) {
         e.printStackTrace();
      } catch (InvalidMidiDataException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}		*/
