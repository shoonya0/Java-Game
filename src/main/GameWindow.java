package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

//this class is for keeping my game window

import javax.swing.JFrame;

public class GameWindow{
	private JFrame jF;
	
	public GameWindow(GamePanel gamePanel) {
		
		jF = new JFrame();
		
		
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.add(gamePanel);
		jF.setLocation(100 ,20);
//		this is use to stop JFrame to resize 
		jF.setResizable(false);
//		this fit the size of window to the preferred size of it's components
		jF.pack();
		jF.setLocationRelativeTo(null);
		jF.setVisible(true);
		
//		in case player loose focus from window than all moment of the player must stop so for the we use 
		jF.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("not focused");
				gamePanel.getGame().windowFocusLost();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
