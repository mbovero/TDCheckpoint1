/**
 * This class holds the information for interacting with and displaying
 * the Tower_Salt object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November, 20, 2022
 */
package game;

import game.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tower_Salt extends GameObject implements Clickable
{

    boolean isMoving;
    int x;
    int y;

    private long lastProjectileFired = 0;

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

        if (!isMoving && (state.totalTime - lastProjectileFired) >= 3)           //Shoot salt every 3 seconds
        {
            Enemy e = state.findNearestEnemy(new Point(x, y));
            if (e != null)
            {
                state.addGameObject(new FlyingSalt(state, control, x, y, e));
                lastProjectileFired = state.totalTime;
                System.out.println("Shot fired");
            }
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
        BufferedImage image = control.loadImage("salt.png");
        g.drawImage(image, x-(image.getWidth()/2), y-(image.getHeight()/2), null);
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
        if (isMoving &&
            mouseX < 600 && mouseY < 600)
        {
            state.changeMoney(-100);

            isMoving = false;
            return true;
        }
        return false;
    }
}
