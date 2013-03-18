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
	
	public boolean questFailed;
	public boolean questClear;
	public boolean questChainClear;
	
	public static ArrayList<int[]> levelEnemyType = new ArrayList<int[]>();
	public static ArrayList<Enemy[]> levelEnemyList = new ArrayList<Enemy[]>();
	
	public static int numEnemies = 1;
	public static int numEnemiesDead = 0;
	
	public static Room room;
	public static ScreenPanel gamePanel;
	public static Save save;
	public static int level = 1;
	
	public Frame myFrame;
	
	public Screen(Frame frame) {
		myFrame = frame;
		
		gameLoop.start();
	}
	
	public void paintComponent(Graphics g) {
		if(!createStaticElements) {
			
			define();
			createStaticElements = true;
		}
		
		room.draw(g);
		g.drawImage(new ImageIcon("res/Graphics/hud_frame.png").getImage(),0, 0, Opening.myWidth, Opening.myHeight, null);
		gamePanel.draw(g);
		
		for(int i = 0; i < levelEnemyList.size(); i++) {
			for(int j = 0; j < levelEnemyList.get(i).length; j++) {
				if(levelEnemyList.get(i)[j].inGame) {
					levelEnemyList.get(i)[j].draw(g);
				}
			}
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		
		if(questFailed) {
			      g.fillRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
			      g.setColor(Color.BLACK);
			      g.drawString("Quest Failed", (Opening.myWidth * 3) / 8, (Opening.myHeight * 3) / 8);
			      g.drawString("Better luck next time!", (Opening.myWidth * 3) / 8, Opening.myHeight / 2);
			      g.drawString("Restarting...", (Opening.myWidth * 3) / 8, (Opening.myHeight * 5) / 8);
			      g.drawRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
		    }
		    else if (questClear) {
			      g.fillRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
			      g.setColor(Color.BLACK);
			      g.drawString("Quest Cleared", (Opening.myWidth * 3) / 8, (Opening.myHeight * 3) / 8);
			      g.drawString("Good job!", (Opening.myWidth * 3) / 8, Opening.myHeight / 2);
			      g.drawString("Progressing to next level...", (Opening.myWidth * 3) / 8, (Opening.myHeight * 5) / 8);
			      g.drawRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
		    }
		    else if (questChainClear) {
		    	g.fillRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
		    	g.setColor(Color.BLACK);
		    	g.drawString("Story Mode Cleared", (Opening.myWidth * 3) / 8, (Opening.myHeight * 3) / 8);
		    	g.drawString("Congratulations, Thanks for playing!", (Opening.myWidth * 5) / 16, Opening.myHeight / 2);
		    	g.drawString("Returning to main screen...", (Opening.myWidth * 3) / 8, (Opening.myHeight * 5) / 8);
		    	g.drawRect(Opening.myWidth / 4, Opening.myHeight / 4, Opening.myWidth / 2, Opening.myHeight / 2);
		    }
	}
	
	public void define() {
		
		room = new Room();
		gamePanel = new ScreenPanel();
		save = new Save();
		
		save.loadSave(new File("Save/StoryQuest" + level));
		
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
					if(!levelEnemyList.get(i)[j].inGame && !levelEnemyList.get(i)[j].isDead) {
						levelEnemyList.get(i)[j].spawnEnemy(levelEnemyType.get(i)[j]);
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
	
	public void levelClear(boolean w) {
		if(w == true) {
			if(level < 5) {
				myHealth = 100;
				myGold = 100;
				ScreenPanel.holdItem = false;
				level += 1;
				questClear = false;
				numEnemiesDead = 0;
				
				levelEnemyType = new ArrayList<int[]>();
				levelEnemyList = new ArrayList<Enemy[]>();
				
				define();
			} 
			else if(level == 5) {
				myHealth = 100;
				myGold = 100;
				ScreenPanel.holdItem = false;
				ScreenPanel.showEnemyInfo = false;
				ScreenPanel.showTowerInfo = false;
				
				myFrame.updateFrame();
			}
			else {
				
			}
			
			
			
		} 
		else {
			myHealth = 100;
			myGold = 100;
			ScreenPanel.holdItem = false;
			questFailed = false;
			
			numEnemiesDead = 0;
			
			define();
		}
	}
	
	public void run() {
		while(true) {
			if(createStaticElements) {
				enemySpawner();
				room.physic();
				for(int i = 0; i < levelEnemyList.size(); i++) {
					for(int j = 0; j < levelEnemyList.get(i).length; j++) {
						if(levelEnemyList.get(i)[j].inGame && !levelEnemyList.get(i)[j].isDead) {
							levelEnemyList.get(i)[j].physic();
							if(levelEnemyList.get(i)[j].contains(Opening.mse)) {
								ScreenPanel.displayEnemyInfo(i, j);
							}
						}
						if(levelEnemyList.get(ScreenPanel.enemyWave)[ScreenPanel.enemyIndex].isDead) {
							ScreenPanel.showEnemyInfo = false;
						}
					}
				}
			}
			
			if(myHealth <= 0) {
				questFailed = true;
				try {
					Thread.sleep(2500);
				} catch (Exception e) {}
				levelClear(false);
			}
			
			//System.out.println(numEnemiesDead);
			if(numEnemiesDead >= numEnemies) {
				if(level == 5) {
					questChainClear = true;
				} 
				else {
					questClear = true;
				}
				try {
					Thread.sleep(2500);
				} catch (Exception e) {}
				levelClear(true);
			}
			repaint();
			
			try {
				Thread.sleep(1);
			} catch(Exception e) {
				
			}
		}
	}
}
