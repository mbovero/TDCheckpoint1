package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {

	Control control;
	State state;
	
	public View(Control control, State state)
	{
		this.control = control;
		this.state = state;
		
        JFrame f = new JFrame("Miles's & Kirt's Tower Defense 2022");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.setContentPane(this);
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(this.getMinimumSize());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect(150, 150, 150, 150);
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
