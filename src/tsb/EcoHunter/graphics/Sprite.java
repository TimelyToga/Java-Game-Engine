package tsb.EcoHunter.graphics;

import java.util.Random;

import tsb.EcoHunter.level.Level;

public class Sprite {

	public final int SIZE;
	private int x, y, ex, ey;
	public int[] pixels;
	private SpriteSheet sheet;
	public static boolean colorfulVoids = false;
	private static Random rgen = new Random();
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite lava = new Sprite(16, 0, 0, SpriteSheet.smTiles);
	public static Sprite playerS = new Sprite(16, 1, 0, SpriteSheet.smTiles);
	public static Sprite wanderer = new Sprite(16, 2, 0, SpriteSheet.smTiles);
	public static Sprite playerM = new Sprite(16, 3, 0, SpriteSheet.smTiles);
	public static Sprite voidSprite = new Sprite(16, 0);
	public static Sprite dtree = new Sprite(16,1,1, SpriteSheet.smTiles);
	public static Sprite ctree = new Sprite(16,2,1, SpriteSheet.smTiles);
	
	public static Sprite bumbum = new Sprite(6, 0);

	public Sprite(int scale, int x, int y, SpriteSheet sheet) {
		this.SIZE = scale;
		this.x = x << 4;
		this.y = y << 4;
		this.ex = x + SIZE * scale;
		this.ey = y + SIZE * scale;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public Sprite(int scale, int colour){
		SIZE = scale;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	public Sprite(int scale){
		SIZE = scale;
		pixels = new int[SIZE * SIZE];
		setColour(rgen.nextInt(0xFFFFFF));
	}
	

	private void setColour(int colour) {
		for(int i = 0; i < SIZE*SIZE; i++){
			if(colorfulVoids){
				pixels[i] = rgen.nextInt(0xFFFFFF);
			} else{
				pixels[i] = colour;
			}
		}
	}

	public void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
