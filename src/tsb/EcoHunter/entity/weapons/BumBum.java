package tsb.EcoHunter.entity.weapons;

import tsb.EcoHunter.entity.Entity;
import tsb.EcoHunter.entity.mob.Player;
import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.graphics.Sprite;
import tsb.EcoHunter.level.Level;
import tsb.EcoHunter.level.tile.Tile;

public class BumBum extends Weapons {
	
	public BumBum(int x, int y, int dir, Player player) {
		int PLAYER_SIZE = player.ENTITY_SIZE;
		
		this.damage = 10; // NOT IMPLEMENTED
		this.maxDist = 1000; // NOT IMPLEMENTED (Tile precision)
		this.accuracy = 1; // Decimal percentage; NOT IMPLEMENTED
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.sprite = Sprite.bumbum;
		this.ENTITY_SIZE = 6;
		this.level = Level.level1;
		this.speed = 2;
		
		if(dir == 0){
			this.y -= ENTITY_SIZE;
			//this.x += PLAYER_SIZE / 2;
		}
		if(dir == 1){
			this.x += PLAYER_SIZE;
			//this.y += PLAYER_SIZE / 2;
		}
		if(dir == 2){
			this.y += PLAYER_SIZE;
			//this.x += PLAYER_SIZE / 2;
		}
		if(dir == 3){
			this.x -= ENTITY_SIZE;
			//this.y += PLAYER_SIZE / 2;
		}
	}

	public void update() {
		int xa = 0, ya = 0;
		if(dir == 0) ya--;
		if(dir == 1) xa++;
		if(dir == 2) ya++;
		if(dir == 3) xa--;
		
		move(xa*this.speed, ya*this.speed);
	}
	
	public void render(Screen screen) {
		screen.renderProj(x, y, this);
	}
	
	// pixel precision 
	public void move(int xa, int ya){
		x += xa;
		y += ya;
		if(collision(x, y)){
			Tile collidingTile = collidingTile(x, y, this.dir, this.ENTITY_SIZE);
			if(collidingTile != Tile.playerM || collidingTile != Tile.playerS){
				collidingTile.takeDamage(damage,x, y, this.dir);
				this.hasCollided = true;
				System.out.println("Collision Tile X: " + x + " Y: " + y);
			}
		}
	}

	
	public void applyDamage(Tile tile){
		
	}
	
	public void init(Entity the){
	}

}
