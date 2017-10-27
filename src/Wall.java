import org.newdawn.slick.SlickException;

public class Wall extends Sprite {
	
	/** Wall constructor
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Wall(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
}
