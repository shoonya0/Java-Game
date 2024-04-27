//this stores the data of the particular level
package levels;

public class Level {

// storing the level data
	private int[][] lvlData;
	
//	getting the lvl data
	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
//	for getting the index of the sprite array
	public int getSpriteIndex(int x ,int y) {
		return lvlData[y][x];
	}
	
}
