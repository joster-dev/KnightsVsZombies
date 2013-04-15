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
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void startMidi(String midiFileName) {
		if(audioHandler.musicIsMuted())
			return;
		closeMidi();
		currentFileName = midiFileName;
		try {
			File midiFile = new File(midiFileName);
			Sequence song = MidiSystem.getSequence(midiFile);
			midiPlayer.open();
			midiPlayer.setSequence(song);
			midiPlayer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); // infinite loop until interrupted
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
		if(audioHandler.musicIsMuted())
				return;
		if(audioHandler.myFrame.settings.isVisible() && !(getFileName().equals("res/Sounds/opening.mid")))
			startMidi("res/Sounds/opening.mid");
		else if((!audioHandler.myFrame.settings.isVisible()) && !(getAppropriateSong(audioHandler.myFrame.gameScreen.level).equals(getFileName())))
			startMidi(getAppropriateSong(audioHandler.myFrame.gameScreen.level));			// figure this out!!!!*****
		else
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


	public String getAppropriateSong(int level) {

		String fileName = "";

		switch(level) 
		{
		case 1:	fileName = "res/Sounds/zelda.mid";		// need to change...
				break;
		case 2: fileName = "res/Sounds/2_swamp.mid";
				break;
		case 3: fileName = "res/Sounds/3_forest.mid";
				break;
		case 4: fileName = "res/Sounds/4_steampunk.mid";
				break;
		case 5: fileName = "res/Sounds/zelda.mid";		// need to change...
				break;
		case 6: fileName = "res/Sounds/zelda.mid";		// for infinite mode...
				break;
		default:fileName = "res/Sounds/opening.mid";	// for the opening screen...
				break;
		}
		return fileName;
	}
}