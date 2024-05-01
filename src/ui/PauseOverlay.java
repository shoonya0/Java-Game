package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.GameState;
import main.Game;
import utilz.Constants.UI.Buttons;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class PauseOverlay {

//	background for pause background
	private BufferedImage backgroundImg;
	private int bgX ,bgY ,bgW ,bgH;
	private SoundButton musicButton ,sfxButton;
	
	public PauseOverlay() {
		loadBackground();
		
		createSoundButton();
	}
	
	private void createSoundButton() {
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(120 * Game.SCALE);
		int sfxY = (int)(165 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(backgroundImg.getWidth() * Game.SCALE);
		bgH = (int)(backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)Game.SCALE;
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
	}
	
	public void draw(Graphics g) {
//		Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH ,null);
		
//		Sound Buttons
		musicButton.draw(g);
		sfxButton.draw(g);
	}
	
	public void mouseDragged() {
		
	}
	
	public void mousePressed(MouseEvent e) {
		if(isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if(isIn(e, sfxButton))
			 sfxButton.setMousePressed(true);
	}

	
//	working
	public void mouseReleased(MouseEvent e) {
		if(isIn(e, musicButton)) {
			if(musicButton.isMousePressed())
//				here we are flipping the value of musicButton
				musicButton.setMuted(!musicButton.isMuted());
		}else if(isIn(e, sfxButton))
			if(sfxButton.isMousePressed())
//				here also we are flipping the sfxButton
				sfxButton.setMuted(!sfxButton.isMuted());
		
		musicButton.resetBools();
		sfxButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		

		if(isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if(isIn(e, sfxButton))
			 sfxButton.setMouseOver(true);
	}	
	
//	we use PauseButton instead of soundButton because pauseButton is the super class of soundButton and we can access all button from here 
	private Boolean isIn(MouseEvent e ,PauseButton b) {
		return b.getBound().contains(e.getX() ,e.getY());
	}
}