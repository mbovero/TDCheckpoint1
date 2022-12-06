/**
 * This superclass creates and allows for interactions and use of enemy objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.enemies;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Enemy extends GameObject {

    protected int health;
    protected double percentage;                // The percentage the enemy is along the path
    protected double velocity;                  // The used velocity or speed
    protected double originalvelocity;          // The original velocity or speed (for upgrades)
    protected int healthSubtract;               // The amount of health subtracted when the enemy reaches the end of the path
    protected int scoreReward;                  // The score added when the enemy is defeated
    protected int moneyReward;                  // The amount of money added when the enemy is defeated
    protected String spriteFile;                // The name of the file to be used as the enemy's sprite
    protected String[] effects = new String[3]; // Holds space for effects to be given and current effects placed on the object
    protected double[] effectDuration = new double[3];  //The individual lengths of time the effect lasts

    protected double[] effectedTime = new double[3];    //The individual times when the effect started

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
     * @param elapsedTime the time elapsed since the last frame
     */
    @Override
    public void update(double elapsedTime)
    {
        //Checking if the first effect duration is over
        if (effectDuration[0] < 0) {effects[0] = null;}
        //Checking if the first effect interval has taken place
        else if (effectDuration[0] > 0 && System.currentTimeMillis()-effectedTime[0] > 1) {
            health -= 3;
            effectedTime[0] = System.currentTimeMillis();
            effectDuration[0] -= 1;
        }

        //Checking if the second effect duration is over
        if (effectDuration[1] < 1 && !(effects[1] == null)) {
            velocity = originalvelocity;
            effects[1] = null;
        }
        //Checking if the second effect interval has taken place
        else if (effectDuration[1] > 1 && System.currentTimeMillis()-effectedTime[0] > 1) {effectDuration[1] -= 1;}

        //Checking if the third effect duration is over
        if (effectDuration[2] < 0) {effects[2] = null;}
        //Checking if the third effect interval has taken place
        else if (effectDuration[2] > 0 && System.currentTimeMillis()-effectedTime[1] > 1) {
            if (effects[2].equals("on_fire.png")) {     //Checking the third version of the second effect
                health -= 6;
                effectedTime[2] = System.currentTimeMillis();
                effectDuration[2] -= 1;
            }
            if (effects[2].equals("red_fire.png")) {    //Checking the third version of the second effect
                health -= 15;
                effectedTime[2] = System.currentTimeMillis();
                effectDuration[2] -= 1;
            }
        }

        if (percentage >= 1)                                // At the end of the path:
        {
            isExpired = true;                               // Queue to remove entity
            isVisible = false;                              // Remove visibility
            state.changeHealth(healthSubtract);				// Decrease health
        }
        if (health < 0) {
            state.changeScore(scoreReward);
            state.changeMoney(moneyReward);
            isExpired = true;
            isVisible = false;
        }
        percentage += velocity * elapsedTime;               // Move enemy
    }

    /**
     * A method that draws the enemy and its effects at a certain point along the path.
     *
     * @param g graphics object to draw with
     */
    @Override
    public void draw(Graphics g)
    {
        Point loc = control.getPath().convertToCoordinates(percentage);
        BufferedImage image = control.loadImage(spriteFile);
        g.drawImage(image, loc.x-(image.getWidth()/2), loc.y-(image.getHeight()/2), null);
        for (String effect : effects) {
            if (effect != null) {
                BufferedImage effect_image = control.loadImage(effect);
                g.drawImage(effect_image, loc.x - (image.getWidth() / 2), loc.y - (image.getHeight() / 2), null);
            }
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

    /**
     * Method that sets the enemy's isExpired variable.
     *
     * @param b a boolean value that determines whether the object is expired
     */
    public void setExpiration (boolean b)
    {
        this.isExpired = b;
    }

    /**
     * Method that sets the enemy's isVisible variable.
     *
     * @param b a boolean value that determines whether the object is visible
     */
    public void setVisibility (boolean b)
    {
        this.isVisible = b;
    }

    /**
     * Method that returns the score awarded for defeating the enemy.
     *
     * @return a score in the form of an integer
     */
    public int getScoreReward () {return scoreReward;}

    /**
     * Method that returns the money awarded for defeating the enemy.
     *
     * @return an amount of money in the form of an integer
     */
    public int getMoneyReward () {return moneyReward;}

    /**
     * Method that changes the enemy's velocity.
     *
     * @param d a number to be multiplied by the enemy's current velocity
     */
    public void changeVelocity (double d) {this.velocity *= d;}

    /**
     * This method allows for projectiles to give the Enemy object a
     * visual effect when collision occurs.
     *
     * @param effect the visual effect to be given to the Enemy Object
     * @param place the position in the effect list to be drawn
     */
    public void setEffect (String effect, int place, double duration)
    {
        effects[place] = effect;
        if (effectDuration[place] != 0) {effectDuration[place] = duration;}
        effectedTime[place] = System.currentTimeMillis();
    }

    /**
     * This method changes the current enemy's health by some amount given
     *
     * @param amount of health to give or take from this enemy object
     */
    public void changeHealth (int amount) {health += amount;}
}
