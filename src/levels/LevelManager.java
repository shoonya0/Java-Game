package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	
	private Game game;
	private BufferedImage[] levelSprite ;
	private Level levelOne;
	
	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		
//		here we are getting the data of the level
		levelOne = new Level(LoadSave.GetLevelDate());
	}
	
//	method for initializing an level array 
	private void importOutsideSprites() {
//		taking the image of level
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ALTAS);
//		getting the images into single 1D array
 		levelSprite = new BufferedImage[48];
		for(int j = 0; j < 4 ;j++) {
			for(int i = 0; i < 12 ; i++) {
				int index = j*12 + i;
				levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
		}
	}

//	here i am drawing the level
	public void draw(Graphics g ,int lvlOffset) {
//		here we are drawing sub image of an image
//		here the 4th variable getFocusCycleRootAncestor() is use for monitoring the status of the image before it's fully drawn
		
		for(int j = 0 ;j < Game.TILES_IN_HEIGHT ;j++)
			for(int i = 0 ; i < levelOne.getLevelData()[0].length ;i++) {
				int index = levelOne.getSpriteIndex(i, j);				
				g.drawImage(levelSprite[index], Game.TILES_SIZE*i - lvlOffset,Game.TILES_SIZE*j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
	}
	
//	for getting level Data called from -> {Game}
	public Level getCurrentLevel() {
		return levelOne;
	}
	
	public void update() {
		
	}
}
