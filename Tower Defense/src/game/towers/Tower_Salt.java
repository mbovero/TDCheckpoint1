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

public class Tower_Salt extends Tower
{
    //Constructor
    public Tower_Salt(State state, Control control, boolean isMoving)
    {
        super(state, control, isMoving);
        this.fireRate = 3;
        this.spriteFile = "salt.png";
    }

    @Override
    public void shoot()
    {
        state.addGameObject(new FlyingSalt(state, control, x, y));       // Shoot the projectile
    }
}
