package tsb.EcoHunter.level.tile;

import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
		this.health = 5;
	}

	public void render(int xx, int yy, Screen screen) {
		int x = xx << 4;
		int y = yy << 4;
		screen.renderTile(x, y, this);
	}
}
