import java.awt.*;

import javax.swing.*;

public class Block extends Rectangle {

	public int groundId;
	public int airId;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		
		this.groundId = groundID;
		this.airId = airID;
	}
	
	public void physic() {
		
	}
	
	public void draw(Graphics g) {
		if(groundId == Value.groundOpen) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		else if(groundId == Value.pathOpen) {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		if(airId == Value.airCastle) {
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		else if(airId > Value.airCastle) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			//g.drawImage(new ImageIcon("res/knights/tower" + airId + ".png").getImage(),x, y, width, height, null);
		}
	}	
}
