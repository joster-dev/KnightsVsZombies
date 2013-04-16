import java.awt.*;

import javax.swing.*;

public class Pause extends JPanel {
	
	public AudioHandler audioHandler;
	
	public boolean backToGame = false;
	public boolean backToOpening = false;
	
	public Rectangle backGame;
	public Rectangle backOpening;
	public Rectangle muteMusicButton;
	public Rectangle muteSoundButton;
	public Rectangle muteAllButton;
	
	public Frame myFrame;
	
	public Pause(Frame frame) {
		myFrame = frame;
		audioHandler = myFrame.audioHandler;
		
		backOpening = new Rectangle((Frame.x * 3) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		backGame = new Rectangle((Frame.x * 20) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		muteMusicButton = new Rectangle((Frame.x * 16) / 27, (Frame.y * 17) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
		muteSoundButton = new Rectangle((Frame.x * 13) / 27, (Frame.y * 17) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
		muteAllButton = new Rectangle((Frame.x * 10) / 27, (Frame.y * 17) / 27, (Frame.x * 2) / 27, (Frame.y * 5) / 27);
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			if(backGame.contains(Opening.mse)) {
				backToGame = true;
				myFrame.updateFrame();
				Frame.gameScreen.gameLoop.resume();
				ScreenPanel.isPaused = false;
				try {
					audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
				} catch(Exception e) { }
			}
			else if(backOpening.contains(Opening.mse)) {
				backToOpening = true;
				if(ScreenPanel.isFastForward) {
					ScreenPanel.fastForward(true);
				}
				myFrame.updateFrame();
				ScreenPanel.isPaused = false;
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
		g.setColor(new Color(255, 255, 255, 1));
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);


		g.drawImage(SpriteSheet.main_menu, backOpening.x, backOpening.y, backOpening.width, backOpening.height, null);
		g.drawImage(SpriteSheet.back_button, backGame.x, backGame.y, backGame.width, backGame.height, null);
		
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.setColor(Color.BLACK);
		g.drawString("Game is Paused...", (Frame.x * 3) / 27, (Frame.y * 7) / 27);


		g.setColor(new Color(0, 0, 255, 1));
		g.fillRect(muteAllButton.x - 20, muteAllButton.y - 10, ((Frame.x * 8) / 27) + 40, ((Frame.x * 2) / 27) + 55);
		g.setColor(Color.BLACK);
		g.drawRect(muteAllButton.x - 20, muteAllButton.y - 10, ((Frame.x * 8) / 27) + 40, ((Frame.x * 2) / 27) + 55);
		
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


		if(backGame.contains(Opening.mse)) {
			g.setColor(new Color(255, 0, 0, 100));
			g.fillOval(backGame.x-2, backGame.y-2, backGame.width+4, backGame.height+4);
		}
		else if(backOpening.contains(Opening.mse)) {
			g.setColor(new Color(255, 0, 0, 100));
			g.fillOval(backOpening.x, backOpening.y, backOpening.width, backOpening.height);
		}
		else if(muteMusicButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 0, 0, 100));
			g.fillRect(muteMusicButton.x, muteMusicButton.y, muteMusicButton.width, muteMusicButton.height);
		}
		else if(muteSoundButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 0, 0, 100));
			g.fillRect(muteSoundButton.x, muteSoundButton.y, muteSoundButton.width, muteSoundButton.height);
		}
		else if(muteAllButton.contains(Opening.mse)) {
			g.setColor(new Color(255, 0, 0, 100));
			g.fillRect(muteAllButton.x, muteAllButton.y, muteAllButton.width, muteAllButton.height);
		}
		
		repaint();
	}
}
