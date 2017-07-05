package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Healer extends Character {

	
	
	public Healer(int x, int y) throws IOException {
		super(x,y);
		img = ImageIO.read(new File("images/unicorn/unicorn_fix_r.png"));
		lifePoints = 4;
	}

	
	public int attack(Character ch){
		System.out.println("Healer Attack");
		ch.setLifePoints(ch.getLifePoints()-1);
		return 1;
	}
	public void bloc(){
		System.out.println("Healer Bloc");
	}
	public void speAttack(Character ch, int deg){
		System.out.println("Healer speAttack");
		this.lifePoints+=2;
		
	}

}
