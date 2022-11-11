package game;

import javax.swing.JPanel;

public class View extends JPanel {

	Control control;
	State state;
	
	public View(Control control, State state)
	{
		this.control = control;
		this.state = state;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
