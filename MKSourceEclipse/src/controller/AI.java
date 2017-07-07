package controller;

import java.io.IOException;
import java.util.Random;
import model.Character;
import model.Damager;
import model.Healer;
import model.Tank;

public class AI {

	Character CharAI;
	private boolean extreme; //boolean that sets the easy or extreme mode
	
	public AI() throws IOException {
		// TODO Auto-generated constructor stub
		this.characterChosen();
	}
	
	public Character characterChosen() throws IOException{
		Random rand = new Random();
		int  n = rand.nextInt(3) + 1; //generate a random number between 1 and 3
		if(n==1){
			CharAI = new Damager(550,350); //chooses a random character for the AI
			CharAI.setImage("images/hero/hero_fix_r.png"); //assign the correct img
		}
		if(n==2){
			CharAI = new Healer(550,330);
			CharAI.setImage("images/unicorn/unicorn_fix.png");
		}
		if(n==3){
			CharAI = new Tank(550,375);
			CharAI.setImage("images/ent/ent_fix.png");
		}
		return CharAI;
	}
	
	public int randomAction(Character cha1, Character cha2, boolean didSpe){
		Random rand = new Random();
		int n = rand.nextInt(3)+1;
		if(n==1){ //choose a random action if the ai is on easy mode
			cha2.attack(cha1);
		}
		if(n==2){
			cha2.bloc();
		}
		if(n==3 && !didSpe){ //looks if the player has done it speAttack
			cha2.speAttack(cha1, 0);
		}
		this.changeImage(cha2, n); //change the ai image depending on the move it has done
		return n;
		
	}
	
	public int intelligentAction(Character cha1, Character cha2, boolean didSpe, boolean p1didSpe){
		Random rand = new Random();
		int n = 0;
		int nbrRand=0;
		
		//IF P2 IS DAMAGER
		if(cha2.getClass()==Damager.class){
			if(cha1.getClass()==Damager.class){ //p2 allways attack if p1 is a damager
				cha2.attack(cha1);
				n=1;
			}
			if(cha1.getClass()==Tank.class){ //if p1 is a tank, p2 bloc 2/3 of time until p1 uses it speAttack, then p2 only attacks
				if(p1didSpe){
					cha2.attack(cha1);
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1;
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.bloc();
						n=2;
					}
				}
			}
			if(cha1.getClass()==Healer.class){ //p2 only attacks if p1 is a healer
				cha2.attack(cha1);
				n=1;
			}
		}
		
		//IF P2 IS TANK
		if(cha2.getClass()==Tank.class){// if p2 is a tank
			if(cha1.getClass()==Damager.class){ //if p1 is a damager
				if(didSpe){
					cha2.attack(cha1); //if p2 has done it's speAttack, it always attack
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1; //1/3 chances to do a speAttack, else attacks
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.speAttack(cha1, 0);
						n=3;
					}
				}
			}
			if(cha1.getClass()==Tank.class){ // Same code as P1 damager / p2 tank
				if(p1didSpe){
					cha2.attack(cha1);
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1;
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.bloc();
						n=2;
					}
				}
			}
			if(cha1.getClass()==Healer.class){ // if p1 is a healer
				if(didSpe){
					cha2.attack(cha1); // always attacks when p2 has done the speAttack
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1; // 2/3 of doing speAttack, 1/3 of doing a classic attack
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.speAttack(cha1, 0);
						n=3;
					}
				}
			}
		}
		//IF P2 IS A HEALER
		if(cha2.getClass()==Healer.class){ 
			if(cha1.getClass()==Damager.class){ //if p1 is a damager
				if(didSpe){ 
					cha2.attack(cha1); //only attacks after doing the speAttack
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1; // 1/3 of attacking, 2/3 of doing the speAttack
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.speAttack(cha1, 0);
						n=3;
					}
				}
			}
			if(cha1.getClass()==Tank.class){ // 
				if(p1didSpe){ //if p1 has done its speAttack, p2 attacks, or has 2/3 chances of doing a speAttack if not done yet
					if(!didSpe){
						int nbrRand3 = rand.nextInt(3) + 1;
						if(nbrRand3==1){
							cha2.attack(cha1);
							n=1;
						}
						else{
							cha2.speAttack(cha1, 0);
							n=3;
						}
					}
					else{
						cha2.attack(cha1);
						n=1;
					}
				}
				else{//if p1 has not done it's speAttack, p2 blocs 2/3 of the time, 
					nbrRand = rand.nextInt(3) + 1; 
					if(nbrRand==1){
						if(!didSpe){
							int nbrRand2 = rand.nextInt(3) + 1;
							if(nbrRand2==1){
								cha2.attack(cha1);
								n=1;
							}
							else{
								cha2.speAttack(cha1, 0);
								n=3;
							}
						}
						else{
							cha2.attack(cha1);
							n=1;
						}
					}
					else{
						cha2.bloc();
						n=2;
					}
				}
			}
			if(cha1.getClass()==Healer.class){ //if p1 is a healer
				if(didSpe){ //if p2 has done the speAttack, it only attacks
					cha2.attack(cha1);
					n=1;
				}
				else{
					nbrRand = rand.nextInt(3) + 1; // 2/3 chances to do a speAttack, 1/3 of doing a regular attack
					if(nbrRand==1){
						cha2.attack(cha1);
						n=1;
					}
					else{
						cha2.speAttack(cha1, 0);
						n=3;
					}
				}
			}
		}
		this.changeImage(cha2, n);
		return n;
	}
	
	public void changeImage(Character cha, int action){ //changes the image depending on the situation
		if(action==1){
			if(cha.getClass()==Damager.class){
				cha.setImage("images/hero/hero_attack_r.png");
			}
			if(cha.getClass()==Healer.class){
				cha.setImage("images/unicorn/unicorn_attack.png");
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
