/**
 * This class creates and allows for interactions and use of a Background object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @Version November 20, 2022
 */
package game.gui;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;

public class GameOver extends GameObject {

	//Constructor
	public GameOver(State state, Control control)
	{
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
	}

	//Unused method
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method that draws the background image.
	 *
	 * @param g a graphics object to draw onto
	 */
	@Override
	public void draw(Graphics g) 
	{
		g.setColor(Color.black);
        g.fillRect(25, 175, 550, 200);
		g.setColor(Color.white);
		g.setFont(new Font("SansSerif", Font.BOLD, 93));
		g.drawString("Game Over", 50, 300);
	}

}
