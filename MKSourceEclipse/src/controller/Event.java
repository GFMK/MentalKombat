package controller;

import view.Window;

import java.util.concurrent.TimeUnit;

import model.Character;
import model.Damager;
import model.Healer;
import model.Tank;

public class Event {

	private int Key;
	private AI ai;
	private Window win;
	private Character p1;
	private Character p2;

	private boolean p2DidSpe=false;
	private boolean p1DidSpe=false;

	/*
	 * A == 65
	 * Z == 90
	 * E == 69
	 */
	public Event(int keyNum, AI Ai, Window wind, Character p, Character pp, boolean bool, boolean p1didspe) { //gets all the correct parameters
		Key = keyNum;
		ai = Ai;
		win = wind;
		p1 = p;
		p2 = pp;
		if(p1didspe){
			p1DidSpe=true;
		}
		if(bool){
			p2DidSpe=true;
		}
		if (Key==65){//depending on the keystroke, it chooses the action
			this.attackAction();
		}
		if (Key==90){
			this.blocAction();
		}
		if (Key==69){
			this.spePowAction();
		}
		win.getWP().add(p1);
		win.getWP().add(p2);//Display the characters with their moves
		win.getWP().repaint();
		this.sleep3sec();//little sleep to see the animation
		this.toFix(p1);
		this.toFixAI(p2);//put the characters back to normal 
		//System.out.println("P1 : " + p1.getLifePoints() + " P2 : " + p2.getLifePoints());
	}
	
	private void spePowAction() {
		if(p1.getClass()==Damager.class){//Choose correct img depending on the character
			p1.setImage("images/hero/hero_sp.png");
		}
		if(p1.getClass()==Healer.class){
			p1.setImage("images/unicorn/unicorn_sp_r.png");
		}
		if(p1.getClass()==Tank.class){
			p1.setImage("images/ent/ent_attack_r.png");
		}
		
		int deg = p1.getLifePoints();
		int deg2 = p2.getLifePoints();
		int n=0;
		if(!ai.getLevel()){ // if ai is on easy mode
			do{
				n = ai.randomAction(p1, p2, p2DidSpe); //action is taken randomly
			}while(p2DidSpe==true &&  n==3);
		}
		if(ai.getLevel()){// if ai is on extreme mode
			n = ai.intelligentAction(p1, p2, p2DidSpe, p1DidSpe); //it take the intelligent action
		}
		if(n==3 && p2.getClass()==Damager.class && p2DidSpe==false){
			p2.speAttack(p1, deg-p2.getLifePoints());//if the ai does a speAttack with the damager i, it observe the lost of life points it had
		}
		if(n==3){
			p2DidSpe=true;//set the fact that ai has done a speAttack
		}
	
		
		p1.speAttack(p2, deg-p1.getLifePoints()); //does the  speAttack
		if(n==2){
			p2.setLifePoints(deg2); //if blocked the LifePoints are restored
		}
		
	}

	private void blocAction() {//if the action done by the plyer is to bloc
		int deg=0;
		int n=0;
		deg=p1.getLifePoints();
		if(p1.getClass()==Damager.class){ //update img of the player
			p1.setImage("images/hero/hero_bloc.png");
		}
		if(p1.getClass()==Healer.class){
			p1.setImage("images/unicorn/unicorn_bloc_r.png");
		}
		if(p1.getClass()==Tank.class){
			p1.setImage("images/ent/ent_bloc_r.png");
		}
		if(!ai.getLevel()){ //random action
			do{
				n = ai.randomAction(p1, p2, p2DidSpe);
			}while(p2DidSpe == true &&  n==3);
		}
		if(ai.getLevel()){ //intelligent action
			n = ai.intelligentAction(p1, p2, p2DidSpe, p1DidSpe);
		}
		if(n==3){
			p2DidSpe=true;
		}
		
		
		p1.setLifePoints(deg); //restores the player's lifepoints no matter the attack
		p1.bloc();
		
	}

	private void attackAction() { // attack action
		if(p1.getClass()==Damager.class){
			p1.setImage("images/hero/hero_attack.png");//correct img chosen
		}
		if(p1.getClass()==Healer.class){
			p1.setImage("images/unicorn/unicorn_attack_r.png");
		}
		if(p1.getClass()==Tank.class){
			p1.setImage("images/ent/ent_run_r.png");
		}
		int deg = p2.getLifePoints();
		int n=0;
		p1.attack(p2);// p1 attacks p2
		if(!ai.getLevel()){ //random action
			do{
				n = ai.randomAction(p1, p2, p2DidSpe);
			}while(p2DidSpe==true &&  n==3);
		}
		if(ai.getLevel()){ //intelligent action
			n = ai.intelligentAction(p1, p2, p2DidSpe, p1DidSpe);
		}
		
		if(n==2){ // restores p2 lifepoints if ai has blocked
			p2.setLifePoints(deg);
		}
		if(n==3 && p2.getClass()==Damager.class && p2DidSpe==false){ //if p2 has done a spe attack as damager
			p2.speAttack(p1, deg-p2.getLifePoints());
		}
		if(n==3){
			p2DidSpe=true;
		}
	
		
		
	}
	
	public void sleep3sec(){
		try {
			TimeUnit.MILLISECONDS.sleep(750); //sleeps only 0.5 sec, I changed it afterwards, 3 sec is too long xD
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean getp2DidSpe(){
		return p2DidSpe;
	}
	public void setp2DidSpe(boolean bool){
		p2DidSpe = bool;
	}
	public void toFix(Character p){ //images are turned back to put the character in a fix position. Player side
		if(p.getLifePoints()<=0){
			if(p.getClass()==Damager.class){
				p.setImage("images/hero/hero_dead.png");
			}
			if(p.getClass()==Healer.class){
				p.setImage("images/unicorn/unicorn_dead_r.png");
			}
			if(p.getClass()==Tank.class){
				p.setImage("images/ent/ent_broken_r.png");
			}
		}
		else{
			if(p.getClass()==Damager.class){
				p.setImage("images/hero/hero_fix.png");
			}
			if(p.getClass()==Healer.class){
				p.setImage("images/unicorn/unicorn_fix_r.png");
			}
			if(p.getClass()==Tank.class){
				p.setImage("images/ent/ent_fix_r.png");
			}
		}
	}
	public void toFixAI(Character p){//images are turned back to put the character in a fix position. AI side
		if(p.getLifePoints()<=0){
			if(p.getClass()==Damager.class){
				p.setImage("images/hero/hero_dead_r.png");
			}
			if(p.getClass()==Healer.class){
				p.setImage("images/unicorn/unicorn_dead.png");
			}
			if(p.getClass()==Tank.class){
				p.setImage("images/ent/ent_broken.png");
			}
		}
		else{
			if(p.getClass()==Damager.class){
				p.setImage("images/hero/hero_fix_r.png");
			}
			if(p.getClass()==Healer.class){
				p.setImage("images/unicorn/unicorn_fix.png");
			}
			if(p.getClass()==Tank.class){
				p.setImage("images/ent/ent_fix.png");
			}
		}

	}
}
