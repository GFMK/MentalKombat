package controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import model.Damager;
import model.Healer;
import model.Tank;
import model.Character;
import view.Window;

public class GameLoop {

	private Window win = new Window();
	private AI ai = new AI();
	
	private Character p1;
	private Character p2;
	
	private int previousKey=1;
	private boolean hasDoneSpe=false;
	private boolean p2hasDoneSpe = false;
	
	private int chooseEnd=0;
	private boolean menuDisplayed=false;
	private boolean authosLifeBar=true;
	
	public GameLoop() throws IOException {
		this.loop();
		
	}
	public void loop() throws IOException{
		while(true){//GENERAL
			while(true){//MENU LOOP
				this.sleepThree();
				if(!this.menu()){
					break;
				}
			}
			
			while(true){//GAMELOOP
				this.sleepThree();
				if(!this.game()){
					break;
				}
			}
			while(true){//END
				this.sleepThree();
				if(!this.end()){
					break;
				}
			}
			this.sleepThree();
		}
	}
	public boolean menu() throws IOException{
		boolean continuer=true;
		
		win.getWP().setAuthos(true, true);
		Menu men= new Menu(win);
		if (!menuDisplayed){
			
			win.setContentPane(men);
			men.addtitle();	
			men.addButtons();
			
			menuDisplayed=true;
			
			win.setVisible(true);
			men.repaint();
		}
		if(men.getCharChosen()!=0){
			win.setContentPane(win.getWP());
			//Until AI extreme coded
				p2 = ai.characterChosen();
				ai.setLevel(false);
			//EndUntil
				if(men.getCharChosen()==1){
					p1 = new Damager(300,350);	
				}
				if(men.getCharChosen()==2){
					p1 = new Tank(300,350);	
				}
				if(men.getCharChosen()==3){
					p1 = new Healer(300,350);	
				}
			continuer=false;
			menuDisplayed=false;
			men.setCharChosen(0);
			win.setVisible(true);
			win.getWP().repaint();
			authosLifeBar=true;
		}
		
		return continuer;
		
	}
	public boolean game(){
		boolean continuer=true;
		if(authosLifeBar){
			win.getWP().setAuthos(true, true);
			authosLifeBar=false;
		}
		win.getWP().clearTable();
		if (win.getDetKey().getKey() != previousKey && !(win.getDetKey().getKey()==69 && hasDoneSpe==true)){
			if(win.getDetKey().getKey()==69){
				hasDoneSpe = true;
			}
			
			Event combatEvent = new Event(win.getDetKey().getKey(), ai, win, p1, p2, p2hasDoneSpe);
			combatEvent.showKey();
			if(!p2hasDoneSpe){
				p2hasDoneSpe = combatEvent.getp2DidSpe();
			}
			previousKey = win.getDetKey().getKey(); 
		}
		
		if(p1.getLifePoints()<=0 && p2.getLifePoints()<=0){
			chooseEnd=1;
			continuer = false;
		}
		else if(p1.getLifePoints()<=0){
			chooseEnd=2;
			continuer = false;
		}
		else if(p2.getLifePoints()<=0){
			chooseEnd=3;
			continuer = false;
		}
		win.getWP().add(p1);
		win.getWP().add(p2);
		win.getWP().repaint();
		return continuer;
		
	}
	public boolean end() throws IOException{
		boolean continuer=true;
		
			win.getWP().setEnd(chooseEnd);
			win.getWP().repaint();
			if(win.getDetKey().getKey()==32){
				win.getWP().setEnd(0);
				chooseEnd=0;
				ai = new AI();
				Character p1=null;
				Character p2=null;
				previousKey=1;
				hasDoneSpe=false;
				p2hasDoneSpe = false;
				continuer=false;
			}
			
		return continuer;
	}
	
	public void sleepThree(){
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	
}
