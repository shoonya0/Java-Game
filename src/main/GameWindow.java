package main;

//this class is for keeping my game window

import javax.swing.JFrame;

public class GameWindow{
	private JFrame jF;
	
	public GameWindow(GamePanel gamePanel) {
		
		jF = new JFrame();
		
		
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.add(gamePanel);
//		jF.setLocationRelativeTo(null);
		jF.setLocation(100 ,20);
//		this is use to stop JFrame to resize 
		jF.setResizable(false);
//		this fit the size of window to the preferred size of it's components
		jF.pack();
		jF.setVisible(true);
	}
}
