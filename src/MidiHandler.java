import java.io.*;
import javax.sound.midi.*;

public class MidiHandler {

	public AudioHandler audioHandler;

	public Sequencer midiPlayer;
	private String currentFileName;
	
	public MidiHandler(AudioHandler a)
	{
		audioHandler = a;
		try {
			midiPlayer = MidiSystem.getSequencer();
//			midiPlayer.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void startMidi(String midiFileName) {
		closeMidi();
		currentFileName = midiFileName;
		try {
			File midiFile = new File(midiFileName);
			Sequence song = MidiSystem.getSequence(midiFile);
//			midiPlayer = MidiSystem.getSequencer();
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


	public void restartMidi() {
		midiPlayer.start();
	}


	public void stopMidi() {
		if( midiPlayer.isOpen() )
		{
			if( midiPlayer.isRunning() )
			{
				midiPlayer.stop();
			}
		}
	}


	public void closeMidi() {
		if( midiPlayer.isOpen() )
		{
			if( midiPlayer.isRunning() )
			{
				midiPlayer.stop();
			}
			midiPlayer.close();
		}
	}


	public void setTempo(float tempo) {
		midiPlayer.setTempoFactor(tempo);
	}


	public String getFileName() {
		return currentFileName;
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