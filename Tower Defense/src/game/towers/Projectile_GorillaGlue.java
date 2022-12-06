/**
 * This superclass creates and allows for interactions and use of
 * glue projectiles that slow enemies on impact.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.State;
import game.enemies.Enemy;

import java.awt.*;

public class Projectile_GorillaGlue extends Projectile
{
    // Constructor
    public Projectile_GorillaGlue(State state, Control control, int x, int y)
    {
        super(state, control, x, y);
        killRange = 50;
        spriteFile = "glue.png";
        speed = 7;
        damage = 0;
    }


    @Override
    public void effect(Enemy e)
    {
        e.changeVelocity(.5);
        e.setEffect("gorilla_glued.png", 1, 0);
    }
}
