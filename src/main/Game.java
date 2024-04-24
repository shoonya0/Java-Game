package main;


//here everything is start and take form

// here Runnable is a method we pass into thread for running the code whenever we want
public class Game implements Runnable{

//	creating an gameWindow object
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	
//	for starting game loop
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
//	constructor -> special class in java that can be consider like head method of a class
	public Game() {
		
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
//		here we are telling that we need to focus inputs to gamePanel
		gamePanel.requestFocus();
		
		
		startGameLoop();
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
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
//		here we define a game loop
		while(true) {
			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				
//				creating an another frame by using repaint
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
//			checking the that how much time have passed and how many frames are created
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS -> " + frames);
				frames = 0;
			}
		}
		
	}


	
}
