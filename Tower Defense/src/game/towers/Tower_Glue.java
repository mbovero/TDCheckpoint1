/**
 * This class holds the information for interacting with and displaying
 * the Tower_Salt object.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November, 20, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Tower_Glue extends Tower
{
    //Constructor
    public Tower_Glue(State state, Control control, boolean isMoving)
    {
        super(state, control, isMoving);
        this.fireRate = 2;
        this.spriteFile = "glue_bottle.png";
    }

    @Override
    public void shoot()
    {
        state.addGameObject(new Projectile_Glue(state, control, x, y));       // Shoot the projectile
    }
}
