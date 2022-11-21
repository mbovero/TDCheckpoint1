/**
 * This is an abstract class that is used to set up GameObject objects. It provides
 * the GameObject objects the required methods.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.Graphics;

abstract public class GameObject {
	
    protected boolean isVisible; 
    protected boolean isExpired;
    
	protected State state;
	protected Control control;

    public boolean isVisible() { return isVisible; }
    public boolean isExpired() { return isExpired; }

    abstract public void update (double elapsedTime);
    abstract public void draw (Graphics g);
	
}
