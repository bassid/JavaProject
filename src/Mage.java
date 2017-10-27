import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Mage extends Sprite implements Movable{

	/** Values to check the distance between the mage and player */
	private static float distX, distY;

	/** Constructor for the mage
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Mage(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}

	/** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) throws SlickException {
		distX = World.getPlayer().getX() - this.getX();
		distY = this.getY() - World.getPlayer().getY();
	}

	/** Moves the mage according to Algorithm 1 provided in the
	 * program specification document
	 * @throws Exception
	 */
	public void move() throws Exception {

		float x = Math.abs(distX);
		float y = Math.abs(distY);

		if (x > y) {
			if(distX < 0 && canMoveLeft()) {
				setX(getX() - App.TILE_SIZE);
			}
			else if (distX >= 0 && canMoveRight()) {
				setX(getX() + App.TILE_SIZE);
			}
		}	
		else {
			if(distY < 0 && canMoveDown()) {
				setY(getY() + App.TILE_SIZE);
			}
			else if (distY >= 0 && canMoveUp()) {
				setY(getY() - App.TILE_SIZE);
			}
		}
	}

	/** Checks to see if the mage can move left
	 */
	public boolean canMoveLeft() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getX() == sprite.getX()+App.TILE_SIZE) &&
					(this.getY() == sprite.getY()) ){
				
				if(sprite.getName().contains("player")) {
					World.restart();
				}
				
				return false;
			}
		}
		return true;
	}

	/** Checks to see if the mage can move right
	 */
	public boolean canMoveRight() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getX()+App.TILE_SIZE == sprite.getX()) &&
					(this.getY() == sprite.getY()) ){
				
				if(sprite.getName().contains("player")) {
					World.restart();
				}
				
				return false;
			}
		}
		return true;
	}

	/** Checks to see if the mage can move up
	 */
	public boolean canMoveUp() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY() == sprite.getY()+App.TILE_SIZE) &&
					(this.getX() == sprite.getX()) ){
				
				if(sprite.getName().contains("player")) {
					World.restart();
				}
				
				return false;	
			}
		}
		return true;
	}

	/** Checks to see if the mage can move down
	 */
	public boolean canMoveDown() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY()+App.TILE_SIZE == sprite.getY()) &&
					(this.getX() == sprite.getX()) ){
				
				if(sprite.getName().contains("player")) {
					World.restart();
				}
				
				return false;	
			}
		}
		return true;
	}
}
