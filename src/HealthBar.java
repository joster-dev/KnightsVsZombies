import java.awt.*;

import javax.swing.*;

public class HealthBar extends Rectangle {
	
	Enemy enemy;
	
//	public boolean visible;

	public static final int width = 36;
	public static final int height = 4;

	public int healthWidth;

	// used to make the health bar appear 14 pixels
	// to the right of the 64x64 cell's left side
	public int xOffset = 14;
	// will be used to either offset the y coordinate
	// of the health bar above the enemy or below it
	public int yOffset;

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

		healthWidth = (int)(healthRatio * width);

		// this is just so the healthbar is visible
		// if the enemy's health gets too low
		if(healthWidth < 2)
			healthWidth = 2;
		if(healthWidth > width)
			healthWidth = width;
		
		if(enemy.y <= 16)
			yOffset = 72;
		else {
			if(enemy.enemyId <5)
				yOffset = -12;
			else
				yOffset = -70;
		}

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
