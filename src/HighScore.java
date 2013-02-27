import java.awt.*;
import javax.swing.*;

public class HighScore extends JPanel {
	
	public Frame myFrame;
	
	public Rectangle background;
	public int backgroundSizeX = Frame.x / 2;
	public int backgroundSizeY = (Frame.y * 2) / 3;
	
	public Rectangle highScoreListSpace;
	public String[] highScores = new String[5];
	public String highScoreTitle = "High Scores";
	public int highScoreSizeX = backgroundSizeX / 4;
	public int highScoreSizeY = backgroundSizeY / 4;
	
	public Rectangle achivementSpace;
	public String achivementTitle = "Achivements";
	public int achivementSizeX = (backgroundSizeX * 3) / 8;
	public int achivementSizeY = (backgroundSizeY * 3) / 4;
	
	public Rectangle imgBox;
	public int imgBoxSizeX = highScoreSizeX;
	public int imgBoxSizeY = highScoreSizeY;
	
	public int spaceBetweenX = Frame.x / 8;
	public int spaceBetweenY = Frame.y / 8;
	
	public HighScore (Frame frame) {
		
		myFrame = frame;
		
		background  = new Rectangle(backgroundSizeX / 2, backgroundSizeY / 4, backgroundSizeX, backgroundSizeY);
		
		highScoreListSpace = new Rectangle((backgroundSizeX / 2) + achivementSizeX + (spaceBetweenX * 2), (backgroundSizeY / 4) + spaceBetweenY, highScoreSizeX, highScoreSizeY);
		achivementSpace = new Rectangle((backgroundSizeX / 2) + spaceBetweenX, (backgroundSizeY / 4) + spaceBetweenY, achivementSizeX, achivementSizeY);
		imgBox = new Rectangle((backgroundSizeX / 2) + achivementSizeX + (spaceBetweenX * 2), (backgroundSizeY / 4) + highScoreSizeY + (spaceBetweenY * 2), imgBoxSizeX, imgBoxSizeY);
	}
}
