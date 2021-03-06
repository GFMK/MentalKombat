package controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel{

	static int charChosen=0;
	static boolean extreme=false; 
	static boolean randAi=false;
	
	public Menu() {
		
		
	}
	public void paintComponent(Graphics g)
	{
		Image img;
		try {
			img = ImageIO.read(new File("images/menu_img.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addtitle(){
		Font font = new Font("Snap ITC", Font.BOLD, 50);
		JLabel titre = new JLabel();
		titre.setForeground(Color.red);
		titre.setFont(font); 
		titre.setText("Welcome to MentalKombat!");
		this.add(titre);
	}

	public void addButtons(){
		
		JButton boutonD = new JButton("Damager");
		JButton boutonH = new JButton("Healer");
		JButton boutonT = new JButton("Tank");
		JCheckBox extreme = new JCheckBox("AI extreme level");
		JCheckBox randomAi = new JCheckBox("Random AI level");
		
		Box b1 = Box.createHorizontalBox();
		Box b0 = Box.createHorizontalBox();
		Box b2 = Box.createVerticalBox();

		b0.add(extreme);
		b0.add(randomAi);
		b1.add(boutonD);
		b1.add(boutonT);
		b1.add(boutonH);
		b2.add(b0);
		b2.add(b1);
		this.add(b2);
		
		boutonD.addActionListener(new boutonDListener());
		boutonT.addActionListener(new boutonTListener());
		boutonH.addActionListener(new boutonHListener());
		extreme.addActionListener(new tickListener());
		randomAi.addActionListener(new tickListener2());
	}
	public boolean getExtreme(){
		return extreme;
	}
	public int getCharChosen(){
		return charChosen;
	}
	public void setCharChosen(int number){
		charChosen=number;
	}
}

class boutonDListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("DAMAGER CHOSEN");
		Menu.charChosen = 1;
	}
	
}
class boutonTListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("TANK CHOSEN");
		Menu.charChosen = 2;
	}
	
}
class boutonHListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("HEALER CHOSEN");
		Menu.charChosen = 3;
	}
	
}
class tickListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {

			if(Menu.extreme){
				Menu.extreme=false;
			}
			else{
				Menu.extreme=true;
			}
		System.out.println("AI MODE : " + Menu.extreme);
	}
}
class tickListener2 implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
			if(Menu.randAi){
				Menu.randAi=false;
			}
			else{
				Menu.randAi=true;
			}
		System.out.println("MODE RAND :" + Menu.randAi);
	}
}
