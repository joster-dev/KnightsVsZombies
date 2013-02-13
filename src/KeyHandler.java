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
			//System.out.println("O" + e.getClickCount());
		}
		else if(Frame.chooseGame.isShowing()) {
			Frame.chooseGame.click(e.getButton());
			//System.out.println("C" + e.getClickCount());
		}
		else if(Frame.gameScreen.isVisible()) {
			
		}
	}

	public void mouseDragged(MouseEvent e) {
		Opening.mse = new Point((e.getX()) - ((Frame.size.width - Opening.myWidth)/2), (e.getY()) - (Frame.size.height - Opening.myHeight) + ((Frame.size.width - Opening.myWidth)/2));
	}

	public void mouseMoved(MouseEvent e) {
		Opening.mse = new Point((e.getX()) - ((Frame.size.width - Opening.myWidth)/2), (e.getY()) - (Frame.size.height - Opening.myHeight) + ((Frame.size.width - Opening.myWidth)/2));
	}
}