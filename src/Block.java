import java.awt.*;

import javax.swing.*;

public class Block extends Rectangle {

	public int groundId;
	public int airId;
	public int randomId;
	
	public boolean hasTower = false;
	public Tower tower;
	
	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		
		this.groundId = groundID;
		this.airId = airID;
		this.randomId = (int)(Math.random()*100);
	}
	
	public void addTower(int towerId) {
		this.hasTower = true;
		this.airId = towerId;
		this.tower = new Tower(towerId);
	}
	
	public void physic() {
		
	}
	
	public void draw(Graphics g) {
		if(groundId == Value.groundOpen) {
			if(this.randomId < 40)
				g.drawImage(new ImageIcon("res/Graphics/grass.png").getImage(),x, y, width, height, null);
			else if(this.randomId < 70)
				g.drawImage(new ImageIcon("res/Graphics/grass_2.png").getImage(),x, y, width, height, null);
			else if(this.randomId < 90)
				g.drawImage(new ImageIcon("res/Graphics/grass_3.png").getImage(),x, y, width, height, null);
			else if(this.randomId < 100)
				g.drawImage(new ImageIcon("res/Graphics/grass_4.png").getImage(),x, y, width, height, null);
		}
		else if(groundId == Value.pathOpen) {
			g.drawImage(new ImageIcon("res/Graphics/path.png").getImage(),x, y, width, height, null);
		}
		
		if(airId == Value.airCastle) {
			//g.drawImage(new ImageIcon("res/Graphics/towr_base.png").getImage(),x, y, width, height, null);
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
		}
		else if(airId > Value.airCastle) {
			g.drawImage(new ImageIcon("res/Towers/tower" + airId + ".png").getImage(),x, y, width, height, null);
		}
	}	
}
