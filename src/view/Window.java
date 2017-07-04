package view;



import javax.swing.JButton;
import javax.swing.JFrame;

import controller.DetectKey;


@SuppressWarnings("serial")
public class Window extends JFrame{

	private DetectKey detKey = new DetectKey();
	private WinPanel wp;
	//private JButton jb = new JButton("yolo");
	public Window() {
		wp = new WinPanel();
	    this.setTitle("MentalKombat");
	    this.setSize(1024, 618);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    this.addKeyListener(detKey);
	    this.setFocusable(true);
	    this.add(wp);
	   // wp.add(jb);
	}
	
	public DetectKey getDetKey(){
		return detKey;
	}
	
	public WinPanel getWP(){
		return wp;
	}

}
