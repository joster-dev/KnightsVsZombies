import java.awt.*;
import java.io.File;

import javax.swing.*;

public class Opening extends JPanel {

	public AudioHandler audioHandler;
	
	public static int myWidth;
	public static int myHeight;
	public boolean firstRun = false;
	
	public static Point mse = new Point(0, 0);
	
	public boolean newGame = false;
	public boolean loadGame = false;
	public boolean highScores = false;
	public boolean settings = false;
	
	public boolean isLoadFile = false;

	public Rectangle[] mainChoices = new Rectangle[4];
	
	public Frame myFrame;
	
	public Opening (Frame frame) {
		
		myFrame = frame;
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		for(int i = 0; i < mainChoices.length; i++) {
			mainChoices[i] = new Rectangle((Frame.x / 3), ((Frame.y / 6) * i) + (Frame.y / 4), Frame.x / 3, Frame.y / 8);
		}
		audioHandler = myFrame.audioHandler;
	}
	
	public void click(int mouseClick) {
		if (mouseClick == 1) {
			for(int i = 0; i < mainChoices.length; i++) {
				if (mainChoices[i].contains(mse)) {
					if(i == 0) {
						try {
							audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
						} catch(Exception e) { }
						newGame = true;
					}
					else if(i == 1) {
						if(isLoadFile) {
							try {
								audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
							} catch(Exception e) { }
							loadGame = true;
						}
					}
					else if(i == 2) {
						try {
							audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
						} catch(Exception e) { }
						highScores = true;
					}
					else if(i == 3) {
						try {
							audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
						} catch(Exception e) { }
						settings = true;
					}
				}
			}
		}
		myFrame.updateFrame();
	}
	
	public void paintComponent(Graphics g) {
		
		if(!firstRun) {
			myWidth = getWidth();
			myHeight = getHeight();
			firstRun = true;
		}
		
		isLoadFile = Screen.save.isLoadFile(new File("Save/SavedGame"));
		
		myWidth = getWidth();
		myHeight = getHeight();
		
		//g.setColor(Color.ORANGE);
		//g.fillRect(0, 0, myWidth, myHeight);
		
		g.drawImage(SpriteSheet.openingBackground, 0, 0, myWidth, myHeight, null);
		
		for(int i = 0; i < mainChoices.length; i++) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
			g.setFont(new Font("Arial", Font.BOLD, 30));	// DEBUG!!!	// below code is temporary until images for buttons are added
			String temp;
			// temporary, until we add pictures to the buttons instead
			switch(i)
			{
				case 0:		temp = "New Game";
							break;
				case 1:		temp = "Load Game";
							break;
				case 2:		temp = "High Scores";
							break;
				default:	temp = "Settings";
			}
			g.drawString(temp, (mainChoices[i].x)+32, (mainChoices[i].y)+42);	// DEBUG!!! above code is temporary 'til images are added
			
			if(mainChoices[i].contains(mse)) {
				if(i == 1 && !isLoadFile) { }
				else {
					g.setColor(new Color(255, 255, 255, 100));
					g.fillRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
				}
			}
		}
		
		if(!isLoadFile) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(mainChoices[1].x, mainChoices[1].y, mainChoices[1].width, mainChoices[1].height);
		}
		
		repaint();
	}
}