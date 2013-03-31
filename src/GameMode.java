import java.awt.*;

import javax.swing.*;

public class GameMode extends JPanel {

	AudioHandler audioHandler;
	
	public boolean storyMode = false;
	public boolean infiniteMode = false;
	
	public Rectangle[] choose = new Rectangle[2];
	
	public Frame myFrame;
	
	public GameMode(Frame frame) {
		myFrame = frame;
		
		for(int i = 0; i < choose.length; i++) {
			choose[i] = new Rectangle((Frame.x / 4), ((Frame.y / 3) * i) + (Frame.y / 4), Frame.x / 2, Frame.y / 8);
		}
		audioHandler = new AudioHandler();
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			for(int i = 0; i < choose.length; i++) {
				if (choose[i].contains(Opening.mse)) {
					if(i == 0) {
						try {
							audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
						} catch(Exception e) { }
						storyMode = true;
						myFrame.updateFrame();
					}
					else if(i == 1) {
						try {
							audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
						} catch(Exception e) { }
						infiniteMode = true;
						myFrame.updateFrame();
					}
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		for(int i = 0; i < choose.length; i++) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			g.setColor(Color.BLACK);								// DEBUG!!!	//\/temporary 'til add pictures here,\/
			g.setFont(new Font("Arial", Font.BOLD, 30));			// DEBUG!!!
			String temp;
			// until we add pictures to the buttons, these are temporary...
			if(i == 0)
				temp = "Story Mode";
			else
				temp = "Infinite Mode";
			g.drawString(temp, (choose[i].x)+32, (choose[i].y)+42);	// DEBUG!!!^^^^^^^^^^^^TEMP UNTIL PICTURES^^^^^
			g.drawRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			
			if(choose[i].contains(Opening.mse)) {
				
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			}
		}
		
		repaint();
	}
}