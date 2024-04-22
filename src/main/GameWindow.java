package main;

import javax.swing.JFrame;

public class GameWindow{
	private JFrame jF;
	
	public GameWindow(GamePanel gamePanel) {
		
		jF = new JFrame();
		
		
		jF.setSize(400,400);
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.add(gamePanel);
		jF.setVisible(true);
	}
}
