import java.awt.*;

import javax.swing.*;

public class HealthBar extends Rectangle {
	
	Enemy enemy;
	
//	public boolean visible;

	public static final int width = 48;
	public static final int height = 8;

	public int healthWidth;

	public int xOffset = 8;	// used to make the health bar appear 8 pixels
							// to the right of the 64x64 cell's left side
	public int yOffset;		// will be used to either offset the y coordinate
							// of the health bar above the enemy or below it

	public double currentHealth;
	public double possibleHealth;

	public HealthBar(Enemy e) {
		this.enemy = e;
//		this.visible = false;

		possibleHealth = Value.getZombieStats("health", enemy.enemyId);
		currentHealth = enemy.health;
	}

	public void draw(Graphics g) {
		currentHealth = enemy.health;

		double healthRatio;
		
		if( (int)currentHealth == possibleHealth ) {
			healthRatio = 1;
		}
		else {
			healthRatio = currentHealth / possibleHealth;
		}
		/*if(enemy.enemyId >= 0)
			System.out.println("The currentHealth is: " + currentHealth +
								" and the possibleHealth is: " + possibleHealth +
								" and the enemy Id is " + enemy.enemyId);*/

		healthWidth = (int)(healthRatio * width);

		// this is just so the healthbar is visible
		// if the enemy's health gets too low
		if(healthWidth < 2)
			healthWidth = 2;
		if(healthWidth > width)
			healthWidth = width;

		if(enemy.y <= 16)
			yOffset = 72;
		else
			yOffset = -16;

		// used for the white background of health bar...
		g.setColor(Color.white);
		g.fillRect((enemy.x + xOffset), (enemy.y + yOffset), width, height);
		// used for the actual health bar itself...
		g.setColor(Color.red);
		g.fillRect((enemy.x + xOffset), (enemy.y + yOffset), healthWidth, height);
		// used for the black outline of the health bar...
		g.setColor(Color.black);
		g.drawRect((enemy.x + xOffset), (enemy.y + yOffset), width, height);
	}
}
