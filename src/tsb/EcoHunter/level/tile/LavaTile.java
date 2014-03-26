package tsb.EcoHunter.level.tile;

import tsb.EcoHunter.graphics.Sprite;

public class LavaTile extends Tile {

	public LavaTile(Sprite sprite) {
		super(sprite);
		this.health = 5;
	}
	
	public boolean solid(){
		return true;
	}

}
