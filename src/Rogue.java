import org.newdawn.slick.SlickException;

public class Rogue extends Sprite {
	
	/** Decides if rogue can move left */
	private boolean leftMove = true;
	/** Decides if rogue can move right */
	private boolean rightMove = false;
	
	/** Rogue constructor
	 * @param imgSrc
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Rogue(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}
	
	/** Checks to see if the Rogue can move left
	 */
	public boolean canMoveLeft() throws Exception {
    	for (Sprite sprite : Loader.blockedSprites) {
    		
    		if( (this.getY() == sprite.getY()) &&
    				(this.getX() == sprite.getX()+App.TILE_SIZE) ){
    			
    			if (sprite.getName().contains("wall") ) {
    				setLeftMove(false);
    				setRightMove(true);
    				return false;
    			}
    			else if(sprite.getName().contains("stone")) {
    				Stone stone = (Stone) sprite;
    				
    				if (stone.canMoveLeft()) {
        				sprite.setX(sprite.getX()-(App.TILE_SIZE));  
        			}
    				else {
    					setLeftMove(false);
        				setRightMove(true);
    					return false;
    				}
    			}
    			else if(sprite.getName().contains("tnt")) {
    				Tnt tnt = (Tnt) sprite;
    				
    				if (tnt.canMoveLeft()) {
        				sprite.setX(sprite.getX()-(App.TILE_SIZE));  

        			}
    				else {
    					setLeftMove(false);
        				setRightMove(true);
    					return false;
    				}
    			}
    			else if (sprite.getName().contains("ice")) {
    				Ice ice = (Ice) sprite;
    				
    				if ( ice.canMoveLeft() ) {
    					System.out.println("Crashed");
    					ice.setLeftMove(true);
    				}	
    				else {
    					setLeftMove(false);
        				setRightMove(true);
    					return false;
    				}
    			}
    			else if (sprite.getName().contains("player")) {
    				World.restart();
    			}
    		}	
    	}
    	return true;
    }

	/** Checks to see if the Rogue can move right
	 */
	public boolean canMoveRight() throws Exception {
    	for (Sprite sprite : Loader.blockedSprites) {
    		
    		if( (this.getY() == sprite.getY()) &&
    				(this.getX()+App.TILE_SIZE == sprite.getX()) ){
    			
    			if (sprite.getName().contains("wall")) {
    				setRightMove(false);
    				setLeftMove(true);
    				return false;
    			}
    			else if(sprite.getName().contains("stone")) {
    				Stone stone = (Stone) sprite;
    				
    				if (stone.canMoveRight()) {
        				sprite.setX(sprite.getX()+(App.TILE_SIZE));  
        			}
    				else {
    					setRightMove(false);
        				setLeftMove(true);
    					return false;
    				}
    			}
    			else if(sprite.getName().contains("tnt")) {
    				Tnt tnt = (Tnt) sprite;
    				
    				if (tnt.canMoveRight()) {
        				sprite.setX(sprite.getX()+(App.TILE_SIZE));  

        			}
    				else {
    					setRightMove(false);
        				setLeftMove(true);
    					return false;
    				}
    			}
    			else if (sprite.getName().contains("ice")) {
    				Ice ice = (Ice) sprite;
    				
    				if ( ice.canMoveRight() ) {
    					System.out.println("Crashed");
    					ice.setRightMove(true);
    				}	
    				else {
    					setRightMove(false);
        				setLeftMove(true);
    					return false;
    				}
    			}
    			else if (sprite.getName().contains("player")) {
    				World.restart();
    			}
    		}
    	}    	
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

}
