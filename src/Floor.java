import org.newdawn.slick.SlickException;

public class Floor extends Sprite {
	
	/** Floor constructor
	 *  @param image_src The location of the image file to be used.
	 *  @param x The x location of the sprite.
	 *  @param y The y location of the sprite.
	 *  @param isBlocked Decides whether the sprite is blocked.
	 */
	public Floor(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
}
