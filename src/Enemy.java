import java.awt.*;
import javax.swing.ImageIcon;


public class Enemy extends Rectangle {
	public int xC;
	public int yC;
	public int walkFrame = 0;
	public int walkSpeed = 15;
	public int health;
	public int armor;
	
	public int enemySize = Room.blockSize;
	public int enemyId;
	public int enemyWalk = 0;
	
	public int enemyHealth;
	/**
	 * @uml.property  name="enemyDmg"
	 */
	public int enemyDmg;
	/**
	 * @uml.property  name="enemyRange"
	 */
	public int enemyRange;
	/**
	 * @uml.property  name="enemyFireRange"
	 */
	public int enemyFireRange;
	/**
	 * @uml.property  name="enemyCost"
	 */
	public int enemyCost;
	
	/**
	 * @uml.property  name="down"
	 */
	public int down = 0;
	/**
	 * @uml.property  name="left"
	 */
	public int left = 1;
	/**
	 * @uml.property  name="right"
	 */
	public int right = 2;
	/**
	 * @uml.property  name="up"
	 */
	public int up = 3;
	/**
	 * @uml.property  name="direction"
	 */
	public int direction = right;
	
	/**
	 * @uml.property  name="inGame"
	 */
	public boolean inGame = false;
	/**
	 * @uml.property  name="isDead"
	 */
	public boolean isDead = false;
	/**
	 * @uml.property  name="wasDown"
	 */
	public boolean wasDown = false;
	/**
	 * @uml.property  name="wasUp"
	 */
	public boolean wasUp = false;
	/**
	 * @uml.property  name="wasRight"
	 */
	public boolean wasRight = false;
	/**
	 * @uml.property  name="wasLeft"
	 */
	public boolean wasLeft = false;

	
	public void spawnEnemy(int enemyId) {
		for(int y = 0; x < Screen.room.block.length; x++) {
			if(Screen.room.block[y][0].groundId == Value.pathOpen) {
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, enemySize, enemySize);
				xC = 0;
				yC = y;
			}
		}
		this.enemyId = enemyId;
		if(enemyId == 0) {
			health = Value.basicZombieHealth;
			armor = Value.basicZombieArmor;
		}
		inGame = true;
	}
	
	public void deleteEnemy() {
		inGame = true;
		isDead = true;
	}
	
	public void looseHealth() {
		Screen.myHealth -= health;
	}
	
	public void physic() {
		
		if(walkFrame >= walkSpeed) {
			if(direction == down) {
				y += 1;
			}
			else if(direction == left) {
				x -= 1;
			}
			else if(direction == right) {
				x += 1;
			}
			else if(direction  == up) {
				y -= 1;
			}
			
			enemyWalk += 1;
			
			if(enemyWalk == Room.blockSize) {
				if(direction == down) {
					yC += 1;
					wasDown = true;
				}
				else if(direction == left) {
					xC -= 1;
					wasLeft = true;
				}
				else if(direction == right) {
					xC += 1;
					wasRight = true;
				}
				else if(direction  == up) {
					yC -= 1;
					wasUp = true;
				}
				
				if(!wasUp) {
					try {
						if(Screen.room.block[yC+1][xC].groundId == Value.pathOpen) {
							direction = down;
						}
					} catch (Exception e) {
						
					}
				}
				if(!wasRight) {
					try {
						if(Screen.room.block[yC][xC-1].groundId == Value.pathOpen) {
							direction = left;
						}
					} catch (Exception e) {
						
					}
				}
				if(!wasLeft) {
					try {
						if(Screen.room.block[yC][xC+1].groundId == Value.pathOpen) {
							direction = right;
						}	
					} catch (Exception e) {
						
					}
				}	
				if(!wasDown) {
					try {
						if(Screen.room.block[yC-1][xC].groundId == Value.pathOpen) {
							direction = up;
						}
					} catch (Exception e) {
						
					} 
				}
				
				if(Screen.room.block[yC][xC].airId == Value.airCastle) {
					deleteEnemy();
					looseHealth();
				}
				
				wasUp = false;
				wasDown = false;
				wasRight = false;
				wasLeft = false;
				
				enemyWalk = 0;
			}
			
			walkFrame = 0;
			
		}
		else {
			walkFrame += 2;
		}
	}
	
	public void draw(Graphics g) {
		if(inGame && !isDead) {
			g.setColor(Color.PINK);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
			//g.drawImage(new ImageIcon("res/enemies/zombie/z_base.png").getImage(),x, y, width, height, null);
		}
	}
	
}
