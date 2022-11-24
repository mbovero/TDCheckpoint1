/**
 * This class creates and allows for interactions and use of a Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 20, 2022
 */
package game;

import java.awt.*;

public class Snail_Fast extends GameObject{

    private double percentage;

    //Constructor
    public Snail_Fast(State state, Control control)
    {
        percentage = 0;
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
    }

    /**
     * If the Snail object is at the end of the path, this method updates
     * the current frame state of the player's health, the visibility of the
     * Snail object, and the expired value of the Snail object. Regardless, 
     * the percentage which the Snail object is along the path is increased.
     *
     * @param elapsedTime
     */
	@Override
	public void update(double elapsedTime) 
	{
        if (percentage >= 1)
        {
        	isExpired = true;
        	isVisible = false;
        	state.changeHealth(-1);								// Remove health
            if (!state.gameOver)
                state.addGameObject(new Snail_Fast(state, control));  	// Add another enemy
            //System.out.println(state.health);					// Testing for properly subtracting health
        }
		
		percentage += 0.1 * elapsedTime;
	}

    /**
     * Method that draws the Snail at a certain point along the path.
     *
     * @param g graphics object to draw onto
     */
    @Override
	public void draw(Graphics g) 
	{
        Point loc = control.getPath().convertToCoordinates(percentage);
        g.drawImage(control.loadImage("snail_fast.png"), loc.x, loc.y, null);
    }
}
