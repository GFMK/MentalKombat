package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Character;



@SuppressWarnings("serial")
public class WinPanel extends JPanel{

	private ArrayList<Character> characters = new ArrayList<Character>(); //an arrayList to store the characters
	private boolean autho = true;
	private boolean autho2 = true; //booleans used to have a fixed surrounding for the lifeBar, they have to update when a game is played again
	private int glp = 0;
	private int glp2 = 0; //ints to get Life points of p1 and p2
	private int end=0; //to display the ending scene
	
	public WinPanel() {

	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image img;
			img = ImageIO.read(new File("images/background.jpg"));// display the background img
			g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

			float thickness = 3;
			g2d.setStroke(new BasicStroke(thickness)); //thickness of the lifeBar surroundings
			
			for (int i=0; i<characters.size(); i++){ //loop to display the characters
				Character tempPlayer = characters.get(i);
				g2d.drawImage(tempPlayer.getImage(), tempPlayer.getX(), tempPlayer.getY(), this);
				
				if(i==0){
					if(autho){
						glp=tempPlayer.getLifePoints(); //for p1 takes one time the amount of lifePoints for the surrounding of the lifeBar
						autho=false;
					}
					g2d.setColor(Color.red);
					g2d.fillRoundRect(110, 206, 50*tempPlayer.getLifePoints(), 30, 5, 5);//X; Y; Width; height; acrw; arch && draws the lifeBar
					g2d.setColor(Color.DARK_GRAY);
					g2d.drawRoundRect(110, 206, 50*glp, 30, 5, 5); //draws the surrounding of the lifeBar
				}
				if(i==1){ //same for p2
					if(autho2){
						glp2=tempPlayer.getLifePoints();
						autho2=false;
					}
					g2d.setColor(Color.red);
					g2d.fillRoundRect(642, 206, 50*tempPlayer.getLifePoints(), 30, 5, 5);
					g2d.setColor(Color.DARK_GRAY);
					g2d.drawRoundRect(642, 206, 50*glp2, 30, 5, 5);
				}
			}
			
			if(end==1){ //depending on the end, it draws the different imgs
				img = ImageIO.read(new File("images/end/tie.jpg"));
				g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); 
				
			}
			if(end==2){
				img = ImageIO.read(new File("images/end/game_over.png"));
				g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			if(end==3){
				img = ImageIO.read(new File("images/end/gg.jpg"));
				g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
	}


	public void add(Character p) { //add a character int the arrayList
		characters.add(p);
		
	}
	public void clearTable(){//clears the ArrayList ! Let's not make a buffer overflow ;-)
		characters.clear();
	}
	public void setEnd(int e){
		end=e;
	}
	public void setAuthos(boolean a1, boolean a2){
		autho=a1;
		autho2=a2;
	}

	
	
	

}