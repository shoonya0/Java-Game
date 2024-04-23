package main;

//here everything is start and take form

public class Game {

//	creating an gameWindow object
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
//	constructor -> special class in java that can be consider like head method of a class
	public Game() {
		
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
//		here we are telling that we need input focus to gamePanel
		gamePanel.requestFocus();
		
	}
	
}
