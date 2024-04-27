// this is our super class which we can use for both player and enemies
package entities;

import java.util.spi.LocaleNameProvider;

// abstract class is a class that you can not make an object of we can extend it and store values like position ,health
public abstract class Entity {
//	only class which extend this can use x ,y
	protected float x ,y;
	protected int playerLen ,playerBre;
	public Entity(float x, float y ,int playerLen ,int playerBre) {
		this.x = x;
		this.y = y;
		this.playerLen = playerLen;
		this.playerBre = playerBre;
	}
	
}
