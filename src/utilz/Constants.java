package utilz;

public class Constants {
	
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
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;

//		method for returning number of player animation in doing certain task {inside whole image}
		public static int GetSpriteAmount(int player_anction) {
			
			switch(player_anction) {
				case RUNNING:
					return 6;
				case IDlE:
					return 5;
				case HIT:
					return 4;
//				here we have 4 different animations which return the same number of animation
				case JUMP:
				case ATTACK_1:
				case ATTACK_JUMP_1:
				case ATTACK_JUMP_2:
					return 3;
				case GROUND:
					return 2;
				case FALLING:
				default:
					return 1;
			}
		}
		
		
				
	}
	
}
