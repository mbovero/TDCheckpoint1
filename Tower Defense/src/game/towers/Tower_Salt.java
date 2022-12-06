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
    protected String[][] salt = new String[][]          //information for salt tower upgrades
            {{"", "Faster Firing", "75", "Increases the speed of salt thrown at enemies"},{"", "More Salt", "100", "Take the cap off and really let loose"},{"", "Special Salt", "120", "Maybe I won't get em', but they're still gonna hurt"}};

    //Constructor
    public Tower_Salt(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        this.fireRate = .75;
        this.spriteFile = "salt.png";
        this.range = 250;
        this.towerName = "Salt";
    }

    @Override
    public void shoot() {state.addGameObject(new Projectile_Salt(state, control, x, y));}
}
