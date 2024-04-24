package main;

import java.awt.Color;
import java.awt.Dimension;

// here i do my drawing part

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

//	so that we do not add two different mouse inputs because we are implementing more than one interface
	private MouseInputs mouseInputs;
	private float xDelta = 100 ,yDelta = 100;
	private BufferedImage img ,subImg;
	
//	JPanel allow us to create functioning window
	public GamePanel() {
		
//		Initialize new MouseInputs()
		mouseInputs = new MouseInputs(this);
		
//		for an method importing image
		importImg();
		
//		for setting the size of the panel because the frame size is include the above dragging bar.
		setPanelSize();
		
//		here we get all the function which will required here because our KeyboardInputs is implements keyListener here we are passing gamePanel into KeyboardInputes()
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
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
	}
}

//	for setting the panel size
	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}

	//	here repaint method is refreshing the paintComponent()
	public void changeXDelta(int value) {
		this.xDelta += value;
	}
	public void changeYDelta(int value) {
		this.yDelta += value;
	}
	public void setRectPos(int x,int y) {
		this.xDelta = x;
		this.yDelta = y;
	}
	
//	method in JPanel
//	this component allow us to draw
	public void paintComponent(Graphics g) {
//		calling an super class which is JPanel which first execute this then do it's also clean the surface so that we do not face glitching image many time
		super.paintComponent(g);

		subImg = img.getSubimage(1*64, 8*40, 64, 40);
		
//		here we are drawing sub image of a	n image
//		here the 4th variable getFocusCycleRootAncestor() is use for monitoring the status of the image before it's fully drawn
		g.drawImage(subImg, (int)xDelta, (int)yDelta, 120, 80, null);
		
		
	}	
	
	
}	
	
	
	
	
	
	
	
	
		
	
////	EXTRA
	
	
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

