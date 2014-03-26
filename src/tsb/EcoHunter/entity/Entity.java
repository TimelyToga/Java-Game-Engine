package tsb.EcoHunter.entity;

import java.util.Random;

import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.level.Level;
import tsb.EcoHunter.level.tile.Tile;

public abstract class Entity {

	public int x, y;
	public int ENTITY_SIZE;
	private boolean removed = false;
	protected Level level;
	protected Random rgen = new Random();

	public void render(Screen screen) {

	}

	public void update() {

	}

	public void remove() {
		// Remove from the level
		removed = true;
	}

	public boolean getRemoved() {
		return removed;
	}

	/** This method checks each of the four tiles that the various corners of the
	 * current Tile are on, and then checks whether any of them have an
	 * intersection with a solid Tile object
	 * 
	 * @param xCood X Coordinate of the UL corner of Entity
	 * @param yCood Y Coordinate of the UL corner of Entity
	 * @return True if there is a collision */
	protected boolean collision(int xCood, int yCood) {
		int ls = ENTITY_SIZE - 1;
		int xTemp = xCood + ls;
		int yTemp = yCood + ls;
		
		Tile ULTile = this.level.getTile((xCood >> 4), (yCood >> 4));
		Tile URTile = this.level.getTile((xTemp) >> 4, yCood >> 4);
		Tile BLTile = this.level.getTile(xCood >> 4, (yTemp) >> 4);
		Tile BRTile = this.level.getTile((xTemp) >> 4, (yTemp) >> 4);
		
//		Tile ULTile = this.level.getTile((xCood >> 4), (yCood >> 4));
//		Tile URTile = this.level.getTile((xCood + ls) >> 4, yCood >> 4);
//		Tile BLTile = this.level.getTile(xCood >> 4, (yCood + ls) >> 4);
//		Tile BRTile = this.level.getTile((xCood + ls) >> 4, (yCood + ls) >> 4);
		if (ULTile.solid() || URTile.solid() || BLTile.solid() || BRTile.solid())
			return true;
		else
			return false;
	}
	
	// pixel precision
	protected Tile collidingTile(int xCood, int yCood, int strikeDir, int colliderSize) {
		int ls = colliderSize;
		if(strikeDir == 0){
			Tile ULTile = this.level.getTile((xCood >> 4), (yCood >> 4));
			Tile URTile = this.level.getTile((xCood + ls) >> 4, yCood >> 4);
			if (ULTile.solid()) return ULTile;
			if (URTile.solid()) return URTile;
			else return null;
		} if(strikeDir == 1){
			Tile URTile = this.level.getTile((xCood + ls) >> 4, yCood >> 4);
			Tile BRTile = this.level.getTile((xCood + ls) >> 4, (yCood + ls) >> 4);
			if (URTile.solid()) return URTile;
			if(BRTile.solid()) return BRTile;
			else return null;
		} if(strikeDir == 2){
			Tile BLTile = this.level.getTile(xCood >> 4, (yCood + ls) >> 4);
			Tile BRTile = this.level.getTile((xCood + ls) >> 4, (yCood + ls) >> 4);
			if (BLTile.solid()) return BLTile;
			if (BRTile.solid()) return BRTile;
			else return null;
		} if(strikeDir == 3){
			Tile ULTile = this.level.getTile((xCood >> 4), (yCood >> 4));
			Tile BLTile = this.level.getTile(xCood >> 4, (yCood + ls) >> 4);
			if (ULTile.solid()) return ULTile;
			if (BLTile.solid()) return BLTile;
			else return null;
		} else {
			System.out.println("No Tile Collision found at x: " + x + " y: " + y);
			return null;
		}
	}

	/** Simplest implementation of the move algorithm
	 * 
	 * @param xa How far in the x axis the Entity is requesting to move
	 * @param ya How far in the y axis the Entity is requesting to move */
	public void move(int xa, int ya) {
		int x2 = x + xa;
		int y2 = y + ya;

		if (!collision(x2, y2)) {
			x += xa;
			y += ya;
		}
	}
	
	public void init(Entity the){
		
	}
}
