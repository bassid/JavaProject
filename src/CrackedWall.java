import org.newdawn.slick.SlickException;

public class CrackedWall extends Sprite {
	
	/** CrackedWall constructor
	 *  @param image_src The location of the image file to be used.
	 *  @param x The x location of the sprite.
	 *  @param y The y location of the sprite.
	 *  @param isBlocked Decides whether the sprite is blocked.
	 */
	public CrackedWall(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
}
