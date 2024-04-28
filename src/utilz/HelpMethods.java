package utilz;

import main.Game;

public class HelpMethods {
	
//	check for overlap with any type of tiles
	public static boolean CanMoveHere(float x ,float y ,float width ,float height ,int[][] lvlData) {
		if(!IsSolid(x, y, lvlData))
			if(!IsSolid(x + width, y + height, lvlData))
				if(!IsSolid(x + width, y, lvlData))
					if(!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}
	
//	checking is it a tile and also checking the position is inside the game window
	private static boolean IsSolid(float x ,float y ,int[][] lvlData) {
		if(x < 0 || x >= Game.GAME_WIDTH)
				return true;
		if(y < 0 || y >= Game.GAME_HEIGHT)
				return true;
		
//		to find my position in the lvlData
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int)yIndex][(int)xIndex];
		
		if(value >= 48 || value <0 || value != 11)
			return true;
		return false;
	}	
	
}
