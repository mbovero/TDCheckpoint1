/**
 * This class creates and allows for interactions and use of a Background object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @Version November 14, 2022
 */
package game;

import java.awt.Graphics;

public class Background extends GameObject{

	public Background () 
	{
        isVisible = true;
        isExpired = false;
	}
	
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g, Control control) 
	{
        g.drawImage(control.getImage("path_2a.jpg"), 0, 0, null);	
	}

}
