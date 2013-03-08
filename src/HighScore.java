import java.awt.*;

import javax.swing.*;

public class HighScore extends JPanel {
	
	public boolean back = false;
	
	public Rectangle highScoreListSpace;
	public String[] highScores = new String[]{"Jim1", "Jim2", "Jim3", "Jim4", "Jim5"};
	public String highScoreTitle = "High Scores";
	
	public Rectangle achivementSpace;
	public String achivementTitle = "Achivements";
	
	public Rectangle imgBox;
	
	public Rectangle backButton;
	
	public Frame myFrame;
	
	public HighScore (Frame frame) {
		
		myFrame = frame;
		
		highScoreListSpace = new Rectangle(Frame.x / 9, Frame.y / 9, Frame.x / 3, (Frame.y * 7) / 9);
		achivementSpace = new Rectangle((Frame.x * 5) / 9, Frame.y / 9, Frame.x / 3, (Frame.y * 10) / 27);
		imgBox = new Rectangle((Frame.x * 5) / 9, (Frame.y * 5) / 9, (Frame.x * 4) / 27, Frame.y / 3);
		backButton = new Rectangle((Frame.x * 20) / 27, (Frame.y * 17) / 27, (Frame.x * 4) / 27, (Frame.y * 5) / 27);
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			if(backButton.contains(Opening.mse)) {
				back = true;
				myFrame.updateFrame();
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		g.setColor(Color.WHITE);
		g.fillRect(highScoreListSpace.x, highScoreListSpace.y, highScoreListSpace.width, highScoreListSpace.height);
		g.fillRect(achivementSpace.x, achivementSpace.y, achivementSpace.width, achivementSpace.height);
		
		g.setColor(Color.GREEN);
		g.fillRect(imgBox.x, imgBox.y, imgBox.width, imgBox.height);
		
		g.setColor(Color.MAGENTA);
		g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		
		g.setColor(Color.BLACK);
		g.drawRect(highScoreListSpace.x, highScoreListSpace.y, highScoreListSpace.width, highScoreListSpace.height);
		g.drawRect(achivementSpace.x, achivementSpace.y, achivementSpace.width, achivementSpace.height);
		g.drawRect(backButton.x, backButton.y, backButton.width, backButton.height);
		
		if(backButton.contains(Opening.mse)) {
			
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
		}
		
		repaint();
	}
}
