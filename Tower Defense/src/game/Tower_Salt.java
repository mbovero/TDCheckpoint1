/**
 * This class holds the information for interacting with and displaying
 * the Tower_Salt object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November, 20, 2022
 */
package game;

import java.awt.*;

public class Tower_Salt extends GameObject implements Clickable
{

    boolean isMoving;
    int x;
    int y;

    //Constructor
    public Tower_Salt(State state, Control control, boolean isMoving)
    {
        isVisible = true;
        isExpired = false;
        this.isMoving = isMoving;

        this.state = state;
        this.control = control;
    }

    /**
     * Method that stores the new x and y coordinates of the
     * mouse on screen and moves the tower accordingly.
     *
     * @param elapsedTime
     */
    @Override
    public void update(double elapsedTime)
    {
        if (isMoving)
        {
            x = control.getMouseX();
            y = control.getMouseY();
        }
    }

    /**
     * Method that draws the Tower_Salt object.
     *
     * @param g a graphics object to draw onto
     */
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(control.loadImage("salt.png"), x, y, null);
    }

    /**
     * Method that allows for the placement of the tower by updating the
     * isMoving value to false when it is true.
     *
     * @param mouseX the mouse's x position
     * @param mouseY the mouse's y position
     * @return boolean value that states if the object is moving
     */
    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (isMoving)
        {
            isMoving = false;
            return true;
        }
        return false;
    }
}
