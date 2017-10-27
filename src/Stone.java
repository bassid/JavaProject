import org.newdawn.slick.SlickException;

public class Stone extends Sprite implements Movable {
	
	/** Stone constructor
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Stone(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
	
	/** Stone constructor which takes in another Stone object 
	 *  and assigns it's position values. 
	 * @param s
	 */
	public Stone(Stone s) {
		super(s);
		this.setX(s.getX()); 	
		this.setY(s.getY());
	}

	/** Checks if stone can move left
	 */
	public boolean canMoveLeft() {
    	for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getX() == sprite.getX()+App.TILE_SIZE) &&
    				(this.getY() == sprite.getY()) ){
    			return false;
    		}
    	}
    	return true;
    }
	
	/** Checks if stone can move right
	 */
	public boolean canMoveRight() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getX()+App.TILE_SIZE == sprite.getX()) &&
    				(this.getY() == sprite.getY()) ){
    			return false;
    		}
    	}
    	return true;
	}
	
	/** Checks if stone can move up
	 */
	public boolean canMoveUp() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getY() == sprite.getY()+App.TILE_SIZE) &&
    				(this.getX() == sprite.getX()) ){
    			return false;
    		}
    	}
    	return true;
	}
	
	/** Checks if stone can move down
	 */
	public boolean canMoveDown() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getY()+App.TILE_SIZE == sprite.getY()) &&
    				(this.getX() == sprite.getX()) ){
    			return false;
    		}
    	}
    	return true;
	}

	
}
