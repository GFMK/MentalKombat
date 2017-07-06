package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Healer extends Character {

	
	
	public Healer(int px, int py) throws IOException {
		x = px;
		y = py;
		img = ImageIO.read(new File("images/unicorn/unicorn_fix_r.png")); //Healer image
		lifePoints = 4;
	}

	
	public int attack(Character ch){
		System.out.println("Healer Attack");
		ch.setLifePoints(ch.getLifePoints()-1); // substract one life point of the opponent
		return 1;
	}
	public void bloc(){
		System.out.println("Healer Bloc");
	}
	public void speAttack(Character ch, int deg){
		System.out.println("Healer speAttack"); // add 2 lifepoints when performed
		this.lifePoints+=2;
		
	}

}
