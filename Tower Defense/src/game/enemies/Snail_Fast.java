/**
 * This class creates and allows for interactions and use of a Fast Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.enemies;

import game.Control;
import game.State;

import java.awt.*;

public class Snail_Fast extends Enemy{

    //Constructor
    public Snail_Fast(State state, Control control)
    {
        super(state, control);
        healthSubtract = -2;
        velocity = 1.0/10;
        spriteFile = "snail_fast.png";
        moneyReward = 10;
        scoreReward = 1;

    }
}
