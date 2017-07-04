package controller;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import view.Window;

public class Menu {

	
	private Window win;
	public Menu(Window win) {
		this.win=win;
	}
	
	public void addButtons(){
		//JRadioButton jrb = new JRadioButton();
		JButton bouton = new JButton("sdddddddddddddddddddddddddddddddddddddddddd");
		win.getWP().add(bouton);
		win.setContentPane(win.getWP());
		win.setVisible(true);
		win.getWP().repaint();	
	}
}
