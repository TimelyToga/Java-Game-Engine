package tsb.EcoHunter.level;

import java.util.ArrayList;
import java.util.List;

import tsb.EcoHunter.entity.Entity;
import tsb.EcoHunter.entity.mob.baddies.Wanderer;
import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.level.tile.Tile;

public class Level {

	public int width;
	public int height;
	public int[] tiles;
	public int tileTypes = 4;
	//List<Entity> entityList = new ArrayList<Entity>();
	Wanderer wand1;

	public static Level level1 = new RandomLevel(32, 32);
	
	public static Level currLevel = level1;

	/** Constructs a Level object with a given number of tiles wide and tall
	 * 
	 * @param width Number of tiles this Level object has in the x plane
	 * @param height Number of tiles this Level object has in the y plane */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		//entityList.add(wand1);
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	private void generateLevel() {

	}

	private void loadLevel(String path) {

	}

	// AI and changes to the level, entities in the level
	public void update() {
		wand1.wanderAI();
	}

	private void time() {

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// From Pixel Precision To Tile P.
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);

			}
		}

		/*
		 * Call the Wanderer render Method, which points to the screen's render
		 * method, which renders the tile. This render is called every time that
		 * the level is rendered
		 */
		wand1.render(screen);
	}

	/** @param x Horizontal Coordinate (in terms of tiles) of said tile
	 * @param y Vertical Coordinate (in terms of tiles) of said tile
	 * @return Gives the Tile object that lives at those coordinates in the
	 *         super Level object */
	public Tile getTile(int x, int y) {
		int pos = x + y * currLevel.width; // Width is in terms of tiles
		if (x < 0 || y < 0 || x > currLevel.width || y > currLevel.height || pos > (currLevel.width * currLevel.height - 1)){
			return Tile.voidTile;
		}
		if (currLevel.tiles[pos] == 0) return Tile.lava;
		if (currLevel.tiles[pos] == 1)
			return Tile.dtree;
		if(currLevel.tiles[pos] == 5){
			return Tile.ctree;
		}
		else
			return Tile.grass;
	}

	/** Loads all the Mobs objects in a this Level object */
	public void loadMobs() {
		wand1 = new Wanderer(this);
	}

}
