package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
	

	private BufferedImage[][] animations;
	private int aniTick, aniIndex ,aniSpeed = 15;
	private int playerAction = IDlE;
	private Boolean moving  = false ,attacking = false;
	private Boolean left = false ,up = false ,right = false ,down = false;
	private float playerSpeed = 2.0f;
//	contain the level data for checking if the player is on the right tile or not (for hitBox)
	private int[][] lvlData;
//	xOffset of player -> from where player start from similarly for yOffset
	private float xDrawOffset = 21*Game.SCALE;
	private float yDrawOffset = 4*Game.SCALE;
	
//	here we are passing the x,y to entity
	public Player(float x, float y ,int width ,int height) {
		super(x, y ,width ,height);
//		for loading the animation in the animation array
		loadAnimations();
		
//		here the x and y are of hitBox x and y
		initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
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
//		we are drawing the player according to hitBox x and y value
		g.drawImage(animations[playerAction][aniIndex], (int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), width, height, null);
		
//		for drawing the hitBox
		drawHitbox(g);
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
		
		if(!left && !right && !up && !down)
			return;
		
		float xSpeed = 0, ySpeed = 0;
		
		if(left && !right) 
			xSpeed = -playerSpeed;
		else if(right && !left)
			xSpeed = playerSpeed;

		if(up && !down) 
			ySpeed = -playerSpeed;
		else if(!up && down) 
			ySpeed = playerSpeed;
		
//		checking if we can move here or not
		if(CanMoveHere(hitBox.x+xSpeed, hitBox.y+ySpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
			moving = true;
		}
	}
	
	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ALTAS);
		
//			Initialization of animation array
		animations = new BufferedImage[9][6];
//			moving the animation using array
		for(int j = 0 ;j<animations.length ;j++) {		
			for(int i=0;i<animations[j].length ;i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
		}	
	}
	
//	for loading the lvl Data called form -> {Game}
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
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



