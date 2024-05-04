package utilz;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Crabby;
import main.Game;
import static utilz.Constants.EnemyConstants.*;


public class LoadSave {
	
	public static final String PLAYER_ALTAS = "player_sprites.png";
	public static final String LEVEL_ALTAS = "outside_sprites.png";
//	public static final String LEVEL_ONE_DATA = "level_one_data.png";
//	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long__.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTON = "sound_button.png";
//	unpause replay and home button
	public static final String URM_BUTTON = "urm_buttons.png";
	public static final String VOLUME_BUTTON = "volume_buttons.png";
	public static final String MENUE_BACKGROUND_IMG = "background_menu.png";
//	public static final String PLAYING_BACKGROUND_IMG = "playing_bg_img.png";
	public static final String PLAYING_BACKGROUND_IMG = "playing_bg_img_3.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String CRABBY_SPRITE = "crabby_sprite.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	
//	here we will not have constructor because we use static method so we do have to create an obj of this class to access any method
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
//		for getting the image
//		because we use static method so we call LoadSave.class so we have to get the reference of the current object to use the getResourceAsStream() method
		InputStream is = LoadSave.class.getResourceAsStream("/"+fileName);
//		error handling
		try {
			img = ImageIO.read(is);
		}catch (IOException e) {
//		telling the user about the exception which has occured and for plan B
			e.printStackTrace();
		}finally {
//			try and catch for closing an input stream
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		throw is use for modified exception like if you want to throw an exception when the input is greater than 10
		return img;
	}
	
	public static ArrayList<Crabby> GetCrabs(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Crabby> list = new ArrayList<>();
		
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth() ;i++) {
//			i ,j -> because of x ,y	{width ,height}
//				to get the image of the particular pixel
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
//				in case of we get the value more then 48 
				if(value == CRABBY)
					list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		}
		return list;
	}
	
//	return an 2d array and the size of this array will match the size of our game window in terms of tile per width and height
	public static int[][] GetLevelDate(){
//		here we are taking the 48 pixel image
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		
		int [][] lvlData = new int[img.getHeight()][img.getWidth()];
		
//		image.height() is use to get the pixel height because we have image in pixel(very small)
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth() ;i++) {
//			i ,j -> because of x ,y	{width ,height}
//				to get the image of the particular pixel
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
//				in case of we get the value more then 48 
				if(value >= 48)
					value = 0;
//				here I am getting the red value of img pixel (r,g,b) only red variable
				lvlData[j][i] = value;
			}
		}
		return lvlData;	
	}
}
