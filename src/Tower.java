
public class Tower {
	
	public static int towerCost;
	public static int towerDamage;
	public static int towerRate;
	public static int towerRange;
	
	public Tower(int towerId) {
		
		towerCost = Value.getTowerStats("cost", towerId);
		towerDamage = Value.getTowerStats("damage", towerId);
		towerRate = Value.getTowerStats("speed", towerId);
		towerRange = Value.getTowerStats("range", towerId);
		
	}
}
