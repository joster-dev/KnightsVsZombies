public class AudioHandler {

	SoundHandler soundHandler;
	MidiHandler midiHandler;

	public AudioHandler() {
		soundHandler = new SoundHandler();
		midiHandler = new MidiHandler();
	}
}
