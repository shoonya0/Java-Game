//similar role as levelManager
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;


import gameStates.Playing;
import utilz.LoadSave;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] crabbyArr;
//	because we want to save more than one crabby
	private ArrayList<Crabby> crabbies = new ArrayList<Crabby>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		
		loadEnemyImgs();
		
		addEnemies();
	}

	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();
		System.out.println("Size of crabs: " + crabbies.size());
	}

	public void update(int[][] lvlData ,Player player) {
		for(Crabby c : crabbies )
			c.update(lvlData ,player);
	}
	
	public void draw(Graphics g ,int xLvlOffset) {
		drawCrabs(g ,xLvlOffset);
	}
	
//	working
	private void drawCrabs(Graphics g ,int xLvlOffset) {
		for(Crabby c : crabbies) {
			g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int)c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X, (int)c.getHitbox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
//			for drawing hitBox of enemy
			c.drawHitbox(g, xLvlOffset);
		}	
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		
		for(int j = 0 ; j < crabbyArr.length ; j++) 
			for(int i = 0 ; i < crabbyArr[j].length ;i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
	}
}