package tsb.EcoHunter.graphics;

import java.util.ArrayList;
import java.util.Random;

import tsb.EcoHunter.entity.mob.Mob;
import tsb.EcoHunter.entity.mob.Player;
import tsb.EcoHunter.entity.weapons.Weapons;
import tsb.EcoHunter.level.tile.Tile;

/**
 * Oversees all objects that are displayed to the screen.
 * 
 * @author Timothy the Scholar
 */
public class Screen {
	public int width, height;
	public int[] pixels;
	int totalScreen = 0xff00ff;

	public final int MAP_SIZE = 16;
	public final int MAP_MASK = MAP_SIZE - 1;

	private int count = 0, listCount = 0;
	private boolean randCol = true;
	java.util.List<Integer> tileBlock;
	private Random random = new Random();
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private int xOffset;
	private int yOffset;

	/**
	 * * Constructor Inputs width: width of the display, in pixels height:
	 * height of the display, in pixels Outputs none
	 * 
	 * @param width
	 *            Width (in pixels) of the screen
	 * @param height
	 *            Height (in pixels) of the screen
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		tileBlock = new ArrayList<Integer>();

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tileBlock.add(i, count);
			count++;
		}
		count = 0;
	}

	public void clear() {
		for (int a = 0; a < pixels.length; a++) {
			pixels[a] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE ];
			}
		}
	}
	
	public void renderPlayer(int xPos, int yPos, Player player){
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y <  player.sprite.SIZE; y++){
			int ya = yPos + y;
			for(int x = 0; x < player.sprite.SIZE; x++){
				int xa = xPos + x;
				if (xa < -player.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = player.sprite.pixels[x + y * player.sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void newList() {
		if (randCol == false) {
			totalScreen = random.nextInt(0xffffff);
		}
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tileBlock.add(i, count);
			count++;
		}
		count = 0;
	}
	
	
	public void renderMob(int xPos, int yPos, Mob mob){
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y <  mob.sprite.SIZE; y++){
			int ya = yPos + y;
			for(int x = 0; x < mob.sprite.SIZE; x++){
				int xa = xPos + x;
				if (xa < -mob.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = mob.sprite.pixels[x + y * mob.sprite.SIZE];
			
			}
		}
	} // End renderMob
	
	public void renderProj(int xPos, int yPos, Weapons mob){
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y <  mob.sprite.SIZE; y++){
			int ya = yPos + y;
			for(int x = 0; x < mob.sprite.SIZE; x++){
				int xa = xPos + x;
				if (xa < -mob.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = mob.sprite.pixels[x + y * mob.sprite.SIZE];
			
			}
		}
	} // End renderMob
	
}
