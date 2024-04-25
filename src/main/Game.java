package main;

import java.awt.Graphics;

import entities.Player;

//here everything is start and take form

// here Runnable is a method we pass into thread for running the code whenever we want
public class Game implements Runnable{

//	creating an gameWindow object
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
//	updates per seconds
	private final int UPS_SET = 200;
	
	private Player player;
	
	
//	constructor -> special class in java that can be consider like head method of a class
	public Game() {
//		for initializing player ,enemies and many more start before game loop to not to stack
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
//		here we are telling that we need to focus inputs to gamePanel
		gamePanel.requestFocus();
		

		startGameLoop();
		
	}

	
	private void initClasses() {
		player = new Player(200, 200);	
	}


//	for starting game loop
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
//	we use this method to control the player moment or event for readability and we need our logic to run as smooth as it can however we can sacrifice some FPS in case of lag  
	private void update() {
		player.update();
	}
	
	public void render(Graphics g) {
		player.render(g);
	}
	
//		the code inside the run method is run on different thread
	@Override
//	we can change 4 things in overriding
//	1> increase the accessibility level {private -> protected}
//	2> body
//	3> covarient return type
//	4> reduce or remove exception
	public void run() {
//		use to know that how long each frame last
		double timePerFrame = 1000000000.0 / FPS_SET;
//		time between two update time of frequency
		double timePerUpdate = 1000000000.0 / UPS_SET;		
		long previousTime = System.nanoTime();
		
		int frames = 0;
//		update per seconds
		int updates = 0;
		
		long lastCheck = System.currentTimeMillis();
		
//		variable for counting the some of the lost time
		double deltaU = 0;
		double deltaF = 0;
		
//		here we define a game loop
		while(true) {
			long currentTime = System.nanoTime();
			
//		deltaU >= 1 if -> when duration of last update is = or greater than timePerUpdate
			deltaU += (currentTime - previousTime)/timePerUpdate;
			deltaF += (currentTime - previousTime)/timePerFrame;
			previousTime = currentTime;
			
//      here if deltaU > 1 then {-- operation only remove more than one part} meaning decimal point remain which if stack up to make 1 more loss frame
//		this help us give an smooth gameplay no matter the if system is powerful or not to some extent 
			if( deltaU >= 1) {
				update();
				updates++;
				deltaU--;
		 	}
			
			if(deltaF >= 1) {
//				creating an another frame by using repaint
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
//			checking the that how much time have passed and how many frames are created
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS -> " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}	
	}
	
	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	
//	getting player
	public Player getPlayer() {
		return player;
	}
	
}
