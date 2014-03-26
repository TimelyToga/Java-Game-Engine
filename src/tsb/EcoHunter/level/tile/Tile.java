package tsb.EcoHunter.level.tile;

import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.graphics.Sprite;
import tsb.EcoHunter.level.Level;

public class Tile {
	
	public int x, y;
	public int health;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile lava = new LavaTile(Sprite.lava);
	public static Tile dtree = new Tile(Sprite.dtree);
	public static Tile ctree = new Tile(Sprite.ctree);
	public static Tile playerS = new Tile(Sprite.playerS);
	public static Tile playerM = new Tile(Sprite.playerM);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
		this.health = 10;
	}
	
	public void render(int xx, int yy, Screen screen){
		int x = (xx << 4);
		int y = yy << 4;
		screen.renderTile(x, y, this);
	}
	
	public boolean solid(){
		return false;
	}
	
	public void takeDamage(int damageTaken, int x, int y, int strikeDir){
		System.out.println("Planned Damage Taken x: " + x + " y: " + y);
		Level.currLevel.getTile(x, y).health -= damageTaken;
		if(true) removeTile(x, y, 5, strikeDir); //Level.currLevel.getTile(x, y).health <= 0
	}
	
	public void removeTile(int xx, int yy, int nextForm, int dir){
		if(xx > 0 && yy > 0) {
					int x = xx >> 4;
		int y = yy >> 4;
		if (dir == 0) Level.currLevel.tiles[(x+1) + y * Level.currLevel.width] = nextForm;
		if (dir == 1) Level.currLevel.tiles[(x+1) + y * Level.currLevel.width] = nextForm;
		if (dir == 2) Level.currLevel.tiles[(x) + (y + 1) * Level.currLevel.width] = nextForm;
		if (dir == 3) Level.currLevel.tiles[(x) + (y) * Level.currLevel.width] = nextForm;
		}

		//Level.currLevel.tiles[(x+1) + y * Level.currLevel.width] = nextForm;
		System.out.println("Tile value " + Level.currLevel.tiles[(x +1) + y * Level.currLevel.width]);
		//if(nextForm == 0){
		//	tile = grass;
		//	System.out.println("It should be grass...");
		//}
		//else tile = voidTile;
	}
}
