import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Screen extends JPanel implements Runnable {
	
	public Thread gameLoop = new Thread(this);
	
	public static int myHealth = 100;

	public static int myGold = 100;

	public static int myWaves;
	boolean[] newWave;
	
	public boolean createStaticElements = false;
	
	public static ArrayList<int[]> levelEnemyType = new ArrayList<int[]>();
	public ArrayList<Enemy[]> levelEnemyList = new ArrayList<Enemy[]>();
	
	public static Room room;
	public static ScreenPanel gamePanel;
	public static Save save;
	
	public Screen(Frame frame) {

		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		gameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		if(!createStaticElements) {
			
			define();
			createStaticElements = true;
		}
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, Opening.myWidth, Opening.myHeight);
		
		room.draw(g);
		gamePanel.draw(g);
		
		for(int i = 0; i < levelEnemyList.size(); i++) {
			for(int j = 0; j < levelEnemyList.get(i).length; j++) {
				if(levelEnemyList.get(i)[j].inGame) {
					levelEnemyList.get(i)[j].draw(g);
				}
			}
		}
		
		repaint();
	
	}
	
	public void define() {
		
		room = new Room();
		gamePanel = new ScreenPanel();
		save = new Save();
		
		save.loadSave(new File("Save/StoryQuest1"));
		
		for(int i = 0; i < levelEnemyType.size(); i++) {
			levelEnemyList.add(new Enemy[levelEnemyType.get(i).length]);
		}
		for(int i = 0; i < levelEnemyList.size(); i++) {
			for(int j = 0; j < levelEnemyList.get(i).length; j ++) {
				levelEnemyList.get(i)[j] = new Enemy();
			}
		}
		newWave = new boolean[levelEnemyList.size()];
		for(int i = 0; i < newWave.length; i++) {
			newWave[i] = false;
		}
	}
	
	public int spawnTime = 1750;    //spawnFrame -> spawnTime. When spawnFrame == spawnTime, enemySpawner is called.

	public int spawnFrame = 0;
	public void enemySpawner() {
		if(spawnFrame >= spawnTime) {
			outerLoop:																//Label so we can break from both loops.
			for (int i = 0; i < levelEnemyList.size(); i++) {
				for (int j = 0; j < levelEnemyList.get(i).length; j++) {
					if(!levelEnemyList.get(i)[j].inGame) {
						levelEnemyList.get(i)[j].spawnEnemy(levelEnemyType.get(i)[j]);
						levelEnemyList.get(i)[j].physic();
						if(j == levelEnemyList.get(i).length - 1) {
							if(!newWave[i]) {
								myWaves -= 1;
								spawnFrame = -10000;
							}
							newWave[i] = true;
						}
						break outerLoop;
					}
				}
			}
			if(spawnFrame > 0 ) {
				spawnFrame = 0;
			}
		}
		else {
			spawnFrame += 1;
		}
	}
	
	public void run() {
		while(true) {
			if(createStaticElements) {
				room.physic();
				enemySpawner();
				for(int i = 0; i < levelEnemyList.size(); i++) {
					for(int j = 0; j < levelEnemyList.get(i).length; j++) {
						if(levelEnemyList.get(i)[j].inGame && !levelEnemyList.get(i)[j].isDead) {
							levelEnemyList.get(i)[j].physic();
						}
					}
				}
			}
			repaint();
			
			try {
				Thread.sleep(1);
			} catch(Exception e) {
				
			}
		}
	}
}
