import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tnt extends Sprite implements Movable {
	
	private int time = 0;
	private boolean explode = false;
	
	public Tnt(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
	
	public Tnt(Tnt s) {
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
		
		if (isExplode()) {
			time += delta;
			if((time/400) > 1) {
				this.setX(this.getX() + App.SCREEN_WIDTH);
				setExplode(false);
				System.out.println("Done");
			}
		}
		
		for (Sprite sprite : Loader.blockedSprites) {
			
			if (sprite.getName().contains("cracked")) {
				if( 	(this.getX() == sprite.getX()+App.TILE_SIZE) &&
	    				(this.getY() == sprite.getY()) ||
	    				
	    				(this.getX()+App.TILE_SIZE == sprite.getX()) &&
	    				(this.getY() == sprite.getY()) ||
	    				
	    				(this.getX() == sprite.getX()) &&
	    				(this.getY() == sprite.getY()+App.TILE_SIZE) ||
	    				
	    				(this.getX() == sprite.getX()) &&
	    				(this.getY()+App.TILE_SIZE == sprite.getY()) ){
	    			
					this.setSprite(new Image("res/explosion.png"));
					this.setX(this.getX() - App.TILE_SIZE);
					
					sprite.setX(sprite.getX() + App.SCREEN_WIDTH);
					setExplode(true);
	    		}
			}
    	}
	}
	
	/** Checks to see if the tnt can move left
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
	
	/** Checks to see if the tnt can move right
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
	
	/** Checks to see if the tnt can move up
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
	
	/** Checks to see if the tnt can move down
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

	/** Getters and setters for the global variables
	 */
	public boolean isExplode() {
		return explode;
	}

	public void setExplode(boolean explode) {
		this.explode = explode;
	}

	
}
