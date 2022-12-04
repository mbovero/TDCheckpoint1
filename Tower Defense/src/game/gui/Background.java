/**
 * This class creates and allows for interactions and use of a Background object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @Version December 3, 2022
 */
package game.gui;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.Graphics;

public class Background extends GameObject {

	//Constructor
	public Background (State state, Control control)
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
        g.drawImage(control.loadImage("path_2a.jpg"), 0, 0, null);
	}

}
