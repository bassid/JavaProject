
// Import required libraries
import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	

	/** Player object (to be used depending on the level) */
	private static Player player;
	/** Rogue object (to be used depending on the level) */
	private static Rogue rogue;
	/** Mage object (to be used depending on the level) */
	private static Mage mage;
	/** Current level */
	private static int lvl = 0;
	/** Counter for moves made */
	private static int totalMoves = 0;
	/** Variable to check if a tnt can explode */
	private boolean explode = false;
	/** Map of the level */
	private static ArrayList<Sprite> map = new ArrayList<Sprite>();
	/** A list of targets to cover */
	private static ArrayList<Sprite> targets = new ArrayList<Sprite>();
	/** A stack of states of blocks which move */
	private static Stack<ArrayList<Sprite>> states = new Stack<ArrayList<Sprite>>();
	/** An array of the state of blocks which move. This is pushed into the 'states' stack */
	private static ArrayList<Sprite> previousState = new ArrayList<Sprite>();

	/** World constructor */
	public World() throws SlickException {
		// Loads the map 
		map = Loader.loadSprites("res/levels/" + lvl + ".lvl");
		targets = Loader.targets;
	}

	/** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(Input input, int delta) throws Exception {

    	// Call update method for each Sprite object in the map
		for (Sprite sprite : map) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}

		// Make sure totalMoves is not below 0
		if (totalMoves < 0) {
			totalMoves = 0;
		}

		// Move up, down, left and right if allowed
		if( input.isKeyPressed(Input.KEY_UP)  && getPlayer().canMoveUp() ) {

			if (mage != null) {
				mage.move();
			}


			Sprite tempPlayer = new Sprite(getPlayer());

			tempPlayer.setName(getPlayer().toString());
			getPreviousState().add(tempPlayer);

			ArrayList<Sprite> tempState = new ArrayList<Sprite>(getPreviousState());
			states.push(tempState);
			getPreviousState().clear();


			getPlayer().setY(getPlayer().getY()-(App.TILE_SIZE));
			++totalMoves;
		}

		if( input.isKeyPressed(Input.KEY_DOWN) && getPlayer().canMoveDown() ) {

			if (mage != null) {
				mage.move();
			}


			Sprite tempPlayer = new Sprite(getPlayer());

			tempPlayer.setName(getPlayer().toString());
			getPreviousState().add(tempPlayer);

			ArrayList<Sprite> tempState = new ArrayList<Sprite>(getPreviousState());
			states.push(tempState);
			getPreviousState().clear();


			getPlayer().setY(getPlayer().getY()+(App.TILE_SIZE));
			++totalMoves;
		}

		if( input.isKeyPressed(Input.KEY_LEFT) && getPlayer().canMoveLeft() ) {

			if (rogue != null) {
				if (rogue.isLeftMove() && rogue.canMoveLeft()) {
					rogue.setX(rogue.getX() - App.TILE_SIZE);
				}	
				else if (rogue.isRightMove() && rogue.canMoveRight()) {
					rogue.setX(rogue.getX() + App.TILE_SIZE);
				}
			}
			if (mage != null) {
				mage.move();
			}


			Sprite tempPlayer = new Sprite(getPlayer());

			tempPlayer.setName(getPlayer().toString());
			getPreviousState().add(tempPlayer);

			ArrayList<Sprite> tempState = new ArrayList<Sprite>(getPreviousState());
			states.push(tempState);
			getPreviousState().clear();


			getPlayer().setX(getPlayer().getX()-(App.TILE_SIZE));
			++totalMoves;
		}

		if( input.isKeyPressed(Input.KEY_RIGHT) && getPlayer().canMoveRight() ) {

			if (rogue != null) {
				if (rogue.isLeftMove() && rogue.canMoveLeft()) {
					rogue.setX(rogue.getX() - App.TILE_SIZE);
				}	
				else if (rogue.isRightMove() && rogue.canMoveRight()) {
					rogue.setX(rogue.getX() + App.TILE_SIZE);
				}
			}
			if (mage != null) {
				mage.move();
			}


			Sprite tempPlayer = new Sprite(getPlayer());

			tempPlayer.setName(getPlayer().toString());
			getPreviousState().add(tempPlayer);

			ArrayList<Sprite> tempState = new ArrayList<Sprite>(getPreviousState());
			states.push(tempState);
			getPreviousState().clear();


			getPlayer().setX(getPlayer().getX()+(App.TILE_SIZE));
			++totalMoves;
		}



		// Undo with Z key
		if( input.isKeyPressed(Input.KEY_Z) ) {
			if(!(states.isEmpty())) {

				ArrayList<Sprite> lastState = states.pop();

				for (Sprite undoBlock : lastState) {

					for (Sprite sprite : map) {

						if (undoBlock.getName().equals(sprite.toString())) {

							if (undoBlock.getName().contains("Tnt")) {

								if ( this.explode == false) {
									sprite.setX(undoBlock.getX());
									sprite.setY(undoBlock.getY());
									this.explode = true;
								}
							}
							else {
								sprite.setX(undoBlock.getX());
								sprite.setY(undoBlock.getY());

							}
						}					
					}

				}
				--totalMoves;
			}
			else {
				System.out.println("No undo\n");
			}
		}		
		
		// Restart the game with R key
		if( input.isKeyPressed(Input.KEY_R) ) {
			restart();
		}

		// Exit the game with ESC key
		if( input.isKeyPressed(Input.KEY_ESCAPE) ) {
			System.exit(0);			
		}		

		// Check to load next level
		int i = 0;
		if (lvl != 5){
			for (Sprite sprite : Loader.targets) {
				Target target = (Target) sprite;

				if( !target.isCovered() ) {
					i = 0;
					break;
				}
				else {
					i++;
				}

				if(i == Loader.targets.size()) {
					lvl++;
					totalMoves = 0;
					for (Sprite tile : map) {
						tile.setX(-App.TILE_SIZE);
						tile.setY(-App.TILE_SIZE);
					}
					map = Loader.loadSprites("res/levels/" + lvl + ".lvl");

					System.out.println("Loading lvl: " + lvl);
					break;
				}
			}
		}
	}

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) throws SlickException {

		if (lvl == 5) {
			g.drawString("ESC to exit", 10, 10);
		}
		else {
			g.drawString("Moves: " + totalMoves, 10, 10);
		}

		for (Sprite sprite : map) {
			if (sprite != null) {
				
				sprite.render(g);

				if (sprite.getName().contains("rogue")) {
					rogue = (Rogue) sprite;
				}
				else if (sprite.getName().contains("player")) {
					setPlayer((Player) sprite);
				}
				else if (sprite.getName().contains("mage")) {
					mage = (Mage) sprite;
				}
			}
		}
	}

	/** Restart the level
     */
	public static void restart() throws Exception {
		for (Sprite sprite : map) {
			sprite.setX(-App.TILE_SIZE);
			sprite.setY(-App.TILE_SIZE);
		}
		map = Loader.loadSprites("res/levels/" + lvl + ".lvl");
		totalMoves = 0;
	}
	
	/** Getters and setters for global variables
     */
	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		World.player = player;
	}

	public static ArrayList<Sprite> getPreviousState() {
		return previousState;
	}

	public static void setPreviousState(ArrayList<Sprite> previousState) {
		World.previousState = previousState;
	}
}
