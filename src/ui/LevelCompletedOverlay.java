package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.Menu;
import gameStates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;


public class LevelCompletedOverlay {

	private Playing playing;
	private UrmButton menu ,next;

	private BufferedImage img;
//	Decide where and how big is the Image
	private int bgX ,bgY ,bgW ,bgH;
	
	public LevelCompletedOverlay(Playing playing) {
		this.playing = playing;
		initImg();
		initButtons();
	}

	private void initButtons() {
		int menuX = (int) (330 * Game.SCALE);
		int nextX = (int) (445 * Game.SCALE);
		int y = (int) (195 * Game.SCALE);
//		0th position in image is for play button and 2th position is for home
		next = new UrmButton(nextX, y, menuX, URM_SIZE, 0);
		menu = new UrmButton(menuX, y, menuX, URM_SIZE, 2);		
	}

	private void initImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
		bgW = (int)(img.getWidth() * Game.SCALE);
		bgH = (int)(img.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (75 * Game.SCALE);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, bgX, bgY, bgW, bgH, null);
		next.draw(g);
		menu.draw(g);
	}
	
	public void update() {
		
	}
	
	public void mouseMove(MouseEvent e) {
		
	}
	
	public void mouseReleased (MouseEvent e) {
		
	}
	
	public void mousePressed (MouseEvent e) {
		
	}
}
