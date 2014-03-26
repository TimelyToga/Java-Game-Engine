package tsb.EcoHunter.entity.mob;

import java.util.ArrayList;
import java.util.List;

import tsb.EcoHunter.entity.weapons.BumBum;
import tsb.EcoHunter.entity.weapons.Weapons;
import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.input.Keyboard;
import tsb.EcoHunter.level.Level;

public class Player extends Mob {

	private Keyboard input;
	int lastFire = (int) System.currentTimeMillis();
	int shotsSec = 6;
	int firePause = 1000 / shotsSec;
	boolean canFire = false;
	private Weapons currWeapon;
	List<Weapons> projectiles = new ArrayList<Weapons>();
	
	
	public Player(Keyboard input){
		this.x = 0;
		this.y = 0;
		this.input = input;
		this.sprite = sprite.playerM;
		this.level = Level.level1;
		this.ENTITY_SIZE = sprite.SIZE;
	}
	
	public Player(int xPos, int yPos, Keyboard input){
		this.x = xPos;
		this.y = yPos;
		this.sprite = sprite.playerM;
		this.input = input;
		this.level = Level.level1;
		this.ENTITY_SIZE = sprite.SIZE;
	}
	
	public void update(){
		int xa = 0, ya = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (input.fire){
			int diff = (int) (System.currentTimeMillis() - lastFire);
			if((diff > firePause) || canFire){
				currWeapon = new BumBum(this.x, this.y,this.dir, this);
				projectiles.add(currWeapon);
				lastFire = (int) System.currentTimeMillis();
				canFire = false;
			}
		} else {
			canFire = true;
		}
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
			moving = true;
		} else moving = false;
		
		if(moving){
			sprite = sprite.playerM;
		} else sprite = sprite.playerS;

		
		updateProj();
	}
	
	public void render(Screen screen){
		screen.renderPlayer(x, y, this);
		for(int a = 0; a < projectiles.size(); a++){
			projectiles.get(a).render(screen);
		}
	}
	
	public void spriteChange(){
		if(sprite == sprite.playerM){
			sprite = sprite.dtree;
		} else {
			sprite = sprite.playerM;
		}
	}
	
	public void updateProj(){
		for(int a = 0; a < projectiles.size(); a++){
			projectiles.get(a).update();
			if(projectiles.get(a).getCollided()) removeProj(projectiles.get(a));
		}
	}
	
	public void removeProj(Weapons w){
		projectiles.remove(w);
	}
	
}
