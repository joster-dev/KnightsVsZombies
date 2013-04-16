import java.awt.*;
import javax.swing.*;

public class HighScore extends JPanel {

	public AudioHandler audioHandler;
	
	public boolean back = false;
	
	public Rectangle achievementListSpace;
	public String achievementTitle = "Achievements";
	public Rectangle[] achievementBoxes = new Rectangle[6];
	public boolean[] achievementState = new boolean[6];
	//** 	Achievements from 0 - 5  **//
	//	0 - 4 	: Complete Story Level 1 - 5
	//	5 		: Score high enough on infinite mode to get a high score
	//*//
	public Rectangle[] achievementStateBoxes = new Rectangle[6];
	public String[] achievementNames = new String[]{"Clear Level 1",
													"Clear Level 2",
													"Clear Level 3",
													"Clear Level 4",
													"Clear Level 5",
													"High Score!"};
	
	public Rectangle highScoreListSpace;
	public String highScoreTitle = "High Scores";
	public Rectangle[] highScoreBoxes = new Rectangle[5];
	public String[] highScoreNames = new String[]{"Ted", "Steve", "Jim", "Tyrone", "Sam"};
	public int[] highScores = new int[]{50, 45, 40, 35, 32};

	public Rectangle backButton;
	
	public Frame myFrame;
	
	public HighScore (Frame frame) {
		myFrame = frame;
		
		achievementListSpace = new Rectangle(Frame.x / 9, Frame.y / 4, Frame.x / 3, (Frame.y * 5) / 8);
		highScoreListSpace = new Rectangle((Frame.x * 5) / 9, Frame.y / 4, Frame.x / 3, (Frame.y * 10) / 27);
		backButton = new Rectangle((Frame.x * 21) / 27, (Frame.y * 19) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);

		for(int i = 0; i < achievementBoxes.length; i++) {
			achievementBoxes[i] = new Rectangle(achievementListSpace.x + (achievementListSpace.width / 10), achievementListSpace.y + (achievementListSpace.height * 4 / 23) + (achievementListSpace.height * 3 / 23) * i, achievementListSpace.width * 4 / 5, achievementListSpace.height * 3 / 23);
			achievementStateBoxes[i] = new Rectangle(achievementBoxes[i].x + achievementBoxes[i].width - (achievementBoxes[i].height * 14 / 15), achievementBoxes[i].y + (achievementBoxes[i].height / 15), achievementBoxes[i].height * 13 / 15, achievementBoxes[i].height * 13 / 15);
		}
		
		for(int j = 0; j < highScoreBoxes.length; j++) {
			highScoreBoxes[j] = new Rectangle(highScoreListSpace.x + (highScoreListSpace.width / 10), highScoreListSpace.y + (highScoreListSpace.height / 5) + (highScoreListSpace.height * 3 / 20) * j, highScoreListSpace.width * 4 / 5, highScoreListSpace.height * 3 / 20);
		}
		audioHandler = myFrame.audioHandler;
	}
	
	public void newHighScore(String name, int highScore) {
		for(int i = 0; i < highScores.length; i++) {
			if(highScore > highScores[i]) {
				for(int j = highScores.length - 1; j > i; j--) {
					highScores[j] = highScores[j - 1];
					highScoreNames[j] = highScoreNames[j - 1];
				}
				highScores[i] = highScore;
				highScoreNames[i] = name;
				unlockAchievement(5);
				break;
			}
		}
	}
	
	public void unlockAchievement(int achieveNum) {
		achievementState[achieveNum] = true;
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			if(backButton.contains(Opening.mse)) {
				try {
					audioHandler.soundHandler.playSound("res/Sounds/thunk.wav");
				} catch(Exception e) { }
				back = true;
				myFrame.updateFrame();
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {

		g.drawImage(SpriteSheet.openingBackground, 0, 0, Opening.myWidth, Opening.myHeight, null);
		
		g.drawImage(SpriteSheet.scroll, achievementListSpace.x-30, achievementListSpace.y-40, achievementListSpace.width+120, achievementListSpace.height+140, null);
		g.drawImage(SpriteSheet.scroll, highScoreListSpace.x-30, highScoreListSpace.y-30, highScoreListSpace.width+120, highScoreListSpace.height+110, null);
		g.drawImage(SpriteSheet.back_button, backButton.x, backButton.y, backButton.width, backButton.height, null);		
		
		for(int i = 0; i < achievementBoxes.length; i++) {
			if(!achievementState[i]) {			//*Sets the opacity to a dark color if the achievement is not unlocked.*//
				g.drawLine(achievementStateBoxes[i].x + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].y + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].x + (achievementStateBoxes[i].width * 4 / 5), achievementStateBoxes[i].y + (achievementStateBoxes[i].width * 4 / 5));
				g.drawLine(achievementStateBoxes[i].x + (achievementStateBoxes[i].width * 4 / 5), achievementStateBoxes[i].y + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].x + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].y + (achievementStateBoxes[i].width * 4 / 5));
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(achievementBoxes[i].x, achievementBoxes[i].y, achievementBoxes[i].width, achievementBoxes[i].height);
			}									//*//
			else {
				g.setColor(Color.BLACK);
				g.drawOval(achievementStateBoxes[i].x + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].y + (achievementStateBoxes[i].width / 5), achievementStateBoxes[i].width * 3 / 5, achievementStateBoxes[i].width * 3 / 5);
			}
			g.setColor(Color.BLACK);
			g.drawRect(achievementBoxes[i].x, achievementBoxes[i].y, achievementBoxes[i].width, achievementBoxes[i].height);
			g.drawRect(achievementStateBoxes[i].x, achievementStateBoxes[i].y, achievementStateBoxes[i].width, achievementStateBoxes[i].height);
		}
		for(int j = 0; j < highScoreBoxes.length; j++) {
			g.drawRect(highScoreBoxes[j].x, highScoreBoxes[j].y, highScoreBoxes[j].width, highScoreBoxes[j].height);
		}
		
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString(achievementTitle, achievementListSpace.x + (achievementListSpace.width * 3 / 10), achievementListSpace.y + (achievementListSpace.height * 2 / 23) * 4 / 3);
		g.drawString(highScoreTitle, highScoreListSpace.x + (highScoreListSpace.width * 7 / 20), highScoreListSpace.y + (highScoreListSpace.height / 10) * 4 / 3);
		
		g.setFont(new Font("Arial", Font.BOLD, 12));
		for(int k = 0; k < highScores.length; k++) {
			g.drawString(Integer.toString(k + 1) + ".", highScoreBoxes[k].x + highScoreListSpace.width / 10, highScoreBoxes[k].y + (highScoreListSpace.height / 10));
			g.drawString(highScoreNames[k], highScoreBoxes[k].x + highScoreListSpace.width * 3 / 10, highScoreBoxes[k].y + (highScoreListSpace.height / 10));
			g.drawString(Integer.toString(highScores[k]), highScoreBoxes[k].x + highScoreListSpace.width / 2, highScoreBoxes[k].y + (highScoreListSpace.height / 10));
		}

		for(int l = 0; l < achievementBoxes.length; l++) {
			g.drawString(achievementNames[l], achievementBoxes[l].x + achievementListSpace.width / 7, achievementBoxes[l].y + (achievementListSpace.height / 13));
		}
		
		if(backButton.contains(Opening.mse)) {
			
			g.setColor(new Color(255, 255, 255, 100));
			g.fillOval(backButton.x-2, backButton.y-2, backButton.width+4, backButton.height+4);
		}
		
		repaint();
	}
}
