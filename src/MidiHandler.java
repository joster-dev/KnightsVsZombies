import java.io.*;
import javax.sound.midi.*;

//UNFINISHED!!! THIS WILL BE USED FOR ADDING MIDI/CHIPTUNES...

public class MidiHandler {

	private static Sequencer midiPlayer;

	public static void startMidi(String midFilename) {
		try {
			File midiFile = new File(midFilename);
			Sequence song = MidiSystem.getSequence(midiFile);
			midiPlayer = MidiSystem.getSequencer();
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

	public static void stopMidi() {
		if(!midiPlayer.isRunning())
			midiPlayer.stop();
        midiPlayer.close();
	}

	public static void setTempo(float tempo) {
		midiPlayer.setTempoFactor(tempo);
	}
}
