package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gameStates.GameState;
import main.GamePanel;

// here we use the property that : we can extend only one class but we can implements more than one 
// interface
public class MouseInputs implements MouseListener ,MouseMotionListener{

	private GamePanel gamePanel;
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(GameState.state) {
			case MENUE:
				gamePanel.getGame().getMenu().mouseMoved(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseMoved(e);				
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		adding mouse click event for changing the state of the game GAME ,MENU
		switch(GameState.state) {
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseClicked(e);				
				break;
			default:
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(GameState.state) {
			case MENUE:
				gamePanel.getGame().getMenu().mousePressed(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mousePressed(e);				
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(GameState.state) {
			case MENUE:
				gamePanel.getGame().getMenu().mouseReleased(e);
				break;
			case PLAYING:
				gamePanel.getGame().getPlaying().mouseReleased(e);				
				break;
			default:
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

//EXTRA
//public void mouseMoved(MouseEvent e) {
////	use to place rectangle into mouse current when mouse is moved
////	gamePanel.setRectPos(e.getX(), e.getY());	
//}
//public void mouseClicked(MouseEvent e) {
//	// TODO Auto-generated method stub
//	System.out.println("Mouse clicked !!!");
////	to span an new rectangle
////	gamePanel.spawnReact(e.getX(), e.getY());	
//}

