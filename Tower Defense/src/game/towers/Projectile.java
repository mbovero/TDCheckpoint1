/**
 * This superclass creates and allows for interactions and use of projectile objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.GameObject;
import game.State;
import game.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Projectile extends GameObject
{
    private double xVelocity;                   // The projectile's x velocity
    private double yVelocity;                   // The projectile's y velocity
    protected double xPos;                      // The projectile's x position
    protected double yPos;                      // The projectile's y position
    protected double speed;                     // The speed multiplied with the velocities
    private double distance;                    // Distance to the enemy
    private double xIncrement;                  // The x distance to be traveled each frame
    private double yIncrement;                  // The x distance to be traveled each frame
    protected double killRange;                 // The range in which the projectile will collide with an enemy
    protected String spriteFile;                // The name of the file to be used as the enemy's sprite
    private Enemy target;                       // The enemy at which the projectile was shot
    protected int damage;

    // Constructor
    public Projectile (State state, Control control, int x, int y)
    {
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
        xPos = x;
        yPos = y;
        target = state.findNearestEnemy(new Point(x, y));   // Store nearest enemy
        xVelocity = (target.getPosition().x - x);    //gets the difference in x to the enemy
        yVelocity = (target.getPosition().y - y);    //gets the difference in y to the enemy
        distance = Math.sqrt((xVelocity*xVelocity)+(yVelocity*yVelocity));
        xIncrement = xVelocity * (1/distance);
        yIncrement = yVelocity * (1/distance);
    }

    /**
     * A method that removes the projectile once it leaves the
     * game area, updates its position, and allows for collisions
     * with enemies.
     *
     * @param elapsedTime the time elapsed since the last frame
     */
    @Override
    public void update(double elapsedTime)
    {
        if (xPos > 600 || yPos > 600 ||
                xPos < 0 || yPos < 0)
        {
            this.isExpired = true;
            this.isVisible = false;
        }
        // Homing projectile
//        xVelocity = (target.getPosition().x - xPos);
//        yVelocity = (target.getPosition().y - yPos);

        xPos += xIncrement * speed;
        yPos += yIncrement * speed;

        // Kill any enemy within range
        collide();
    }

    /**
     *  A method that determines the conditions to, and
     *  result of, colliding with an enemy.
     */
    public void collide()
    {
        Enemy e = state.findNearestEnemy(new Point((int)xPos, (int)yPos));
        if (e != null)
        {
            double distance = Math.sqrt(Math.pow((e.getPosition().x - xPos), 2) + Math.pow((e.getPosition().y - yPos), 2));
            if (distance < killRange) {
                this.isExpired = true;
                this.isVisible = false;
                effect(e);
            }
        }
    }

    /**
     * A method that draws the projectile as it moves.
     *
     * @param g graphics object to draw with
     */
    @Override
    public void draw(Graphics g)
    {
        BufferedImage image = control.loadImage(spriteFile);
        g.drawImage(image, (int)xPos-(image.getWidth()/2), (int)yPos-(image.getHeight()/2), null);
    }

    /**
     * Abstract method lets the specific Projectile object interact
     * with the Enemy object in an individual way
     *
     * @param e enemy to effect
     */
    abstract public void effect (Enemy e);
}
