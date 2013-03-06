import java.io.*;
import java.util.*;

public class Save {
	
	public void loadSave(File loadPath) {
		int[]waveEnemies;
		int numEnemies = 0;
		
		try {
			Scanner loadScanner = new Scanner(loadPath);
			
			while (loadScanner.hasNext()) {
				
				for(int y = 0; y < Screen.room.block.length; y++) {
					for(int x = 0; x < Screen.room.block[0].length; x++) {
						Screen.room.block[y][x].groundId = loadScanner.nextInt();
					}
				}
				
				for(int y = 0; y < Screen.room.block.length; y++) {
					for(int x = 0; x < Screen.room.block[0].length; x++) {
						Screen.room.block[y][x].airId = loadScanner.nextInt();
					}
				}
				
				Screen.myWaves = loadScanner.nextInt();
				for(int i = 0; i < Screen.myWaves; i++) {
					int numEnemiesInWave = loadScanner.nextInt();
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
			
		} catch(Exception e) {
			
		}
	}
}