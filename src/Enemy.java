import java.awt.*;
import javax.swing.ImageIcon;


public class Enemy extends Rectangle {
	public int xC, yC;
	public int walkFrame = 0, walkSpeed = 15;
	public int health;
	public int armor;
	
	public int enemySize = Room.blockSize;
	public int enemyId;
	public int enemyWalk = 0;
	
	public int enemyHealth;
	public int enemyDmg;
	public int enemyRange;
	public int enemyFireRange;
	public int enemyCost;
	
	public int down = 0, left = 1, right = 2, up = 3;
	public int direction = right;
	
	public boolean inGame = false;
	public boolean isDead = false;
	public boolean wasDown = false, wasUp = false, wasRight = false, wasLeft = false;

	
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
			
			if(enemyWalk == Screen.room.blockSize) {
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
