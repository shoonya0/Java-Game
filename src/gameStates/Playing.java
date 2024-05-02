package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;
import static utilz.Constants.Environment.*;

// the super class is State
public class Playing extends State implements Statemethods{

	private Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
//	this is the offset we add to or remove to draw left or right side of the game
	private int xLevelOffset;
//	this means 20 % of game width and the sum is the full width
	private int leftBorder = (int)(0.20 * Game.GAME_WIDTH);
	private int rightBorder = (int)(0.80 * Game.GAME_WIDTH);
//	total tiles of level
	private int lvlTilesWide = LoadSave.GetLevelDate()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

	private BufferedImage backgroundImg ,bigCloud ,smallCloud;
//	this array contain different y values for our small cloud
	private int[] smallCloudsPos;
	private Random rnd = new Random();
	
//	constructor
	public Playing(Game game) {
		super(game);
		
//		Initializing level and player
		initClasses();
		
//		Initializing background imgae in level
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMG);
		bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
		smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
		smallCloudsPos = new int[8];
		for(int i = 0 ; i <smallCloudsPos.length ;i++)
			smallCloudsPos[i] = (int)(70 * Game.SCALE) + rnd.nextInt((int)(150 * Game.SCALE));
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
		player = new Player(200, 200 ,(int)(64*Game.SCALE) ,(int)(40*Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if(!paused) {
			levelManager.update();
			player.update();	
			enemyManager.update(levelManager.getCurrentLevel().getLevelData() ,player);
			checkCloseToBorder();
		}else {
			pauseOverlay.update();
		}
		
	}

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
//		if the diff is more than then the right border then we know the player is beyond right border then we know that we have to move the border to the right simararly for the left
		int diff = playerX - xLevelOffset;
		
		if(diff > rightBorder)
			xLevelOffset += diff - rightBorder;
		else if(diff < leftBorder)
			xLevelOffset += diff - leftBorder;
		
//		this is to check that our lvlOffset does not get too high or too low
		if(xLevelOffset > maxLvlOffsetX)
			xLevelOffset = maxLvlOffsetX;
		else if(xLevelOffset < 0)
			xLevelOffset = 0;
	}

	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseDragged(e);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		
		drawClouds(g);
		
		levelManager.draw(g ,xLevelOffset);
		player.render(g ,xLevelOffset);
		enemyManager.draw(g ,xLevelOffset);
		if(paused) {
			g.setColor(new Color(0 ,0 ,0 ,200));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
	}

	private void drawClouds(Graphics g) {
//		for an illusion of cloud moving here i am adding xlvlOffset
		for(int i = 0 ;  i < 4 ;i++)
			g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int)(xLevelOffset * 0.3), (int)(224 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
		
		for(int i = 0 ; i < smallCloudsPos.length ; i++)
			g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int)(xLevelOffset * 0.7), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) 
			player.setAttacking(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pauseOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_J:
				player.setAttacking(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(true);
				break;
			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;			
			case KeyEvent.VK_SPACE:
				player.setJump(false);
				break;
			case KeyEvent.VK_BACK_SPACE:
				GameState.state = GameState.MENUE;
		}
	}
	
	public void unpauseGame() {
		paused = false;
	}
	
	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	
//	getting player
	public Player getPlayer() {
		return player;
	}
}
