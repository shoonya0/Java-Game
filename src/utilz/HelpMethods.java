package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	
//	check for overlap with any type of tiles called from -> {Player}
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
//		to get all the width of the level
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		if(x < 0 || x >= maxWidth)
				return true;
		if(y < 0 || y >= Game.GAME_HEIGHT)
				return true;
		
//		to find my position in the lvlData
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;


		int value = lvlData[(int)yIndex][(int)xIndex];

//		checking if the value of particular tile is wall
		if(value >= 48 || value <0 || value != 11)
			return true;
		return false;
	}	
//	called from -> {Player}
//	in normal case we have to check for position of our player but not in this case because our hitBox is smaller than a tile both in width and height
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox ,float xSpeed) {
//		currently our X position in tile
		int currentTile = (int)(hitBox.x / Game.TILES_SIZE);
		if(xSpeed > 0) {
//			Right
//			Position in pixel (area of the tile)
			int tileXPos = currentTile * Game.TILES_SIZE;
//			the x-axis distance between player on right and the starting of tile
			int xOffset = (int)(Game.TILES_SIZE - hitBox.width);
//			so that our hitBox do not collide with other entity
			return tileXPos + xOffset - 1;
		}else 
//			Left
//			because our current tile is the starting position 
			return currentTile * Game.TILES_SIZE;
	}
	
	public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox ,float airSpeed) {
//		currently our X position in tile
		int currentTile = (int)(hitBox.y / Game.TILES_SIZE);
		if(airSpeed > 0) {
//			Falling -> touching floor , reference from (GetEntityXPosNextToWall())
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int)(Game.TILES_SIZE - hitBox.height);
			return tileYPos + yOffset - 1;
		}else
//			Jumping -> 
			return currentTile * Game.TILES_SIZE;
	}
	
//	checking if an entity is on the floor or not called from -> {Player}
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox ,int[][] lvlData) {
//		check pixel below bottom left and bottom right
		if(!IsSolid(hitBox.x, hitBox.y + hitBox.height + 2, lvlData))
			if(!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 2, lvlData))
//				means we are not on floor
				return false;
		return true;		
	}
	
}
