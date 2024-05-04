package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import gameStates.Playing;
import main.Game;
import utilz.LoadSave;

public class Player extends Entity{
	

	private BufferedImage[][] animations;
	private int aniTick, aniIndex ,aniSpeed = 15;
	private int playerAction = IDlE;
	private Boolean moving  = false ,attacking = false;
	private Boolean left = false ,up = false ,right = false ,down = false ,jump = false;
	private float playerSpeed = 1.0f * Game.SCALE;
//	contain the level data for checking if the player is on the right tile or not (for hitBox)
	private int[][] lvlData;
//	xOffset of player -> from where player start from similarly for yOffset
	private float xDrawOffset = 21*Game.SCALE;
	private float yDrawOffset = 4*Game.SCALE;
	
//	Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.019f*Game.SCALE;
	private float jumpSpeed = -2.25f*Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f*Game.SCALE;
	private boolean inAir = false;
	
//	StatusBarUI
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int)(192 * Game.SCALE);
	private int statusBarHeight = (int)(58 * Game.SCALE);
	private int statusBarX = (int)(10 * Game.SCALE);
	private int statusBarY = (int)(10 * Game.SCALE);
	
	private int healthBarWidth = (int)(150 * Game.SCALE);
	private int healthBarHeight = (int)(4 * Game.SCALE);
	private int healthBarXStart = (int)(34 * Game.SCALE);
	private int healthBarYStart = (int)(14 * Game.SCALE);
	
	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	
//	AttackBox -> an area is happen if enemy is inside this attackBox area
	private Rectangle2D.Float attackBox;
//	this is used in changing the direction of the player
	private int flipX = 0;
	private int flipW = 1;
	
	private boolean attackChecked = false;
	private Playing playing;
	
	
//	here we are passing the x,y to entity called from -> {Game -> initClasses()}
	public Player(float x, float y ,int width ,int height ,Playing playing) {
//		calling the super class which is Entity
		super(x, y ,width ,height);
		
		this.playing = playing;
		
//		for loading the animation in the animation array
		loadAnimations();
		
//		here the x and y are of hitBox x and y meaning changing the area of player hitRange
//		we have done 27 in place of 28 in height because to reduce one pixel so that our hitBox (hitBox become little larger which cause problem in player movement in different SCALE)
		initHitbox(x, y, (int)(20 * Game.SCALE), (int)(27 * Game.SCALE));
		
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float( x, y, (int)(20 * Game.SCALE) ,(int)(20 * Game.SCALE));
	}

//	called from -> ./gameState/Playing
	public void update() {
		updateHealthBar();
		
		if(currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
		
		
//		method for updating the attackBox with entity movements  
		updateAttackBox();
		
//		updating the position of the player according to player actions
		updatePos();
		if(attacking)
			checkAttack();
//		updating the animation
		updateAnimationTick();
		
//		setting the animation according to player action if moving ,ideal ,attack ...  
		setAnimation();
		
	}

	private void checkAttack() {
		if(attackChecked || aniIndex != 1) 
			return ;
		attackChecked = true;
//		here we are passing the attack box the player
		playing.checkEnemyHit(attackBox);
	}

	private void updateAttackBox() {
		if(right) {
//			by this wherever the hitBox of player go the attackBox follow
			attackBox.x = hitBox.x + hitBox.width + (int)(Game.SCALE * 10);
		}else if(left){
			attackBox.x = hitBox.x - hitBox.width - (int)(Game.SCALE * 10);			
		}
		attackBox.y = hitBox.y + (Game.SCALE * 10);
	}

	private void updateHealthBar() {
		healthWidth = (int)((currentHealth / (float)maxHealth) * healthBarWidth);
	
	}

//	for rendering player called from -> ./gameState/Playing 
	public void render(Graphics g ,int lvlOffset) {
//		here we are drawing sub image of an image
//		here the 4th variable getFocusCycleRootAncestor() is use for monitoring the status of the image before it's fully drawn
//		we are drawing the player according to hitBox x and y value
//		this is drawImgage is used to change where player is seeing by adding the width of player and draw the player from right side when player is seeing left and right is default
		g.drawImage(animations[playerAction][aniIndex], 
				(int)(hitBox.x - xDrawOffset) - lvlOffset + flipX, 
				(int)(hitBox.y - yDrawOffset), 
				width * flipW, height, null);
		
//		for drawing the hitBox
//		drawHitbox(g ,lvlOffset);
//		drawAttackBox(g,lvlOffset);
		
		drawUI(g);
	}
	
	private void drawAttackBox(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		g.drawRect((int)attackBox.x - lvlOffsetX, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
		
	}

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
//		here statusBarX is the offset value of x-axis from start same for y-axis also
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
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
				attackChecked = false;
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
		
		if(inAir) {
			if(airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}
		
//		for attacking
		if(attacking) {
			playerAction = ATTACK;
//			meaning we were not attacking before this it's because we want instant response from player
			if(startAni != ATTACK) {
				aniIndex = 1;
				aniTick = 0;
				return ;
			}
		}
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
		
//		for jumping
		if(jump)
			jump();
		
//		if(!left && !right && !inAir)
//			return;
		
		if(!inAir)
			if((!left && !right) || (left && right))
				return ;
		
		
		float xSpeed = 0;
		
		if(left) {
			xSpeed -= playerSpeed;
			flipX = width;
			flipW = -1;
		}if(right) {
			xSpeed += playerSpeed;
			flipX = 0;
			flipW = 1;
		}
//		checking in every move that we are on floor or not
		if(!inAir)
			if(!IsEntityOnFloor(hitBox ,lvlData))
				inAir = true;
		
		if(inAir) {
//			checking for collision in x-axis as well as y-axis
//			first we check up and down then we go sideways
			if(CanMoveHere(hitBox.x ,hitBox.y + airSpeed, hitBox.width, hitBox.height ,lvlData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}else{
//				if we are hitting roof or floor
				hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox ,airSpeed);
//				checking that we are going down and we hit an entity
				if(airSpeed > 0)
//					for hitting the floor
					resetInAir();
				else
//					for hitting the roof
//					this means we hit something and we are going down and for giving a little speed after collision we do
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}
				
		}else 
			updateXPos(xSpeed);
		moving = true;
	}
	
	private void jump() {
		if(inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

//	checking if we can move in x-axis left or right
	private void updateXPos(float xSpeed) {
//		checking if we can move here or not
		if(CanMoveHere(hitBox.x+xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
		}else {
//			method for hitBox is hitting a wall before this we are colliding with the wall now we want to move next to that
			hitBox.x = GetEntityXPosNextToWall(hitBox ,xSpeed);
		}
	}
	
//	method for changing the current health
	public void changeHealth(int value) {
		currentHealth += value;
		
		if(currentHealth <= 0) {
			currentHealth = 0;
//			gameOver();
		}else if(currentHealth >= maxHealth)
			currentHealth = maxHealth;
	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ALTAS);
		
//			Initialization of animation array
//		animations = new BufferedImage[9][6];
		animations = new BufferedImage[7][8];
//			moving the animation using array
		for(int j = 0 ;j<animations.length ;j++)		
			for(int i=0;i<animations[j].length ;i++)
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			
		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
	}
	
//	for loading the lvl Data called form -> {Game}
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitBox, lvlData))
			inAir = true;
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
	
	public void setJump(Boolean jump) {
		this.jump = jump;
	}
	
//	for reseting all the in case of player dies
	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDlE;
		currentHealth = maxHealth;
		
		hitBox.x = x;
		hitBox.y = y;
		

		if(!IsEntityOnFloor(hitBox ,lvlData))
			inAir = true;
	}
	
}