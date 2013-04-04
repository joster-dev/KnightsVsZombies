import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Screen extends JPanel implements Runnable {
	
	public Thread gameLoop = new Thread(this);

	public AudioHandler audioHandler;

	public static int myHealth = 100;
	public static int myGold = 100;
	public static int myWaves;

	boolean[] newWave;
	
	public boolean isViewed = false;
	
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
	public int level = 1;
	public boolean loadGame = false;
	public boolean infiniteGame = false;
	
	public Frame myFrame;

	public Screen(Frame frame) throws Exception {
		myFrame = frame;

		save = new Save();
		
		gameLoop.start();
		audioHandler = new AudioHandler();
	}

	public void paintComponent(Graphics g) {
		if(!isViewed) {
			
			isViewed = true;
		}
		
		room.draw(g);
		g.drawImage(ScreenPanel.sprites.hudFrame ,0, 0, Opening.myWidth, Opening.myHeight, null);
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
		
		ScreenPanel.showEnemyInfo = false;
		ScreenPanel.showTowerInfo = false;
		spawnTime = 1750;
		spawnFrame = -9000;
		nextWaveWaitTime = -10000;
		numEnemiesDead = 0;

		room = new Room();
		gamePanel = new ScreenPanel();
		
		levelEnemyType = new ArrayList<int[]>();
		levelEnemyList = new ArrayList<Enemy[]>();
		
		
		System.out.println("beep");
		if(infiniteGame) {
			System.out.println("boop");
			save.loadSave(new File("Save/InfiniteStage"));
			myWaves = 0;
			infiniteGame = false;
		}
		else if(loadGame) {
			save.loadSave(new File("Save/SavedGame"));
			if(level == 6) myWaves = 0;
			loadGame = false;
		}
		else {
			save.loadSave(new File("Save/StoryQuest" + level));
		}
		
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

		// here's the audio for the game... this is only
		// a sample track... a REALLY bad, sample track...
		// ... still tweaking different aspects of this as well...
		audioHandler.midiHandler.startMidi("res/Sounds/zelda.mid");	// WAS ("res/Sounds/flourish.mid");
		gameLoop.resume();
	}
	
	public int spawnTime = 1750;    //spawnFrame -> spawnTime. When spawnFrame == spawnTime, enemySpawner is called.
	public int spawnFrame = -9000;
	public int nextWaveWaitTime = -10000;
	
	public void enemySpawner() {
		if(spawnFrame >= spawnTime) {
			outerLoop:																//Label so we can break from both loops.
			for (int i = 0; i < levelEnemyList.size(); i++) {
				for (int j = 0; j < levelEnemyList.get(i).length; j++) {
					if(!levelEnemyList.get(i)[j].inGame && !levelEnemyList.get(i)[j].isDead) {
						levelEnemyList.get(i)[j].spawnEnemy(levelEnemyType.get(i)[j]);
						if(j == levelEnemyList.get(i).length - 1) {
							if(!newWave[i]) {
								if(myWaves != 0) myWaves -= 1;
								spawnFrame = nextWaveWaitTime;
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
				ScreenPanel.showEnemyInfo = false;
				ScreenPanel.enemyWave = 0;
				ScreenPanel.enemyIndex = 0;
				ScreenPanel.showTowerInfo = false;
				ScreenPanel.isFastForward = false;
				spawnTime = 1750;
				spawnFrame = -9000;
				nextWaveWaitTime = -10000;
				level += 1;
				questClear = false;
				numEnemiesDead = 0;
				
				levelEnemyType = new ArrayList<int[]>();
				levelEnemyList = new ArrayList<Enemy[]>();

				audioHandler.midiHandler.stopMidi();
				define();
			} 
			else if(level == 5) {
				myHealth = 100;
				myGold = 100;
				ScreenPanel.holdItem = false;
				ScreenPanel.showEnemyInfo = false;
				ScreenPanel.enemyWave = 0;
				ScreenPanel.enemyIndex = 0;
				ScreenPanel.showTowerInfo = false;
				ScreenPanel.isFastForward = false;
				spawnTime = 1750;
				spawnFrame = -9000;
				nextWaveWaitTime = -10000;

				myFrame.updateFrame();
				
				level = 1;
				numEnemiesDead = 0;
				questChainClear = false;

				audioHandler.midiHandler.stopMidi();
				define();
			}
			else {
				for(int i = 0; i < levelEnemyType.size(); i++) {
					for(int j = 0; j < levelEnemyType.get(i).length; j++) {
						levelEnemyList.get(i)[j] = new Enemy();
						if(ScreenPanel.isFastForward) {
							levelEnemyList.get(i)[j].walkSpeed = levelEnemyList.get(i)[j].walkSpeed / 2;
						}
					}
				}
				for (int k = 0; k < levelEnemyType.size(); k++) {
					int indexToIncrease = (int)(Math.random() * levelEnemyType.get(k).length);
					levelEnemyType.get(k)[indexToIncrease] += 1;
					indexToIncrease = (int)(Math.random() * levelEnemyType.get(k).length);
					levelEnemyType.get(k)[indexToIncrease] += 1;
				}
				for(int l = 0; l < newWave.length; l++) {
					newWave[l] = false;
				}
				
				numEnemiesDead = 0;
			}
		} 
		else {
			myHealth = 100;
			myGold = 100;
			ScreenPanel.holdItem = false;
			ScreenPanel.showEnemyInfo = false;
			ScreenPanel.enemyWave = 0;
			ScreenPanel.enemyIndex = 0;
			ScreenPanel.showTowerInfo = false;
			ScreenPanel.isFastForward = false;
			questFailed = false;
			spawnTime = 1750;
			spawnFrame = -9000;
			nextWaveWaitTime = -10000;
			
			numEnemiesDead = 0;
			
			if(level > 5) {
				infiniteGame = true;
			}
			
			audioHandler.midiHandler.stopMidi();
			define();
		}
	}
	
	public void run() {
		while(true) {
			if(isViewed) {
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
					audioHandler.midiHandler.stopMidi();
					audioHandler.soundHandler.playSound("res/Sounds/bitedust.wav");
				}
				catch (Exception e) { e.printStackTrace(); }
				try {
					Thread.sleep(2000);	// was 2500
				} catch (Exception e) {}
				levelClear(false);
			}
			
			if(numEnemiesDead >= numEnemies) {
				if(level == 5) {
					questChainClear = true;
				} 
				else if (level < 5) {
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
