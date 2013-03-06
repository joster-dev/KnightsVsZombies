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
	public int enemyDmg;
	public int enemyRange;
	public int enemyFireRange;
	public int enemyCost;
	
	public int down = 0;
	public int left = 1;
	public int right = 2;
	public int up = 3;
	public int direction = right;
	
	public boolean inGame = false;
	public boolean isDead = false;
	public boolean wasDown = false;
	public boolean wasUp = false;
	public boolean wasRight = false;
	public boolean wasLeft = false;

	
	public void spawnEnemy(int enemyId) {
		System.out.println(Screen.room.block.length);
		for(int y = 0; y < Screen.room.block.length; y++) {
			if(Screen.room.block[y][0].groundId == Value.pathOpen) {
				System.out.println(y);
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
		Screen.numEnemiesDead += 1;
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
			g.drawImage(new ImageIcon("res/Enemys/enemy" + enemyId + ".png").getImage(),x, y, width, height, null);
		}
	}
	
}
