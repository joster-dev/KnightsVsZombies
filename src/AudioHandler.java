public class AudioHandler {

	public SoundHandler soundHandler;
	public MidiHandler midiHandler;
	
	public boolean soundMuted = false;
	public boolean musicMuted = false;
	public boolean allMuted = false;

	public AudioHandler() {
		soundHandler = new SoundHandler(this);
		midiHandler = new MidiHandler(this);
	}
	
	public boolean soundIsMuted() {
		return soundMuted;
	}
	
	public void muteSound() {
		soundMuted = true;
	}
	
	public void unmuteSound() {
		soundMuted = false;
	}

	public boolean musicIsMuted() {
		return musicMuted;
	}

	public void muteMusic() {
		musicMuted = true;
	}
	
	public void unmuteMusic() {
		musicMuted = false;
	}

	public boolean allIsMuted() {
		return allMuted;
	}

	public void muteAll() {
		allMuted = true;
	}
	
	public void unmuteAll() {
		allMuted = false;
	}
}
