public class Value {
	
	//*Ground*//
	
	public static int groundOpen = 0;
	public static int pathOpen = 1;
	
	//*//
	
	//*Air*//
	
	public static int airOpen = -1;
	public static int airCastle = 0;
	
	public static int knightTower = 1;
	public static int knightCost = 25;
	public static int knightAtk = 15;
	public static int knightSpd = 1;
	public static int knightRng = 1;
	
	public static int archerTower = 2;
	public static int archerCost = 35;
	public static int archerAtk = 17;
	public static int archerSpd = 1;
	public static int archerRng = 2;
	
	public static int mageTower = 3;
	public static int mageCost = 60;
	public static int mageAtk = 25;
	public static int mageSpd = 1;
	public static int mageRng = 1;
	
	public static int longTower = 4;
	public static int longCost = 75;
	public static int longAtk = 25;
	public static int longSpd = 1;
	public static int longRng = 3;
	
	public static int demoTower = 5;
	public static int demoCost = 99;
	public static int demoAtk = 50;
	public static int demoSpd = 1;
	public static int demoRng = 2;
	
	//*//
	
	//*Zombies*//
	
	public static int basicZombie = 0;
	public static int basicZombieHealth = 55;
	public static int basicZombieArmor = 1;
	
	public static int skeletonZombie = 1;
	public static int skeletonZombieHealth = 75;
	public static int skeletonZombieArmor = 3;
	
	public static int ghost = 2;
	public static int ghostHealth = 80;
	public static int ghostArmor = 9;
	
	public static int swampZombie = 3;
	public static int swampZombieHealth = 100;
	public static int swampZombieArmor = 6;
	
	public static int bombZombie = 4;
	public static int bombZombieHealth = 110;
	public static int bombZombieArmor = 8;
	
	public static int level1boss = 5;
	public static int level1bossHealth = 400;
	public static int level1bossArmor = 1;
	
	public static int level2boss = 6;
	public static int level2bossHealth = 350;
	public static int level2bossArmor = 5;
	
	public static int level3boss = 7;
	public static int level3bossHealth = 250;
	public static int level3bossArmor = 12;
	
	public static int level4boss = 8;
	public static int level4bossHealth = 450;
	public static int level4bossArmor = 6;
	
	public static int level5boss = 9;
	public static int level5bossHealth = 550;
	public static int level5bossArmor = 9;
	
	//*//
	
	public static int getZombieStats(String stat, int zombieId) {
		if(stat == "health") {
			if(zombieId == basicZombie) {
				return basicZombieHealth;
			}
			else if(zombieId == skeletonZombie) {
				return skeletonZombieHealth;
			}
			else if(zombieId == ghost) {
				return ghostHealth;
			}
			else if(zombieId == swampZombie) {
				return swampZombieHealth;
			}
			else if(zombieId == bombZombie) {
				return bombZombieHealth;
			}
			else if(zombieId == level1boss) {
				return level1bossHealth;
			}
			else if(zombieId == level2boss) {
				return level2bossHealth;
			}
			else if(zombieId == level3boss) {
				return level3bossHealth;
			}
			else if(zombieId == level4boss) {
				return level4bossHealth;
			}
			else if(zombieId == level5boss) {
				return level5bossHealth;
			}
			else {
				return 0;
			}
		}
		else if(stat == "armor") {
			if(zombieId == basicZombie) {
				return basicZombieArmor;
			}
			else if(zombieId == skeletonZombie) {
				return skeletonZombieArmor;
			}
			else if(zombieId == ghost) {
				return ghostArmor;
			}
			else if(zombieId == swampZombie) {
				return swampZombieArmor;
			}
			else if(zombieId == bombZombie) {
				return bombZombieArmor;
			}
			else if(zombieId == level1boss) {
				return level1bossArmor;
			}
			else if(zombieId == level2boss) {
				return level2bossArmor;
			}
			else if(zombieId == level3boss) {
				return level3bossArmor;
			}
			else if(zombieId == level4boss) {
				return level4bossArmor;
			}
			else if(zombieId == level5boss) {
				return level5bossArmor;
			}
			else {
				return 0;
			}
		}
		return -1;
	}
	
	public static int getTowerStats(String stat, int towerId) {
		if(stat == "cost") {
			if(towerId == knightTower) {
				return knightCost;
			}
			else if(towerId == archerTower) {
				return archerCost;
			}
			else if(towerId == mageTower) {
				return mageCost;
			}
			else if(towerId == longTower) {
				return longCost;
			}
			else if(towerId == demoTower) {
				return demoCost;
			}
		}
		else if(stat == "attack") {
			if(towerId == knightTower) {
				return knightAtk;
			}
			else if(towerId == archerTower) {
				return archerAtk;
			}
			else if(towerId == mageTower) {
				return mageAtk;
			}
			else if(towerId == longTower) {
				return longAtk;
			}
			else if(towerId == demoTower) {
				return demoAtk;
			}
		}
		else if(stat == "speed") {
			if(towerId == knightTower) {
				return knightSpd;
			}
			else if(towerId == archerTower) {
				return archerSpd;
			}
			else if(towerId == mageTower) {
				return mageSpd;
			}
			else if(towerId == longTower) {
				return longSpd;
			}
			else if(towerId == demoTower) {
				return demoSpd;
			}
		}
		else if(stat == "range") {
			if(towerId == knightTower) {
				return knightRng;
			}
			else if(towerId == archerTower) {
				return archerRng;
			}
			else if(towerId == mageTower) {
				return mageRng;
			}
			else if(towerId == longTower) {
				return longRng;
			}
			else if(towerId == demoTower) {
				return demoRng;
			}
		}
		return -1;
	}
}
