package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
	

	private BufferedImage[][] animations;
	private int aniTick, aniIndex ,aniSpeed = 15;
	private int playerAction = IDlE;
	private Boolean moving  = false ,attacking = false;
	private Boolean left = false ,up = false ,right = false ,down = false;
	private float playerSpeed = 2.0f;
	
//	here we are passing the x,y to entity
	public Player(float x, float y) {
		super(x, y);
//		for loading the animation in the animation array
		loadAnimations();
	}
	
	public void update() {
//		updating the position of the player according to player actions
		updatePos();
		
//		updating the animation
		updateAnimationTick();
		
//		setting the animation according to player action if moving ,ideal ,attack ...  
		setAnimation();
		
	}

//	for rendering player
	public void render(Graphics g) {
//		here we are drawing sub image of an image
//		here the 4th variable getFocusCycleRootAncestor() is use for monitoring the status of the image before it's fully drawn
		g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 256, 160, null);
	}
	
	private void updateAnimationTick() {
		aniTick++;
//		controlling the speed of the animation
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
//			getting the animation number from
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
//				for stopping animation
				attacking = false;
			}
		}
	}
	
//	for setting the animation for the particular frame
	private void setAnimation() {
		int startAni = playerAction;		
		
		if(moving)
			playerAction = RUNNING;
		else
			playerAction = IDlE;
//		for attacking
		if(attacking)
			playerAction = ATTACK_1;
		
//		for reseting animation index to zero
		if(startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;	
	}

//	use for updating the position of the player
	private void updatePos() {
		
//		for ideal case
		moving = false;
		
		if(left && !right) {
			x -= playerSpeed;
			moving = true;
		}else if(right && !left){
			x += playerSpeed;
			moving = true;
		}
		if(up && !down) {
			y -= playerSpeed;
			moving = true;
		}else if(!up && down) {
			y += playerSpeed;
			moving = true;
		}
	}
	
	private void loadAnimations() {
//		for getting the image
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
//		error handling
		try {
			BufferedImage img = ImageIO.read(is);
			
//			Initialization of animation array
			animations = new BufferedImage[9][6];
//			moving the animation using array
			for(int j = 0 ;j<animations.length ;j++) {		
				for(int i=0;i<animations[j].length ;i++) {
					animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
//			try and catch for closing an input stream
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
//		for attacking animation
		this.attacking = attacking;
	}
	
//	here we are adding getters -> right click -> source -> generate Getter and setter
	public Boolean getLeft() {
		return left;
	}

	public void setLeft(Boolean left) {
		this.left = left;
	}

	public Boolean getUp() {
		return up;
	}

	public void setUp(Boolean up) {
		this.up = up;
	}

	public Boolean getRight() {
		return right;
	}

	public void setRight(Boolean right) {
		this.right = right;
	}

	public Boolean getDown() {
		return down;
	}

	public void setDown(Boolean down) {
		this.down = down;
	}
	
}



