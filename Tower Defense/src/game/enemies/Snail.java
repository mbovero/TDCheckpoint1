/**
 * This class creates and allows for interactions and use of a Snail object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 20, 2022
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
        velocity = 0.05;
        spriteFile = "snail.png";
    }
}
