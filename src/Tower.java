public class Tower {
	
	public int towerCost;
	public int towerDamage;
	public int towerRate;
	public int towerRange;
	
	public Tower(int towerId) {
		towerCost = Value.getTowerStats("cost", towerId);
		towerDamage = Value.getTowerStats("attack", towerId);
		towerRate = Value.getTowerStats("speed", towerId);
		towerRange = Value.getTowerStats("range", towerId);
	}
}
