package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends Character {

	public Tank(int x, int y) throws IOException {
		super(x,y);
		lifePoints = 5;
		img = ImageIO.read(new File("images/ent/ent_fix_r.png"));
		
	}
	public int attack(Character ch){
		System.out.println("Tank Attack");
		ch.setLifePoints(ch.getLifePoints()-1);	
		return 1;
	}
	public void bloc(){
		System.out.println("Tank Bloc");
	}
	public void speAttack(Character ch, int deg){
		System.out.println("Tank speAttack");
		ch.setLifePoints(ch.getLifePoints()-2);	
		this.setLifePoints(getLifePoints()-1);
		
	}


}
