import java.awt.*;

import javax.swing.*;

public class Settings extends JPanel {

	public AudioHandler audioHandler;
	
	public boolean back = false;
	
	public Rectangle backButton;
	public Rectangle muteMusicButton;
	public Rectangle muteSoundButton;
	public Rectangle muteAllButton;
	
	public Frame myFrame;
	
	public Settings(Frame frame) {
		myFrame = frame;
		audioHandler = myFrame.audioHandler;
		
		backButton = new Rectangle((Frame.x * 21) / 27, (Frame.y * 19) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		muteMusicButton = new Rectangle((Frame.x * 17) / 27, (Frame.y * 19) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
		muteSoundButton = new Rectangle((Frame.x * 14) / 27, (Frame.y * 19) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
		muteAllButton = new Rectangle((Frame.x * 11) / 27, (Frame.y * 19) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			if(backButton.contains(Opening.mse)) {
				back = true;
				myFrame.updateFrame();
				try {
					audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
				} catch(Exception e) { }
			}
			else if(muteMusicButton.contains(Opening.mse)) {
				myFrame.audioHandler.musicMuted = !myFrame.audioHandler.musicMuted;
				if(myFrame.audioHandler.musicIsMuted()) {
					myFrame.audioHandler.midiHandler.stopMidi();
				}
				else {
					myFrame.audioHandler.midiHandler.restartMidi();
				}	
			}
			else if(muteSoundButton.contains(Opening.mse)) {
				myFrame.audioHandler.soundMuted = !myFrame.audioHandler.soundMuted;
				if(myFrame.audioHandler.soundIsMuted()) {
					//...
				}
				else {
					try {	
						audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
					} catch(Exception e) { }
				}
			}
			else if(muteAllButton.contains(Opening.mse)) {
				myFrame.audioHandler.allMuted = !myFrame.audioHandler.allMuted;
				if(myFrame.audioHandler.allMuted) {
					myFrame.audioHandler.muteAll();
					myFrame.audioHandler.midiHandler.stopMidi();
				}
				else {
					myFrame.audioHandler.unmuteAll();
					myFrame.audioHandler.midiHandler.restartMidi();
					try {
						audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
					} catch(Exception e) { }
				}
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {

		g.drawImage(SpriteSheet.openingBackground, 0, 0, Opening.myWidth, Opening.myHeight, null);
		g.drawImage(SpriteSheet.scroll, 300, 115, 400, 245, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Brought to you by Team KvZ:", 360, 165);
		g.drawString("     - Anthony Bolton", 360, 195);
		g.drawString("     - Amanda Dale", 360, 215);
		g.drawString("     - Jacob Osterhout", 360, 235);
		g.drawString("     - Nick Roberts", 360, 255);
		g.drawString("     - Kevin Samms", 360, 275);
		g.drawString("     - Dan Zuber", 360, 295);
		
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(muteAllButton.x - 20, muteAllButton.y - 10, ((Frame.x * 8) / 27) + 40, ((Frame.x * 2) / 27) + 55);
		g.setColor(Color.BLACK);
		g.drawRect(muteAllButton.x - 20, muteAllButton.y - 10, ((Frame.x * 8) / 27) + 40, ((Frame.x * 2) / 27) + 55);
		
		g.drawImage(SpriteSheet.back_button, backButton.x, backButton.y, backButton.width, backButton.height, null);
		g.drawImage(SpriteSheet.music_button, muteMusicButton.x, muteMusicButton.y, muteMusicButton.width, muteMusicButton.height, null);
		g.drawImage(SpriteSheet.sound_button, muteSoundButton.x, muteSoundButton.y, muteSoundButton.width, muteSoundButton.height, null);

		g.drawImage(SpriteSheet.sound_button, muteAllButton.x, muteAllButton.y, muteAllButton.width, muteAllButton.height, null);
		g.drawImage(SpriteSheet.music_button, muteAllButton.x, muteAllButton.y, muteAllButton.width, muteAllButton.height, null);

		// These lines draw the image of a 'cancel icon'
		// over the sound icons to indicate that audio has
		// been disabled when the mute buttons are clicked
		if(myFrame.audioHandler.allIsMuted()) {
			g.drawImage(SpriteSheet.cancel, muteAllButton.x, muteAllButton.y,
						muteAllButton.width, muteAllButton.height, null);
			g.drawImage(SpriteSheet.cancel, muteMusicButton.x, muteMusicButton.y,
					muteMusicButton.width, muteMusicButton.height, null);
			g.drawImage(SpriteSheet.cancel, muteSoundButton.x, muteSoundButton.y,
					muteSoundButton.width, muteSoundButton.height, null);
		}
		else if(myFrame.audioHandler.musicIsMuted()) {
			g.drawImage(SpriteSheet.cancel, muteMusicButton.x, muteMusicButton.y,
						muteMusicButton.width, muteMusicButton.height, null);
		}
		else if(myFrame.audioHandler.soundIsMuted()) {
			g.drawImage(SpriteSheet.cancel, muteSoundButton.x, muteSoundButton.y,
						muteSoundButton.width, muteSoundButton.height, null);
		}


		// The 'faded' color that lets you know a button has current focus
		if(backButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillOval(backButton.x-2, backButton.y-2, backButton.width+4, backButton.height+4);
		}
		else if(muteMusicButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(muteMusicButton.x, muteMusicButton.y, muteMusicButton.width, muteMusicButton.height);
		}
		else if(muteSoundButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(muteSoundButton.x, muteSoundButton.y, muteSoundButton.width, muteSoundButton.height);
		}
		else if(muteAllButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(muteAllButton.x, muteAllButton.y, muteAllButton.width, muteAllButton.height);
		}
		
		repaint();
	}
}
