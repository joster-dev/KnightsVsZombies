import java.awt.*;

import javax.swing.*;

public class Pause extends JPanel {
	
	public AudioHandler audioHandler;
	
	public boolean backToGame = false;
	public boolean backToOpening = false;
	
	public Rectangle backGame;
	public Rectangle backOpening;
	
	public Frame myFrame;
	
	public Pause(Frame frame) {
		myFrame = frame;
		
		backOpening = new Rectangle((Frame.x * 3) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		backGame = new Rectangle((Frame.x * 20) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		
		audioHandler = myFrame.audioHandler;
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
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 1));
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		g.setColor(Color.MAGENTA);
		g.fillRect(backGame.x, backGame.y, backGame.width, backGame.height);
		
		g.setColor(Color.BLUE);
		g.fillRect(backOpening.x, backOpening.y, backOpening.width, backOpening.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(backGame.x, backGame.y, backGame.width, backGame.height);
		g.drawRect(backOpening.x, backOpening.y, backOpening.width, backOpening.height);
		
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Opening", backOpening.x, backOpening.y + (backOpening.height / 2));
		g.drawString("Game", backGame.x, backGame.y + (backGame.height / 2));
		
		g.setFont(new Font("Arial", Font.BOLD, 40));
		
		g.drawString("Game is Paused...", (Frame.x * 5) / 27, (Frame.y * 7) / 27);
		
		if(backGame.contains(Opening.mse)) {
			
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(backGame.x, backGame.y, backGame.width, backGame.height);
		}
		else if(backOpening.contains(Opening.mse)) {
			
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(backOpening.x, backOpening.y, backOpening.width, backOpening.height);
		}
		
		repaint();
	}
}
