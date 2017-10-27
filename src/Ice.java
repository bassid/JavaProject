import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Ice extends Sprite implements Movable {
	
	/** Decides if ice can move left */
	private boolean leftMove = false;
	/** Decides if ice can move right */
	private boolean rightMove = false;
	/** Decides if ice can move up */
	private boolean upMove = false;
	/** Decides if ice can move down */
	private boolean downMove = false;
	/** Timer used for speed purposes */
	private int time = 0;
	
	/** Ice constructor
	 *  @param image_src The location of the image file to be used.
	 *  @param x The x location of the sprite.
	 *  @param y The y location of the sprite.
	 *  @param isBlocked Decides whether the sprite is blocked.
	 */
	public Ice(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
	
	/** Ice constructor which takes in another Ice object 
	 *  and assigns it's position values.  
	 *  @param s The Sprite object to get position values from.
	 */
	public Ice(Ice s) {
		super(s);
		this.setX(s.getX()); 	
		this.setY(s.getY());
	}

	/** Update the game state for a frame.
     *  @param gc The Slick game container object.
     *  @param delta Time passed since last frame (milliseconds).
     */
	@Override
	public void update(Input input, int delta) throws SlickException {
		
		time += delta;
		if (isLeftMove() && canMoveLeft()) {
			this.setX(this.getX()-(Math.round(delta/12)*2));
		}
		if (isRightMove() && canMoveRight()) {
			this.setX(this.getX()+(Math.round(delta/12)*2));
		}
		if (isUpMove() && canMoveUp()) {
			this.setY(this.getY()-(Math.round(delta/12)*2));
		}
		if (isDownMove() && canMoveDown()) {
			this.setY(this.getY()+(Math.round(delta/12)*2));
		}
		
	}
	
	/** Checks to see if the ice can move left
	 */
	public boolean canMoveLeft() {
    	for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getX() == sprite.getX()+App.TILE_SIZE) &&
    				(this.getY() == sprite.getY()) ){
    			//System.out.println("Stopped");
    			setLeftMove(false);
    			return false;
    		}
    	}
    	setLeftMove(true);
    	return true;
    }
	
	/** Checks to see if the ice can move right
	 */
	public boolean canMoveRight() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getX()+App.TILE_SIZE == sprite.getX()) &&
    				(this.getY() == sprite.getY()) ){
    			setRightMove(false);
    			return false;
    		}
    	}
		setRightMove(true);
    	return true;
	}
	
	/** Checks to see if the ice can move up
	 */
	public boolean canMoveUp() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getY() == sprite.getY()+App.TILE_SIZE) &&
    				(this.getX() == sprite.getX()) ){
    			setUpMove(false);
    			return false;
    		}
    	}
		setUpMove(true);
    	return true;
	}
	
	/** Checks to see if the ice can move down
	 */
	public boolean canMoveDown() {
		for (Sprite sprite : Loader.blockedSprites) {
    		if( (this.getY()+App.TILE_SIZE == sprite.getY()) &&
    				(this.getX() == sprite.getX()) ){
    			setDownMove(false);
    			return false;
    		}
    	}
		setDownMove(true);
    	return true;
	}

	
	
	/** Getters and setters for global variables
     */
	public boolean isLeftMove() {
		return leftMove;
	}

	public void setLeftMove(boolean leftMove) {
		this.leftMove = leftMove;
	}

	public boolean isRightMove() {
		return rightMove;
	}

	public void setRightMove(boolean rightMove) {
		this.rightMove = rightMove;
	}

	public boolean isUpMove() {
		return upMove;
	}

	public void setUpMove(boolean upMove) {
		this.upMove = upMove;
	}

	public boolean isDownMove() {
		return downMove;
	}

	public void setDownMove(boolean downMove) {
		this.downMove = downMove;
	}

	
}
