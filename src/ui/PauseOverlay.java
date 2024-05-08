package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.GameState;
import gameStates.Playing;
import main.Game;
import utilz.Constants.UI.Buttons;
import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverlay {

	private Playing playing;
//	background for pause background
	private BufferedImage backgroundImg;
	private int bgX ,bgY ,bgW ,bgH;
	private SoundButton musicButton ,sfxButton;
	private UrmButton menuB ,replayB ,unpauseB;
	private VolumeButton volumeButton;
	
	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		
		createSoundButton();
		
		creatUrmButtons();
		
		createVolumeButton();
	}
	
	private void createVolumeButton() {
		int vX = (int)(309 * Game.SCALE);
		int vY = (int)(255 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void creatUrmButtons() {
//		all the button have different X values but same Y value
		int menuX = (int)(313 * Game.SCALE);
		int replayX = (int)(383 * Game.SCALE);
		int unpauseX = (int)(452 * Game.SCALE);
		int bY = (int)(300 * Game.SCALE);
		
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		
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
		
		menuB.update();
		replayB.update();
		unpauseB.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
//		Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH ,null);
		
//		Sound Buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
//		URM Buttons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
//		Volume slider
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(volumeButton.isMousePressed()) 
			volumeButton.changeX(e.getX());
	}
	
	public void mousePressed(MouseEvent e) {
		if(isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if(isIn(e, sfxButton))
			 sfxButton.setMousePressed(true);
		else if(isIn(e, menuB))
			menuB.setMousePressed(true);
		else if(isIn(e, replayB))
			replayB.setMousePressed(true);
		else if(isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if(isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
	}

	
	public void mouseReleased(MouseEvent e) {
		if(isIn(e, musicButton)) {
			if(musicButton.isMousePressed())
//				here we are flipping the value of musicButton
				musicButton.setMuted(!musicButton.isMuted());
		}else if(isIn(e, sfxButton)) {
			if(sfxButton.isMousePressed())
//				here also we are flipping the sfxButton
				sfxButton.setMuted(!sfxButton.isMuted());
		}else if(isIn(e, menuB)) {
			if(menuB.isMousePressed()) {
				GameState.state = GameState.MENUE;
//				this means in between game if we pause our game and go to home and then click play then we can play our game directly
				playing.unpauseGame();
			}
		}else if(isIn(e, replayB)) {
			if(replayB.isMousePressed()){
				playing.resetAll();
				playing.unpauseGame();
			}
		}else if(isIn(e, unpauseB)) {
			if(unpauseB.isMousePressed())
				playing.unpauseGame();
		}
		
		
		musicButton.resetBools();
		sfxButton.resetBools();
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);		
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if(isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if(isIn(e, sfxButton))
			 sfxButton.setMouseOver(true);
		else if(isIn(e, menuB))
			menuB.setMouseOver(true);
		else if(isIn(e, replayB))
			replayB.setMouseOver(true);
		else if(isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if(isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
	}	
	
//	we use PauseButton instead of soundButton because pauseButton is the super class of soundButton and we can access all button from here 
	private Boolean isIn(MouseEvent e ,PauseButton b) {
		return b.getBound().contains(e.getX() ,e.getY());
	}
}