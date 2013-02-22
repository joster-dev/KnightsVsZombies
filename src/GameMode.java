import java.awt.*;

import javax.swing.*;

public class GameMode extends JPanel {
	
	/**
	 * @uml.property  name="storyMode"
	 */
	public boolean storyMode = false;
	/**
	 * @uml.property  name="infiniteMode"
	 */
	public boolean infiniteMode = false;
	
	/**
	 * @uml.property  name="choose" multiplicity="(0 -1)" dimension="1"
	 */
	public Rectangle[] choose = new Rectangle[2];
	
	/**
	 * @uml.property  name="myFrame"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public Frame myFrame;
	
	public GameMode(Frame frame) {
		myFrame = frame;
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		for(int i = 0; i < choose.length; i++) {
			choose[i] = new Rectangle((Frame.x / 4), ((Frame.y / 3) * i) + (Frame.y / 4), Frame.x / 2, Frame.y / 8);
		}
	}
	
	public void click(int mouseclick) {
		if (mouseclick == 1) {
			for(int i = 0; i < choose.length; i++) {
				if (choose[i].contains(Opening.mse)) {
					if(i == 0) {
						storyMode = true;
					}
					else if(i == 1) {
						infiniteMode = true;
					}
				}
			}
		}
		myFrame.updateFrame();
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		for(int i = 0; i < choose.length; i++) {
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			g.setColor(Color.BLACK);
			g.drawRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			
			if(choose[i].contains(Opening.mse)) {
				
				g.setColor(new Color(255, 255, 255, 100));
				g.fillRect(choose[i].x, choose[i].y, choose[i].width, choose[i].height);
			}
		}
		
		repaint();
	}
}
