package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;
import gameStates.GameState;
import main.GamePanel;
//Importing directions 
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener{
// we can only extend one class but implements more than one 

//	to change the values we first have to give access to this curr class
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

//	called from -> {Player}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(GameState.state) {
			case MENUE:
				gamePanel.getGame().getMenu().keyReleased(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().keyReleased(e);
				break;
			default:
				break;
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(GameState.state) {
		case MENUE:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		default:
			break;
		}		
	}
	
}
