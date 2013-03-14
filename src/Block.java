import java.awt.*;

import javax.swing.*;

public class Block extends Rectangle {

	public int groundId;
	public int airId;
	public int randomId;
	
	public boolean hasTower = false;
	public Tower tower;
	
	public int targetWave = -1;
	public int targetEnemy = -1;
	public boolean shoting = false;
	public int shotFrame = 0, fire = 1000;
	
	public SpriteSheet sprites = new SpriteSheet();
	
	public Rectangle towerHitBox = new Rectangle();
	
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
		towerHitBox = new Rectangle(x - (width * tower.towerRange), y - (width * tower.towerRange), width + (width * (2 * tower.towerRange)), width + (width * (2 * tower.towerRange)));
	}
	
	public void physic() {
		if(airId > Value.airCastle) {
			outerLoop:
			for (int i = 0; i < Screen.levelEnemyList.size(); i++) {
				for (int j = 0; j < Screen.levelEnemyList.get(i).length; j++) {
					if(Screen.levelEnemyList.get(i)[j].inGame) {
						if(towerHitBox.intersects(Screen.levelEnemyList.get(i)[j])) {
							this.targetWave = i;
							this.targetEnemy = j;
							this.shoting = true;
							break outerLoop;
						}
						else {
							shoting  = false;
						}
					}
				}
			}
			if(shoting) {
				if(shotFrame >= (fire / tower.towerRate)) {
					Screen.levelEnemyList.get(targetWave)[targetEnemy].looseEnemyHealth(tower.towerDamage);
					shoting = false;
					shotFrame = 0;
				}
				else {
					shotFrame += 1;
				}
			} 
		}
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
			g.drawImage(new ImageIcon("res/Graphics/towr_base.png").getImage(),x-32, y-64, width+64, height+64, null);
			//g.setColor(Color.CYAN);
			//g.fillRect(x, y, width, height);
		}
		else if(airId > Value.airCastle) {
			//g.drawImage(new ImageIcon("res/Towers/tower" + airId + ".png").getImage(),x, y, width, height, null);
			g.drawImage(sprites.getSprite("tower", (airId - 1), 0) ,x, y, width, height, null);
		}
	}
	
	public void fight(Graphics g) {
		g.setColor(Color.BLACK);
		if(this.contains(Opening.mse) && this.hasTower) {
			g.drawRect(towerHitBox.x, towerHitBox.y, towerHitBox.width, towerHitBox.height);
		}
		
		if(shoting) {
			g.drawLine(x + (width / 2), y + (height / 2), Screen.levelEnemyList.get(targetWave)[targetEnemy].x + (width / 2), Screen.levelEnemyList.get(targetWave)[targetEnemy].y + (height / 2) );
		}
	}
}
