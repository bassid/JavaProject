import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Target extends Sprite {
	
	/** Decides if the target is covered */
	private boolean covered = false;
	
	/** Target constructor
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Target(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}

	/** Update the game state for a frame.
     *  @param gc The Slick game container object.
     *  @param delta Time passed since last frame (milliseconds).
     */
	@Override
	public void update(Input input, int delta) throws SlickException {
		
		for (Sprite sprite : Loader.blockedSprites) {

			if ( (sprite.getName().contains("stone")) ||
					(sprite.getName().contains("ice")) ) {
					
				
				if ( (this.getX() == sprite.getX()) &&
						(this.getY() == sprite.getY())) {
					setCovered(true);
					break;
				}	
				else {
					setCovered(false);
				}
			}
		}
	}

	/** Getters and setters for the global variables
	 */
	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean covered) {
		this.covered = covered;
	}
}
