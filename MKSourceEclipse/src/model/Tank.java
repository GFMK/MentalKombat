package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends Character {

	public Tank(int px, int py) throws IOException {
		x=px;
		y=py;
		lifePoints = 5;
		img = ImageIO.read(new File("images/ent/ent_fix_r.png")); //tank img
		
	}
	public int attack(Character ch){
		System.out.println("Tank Attack");
		ch.setLifePoints(ch.getLifePoints()-1);	//substract 1 LifePoint to the opponent
		return 1;
	}
	public void bloc(){
		System.out.println("Tank Bloc");
	}
	public void speAttack(Character ch, int deg){ //the tank loses 1 LifePoint of substract 2 to the opponent
		System.out.println("Tank speAttack");
		ch.setLifePoints(ch.getLifePoints()-2);	
		this.setLifePoints(getLifePoints()-1);
		
	}


}
