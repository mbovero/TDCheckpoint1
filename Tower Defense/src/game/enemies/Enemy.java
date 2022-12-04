/**
 * This superclass creates and allows for interactions and use of enemy objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.enemies;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Enemy extends GameObject {

    protected double percentage;                // The percentage the enemy is along the path
    protected double velocity;                  // The velocity or speed
    protected int healthSubtract;               // The amount of health subtracted when the enemy reaches the end of the path
    protected int scoreReward;                  // The score added when the enemy is defeated
    protected int moneyReward;                  // The amount of money added when the enemy is defeated
    protected String spriteFile;                // The name of the file to be used as the enemy's sprite
    protected String[] effects = new String[2]; // Holds space for effects to be given and current effects placed on the object

    public Enemy (State state, Control control)
    {
        percentage = 0;
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
    }

    /**
     * If the enemy is at the end of the path, this method updates
     * the current frame state of the player's health, the visibility
     * of the enemy, and the expired value of the enemy. Regardless,
     * the percentage which the enemy is along the path is increased.
     *
     * @param elapsedTime
     */
    @Override
    public void update(double elapsedTime)
    {
        if (percentage >= 1)                                // At the end of the path:
        {
            isExpired = true;                               // Queue to remove entity
            isVisible = false;                              // Remove visibility
            state.changeHealth(healthSubtract);				// Decrease health
        }
        percentage += velocity * elapsedTime;               // Move enemy
    }

    /**
     * Method that draws the enemy and its effects at a certain point along the path.
     *
     * @param g graphics object to draw onto
     */
    @Override
    public void draw(Graphics g)
    {
        Point loc = control.getPath().convertToCoordinates(percentage);
        BufferedImage image = control.loadImage(spriteFile);
        g.drawImage(image, loc.x-(image.getWidth()/2), loc.y-(image.getHeight()/2), null);
        for (int i=0; i<effects.length; i++)
            {
            if (effects[i] != null);
                BufferedImage effect_image = control.loadImage(effects[i]);
                g.drawImage(effect_image, loc.x-(image.getWidth()/2), loc.y-(image.getHeight()/2), null);
            }
    }

    /**
     * Method that returns the enemy's current location along the path.
     *
     * @return a Point object containing the object's x & y position
     */
    public Point getPosition ()
    {
        return control.getPath().convertToCoordinates(percentage);
    }

    public void setExpiration (boolean b)
    {
        this.isExpired = b;
    }

    public void setVisibility (boolean b)
    {
        this.isVisible = b;
    }

    public int getScoreReward () {return scoreReward;}

    public int getMoneyReward () {return moneyReward;}

    public void changeVelocity (double d) {this.velocity *= d;}

    /**
     * This method allows for projectiles to give the Enemy object a
     * visual effect when collision occurs.
     *
     * @param effect the visual effect to be given to the Enemy Object
     * @param place the position in the effect list to be drawn
     */
    public void setEffect (String effect, int place)
    {
        effects[place] = effect;
    }
}
