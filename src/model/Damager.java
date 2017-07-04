package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Damager extends Character{
	
	public Damager(int x, int y) throws IOException {
	super(x, y);	
		img = ImageIO.read(new File("images/hero/hero_fix.png"));
		lifePoints = 3;
	}
	

	public int attack(Character ch){
		System.out.println("Damager Attack");
		ch.setLifePoints(ch.getLifePoints()-2);
		return 2;
	}
	public void bloc(){
		System.out.println("Damager bloc");
	}
	public void speAttack(Character ch , int deg){
		System.out.println("Damager speAttack");
		ch.setLifePoints(ch.getLifePoints()-deg);
		
	}

	
}
