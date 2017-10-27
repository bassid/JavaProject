
// Import required libraries
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.Graphics;

public class Sprite {	
	
	/** X location of a sprite */
	private float x_location;
	/** Y location of a sprite */
	private float y_location;
	/** Image of a sprite */
	private Image sprite;
	/** Name of a sprite */
	private String name;
	/** Decides if sprite is blocked */
	private boolean blocked = true;
	
	/** Sprite constructor which takes
	 *  in another Sprite object and assigns it's position values.  
	 *  @param s The Sprite object to get position values from.
	 */
	public Sprite(Sprite s) {
		x_location = s.getX();		
		y_location = s.getY();		
	}
	
	/** Sprite constructor
	 *  @param image_src The location of the image file to be used.
	 *  @param x The x location of the sprite.
	 *  @param y The y location of the sprite.
	 *  @param isBlocked Decides whether the sprite is blocked.
	 */
	public Sprite(String image_src, float x, float y, boolean isBlocked) throws SlickException {
		setSprite(new Image(image_src));
		setName(image_src);
		x_location = x;
		y_location = y;
		blocked = isBlocked;
	}
	
	/** Update the game state for a frame.
     *  @param gc The Slick game container object.
     *  @param delta Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) throws SlickException {
		
	}
	
	/** Render the entire screen, so it reflects the current game state.
     *  @param gc The Slick game container object.
     *  @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) throws SlickException {
		
		getSprite().draw(this.getX(), this.getY());
		
	}
	
	
	
	/** Getters and setters for global variables
     */
	public float getX() {
		return this.x_location;
	}
	
	public float getY() {
		return this.y_location;
	}
	
	public void setX(float x) {
		this.x_location = x;
	}
	
	public void setY(float y) {
		this.y_location = y;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
