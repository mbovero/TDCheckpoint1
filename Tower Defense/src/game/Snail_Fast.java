/**
 * This class creates and allows for interactions and use of a Fast Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 20, 2022
 */
package game;

import java.awt.*;

public class Snail_Fast extends Enemy{

    //Constructor
    public Snail_Fast(State state, Control control)
    {
        super(state, control);
        healthSubtract = -2;
        velocity = 0.1;
        spriteFile = "snail_fast.png";
    }
}
