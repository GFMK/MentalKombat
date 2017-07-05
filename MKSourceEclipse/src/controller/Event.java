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

	/*
	 * A == 65
	 * Z == 90
	 * E == 69
	 */
	public Event(int keyNum, AI Ai, Window wind, Character p, Character pp, boolean bool) {
		Key = keyNum;
		ai = Ai;
		win = wind;
		p1 = p;
		p2 = pp;
		if(bool){
			p2DidSpe=true;
		}
		if (Key==65){
			this.attackAction();
		}
		if (Key==90){
			this.blocAction();
		}
		if (Key==69){
			this.spePowAction();
		}
		win.getWP().add(p1);
		win.getWP().add(p2);
		win.getWP().repaint();
		this.sleep3sec();
		this.toFix(p1);
		this.toFixAI(p2);
		System.out.println("P1 : " + p1.getLifePoints() + " P2 : " + p2.getLifePoints());
	}
	
	private void spePowAction() {
		if(p1.getClass()==Damager.class){
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
		if(!ai.getLevel()){
			
			do{
				n = ai.randomAction(p1, p2, p2DidSpe);
			}while(p2DidSpe==true &&  n==3);
			
			if(n==3 && p2.getClass()==Damager.class && p2DidSpe==false){
				p2.speAttack(p1, deg-p2.getLifePoints());
			}
			if(n==3){
				p2DidSpe=true;
			}
		}
		
		p1.speAttack(p2, deg-p1.getLifePoints());
		if(n==2){
			p2.setLifePoints(deg2);
		}
		
	}

	private void blocAction() {
		int deg=0;
		deg=p1.getLifePoints();
		if(p1.getClass()==Damager.class){
			p1.setImage("images/hero/hero_bloc.png");
		}
		if(p1.getClass()==Healer.class){
			p1.setImage("images/unicorn/unicorn_bloc_r.png");
		}
		if(p1.getClass()==Tank.class){
			p1.setImage("images/ent/ent_bloc_r.png");
		}
		if(!ai.getLevel()){
			int n=0;
			do{
				n = ai.randomAction(p1, p2, p2DidSpe);
			}while(p2DidSpe == true &&  n==3);
			if(n==3){
				p2DidSpe=true;
			}
			
		}
		p1.setLifePoints(deg);
		p1.bloc();
		
	}

	private void attackAction() {
		if(p1.getClass()==Damager.class){
			p1.setImage("images/hero/hero_attack.png");
		}
		if(p1.getClass()==Healer.class){
			p1.setImage("images/unicorn/unicorn_sp_r.png");
		}
		if(p1.getClass()==Tank.class){
			p1.setImage("images/ent/ent_run_r.png");
		}
		int deg =p2.getLifePoints();
		
		p1.attack(p2);
		if(!ai.getLevel()){
			int n=0;
			do{
				n = ai.randomAction(p1, p2, p2DidSpe);
			}while(p2DidSpe==true &&  n==3);
			if(n==2){
				p2.setLifePoints(deg);
			}
			if(n==3 && p2.getClass()==Damager.class && p2DidSpe==false){
				p2.speAttack(p1, deg-p2.getLifePoints());
			}
			if(n==3){
				p2DidSpe=true;
			}
		}
		
		
	}

	public void showKey(){
		System.out.println(Key);
	}
	
	public void sleep3sec(){
		try {
			TimeUnit.MILLISECONDS.sleep(500);
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
	public void toFix(Character p){
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
	public void toFixAI(Character p){
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
