package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DetectKey implements KeyListener { //classic keylistening class
	
	private int key;
	
	public DetectKey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		key = arg0.getKeyCode(); //get the keycode in an int to interact with it.
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		key=0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	public int getKey(){
		return key;
	}

}
