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

public class Projectile_Glue extends Projectile
{
    // Constructor
    public Projectile_Glue(State state, Control control, int x, int y)
    {
        super(state, control, x, y);
        this.killRange = 50;
        this.spriteFile = "glue.png";
        this.speed = 7;
    }

    /**
     * This class overrides the normal collide method in
     * order to apply a unique effect that slows enemies
     * once a glue projectile has collided.
     */
    @Override
    public void collide()
    {
        Enemy e = state.findNearestEnemy(new Point((int)xPos, (int)yPos));
        if (e != null)
        {
            double distance = Math.sqrt(Math.pow((e.getPosition().x - xPos), 2) + Math.pow((e.getPosition().y - yPos), 2));
            if (distance < killRange) {
                this.isExpired = true;
                this.isVisible = false;
                e.changeVelocity(.5);
                e.setEffect("glued.png", 0);
            }
        }
    }
}
