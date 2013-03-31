import java.awt.*;

import javax.swing.*;

public class Settings extends JPanel {

	AudioHandler audioHandler;
	
	public boolean back = false;
	
	public Rectangle backButton;
	
	public Frame myFrame;
	
	public Settings(Frame frame) {
		myFrame = frame; 
		
		backButton = new Rectangle((Frame.x * 20) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
		audioHandler = new AudioHandler();
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
		}
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		g.setColor(Color.MAGENTA);
		g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
		
		if(backButton.contains(Opening.mse)) {
			
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		}
		
		repaint();
	}
}
