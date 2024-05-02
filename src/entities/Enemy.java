package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;

import main.Game;

// we can make Enemy out of Enemy to not get wage output
public abstract class Enemy extends Entity{

	private int aniIndex ,enemyState ,enemyType;
	private int aniTick ,aniSpeed = 25;
	private boolean firstUpdate = true;
	private boolean inAir = false;
	private float fallSpeed = 0;
	private float gravity = 0.04f * Game.SCALE;
	private float walkSpeed = 0.35f * Game.SCALE;
	private int walkDir = LEFT;
	
	public Enemy(float x, float y, int width, int height ,int enemyType) {
		super(x, y, width, height);
		
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpritAmount(enemyType, enemyState)) {
				aniIndex = 0;
			}
		}
	}
	
	public void update(int[][] lvlData) {
		updateMove(lvlData);
		updateAnimationTick();
	}
	
	private void updateMove(int[][] lvlData) {
		if(firstUpdate) {
			if(!IsEntityOnFloor(hitBox, lvlData)) 
				inAir = true;
			firstUpdate = false;
		}			
		if(inAir) {
			if(CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
				hitBox.y += fallSpeed;
				fallSpeed += gravity;
			}else {
				inAir = false;
				hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
			}
		}else {
			switch (enemyState) {
				case IDEL:
					enemyState = RUNNING;
					break;
				case RUNNING:
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
					break;
			}
		}	
	}
	
	private void changeWalkDir() {
		if(walkDir == LEFT)
			walkDir = RIGHT;
		else 
			walkDir = LEFT;
	}

	public int getAniIndex() {
		return aniIndex;
	}
	public int getEnemyState() {
		return enemyState;
	}
}
