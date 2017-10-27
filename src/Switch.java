import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Switch extends Sprite {

	/** Decides if door is open */
	private static boolean doorOpen = false;
	/** The x location of the door */
	private static float doorX;
	/** Checks if the level has begun */
	private boolean start = true;

	/** Switch constructor
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Switch(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}

	/** Update the game state for a frame.
     *  @param gc The Slick game container object.
     *  @param delta Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) throws SlickException {

		// Gets door's x location at the beginning of the level
		if(start) {
			doorX = getDoorX();
			start = false;
		}
			
		//Check if switch covered
		for (Sprite sprite : Loader.blockedSprites) {

			if ( (sprite.getName().contains("stone")) ||
					(sprite.getName().contains("ice")) ) {
									
				if ( (this.getX() == sprite.getX()) &&
						(this.getY() == sprite.getY())) {
					doorOpen = true;
					break;
				}	
				else {
					doorOpen = false;
				}
			}
		}




		// Show or hide door
		if (doorOpen) {
			for (Sprite sprite : Loader.blockedSprites) {
				if (sprite.getName().contains("door")) {
					sprite.setX(doorX + App.SCREEN_WIDTH);
				}
			}
		}
		else {
			for (Sprite sprite : Loader.blockedSprites) {
				if (sprite.getName().contains("door")) {
					sprite.setX(doorX);
				}
			}
		}
	}
	
	/** A getter for the 'doorX' global variable
	 * @return
	 */
	private float getDoorX() {
		
		for (Sprite sprite : Loader.blockedSprites) {
			if (sprite.getName().contains("door")) {
				return sprite.getX();
			}
		}
		
		return 0;
	}
	
}