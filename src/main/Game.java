package main;

public class Game {

//	creating an gameWindow object
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
//	constructor -> special class in java that can be consider like head method of a class
	public Game() {
		
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		
	}
	
}
