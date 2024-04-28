package utilz;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ALTAS = "player_sprites.png";
	public static final String LEVEL_ALTAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";
	
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
	
//	return an 2d array and the size of this array will match the size of our game window in terms of tile per width and height
	public static int[][] GetLevelDate(){
		int [][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//		here we are taking the 48 pixel image
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		
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
