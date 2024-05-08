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
		next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
		menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);		
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
		next.update();
		menu.update();
	}
	
//	this is because we do not have to check that we are inside button or not over and over
	private boolean isIn(UrmButton b ,MouseEvent e) {
		return b.getBound().contains(e.getX() ,e.getY());
	}
	
	public void mouseMove(MouseEvent e) {
		next.setMouseOver(false);
		menu.setMouseOver(false);
		
		if(isIn(menu, e))
			menu.setMouseOver(true);
		else if(isIn(next, e)){
			next.setMouseOver(true);
		}
	}
	
	public void mouseReleased (MouseEvent e) {
		if(isIn(menu, e)) {
			if(menu.isMousePressed())
				System.out.println("Menu");
		}else if(isIn(next, e)){
			if(next.isMousePressed())
				System.out.println("Next");
		}
		
//		when we release our mouse
		menu.resetBools();
		next.resetBools();
	}
	
	public void mousePressed (MouseEvent e) {
		if(isIn(menu, e))
			menu.setMousePressed(true);
		else if(isIn(next, e))
			next.setMousePressed(true);
	}
}
