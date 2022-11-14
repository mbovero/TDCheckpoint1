/**
 * This class creates and allows for interactions and use of a Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.Graphics;

public class Snail extends GameObject{

    private double percentage;
    private int test;  // Remove this later
    
    public Snail() 
    {
        percentage = 0;
        test = 100;  // Remove this later
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
        test++;	
	}

	@Override
	public void draw(Graphics g, Control control) 
	{
        g.drawImage(control.getImage("snail.png"), test, test, null);
	}

}
