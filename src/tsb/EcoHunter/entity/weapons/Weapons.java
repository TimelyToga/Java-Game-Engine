package tsb.EcoHunter.entity.weapons;

import tsb.EcoHunter.entity.Entity;
import tsb.EcoHunter.graphics.Sprite;

public class Weapons extends Entity{

	public int distTraveled = 0; //NOT IMPLEMENTED
	public int damage; //NOT IMPLEMENTED
	public int maxDist; //NOT IMPLEMENTED (Tile precision)
	public int accuracy; //Decimal percentage; NOT IMPLEMENTED
	public int dir; //0N, 1E, 2S, 3W
	public int speed;
	public Sprite sprite;
	public boolean hasCollided = false;
	
	public Weapons() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean getCollided(){
		return this.hasCollided;
	}
}
