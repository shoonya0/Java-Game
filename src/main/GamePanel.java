package main;

// here i do my drawing part

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

//	so that we do not add two different mouse inputs because we are implementing more than one interface
	private MouseInputs mouseInputs;
	private int xDelta = 100 ,yDelta = 1000;
	
//	JPanel allow us to create functioning window
	public GamePanel() {
		
//		Initialize new MouseInputs()
		mouseInputs = new MouseInputs(this);
		
//		here we get all the function which will required here because our
//		KeyboardInputs is implements keyListener
//		here we are passing gamePanel into KeyboardInputes()
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	public void setRectPos(int x,int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}
	
	
//	here repaint method is refreshing the paintComponent()
	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	
//	method in JPanel
//	this component allow us to draw
	public void paintComponent(Graphics g) {
//		calling an super class which is JPanel which first execute this then do it's
//		also clean the surface so that we do not glitching image many time
		super.paintComponent(g);
		
		g.fillRect( xDelta, yDelta, 200 , 50);
	}
}
