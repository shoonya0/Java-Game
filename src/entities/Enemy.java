package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

import java.awt.ActiveEvent;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;

import main.Game;

// we can make Enemy out of Enemy to not get wage output
public abstract class Enemy extends Entity{

	protected int aniIndex ,enemyState ,enemyType;
	protected int aniTick ,aniSpeed = 25;
	protected boolean firstUpdate = true;
	protected boolean inAir = false;
	protected float fallSpeed = 0;
	protected float gravity = 0.04f * Game.SCALE;
	protected float walkSpeed = 0.85f * Game.SCALE;
	protected int walkDir = LEFT;
//	Position of enemy in y-axis use for enemy to detect the player
	protected int tileY;
//	attack distance of the enemy
	protected float attackDistance = Game.TILES_SIZE;	
	protected int maxHealth;
	protected int currHealth;
	protected boolean active = true;
	protected boolean attackChecked = false;
	
	
	
	public Enemy(float x, float y, int width, int height ,int enemyType) {
		super(x, y, width, height);
		
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
		
		maxHealth = GetMaxHealth(enemyType);
		currHealth = maxHealth;
	}

//	for checking the status of enemy for first time
	protected void firstUpdateCheck(int[][] lvlData) {
		if(!IsEntityOnFloor(hitBox, lvlData)) 
			inAir = true;
		firstUpdate = false;
	}
	
	protected void updateInAir(int[][] lvlData) {
		if(CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.y += fallSpeed;
			fallSpeed += gravity;
		}else {
			inAir = false;
			hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
			tileY = (int)(hitBox.y / Game.TILES_SIZE );
		}
	}
	
	protected void move(int[][] lvlData) {
		float xSpeed = 0;
		
		if(walkDir == LEFT) 
			xSpeed = -walkSpeed;
		else 
			xSpeed = walkSpeed;
		
		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData))
			if(IsFloor(hitBox ,xSpeed ,lvlData)) {
				hitBox.x += xSpeed;
				return ;
			}
		changeWalkDir();
	}
	
//	mover toward the player method
	protected void turnTowardsPlayer(Player player) {
		if(player.hitBox.x > hitBox.x)
			walkDir = RIGHT;
		else 
			walkDir = LEFT;
	}
	
	protected boolean canSeePlayer(int[][] lvlData ,Player player) {
//		take y pos of player and convert it into tile y
		int playerTileY = (int)player.getHitbox().y / Game.TILES_SIZE;
		
//		System.out.println(playerTileY + "  " + tileY);
		
		if(playerTileY == tileY)
			if(isPlayerInRange(player)) {
//				here we have to pass hitBox of enemy and player with tileY -> current tile of enemy 
				if(IsSightClear(lvlData ,hitBox ,player.hitBox ,tileY))
					return true;
			}
		return false;
	}
	
//	for checking the range of the enemy from the player
	protected boolean isPlayerInRange(Player player) {
		int absValue = (int)Math.abs(player.hitBox.x - hitBox.x);
		return absValue <= attackDistance * 5;
	}

//	checking that can a player able to attack or not
	protected boolean isPlayerCloseForAttack(Player player) {
		int absValue = (int)Math.abs(player.hitBox.x - hitBox.x);
		return absValue <= attackDistance ;
	}
	
//	starting the action from the starting index
	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		aniIndex = 0;
		aniTick = 0;
	}
	
	public void hurt(int amount) {
		currHealth -= amount;
		if(currHealth <= 0) 
			newState(DEAD);
		else 
			newState(HIT);
	}
	
//	checking for enemy hit
	protected void checkEnemyHit(Rectangle2D.Float attackBox ,Player player) {
		if(attackBox.intersects(player.hitBox)) 
			player.changeHealth(-GetEnemyDmg(enemyType));
		attackChecked = true;
	}
	
	protected void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpritAmount(enemyType, enemyState)) {
				aniIndex = 0;
//				here we are changing the state to the IDEL as soon as the enemy ATTACK so that we can leave the attack animation
//				Modern switch case
				switch (enemyState) {
					case ATTACK ,HIT -> enemyState = IDEL;
					case DEAD -> active = false;
				}
			}
		}
	}
	
	protected void changeWalkDir() {
		if(walkDir == LEFT)
			walkDir = RIGHT;
		else 
			walkDir = LEFT;
	}
	
	public void resetEnemy() {
		hitBox.x = x;
		hitBox.y = y;
		firstUpdate = true;
		currHealth = maxHealth;
		newState(IDEL);
		active = true;
		fallSpeed = 0;
	}

	public int getAniIndex() {
		return aniIndex;
	}
	public int getEnemyState() {
		return enemyState;
	}
	public boolean isActive() {
		return active;
	}
}
