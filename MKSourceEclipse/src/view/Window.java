package view;



import javax.swing.JFrame;

import controller.DetectKey;


@SuppressWarnings("serial")
public class Window extends JFrame{ // creation of a new window

	private DetectKey detKey = new DetectKey(); //creation of the keydetection
	private WinPanel wp;
		
	public Window() {
		wp = new WinPanel(); //new panel
	    this.setTitle("MentalKombat");
	    this.setSize(1024, 618); //the window size
	    this.setResizable(false); //not resizable 
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.addKeyListener(detKey); // add the key listener
	    this.setFocusable(true);
	    this.add(wp); //add the panel
	}
	
	public DetectKey getDetKey(){
		return detKey;
	}
	
	public WinPanel getWP(){
		return wp;
	}

}
