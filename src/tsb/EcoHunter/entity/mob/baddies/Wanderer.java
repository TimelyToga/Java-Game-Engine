package tsb.EcoHunter.entity.mob.baddies;

import tsb.EcoHunter.entity.mob.Mob;
import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.level.Level;

public class Wanderer extends Mob {

	private static int moveCount = 33;

	public Wanderer(Level level) {
		this.sprite = sprite.wanderer;
		this.level = level;
		this.ENTITY_SIZE = sprite.SIZE;

		// Creates a random position to spawn
		int landX = rgen.nextInt(level.width * sprite.SIZE);
		int landY = rgen.nextInt(level.height * sprite.SIZE);

		// If it would be colliding here, try again
		while (collision(landX, landY)) {
			landX = rgen.nextInt(level.width * sprite.SIZE);
			landY = rgen.nextInt(level.height * sprite.SIZE);
		}

		// Once a position is found that doesn't collide, spawn the Wanderer
		this.x = landX;
		this.y = landY;
		System.out.println(x + " " + y);
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, this);
	}

	public void update() {

	}

	public void wanderAI() {
		int xa = 0, ya = 0, lastDir = 0;

		if (moveCount > 32 || !moving) {
			moveCount = 0;
			dir = rgen.nextInt(4);
		}

		while ((dir % 2) == (lastDir % 2)) {
			dir = rgen.nextInt(4);
		}

		if (dir == 0) ya--;
		if (dir == 1) xa++;
		if (dir == 2) ya++;
		if (dir == 3) xa--;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			moveCount++;
			lastDir = dir;
		}
	}

}
