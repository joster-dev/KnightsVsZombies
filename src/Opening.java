import java.awt.*;
import javax.swing.*;

public class Opening extends JPanel {
	
	public static int myWidth;

	public static int myHeight;
	
	public static Point mse = new Point(0, 0);
	
	/**
	 * @uml.property  name="newGame"
	 */
	public boolean newGame = false;
	/**
	 * @uml.property  name="loadGame"
	 */
	public boolean loadGame = false;
	/**
	 * @uml.property  name="highScores"
	 */
	public boolean highScores = false;
	/**
	 * @uml.property  name="settings"
	 */
	public boolean settings = false;
	
	/**
	 * @uml.property  name="mainChoices" multiplicity="(0 -1)" dimension="1"
	 */
	public Rectangle[] mainChoices = new Rectangle[4];
	
	/**
	 * @uml.property  name="myFrame"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public Frame myFrame;
	
	public Opening (Frame frame) {
		
		myFrame = frame;
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		for(int i = 0; i < mainChoices.length; i++) {
			mainChoices[i] = new Rectangle((Frame.x / 3), ((Frame.y / 6) * i) + (Frame.y / 4), Frame.x / 3, Frame.y / 8);
		}
	}
	
	public void click(int mouseClick) {
		if (mouseClick == 1) {
			for(int i = 0; i < mainChoices.length; i++) {
				if (mainChoices[i].contains(mse)) {
					if(i == 0) {
						newGame = true;
					}
					else if(i == 1) {
						loadGame = true;
					}
					else if(i == 2) {
						highScores = true;
					}
					else if(i == 3) {
						settings = true;
					}
				}
			}
		}
		myFrame.updateFrame();
	}
	
	public void paintComponent(Graphics g) {
		
		myWidth = getWidth();
		myHeight = getHeight();
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, myWidth, myHeight);
		
		for(int i = 0; i < mainChoices.length; i++) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
			
			if(mainChoices[i].contains(mse)) {
				
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(mainChoices[i].x, mainChoices[i].y, mainChoices[i].width, mainChoices[i].height);
			}
		}
		
		repaint();
	}
}