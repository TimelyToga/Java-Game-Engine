package tsb.EcoHunter.entity.mob;

import tsb.EcoHunter.entity.Entity;
import tsb.EcoHunter.graphics.Sprite;
import tsb.EcoHunter.level.Level;
import tsb.EcoHunter.level.tile.Tile;

public abstract class Mob extends Entity {

	public int dir = 0; // 0N, 1E, 2S, 3W
	public Sprite sprite;
	public boolean moving = false;

	public void move(int xa, int ya) {
		// Direction determination
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya < 0) dir = 0;
		if (ya > 0) dir = 2;

		// Create another variable to track the new position
		int x2 = xa + x;
		int y2 = ya + y;

		/*
		 * Check X collision at the projected position, if there is, don't
		 * commit the move. Each axis remains independent here. If there is a
		 * collision in the y, you can still move x.
		 */

		if (!collision(x2, y)) {
			x += xa;
			moving = true;
		}
		if (!collision(x, y2)) {
			y += ya;
			moving = true;
		} else {
			moving = false;
		}
	}

	public void update() {

	}

}
