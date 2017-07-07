package controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.Damager;
import model.Healer;
import model.Tank;
import model.Character;
import view.Window;

public class GameLoop {

	private Window win = new Window(); // We create a new JFrame
	private AI ai = new AI(); //We create a new AI Sytem.
	
	private Character p1; // P1 is the Player, P2 will be controled by the AI
	private Character p2;
	
	private int previousKey=1; //int to launche the method only once after a keystroke
	private boolean hasDoneSpe=false; //see if p1 has done SpeAttack
	private boolean p2hasDoneSpe = false;//see if p2 has done SpeAttack
	
	private int chooseEnd=0; //different end in fuction of this int
	private boolean menuDisplayed=false; //See if menu is displayed
	private boolean authosLifeBar=true; //boolean to update after a game the surroundings of the lifeBars
	
	public GameLoop() throws IOException {
		this.loop();
		
	}
	public void loop() throws IOException{ //Calling in loop the methods
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
		
		Menu men= new Menu(); //create a new menu panel
		if (!menuDisplayed){//THE MENU IS DISPLAYED ONCE
			
			win.setContentPane(men); //set on the window the menu panel
			men.addtitle();	 // add a title
			men.addButtons(); // add buttons
			
			menuDisplayed=true; 
			
			win.setVisible(true);
			men.repaint(); 
		}
		if(men.getCharChosen()!=0){ //once the played has done his choice on the menu
			win.setContentPane(win.getWP());
			//Until AI extreme coded
				p2 = ai.characterChosen(); //P2 picks a random character
				if(Menu.randAi){
					Random rand = new Random(); //Make a random number to have a random AI
					int r = rand.nextInt(2)+1;
					if(r==1){
						ai.setLevel(true);
					}
					else{
						ai.setLevel(false);
					}
				}
				else{
					ai.setLevel(Menu.extreme); //set level of ai (extreme or not)
				}
				
			//EndUntil
				if(men.getCharChosen()==1){ //creates p1 depending on the button activated
					p1 = new Damager(315,350);	
				}
				if(men.getCharChosen()==2){
					p1 = new Tank(315,375);	
				}
				if(men.getCharChosen()==3){
					p1 = new Healer(225,330);	
				}
			continuer=false;//breaks the menu loop
			menuDisplayed=false;//menu isn't diplayed
			men.setCharChosen(0);//reset the number of the char chosen to reload the menu after a game
			win.setVisible(true);
			win.getWP().repaint();
			authosLifeBar=true;
		}
		
		return continuer;
		
	}
	public boolean game(){ //GAME LOOP
		boolean continuer=true;
		if(authosLifeBar){ //done only one time, for the surrounding of life bar, initialize them
			win.getWP().setAuthos(true, true);
			authosLifeBar=false;
		}
		win.getWP().clearTable(); //clears the content of a table that ahs the caracter to display
		if (win.getDetKey().getKey() != previousKey && !(win.getDetKey().getKey()==69 && hasDoneSpe==true)){//detects a key, and prevent from activating 2 times the SpeAttack key
			if(win.getDetKey().getKey()==69){
				hasDoneSpe = true;
			}
			
			Event combatEvent = new Event(win.getDetKey().getKey(), ai, win, p1, p2, p2hasDoneSpe, hasDoneSpe); // creates an event 

			if(!p2hasDoneSpe){
				p2hasDoneSpe = combatEvent.getp2DidSpe();//sees if p2 has done it's speAttack
			}
			previousKey = win.getDetKey().getKey(); 
		}
		
		if(p1.getLifePoints()<=0 && p2.getLifePoints()<=0){ //choose end  TIE SITUATION
			chooseEnd=1;
			continuer = false;
		}
		else if(p1.getLifePoints()<=0){ //LOOSE SITUATION
			chooseEnd=2;
			continuer = false;
		}
		else if(p2.getLifePoints()<=0){ //WIN SITUATION
			chooseEnd=3;
			continuer = false;
		}
		win.getWP().add(p1);
		win.getWP().add(p2); //we add p1 and p2 to be displayed
		win.getWP().repaint();
		
		if(p1.getLifePoints()<=0 || p2.getLifePoints()<=0) //sleep to see the dead sprites :'D
			this.sleepTwo();
		return continuer;
		
	}
	@SuppressWarnings("unused")
	public boolean end() throws IOException{
		boolean continuer=true;
		
			win.getWP().setEnd(chooseEnd);//display the coorect ending image
			win.getWP().repaint();
			if(win.getDetKey().getKey()==32){ //IF spacebar is hit it reinitialize all objects/attributes
				win.getWP().setEnd(0);
				chooseEnd=0;
				ai = new AI();
				Character p1=null;
				Character p2=null;
				previousKey=1;
				hasDoneSpe=false;
				p2hasDoneSpe = false;
				Menu.extreme=false;
				Menu.randAi=false;
				continuer=false;
			}
			
		return continuer;
	}
	
	public void sleepThree(){ //little wait, to prevent the CPU from exploding
		
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sleepTwo(){
		try {
			TimeUnit.MILLISECONDS.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
