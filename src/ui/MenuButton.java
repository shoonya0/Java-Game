package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import gameStates.GameState;
import utilz.LoadSave;
// for importing the constant class whose value do not change
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
	private int xPos ,yPos ,rowIndex ,index;
	private int xOffsetCenter = B_WIDTH / 2;
	private GameState state;
//	for storing all the images of button
	private BufferedImage[] imgs;
	private Boolean mouseOver = false ,mousePressed = false;
//	hitBox of a Button we only want int value so we do not use Rectangel2D 
	private Rectangle bounds;
	
	public MenuButton(int xPos ,int yPos ,int rowIndex ,GameState state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		
//		loading of menu images
		loadImgs();
		
//		for setting Bounds of menue
		initBounds();
	}

//	Initializing the hitBox of Rectangle
	private void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter ,yPos ,B_WIDTH ,B_HEIGHT);
	}

	private void loadImgs() {
		imgs = new BufferedImage[3];
		
//		taking image of menu from -> ./utilz/LoadSave
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for(int i=0 ; i < imgs.length ;i++)
			imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
	}
	
	public void draw(Graphics g) {
//		the image is start from center so we subtract it with Offset
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}

//	our getter and setter method for mouse pressed and mouse over
	public Boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(Boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public Boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(Boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle geBounds() {
		return bounds;
	}
	
//	for updating the state of the Game like menu to pause or vice versa
	public void applyGameState() {
		GameState.state = state;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
}
