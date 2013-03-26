import java.io.*;
import java.util.*;

public class Save {
	
	public void loadSave(File loadPath) {
		int[] waveEnemies;
		int numEnemiesInWave;
		int numEnemies = 0;
		int airIdHolder = 0;
		
		try {
			Scanner loadScanner = new Scanner(loadPath);
			
			while (loadScanner.hasNext()) {
				
				Frame.gameScreen.level = loadScanner.nextInt();
				
				for(int y = 0; y < Room.block.length; y++) {
					for(int x = 0; x < Room.block[0].length; x++) {
						Room.block[y][x].groundId = loadScanner.nextInt();
					}
				}
				
				for(int y = 0; y < Room.block.length; y++) {
					for(int x = 0; x < Room.block[0].length; x++) {
						airIdHolder = loadScanner.nextInt();
						
						if(airIdHolder > 0) Room.block[y][x].addTower(airIdHolder);
						else Room.block[y][x].airId = airIdHolder;
					}
				}
				
				Screen.myWaves = loadScanner.nextInt();
				for(int i = 0; i < Screen.myWaves; i++) {
					numEnemiesInWave = loadScanner.nextInt();
					numEnemies += numEnemiesInWave;
					waveEnemies = new int[numEnemiesInWave];
					for(int j = 0; j < numEnemiesInWave; j ++) {
						waveEnemies[j] = loadScanner.nextInt();
					}
					Screen.levelEnemyType.add(waveEnemies);
				}
				Screen.numEnemies = numEnemies;
			}
			
			loadScanner.close();
			
		} catch(Exception e) {}
	}
	
	public void saveGame(File savePath) {
		String enemyGroundIds = "";
		String enemyAirIds = "";
		String numWaves = "";
		int startWave = 0;
		int startIndex = 0;
		
		try {
			savePath.createNewFile();
			
			FileWriter fw = new FileWriter(savePath.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(Integer.toString(Frame.gameScreen.level) + "\n \n");
			
			for(int y = 0; y < Room.block.length; y++) {
				for(int x = 0; x < Room.block[0].length; x++) {
					enemyGroundIds += " " + Room.block[y][x].groundId;
				}
				enemyGroundIds += "\n";
			}
			enemyGroundIds += "\n";
			
			bw.write(enemyGroundIds);
			
			for(int y = 0; y < Room.block.length; y++) {
				for(int x = 0; x < Room.block[0].length; x++) {
					enemyAirIds += " " + Room.block[y][x].airId;
				}
				enemyAirIds += "\n";
			}
			enemyAirIds += "\n";
			
			bw.write(enemyAirIds);
			
			for(int i = 0; i < Screen.levelEnemyList.size(); i++) {
				if(numWaves == "") {
					for(int j = 0; j < Screen.levelEnemyList.get(i).length; j++) {
						if(!Screen.levelEnemyList.get(i)[j].isDead) {
							startWave = i;
							startIndex = j;
							numWaves = (Integer.toString(Screen.levelEnemyList.size() - i));
							break;
						}
					}
				}
			}
			bw.write(numWaves + "\n \n");
			
			for(int i = startWave; i < Screen.levelEnemyList.size(); i++) {
				if(i == startWave) { 
					bw.write(Integer.toString(Screen.levelEnemyList.get(i).length - startIndex));
					bw.write("  ");
					for(int j = startIndex; j < Screen.levelEnemyList.get(i).length; j++) {
						bw.write(Integer.toString(Screen.levelEnemyType.get(i)[j]) + " ");
					}
					bw.write("\n");
				}
				else {
					bw.write(Integer.toString(Screen.levelEnemyList.get(i).length));
					bw.write("  ");
					for(int j = 0; j < Screen.levelEnemyList.get(i).length; j++) {
						bw.write(Integer.toString(Screen.levelEnemyType.get(i)[j]) + " ");
					}
					bw.write("\n");
				}
			}
			
			bw.close();
		}
		catch(Exception e) {}
	}
}