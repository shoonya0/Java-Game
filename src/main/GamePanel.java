package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

//	JPanel allow us to create functioning window
	public GamePanel() {
		
	}
	
//	method in JPanel
//	this component allow us to draw
	public void paintComponent(Graphics g) {
//		calling an super class which is JPanel which first execute this then do it's
//		also clean the surface so that we do not glitching image many time
		super.paintComponent(g);
		
		g.fillRect(100 ,100 ,200 ,50);
	}
}
