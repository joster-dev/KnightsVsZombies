import java.awt.event.*;
import java.awt.*;

public class KeyHandler implements MouseMotionListener, MouseListener {
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		if(Frame.openingScreen.isVisible()) {
			Frame.openingScreen.click(e.getButton());
		}
		else if(Frame.chooseGame.isVisible()) {
			Frame.chooseGame.click(e.getButton());
		}
		else if(Frame.highScores.isVisible()) {
			Frame.highScores.click(e.getButton());
		}
		else if(Frame.gameScreen.isVisible()) {
			try{
				Screen.gamePanel.click(e.getButton());
			} catch(Exception i) {}
		}
	}

	public void mouseDragged(MouseEvent e) {
		Opening.mse = new Point((e.getX()) - ((Frame.size.width - Opening.myWidth)/2), (e.getY()) - (Frame.size.height - Opening.myHeight) + ((Frame.size.width - Opening.myWidth)/2));
	}

	public void mouseMoved(MouseEvent e) {
		Opening.mse = new Point((e.getX()) - ((Frame.size.width - Opening.myWidth)/2), (e.getY()) - (Frame.size.height - Opening.myHeight) + ((Frame.size.width - Opening.myWidth)/2));
	}
}