import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Skeleton extends Sprite {

	/** Decides if skeleton can move up */
	private boolean upMove = true;
	/** Decides if skeleton can move down */
	private boolean downMove = false;
	/** Timer used for speed purposes */
	private static int time = 0;

	public Skeleton(String imgSrc, float x, float y, boolean isBlocked) throws SlickException {
		super(imgSrc, x, y, isBlocked);
	}

	public void update(Input input, int delta) throws SlickException {
		time += delta;
		if (time>1000) {
			time = 0;
			if (upMove && canMoveUp()) {
				this.setY( this.getY() - App.TILE_SIZE);
				System.out.println("Skeleton moved up");
			}
			else if (downMove && canMoveDown() ) {
				this.setY( this.getY() + App.TILE_SIZE);
				System.out.println("Skeleton moved down");
			}
		}
	}

	/** Checks to see if the Rogue can move up
	 */
	public boolean canMoveUp() {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY() == sprite.getY()+App.TILE_SIZE) &&
					(this.getX() == sprite.getX()) ){
				upMove = false;
				downMove = true;
				return false;	
			}
		}
		upMove = true;
		downMove = false;
		return true;
	}

	/** Checks to see if the Rogue can move down
	 */
	public boolean canMoveDown() {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY()+App.TILE_SIZE == sprite.getY()) &&
					(this.getX() == sprite.getX()) ){
				downMove = false;
				upMove = true;
				return false;	
			}
		}
		downMove = true;
		upMove = false;
		return true;
	}
}
