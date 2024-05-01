// this is the super class for all our gameState
package gameStates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {

	protected Game game;
	public State(Game game) {
		this.game = game;
	}
	
//	checking is the player is pressing inside button called from -> ./gameState/Menu (mousePressed())
	public boolean isIn(MouseEvent e,MenuButton mb) {
		return mb.geBounds().contains(e.getX() ,e.getY());
	}
	
	public Game getGame() {
		return game;
	}
}
