/**
 * This class creates and allows for interactions and use of a Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.enemies;

import game.Control;
import game.State;

import java.awt.Graphics;
import java.awt.Point;

public class Snail extends Enemy {

    //Constructor
    public Snail(State state, Control control) {
        super(state, control);
        healthSubtract = -1;
        velocity = 1.0/15;          // Percentage along path / total seconds (reaches end in 15 seconds)
        spriteFile = "snail.png";
        moneyReward = 5;
        scoreReward = 1;
    }
}
