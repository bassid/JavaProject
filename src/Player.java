import org.newdawn.slick.SlickException;

public class Player extends Sprite implements Movable {

	/** Player constructor
	 * @param image_src
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @throws SlickException
	 */
	public Player(String image_src, float x, float y, boolean isBlocked) throws SlickException {
		super(image_src, x, y, isBlocked);
	}

	/** Checks to see if the player can move left
	 */
	public boolean canMoveLeft() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {

			if( (this.getY() == sprite.getY()) &&
					(this.getX() == sprite.getX()+App.TILE_SIZE) ){

				if (sprite.getName().contains("wall") ||
						sprite.getName().contains("door")) {
					return false;
				}
				else if(sprite.getName().contains("stone")) {
					Stone stone = (Stone) sprite;

					if (stone.canMoveLeft()) {

						Stone tempStone = new Stone(stone);

						tempStone.setName(stone.toString());

						World.getPreviousState().add(tempStone);

						sprite.setX(sprite.getX()-(App.TILE_SIZE));  
					}
					else {
						return false;
					}
				}
				else if(sprite.getName().contains("tnt")) {
					Tnt tnt = (Tnt) sprite;

					if (tnt.canMoveLeft()) {

						if( !(tnt.getName().contains("explosion")) ) {
							Tnt tempTnt = new Tnt(tnt);

							tempTnt.setName(tnt.toString());

							World.getPreviousState().add(tempTnt);
						}

						sprite.setX(sprite.getX()-(App.TILE_SIZE));  

					}
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("ice")) {
					Ice ice = (Ice) sprite;

					if ( ice.canMoveLeft() ) {
						
						Ice tempIce = new Ice(ice);

						tempIce.setName(ice.toString());

						World.getPreviousState().add(tempIce);
						
						ice.setLeftMove(true);
					}	
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("skull")) {
					World.restart();
				}
				else if (sprite.getName().contains("rogue")) {
					World.restart();
				}
				else if (sprite.getName().contains("mage") ) {
					World.restart();
				}
			}	
		}
		return true;
	}

	/** Checks to see if the player can move right
	 */
	public boolean canMoveRight() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {

			if( (this.getY() == sprite.getY()) &&
					(this.getX()+App.TILE_SIZE == sprite.getX()) ){

				if (sprite.getName().contains("wall") ||
						sprite.getName().contains("door")) {
					return false;
				}
				else if(sprite.getName().contains("stone")) {
					Stone stone = (Stone) sprite;

					if (stone.canMoveRight()) {     

						Stone tempStone = new Stone(stone);

						tempStone.setName(stone.toString());

						World.getPreviousState().add(tempStone);

						sprite.setX(sprite.getX()+(App.TILE_SIZE));
					}
					else {
						return false;
					}
				}
				else if(sprite.getName().contains("tnt")) {
					Tnt tnt = (Tnt) sprite;

					if (tnt.canMoveRight()) {

						if( !(tnt.getName().contains("explosion")) ) {
							Tnt tempTnt = new Tnt(tnt);

							tempTnt.setName(tnt.toString());

							World.getPreviousState().add(tempTnt);
						}

						sprite.setX(sprite.getX()+(App.TILE_SIZE));  

					}
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("ice")) {
					Ice ice = (Ice) sprite;

					if ( ice.canMoveRight() ) {
						Ice tempIce = new Ice(ice);

						tempIce.setName(ice.toString());

						World.getPreviousState().add(tempIce);
						
						ice.setRightMove(true);
					}	
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("skull")) {
					World.restart();
				}
				else if (sprite.getName().contains("rogue")) {
					World.restart();
				}
				else if (sprite.getName().contains("mage") ) {
					World.restart();
				}
			}
		}    	
		return true;
	}

	/** Checks to see if the player can move up
	 */
	public boolean canMoveUp() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY() == sprite.getY()+App.TILE_SIZE) &&
					(this.getX() == sprite.getX()) ){

				if (sprite.getName().contains("wall") ||
						sprite.getName().contains("door")) {
					return false;
				}
				else if(sprite.getName().contains("stone")) {
					Stone stone = (Stone) sprite;

					if (stone.canMoveUp()) {

						Stone tempStone = new Stone(stone);

						tempStone.setName(stone.toString());

						World.getPreviousState().add(tempStone);

						sprite.setY(sprite.getY()-(App.TILE_SIZE));  
					}
					else {
						return false;
					}
				}
				else if(sprite.getName().contains("tnt")) {
					Tnt tnt = (Tnt) sprite;

					if (tnt.canMoveUp()) {

						if( !(tnt.getName().contains("explosion")) ) {
							Tnt tempTnt = new Tnt(tnt);

							tempTnt.setName(tnt.toString());

							World.getPreviousState().add(tempTnt);
						}

						sprite.setY(sprite.getY()-(App.TILE_SIZE));  

					}
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("ice")) {
					Ice ice = (Ice) sprite;

					if ( ice.canMoveUp() ) {
						Ice tempIce = new Ice(ice);

						tempIce.setName(ice.toString());

						World.getPreviousState().add(tempIce);
						
						ice.setUpMove(true);

					}	
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("skull")) {
					World.restart();
				}
				else if (sprite.getName().contains("rogue")) {
					World.restart();
				}
				else if (sprite.getName().contains("mage") ) {
					World.restart();
				}
			}
		}
		return true;
	}

	/** Checks to see if the player can move down
	 */
	public boolean canMoveDown() throws Exception {
		for (Sprite sprite : Loader.blockedSprites) {
			if( (this.getY()+App.TILE_SIZE == sprite.getY()) &&
					(this.getX() == sprite.getX()) ){

				if (sprite.getName().contains("wall") ||
						sprite.getName().contains("door")) {
					return false;
				}
				else if(sprite.getName().contains("stone")) {
					Stone stone = (Stone) sprite;

					if (stone.canMoveDown()) {

						Stone tempStone = new Stone(stone);

						tempStone.setName(stone.toString());

						World.getPreviousState().add(tempStone);

						sprite.setY(sprite.getY()+(App.TILE_SIZE));  

					}
					else {
						return false;
					}
				}
				else if(sprite.getName().contains("tnt")) {
					Tnt tnt = (Tnt) sprite;

					if (tnt.canMoveDown()) {

						if (tnt.isExplode() == false) {
							Tnt tempTnt = new Tnt(tnt);

							tempTnt.setName(tnt.toString());

							World.getPreviousState().add(tempTnt);
						}

						sprite.setY(sprite.getY()+(App.TILE_SIZE));  

					}
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("ice")) {
					Ice ice = (Ice) sprite;

					if ( ice.canMoveDown() ) {
						
						Ice tempIce = new Ice(ice);

						tempIce.setName(ice.toString());

						World.getPreviousState().add(tempIce);
						
						ice.setDownMove(true);

					}	
					else {
						return false;
					}
				}
				else if (sprite.getName().contains("skull")) {
					World.restart();
				}
				else if (sprite.getName().contains("rogue")) {
					World.restart();
				}
				else if (sprite.getName().contains("mage") ) {
					World.restart();
				}
			}
		}
		return true;
	}





}