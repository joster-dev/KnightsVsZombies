import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class Screen extends JPanel implements Runnable {
	
	public Thread gameLoop = new Thread(this);

	public AudioHandler audioHandler;

	public static int myHealth = 100;
	public static int myGold = 100;
	public static int myWaves;								//Number of groups of enemies in a level.

	boolean[] newWave;
	
	public boolean isViewed = false;						//Only runs the gameLoop thread after the Screen was painted.
	public boolean initiateInfinite = false;				// boolean used to continue streaming music in infinite mode
	
	public boolean questFailed;
	public boolean questClear;
	public boolean questChainClear;
	
	public static ArrayList<int[]> levelEnemyType = new ArrayList<int[]>();				//Stores what enemy.
	public static ArrayList<Enemy[]> levelEnemyList = new ArrayList<Enemy[]>();			//Stores actual enemy.
	
	public static int numEnemies = 1;
	public static int numEnemiesDead = 0;
	public static int totalEnemiesDead = 0;					//For High Score purposes.
	
	public static Room room;
	public static ScreenPanel gamePanel;
	public static Save save;

	public int level = 1;
	//*For Loading*//
	public boolean loadGame = false;
	public boolean infiniteGame = false;
	//*//
	public static int infiniteResetCounter = 0;
	
	public Frame myFrame;

	public Screen(Frame frame) throws Exception {
		myFrame = frame;
		audioHandler = myFrame.audioHandler;

		save = new Save();
		
		gameLoop.start();
	}

	public void paintComponent(Graphics g) {
		if(!isViewed) {
			isViewed = true;								//Once Screen is painted, isViewed becomes true, starting gameLoop.
		}
		
		room.draw(g);
		
		for(int i = 0; i < levelEnemyList.size(); i++) {
			for(int j = 0; j < levelEnemyList.get(i).length; j++) {
				if(levelEnemyList.get(i)[j].inGame) {
					levelEnemyList.get(i)[j].draw(g);		//Draws all of the enemies.
				}
			}
		}

		g.drawImage(ScreenPanel.sprites.hudFrame ,0, 0, Opening.myWidth, Opening.myHeight, null);
		gamePanel.draw(g);
		
		g.setColor(Color.ORANGE);
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
		
		if(infiniteGame) {
			save.loadSave(new File("Save/InfiniteStage"));
			myWaves = 0;
			infiniteGame = false;
		}
		else if(loadGame) {
			save.loadSave(new File("Save/SavedGame"));
			if(level == 6) {
				myWaves = 0;
				initiateInfinite = true;
			}
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
				levelEnemyList.get(i)[j] = new Enemy(myFrame);
			}
		}
		newWave = new boolean[levelEnemyList.size()];
		for(int i = 0; i < newWave.length; i++) {
			newWave[i] = false;
		}

		switch(level) 
		{
		case 1:	if( !myFrame.openingScreen.isVisible() ) {
					// I will replace this with level 1 music soon...
					audioHandler.midiHandler.startMidi("res/Sounds/zelda.mid");
				}
				break;
		case 2: if( !myFrame.openingScreen.isVisible() ) {
					audioHandler.midiHandler.startMidi("res/Sounds/2_swamp.mid");
				}
				break;
		case 3: if( !myFrame.openingScreen.isVisible() ) {
					audioHandler.midiHandler.startMidi("res/Sounds/3_forest.mid");
				}
				break;
		case 4: if( !myFrame.openingScreen.isVisible() ) {
					// I will replace this with level 4 music soon...
					audioHandler.midiHandler.startMidi("res/Sounds/4_steampunk.mid");
				}
				break;
		case 5: if( !myFrame.openingScreen.isVisible() ) {
					// I will replace this with level 5 music soon...
					audioHandler.midiHandler.startMidi("res/Sounds/zelda.mid");
				}
				break;
		default:if( !myFrame.openingScreen.isVisible() && initiateInfinite) {
					// I will replace this with infinite music soon...
					audioHandler.midiHandler.startMidi("res/Sounds/zelda.mid");
					initiateInfinite = false;
				}
				break;
		}

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
	
	public void levelClear(boolean w) {							//Parameter true means level complete, false means level fail.
		if(w == true) {
			if(level < 5) {										//If story level between 1-4 reset and progress.
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

				Frame.highScores.unlockAchievement(level - 1);		//Unlock achievement for beating a level.
				level += 1;
				questClear = false;
				numEnemiesDead = 0;

				audioHandler.midiHandler.stopMidi();
				define();
			} 
			else if(level == 5) {							//If story level is 5 reset and return to main screen.
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
				isViewed = false;

				levelEnemyType = new ArrayList<int[]>();
				levelEnemyList = new ArrayList<Enemy[]>();

				myFrame.updateFrame();
				Frame.highScores.unlockAchievement(level - 1);		//Unlock achievement for beating level 5.

				numEnemiesDead = 0;
				questChainClear = false;
			}
			else {													//Handles level progression in infinite mode.
				for(int i = 0; i < levelEnemyType.size(); i++) {
					for(int j = 0; j < levelEnemyType.get(i).length; j++) {
						levelEnemyList.get(i)[j] = new Enemy(myFrame);
						if(ScreenPanel.isFastForward) {
							levelEnemyList.get(i)[j].walkSpeed = levelEnemyList.get(i)[j].walkSpeed / 2;
						}
					}
				}
				for (int k = 0; k < levelEnemyType.size(); k++) {					//Make 3 enemies from each wave stronger.
					int indexToIncrease = (int)(Math.random() * levelEnemyType.get(k).length);
					if(levelEnemyType.get(k)[indexToIncrease] < Value.level5boss) {
						levelEnemyType.get(k)[indexToIncrease] += 1;
					}
					indexToIncrease = (int)(Math.random() * levelEnemyType.get(k).length);
					if(levelEnemyType.get(k)[indexToIncrease] < Value.level5boss) {
						levelEnemyType.get(k)[indexToIncrease] += 1;
					}
					indexToIncrease = (int)(Math.random() * levelEnemyType.get(k).length);
					if(levelEnemyType.get(k)[indexToIncrease] < Value.level5boss) {
						levelEnemyType.get(k)[indexToIncrease] += 1;
					}
				}
				for(int l = 0; l < newWave.length; l++) {							//Make sure we can spawn more enemies.
					newWave[l] = false;
				}
				numEnemiesDead = 0;
			}
		} 
		else {															//This case handles losing a level.
			myHealth = 100;												//Reset health and gold.
			myGold = 100;
			ScreenPanel.holdItem = false;								//Reset the level variables.
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
			
			if(level > 5) {												//If they lose infite mode, see if they got a high score.
				Frame.highScores.newHighScore("You", totalEnemiesDead);
				infiniteGame = true;
			}

			define();												//Reset the level.
		}
	}
	
	public void run() {
		while(true) {
			if(isViewed) {										//Only run the thread if the Screen is visible.
				enemySpawner();									//Spawn some enemies.
				room.physic();									//Handles logic for the map.
				for(int i = 0; i < levelEnemyList.size(); i++) {
					for(int j = 0; j < levelEnemyList.get(i).length; j++) {
						if(levelEnemyList.get(i)[j].inGame && !levelEnemyList.get(i)[j].isDead) {
							levelEnemyList.get(i)[j].physic();			//Move enemies around if they are alive.
							if(levelEnemyList.get(i)[j].contains(Opening.mse)) {
								ScreenPanel.displayEnemyInfo(i, j);		//If enemy is moused over, show its stats.
							}
						}
						if(levelEnemyList.get(ScreenPanel.enemyWave)[ScreenPanel.enemyIndex].isDead) {
							ScreenPanel.showEnemyInfo = false;			//If the enemy dies, dont show its info.
						}
					}
				}
			}
			
			if(myHealth <= 0) {											//Handles losing a level.
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
			
			if(numEnemiesDead >= numEnemies) {							//Handles winning a level.
				if(level == 5) {
					questChainClear = true;
					repaint();
					audioHandler.midiHandler.stopMidi();
					try {
						Thread.sleep(2500);
					} catch (Exception e) {}
				} 
				else if (level < 5) {
					questClear = true;
					repaint();
					audioHandler.midiHandler.stopMidi();
					try {
						Thread.sleep(2500);
					} catch (Exception e) {}
				}
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
