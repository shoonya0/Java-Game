package main;

import java.awt.Dimension;

//importing the player animation number
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

// here i do my drawing part

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

//	so that we do not add two different mouse inputs because we are implementing more than one interface
	private MouseInputs mouseInputs;
	private float xDelta = 100 ,yDelta = 100;
	private BufferedImage img ;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex ,aniSpeed = 15;
	private int playerAction = IDlE;
	private int playerDir = -1;
	private Boolean moving  = false;
	
	
//	JPanel allow us to create functioning window
	public GamePanel() {
		
//		Initialize new MouseInputs()
		mouseInputs = new MouseInputs(this);
		
//		for an method importing image
		importImg();
		loadAnimations();
		
//		for setting the size of the panel because the frame size is include the above dragging bar.
		setPanelSize();
		
//		here we get all the function which will required here because our KeyboardInputs is implements keyListener here we are passing gamePanel into KeyboardInputes()
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void loadAnimations() {
	//	Initialization of animation array
		animations = new BufferedImage[9][6];
	//	moving the animation using array
		for(int j = 0 ;j<animations.length ;j++) {		
			for(int i=0;i<animations[j].length ;i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
		}	
	}

	private void importImg() {
	//	for getting the image
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
	//	error handling
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	//		try and catch for closing an input stream
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	for setting the panel size
	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimationTick() {
		aniTick++;
//		controlling the speed of the animation
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
//			getting the animation number from
			if(aniIndex >= GetSpriteAmount(playerAction))
				aniIndex = 0;
		}
	}
	
//	for setting the animation for the particular frame
	private void setAnimation() {
		if(moving)
			playerAction = RUNNING;
		else
			playerAction = IDlE;		
	}

//	
	private void updatePos() {
		if(moving) {
			switch (playerDir) {
				case LEFT:
					xDelta-=5;
					break;
				case UP:
					yDelta-=5;
					break;
				case RIGHT:
					xDelta+=5;
					break;
				case DOWN:
					yDelta+=5;
					break;
			}
		}
	}
	
	public void updateGame() {
//		updating the animation
		updateAnimationTick();
		
//		setting the animation according to player action
		setAnimation();
		
//		updating the position of the player according to player actions
		updatePos();
	}
	
//	method in JPanel
//	this component allow us to draw
	public void paintComponent(Graphics g) {
//		calling an super class which is JPanel which first execute this then do it's also clean the surface so that we do not face glitching image many time
		super.paintComponent(g);
		
//		here we are drawing sub image of an image
//		here the 4th variable getFocusCycleRootAncestor() is use for monitoring the status of the image before it's fully drawn
		g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
	}


}	
	
	
	
	
	
	
	
	
		
	
////	EXTRA
	
////	here repaint method is refreshing the paintComponent()
//public void changeXDelta(int value) {
//	this.xDelta += value;
//}
//public void changeYDelta(int value) {
//	this.yDelta += value;
//}
//public void setRectPos(int x,int y) {
//	this.xDelta = x;
//	this.yDelta = y;
//}	

//	private float xDir = 1f ,yDir = 1f;
//	private Color color = new Color(150 ,20 ,90);
//	private Random random;
	
//	inside -> Constructor{
//		Initializing random to get random values of color 
//		random = new Random();
//	}
	
//	inside -> paintComponent(Graphics g) {
//	//calling an method
//	updateRectangle();
//	//here we are changing color of all the graphic element{shapes ,lines} which comes after setColor
//	//whenever the 
//	g.setColor(color);
//	g.fillRect( (int)xDelta, (int)yDelta, 200 , 50);
//	
//	//extra for creating more than one rectangle
//	for(MyReact rect :rects) {
//		rect.updateReact();
//		rect.draw(g);
//	}
//}

////	method to get random color every time the rectangle hit the border
//	private Color getRandColor() {
//		int r = random.nextInt(255);
//		int g = random.nextInt(255);
//		int b = random.nextInt(255);
//		return new Color(r ,g ,b);
//	}
//	private void updateRectangle() {
//		xDelta += xDir;
//		if(xDelta > 400 || xDelta < 0) { 
//			xDir *= -1;
////			updating the color
//			color = getRandColor();
//		}
//		
//		yDelta += yDir;
//		if(yDelta > 400 || yDelta < 0) { 
//			yDir *= -1;	
//			color = getRandColor();
//		}
//	}
//	
////	Temporary {extra} for effects
////	extra for creating more than one rectangle
//	private ArrayList<MyReact> rects = new ArrayList<>();
//	
////	extra for creating more than one rectangle
//	public void spawnReact(int x,int y) {
//		rects.add(new MyReact(x,y));
//	}
//	
////	extra for creating more than one rectangle
//	public class MyReact{
//		int x, y, w, h;
//		int xDir = 1, yDir = 1;
//		Color color;
//		public MyReact(int x ,int y) {
//			this.x = x;
//			this.y = y;
//			w = random.nextInt(50);
//			h = w;
//			color = newColor();
//		}
//		public void updateReact() {
//			this.x += xDir;
//			this.y += yDir;
//			
//			if((x+w) > 400 || x < 0) {
//				xDir *= -1;
//				color = newColor();
//			}
//			if((y+h) > 400 || y < 0) {
//				yDir *= -1;
//				color = newColor();
//			}
//		}
//		private Color newColor() {
//			return new Color(random.nextInt(255) ,random.nextInt(255) ,random.nextInt(255));
//		}
//		public void draw(Graphics g) {
//			g.setColor(color);
//			g.fillRect(x, y, w, h);
//		}
//	}

