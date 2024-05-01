package ui;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton{

	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver ,mousePressed;
//	actual button of our slider with minX and maxX of our slider
	private int buttonX ,minX , maxX;
	
//	here all the passed values signifies the entire slider not just one button
	public VolumeButton(int x, int y, int width, int height) {
//		this is creating a bound of passed value
		super(x + width / 2, y, VOLUME_WIDTH, height);
		bound.x -= VOLUME_WIDTH / 2;
		buttonX = x + width / 2;
		this.x = x;
		this.width = width;
		minX = x + VOLUME_WIDTH / 2;
		maxX = x + width - VOLUME_WIDTH / 2;
		loadImgs();
	}
	
	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTON);
		imgs = new BufferedImage[3];
		for(int i = 0; i < imgs.length ;i++) 
			imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
		
//		because we have 3 volume button of width VOLUME_DEFAULT_WIDTH and after that we have the slider
		slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
	}

	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(slider, x, y, width, height, null);
		g.drawImage(imgs[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);
	}
	
	public void changeX(int x) {
		if(x < minX)
			buttonX = minX;
		else if(x > maxX)
			buttonX = maxX;
		else 
			buttonX = x;
		
		bound.x = buttonX - VOLUME_WIDTH / 2;
	}
	
//	we call this whenever we have a mouse release event happening
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}	
}
