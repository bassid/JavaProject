
// Import required libraries
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Loader {	
	
	/** Array of sprites which are blocked */
	public static ArrayList<Sprite> blockedSprites =  new ArrayList<Sprite>();
	/** A list of targets to cover */
	public static ArrayList<Sprite> targets =  new ArrayList<Sprite>();
	
	/** This function creates the Sprite objects depending on the parameter 'name'. 
	 * @param name
	 * @param x
	 * @param y
	 * @param isBlocked
	 * @return
	 * @throws SlickException
	 */
	private static Sprite createSprite(String name, float x, float y, boolean isBlocked) throws SlickException {
		switch (name) {
			case "wall":
				return new Wall("res/"+name+".png", x, y, isBlocked);
			case "floor":
				return new Floor("res/"+name+".png", x, y, isBlocked);
			case "stone":
				return new Stone("res/"+name+".png", x, y, isBlocked);
			case "target":
				return new Target("res/"+name+".png", x, y, isBlocked);
			case "ice":
				return new Ice("res/"+name+".png", x, y, isBlocked);
			case "tnt":
				return new Tnt("res/"+name+".png", x, y, isBlocked);
			case "switch":
				return new Switch("res/"+name+".png", x, y, isBlocked);
			case "door":
				return new Door("res/"+name+".png", x, y, isBlocked);
			case "cracked":
				return new CrackedWall("res/cracked_wall.png", x, y, isBlocked);
			case "skeleton":
				return new Skeleton("res/skull.png", x, y, isBlocked);
			case "rogue":
				return new Rogue("res/"+name+".png", x, y, isBlocked);
			case "mage":
				return new Mage("res/"+name+".png", x, y, isBlocked);
			case "player":
				return new Player("res/"+name+".png", x, y, isBlocked);
		}
		return null;
	}
	
	/**Checks name of sprite and returns whether it is blocked or not.
	 * @param name
	 * @return
	 */
	public static boolean isBlocked(String name) {
		
		if(name.equals("wall") || name.equals("stone") || name.equals("ice")
				|| name.equals("tnt") || name.equals("door") || name.equals("cracked")
				|| name.equals("skeleton") || name.equals("rogue") || name.equals("mage") || name.equals("player")) {
			return true;
		}
		return false;
	}
	
			
	/** Loads the sprites from a given file.
	 * @param filename
	 * @return
	 * @throws SlickException 
	 */
	public static ArrayList<Sprite> loadSprites(String filename) throws SlickException {
		
		// Create an ArrayList for the map data. This will store all the map sprites
		ArrayList<Sprite> map = new ArrayList<Sprite>();
		
		// Open CSV file 
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String text;
				
			text = br.readLine();
			String[] line = text.split(",");
			
			// Calculate the starting position of the first sprite
			int mapWidth = Integer.parseInt(line[0])*(App.TILE_SIZE);
			int mapHeight = Integer.parseInt(line[1])*(App.TILE_SIZE);
			int start_x = (App.SCREEN_WIDTH/2) - (mapWidth/2);
			int start_y = (App.SCREEN_HEIGHT/2) - (mapHeight/2);
			
			// Read every line in the CSV file
			while ((text = br.readLine()) != null) {
				line = text.split(",");
				
				// Calculate the image source, x position and y position (in pixels)
				String imgSrc = line[0].trim();
				
				int x = Integer.parseInt(line[1].trim());
				x = (x*App.TILE_SIZE)+(start_x);
				
				int y = Integer.parseInt(line[2].trim());
				y = (y*App.TILE_SIZE)+(start_y);
				
				boolean isBlocked = isBlocked(imgSrc);
				
				Sprite sprite = createSprite(imgSrc, x, y, isBlocked);
				
				if (isBlocked) {
					blockedSprites.add(sprite);
				}
				else if (sprite.getName().contains("target")) {
					targets.add(sprite);
				}
				
				map.add(sprite);				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return map;
	}
}
