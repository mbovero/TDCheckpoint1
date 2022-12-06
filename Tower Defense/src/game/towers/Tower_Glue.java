/**
 * This class holds the information for interacting with and displaying
 * Glue Tower objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Tower_Glue extends Tower
{
    protected String[][] glue = new String[][]      //Information for glue tower upgrades
            {{"", "Mass Gluing", "120", "Cover them all"},{"", "Glue Trap", "75", "Make them stay put and get what they deserve"},{"", "Gorilla Glue", "100","Glue so strong it doesn't go away"}};

    //Constructor
    public Tower_Glue(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        this.fireRate = 1.5;
        this.spriteFile = "glue_bottle.png";
        this.range = 150;
        this.towerName = "Glue";
    }

    @Override
    public void shoot() {state.addGameObject(new Projectile_Glue(state, control, x, y));}
}
