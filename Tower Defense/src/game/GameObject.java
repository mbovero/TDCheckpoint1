/**
 * This is an abstract class that is used to set up Game Objects. It provides
 * the GameObject objects with the required methods.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game;

import java.awt.Graphics;

abstract public class GameObject {
	
    protected boolean isVisible;                        // Whether the enemy is visible or not
    protected boolean isExpired;                        // Whether the enemy has expired or not (and will be removed)
    
	protected State state;
	protected Control control;

    /**
     * Method that returns the object's current visibility.
     *
     * @return the boolean value determining the object's visibility
     */
    public boolean isVisible() { return isVisible; }

    /**
     * Method that returns whether the object has expired or not.
     *
     * @return the boolean value determining the object's expiration
     */
    public boolean isExpired() { return isExpired; }

    /**
     * A method that updates the current state and qualities
     * of the object.
     *
     * @param elapsedTime the time elapsed since the last frame
     */
    abstract public void update (double elapsedTime);

    /**
     * A method that draws the object within the window.
     *
     * @param g graphics object to draw with
     */
    abstract public void draw (Graphics g);
	
}
