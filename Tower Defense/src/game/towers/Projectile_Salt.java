/**
 * This superclass creates and allows for interactions and use of
 * salt projectiles that damage enemies on impact.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Projectile_Salt extends Projectile
{
    // Constructor
    public Projectile_Salt(State state, Control control, int x, int y)
    {
        super(state, control, x, y);
        this.killRange = 50;
        this.spriteFile = "salt_crystals.png";
        this.speed = 10;
    }
}
