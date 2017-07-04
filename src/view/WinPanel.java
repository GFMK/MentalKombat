package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Character;



@SuppressWarnings("serial")
public class WinPanel extends JPanel{

	private ArrayList<Character> characters = new ArrayList<Character>();
	private boolean autho = true;
	private boolean autho2 = true;
	private int glp = 0;
	private int glp2 = 0;
	private int end=0;
	
	public WinPanel() {

	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			Image img;
			img = ImageIO.read(new File("images/background.jpg"));
		//	g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			

			
			float thickness = 3;
			g2d.setStroke(new BasicStroke(thickness));
			
			for (int i=0; i<characters.size(); i++){
				Character tempPlayer = characters.get(i);
				g2d.drawImage(tempPlayer.getImage(), tempPlayer.getX(), tempPlayer.getY(), this);
				if(i==0){
					if(autho){
						glp=tempPlayer.getLifePoints();
						autho=false;
					}
					g2d.setColor(Color.red);
					g2d.fillRoundRect(110, 206, 50*tempPlayer.getLifePoints(), 30, 5, 5);//X; Y; Width; height; acrw; arch
					g2d.setColor(Color.DARK_GRAY);
					g2d.drawRoundRect(110, 206, 50*glp, 30, 5, 5);
				}
				if(i==1){
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
			
			if(end==1){
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


	public void add(Character p) {
		characters.add(p);
		
	}
	public void clearTable(){
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