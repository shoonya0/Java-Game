// this is our super class which we can use for both player and enemies
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

// abstract class is a class that you can not make an object of we can extend it and store values like position ,health
public abstract class Entity {
//	only class which extend this can use x ,y
	protected float x ,y;
	protected int width ,height;
//	this is the hitBox for the player and enemies (using 2D.float to use float value) this help us in collision detection
	protected Rectangle2D.Float hitBox;
	
//	called from -> {player} (this is the super class of Player)
	public Entity(float x, float y ,int width ,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

//	this is for drawing the hitBox so that we can check how much area did our hitBox cover called from { Player }
	protected void drawHitbox(Graphics g ,int xLvlOffset) {
//		for debugging the hitBox
		g.setColor(Color.BLACK);
		g.drawRect((int)hitBox.x - xLvlOffset, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
	}
	
//	for initializing hitBox -> called from { Player }
	protected void initHitbox(float x ,float y ,int width ,int height) {
		hitBox = new Rectangle2D.Float(x ,y ,width ,height);
	}
	
//	protected void updateHitbox() {
//		hitBox.x = (int)x;
//		hitBox.y = (int)y;		
//	}
	
//	for getting the hitBox
	public Rectangle2D.Float getHitbox() {
		return hitBox;
	}
}
