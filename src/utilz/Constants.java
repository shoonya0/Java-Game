package utilz;

import main.Game;

public class Constants {

	public static class EnemyConstants {
		public static final int CRABBY = 0;
		public static final int IDEL = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;
		
		public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int)(CRABBY_HEIGHT_DEFAULT * Game.SCALE);
		
		
		public static final int CRABBY_DRAWOFFSET_X = (int)(26 * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_Y = (int)(9 * Game.SCALE);
		
		public static int GetSpritAmount(int enemy_type ,int enemy_state) {
			switch (enemy_type) {
				case CRABBY:
					switch (enemy_state) {
						case IDEL:
							return 9;
						case RUNNING:
							return 6;
						case ATTACK:
							return 7;
						case HIT:
							return 4;
						case DEAD:
							return 5;
						
					}
			}
			return 0;
		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
				case CRABBY:
					return 50;
				default :
					return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
				case CRABBY:
					return 15;
				default :
					return 0;
			}
		}
		
	}
	
	
	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int BIG_CLOUD_WIDTH = (int)(BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int)(BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int)(SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int)(SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}
	
//	class for UI like button in -> ./ui/MenuButton
	public static class UI{
		public static class Buttons{
//			how big is the button in button atlas
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
		}
		
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
		}
		
		public static class URMButtons{
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE * Game.SCALE);
			
		}
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);	
		}
	}
	
//	inner class
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
//	inner class
	public static class PlayerConstants{
		public static final int IDlE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
//		public static final int GROUND = 4;
		public static final int ATTACK = 4;
		public static final int HIT = 5;
		public static final int DEAD = 6;
//		public static final int ATTACK_JUMP_1 = 7;
//		public static final int ATTACK_JUMP_2 = 8;

//		method for returning number of player animation in doing certain task {inside whole image}
		public static int GetSpriteAmount(int player_anction) {
			
			switch(player_anction) {
				case DEAD:
					return 8;
				case RUNNING:
					return 6;
				case IDlE:
					return 5;
				case HIT:
					return 4;
//				here we have 4 different animations which return the same number of animation
				case JUMP:
				case ATTACK:
					return 3;
				case FALLING:
				default:
					return 1;
			}
		}		
	}
}
