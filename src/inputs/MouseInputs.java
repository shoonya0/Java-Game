package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
//		use to move the player
		gamePanel.setRectPos(e.getX(), e.getY());		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

