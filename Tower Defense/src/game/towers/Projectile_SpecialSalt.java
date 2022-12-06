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
import game.enemies.Enemy;

public class Projectile_SpecialSalt extends Projectile
{
    // Constructor
    public Projectile_SpecialSalt(State state, Control control, int x, int y)
    {
        super(state, control, x, y);
        killRange = 50;
        spriteFile = "salt_crystals.png";
        speed = 10;
        damage = 7;
    }

    @Override
    public void effect(Enemy e)
    {
        e.changeHealth(-damage);
        e.setEffect("salted.png", 0, 3);
    }
}
