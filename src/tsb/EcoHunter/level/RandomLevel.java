package tsb.EcoHunter.level;

import java.util.Random;

import tsb.EcoHunter.level.tile.Tile;

public class RandomLevel extends Level {

	private static final Random rgen = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
		generateLevel();
	}

	protected void generateLevel() {
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(x < 2 && y < 2){
					tiles[x + y * width] = 2;
				} else{
				tiles[x + y * width] = rgen.nextInt(tileTypes);
				}
			}
		}
	}
	

}
