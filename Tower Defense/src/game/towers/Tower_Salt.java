/**
 * This class holds the information for interacting with and displaying
 * Salt Tower objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Tower_Salt extends Tower
{
    //Constructor
    public Tower_Salt(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        this.fireRate = .75;
        this.spriteFile = "salt.png";
        this.range = 250;
    }

    @Override
    public void shoot() {state.addGameObject(new Projectile_Salt(state, control, x, y));}
}
