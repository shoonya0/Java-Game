package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

// implementing State methods
public class Menu extends State implements Statemethods{
//	array of buttons in menu
	private MenuButton[] buttons = new MenuButton[3];
//	for background image of menue
	private BufferedImage backgroundImage ,backgroundImgPink;
	private int menuX ,menuY ,menuWidth ,menuHeight;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENUE_BACKGROUND_IMG);
	}

	private void loadBackground() {
		backgroundImage = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int)(backgroundImage.getWidth() * Game.SCALE);
		menuHeight = (int)(backgroundImage.getHeight() * game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int)(45 * Game.SCALE);
	}

	private void loadButtons() {
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int)(150 * Game.SCALE), 0, GameState.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int)(220 * Game.SCALE), 1, GameState.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int)(290 * Game.SCALE), 2, GameState.QUIT);
	}

	@Override
	public void update() {
		for(MenuButton mb : buttons) 
			mb.update();
	}

	@Override
	public void draw(Graphics g) {
//		drawing the whole background of the menu
		g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		
//		this is to draw menu
		g.drawImage(backgroundImage, menuX, menuY, menuWidth ,menuHeight ,null);
		for(MenuButton mb : buttons) 
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(MenuButton mb : buttons){
			if(isIn(e, mb)) {
				mb.setMousePressed(true);
//				because we can only click one button at a time
				break;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(MenuButton mb : buttons){
			if(isIn(e, mb)) {
//				in case we drag the mouse outside then this does not work and this button is pressed befor
				if(mb.isMousePressed())
					mb.applyGameState();
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		for(MenuButton mb : buttons){
			mb.resetBools();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		this is for ensuring that with each update we are resetting the mouseButton because we might move away from one button 
		for(MenuButton mb : buttons)
			mb.setMouseOver(false);
		for(MenuButton mb : buttons)
			if(isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			GameState.state = GameState.PLAYING;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
