package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Damager extends Character{
	
	public Damager(int px, int py) throws IOException {
		x=px;
		y=py;
		img = ImageIO.read(new File("images/hero/hero_fix.png")); //fixed position of the damager
		lifePoints = 3;
	}
	

	public int attack(Character ch){
		//System.out.println("Damager Attack");
		ch.setLifePoints(ch.getLifePoints()-2);// substract 2 lifepoints to the opponent
		return 2;
	}
	public void bloc(){
		//System.out.println("Damager bloc");//doesn't do anything, but might be useful if we want actions to happen when the character blocks
	}
	public void speAttack(Character ch , int deg){
		//System.out.println("Damager speAttack");
		ch.setLifePoints(ch.getLifePoints()-deg); //deg is the amount of lifepoint lost by the Damager himself. 
		
	}

	
}
