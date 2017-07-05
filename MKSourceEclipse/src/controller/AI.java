package controller;

import java.io.IOException;
import java.util.Random;
import model.Character;
import model.Damager;
import model.Healer;
import model.Tank;

public class AI {

	Character CharAI;
	private boolean extreme;
	
	public AI() throws IOException {
		// TODO Auto-generated constructor stub
		this.characterChosen();
	}
	
	public Character characterChosen() throws IOException{
		Random rand = new Random();
		int  n = rand.nextInt(3) + 1;
		if(n==1){
			CharAI = new Damager(550,350);
			CharAI.setImage("images/hero/hero_fix_r.png");
		}
		if(n==2){
			CharAI = new Healer(550,350);
			CharAI.setImage("images/unicorn/unicorn_fix.png");
		}
		if(n==3){
			CharAI = new Tank(550,350);
			CharAI.setImage("images/ent/ent_fix.png");
		}
		return CharAI;
	}
	
	public int randomAction(Character cha1, Character cha2, boolean didSpe){
		Random rand = new Random();
		int n = rand.nextInt(3)+1;
		if(n==1){
			cha2.attack(cha1);
		}
		if(n==2){
			cha2.bloc();
		}
		if(n==3 && !didSpe){
			cha2.speAttack(cha1, 0);
		}
		this.changeImage(cha2, n);
		return n;
		
	}
	public void changeImage(Character cha, int action){
		if(action==1){
			if(cha.getClass()==Damager.class){
				cha.setImage("images/hero/hero_attack_r.png");
			}
			if(cha.getClass()==Healer.class){
				cha.setImage("images/unicorn/unicorn_sp.png");
			}
			if(cha.getClass()==Tank.class){
				cha.setImage("images/ent/ent_run.png");
			}
		}
		if(action==2){
			if(cha.getClass()==Damager.class){
				cha.setImage("images/hero/hero_bloc_r.png");
			}
			if(cha.getClass()==Healer.class){
				cha.setImage("images/unicorn/unicorn_bloc.png");
			}
			if(cha.getClass()==Tank.class){
				cha.setImage("images/ent/ent_bloc.png");
			}
		}
		if(action==3){
			if(cha.getClass()==Damager.class){
				cha.setImage("images/hero/hero_sp_r.png");
			}
			if(cha.getClass()==Healer.class){
				cha.setImage("images/unicorn/unicorn_sp.png");
			}
			if(cha.getClass()==Tank.class){
				cha.setImage("images/ent/ent_attack.png");
			}
		}
	}
	public void setLevel(boolean ext){
		extreme = ext;
	}
	public boolean getLevel(){
		return extreme;
	}
	
	
}
