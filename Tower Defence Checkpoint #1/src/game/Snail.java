/**
 * This class creates and allows for interactions and use of a Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

public class Snail extends GameObject{

    private double percentage;
    
    public Snail() 
    {
        percentage = 0;
        isVisible = true;
        isExpired = false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(double elapsedTime) 
	{
        percentage += 0.001;
	}

	@Override
	public void draw(Graphics g, Control control) 
	{
        Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.getImage("snail.png"), loc.x, loc.y, null);	
    }

}
