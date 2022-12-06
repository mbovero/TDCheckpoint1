/**
 * This class holds the information for displaying and interacting with a salt
 * tower that is available to buy in the sidebar menu.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.towers;

import game.*;

public class PurchaseTower_Salt extends PurchaseTower {

    //Constructor
    public PurchaseTower_Salt(State state, Control control)
    {
        super(state, control);
        this.xDraw = 630;
        this.yDraw = 75;
        this.cost = 100;
        this.spriteFile = "salt.png";
    }

    //Unused method
    @Override
    public void update(double elapsedTime) {}

    public void placeTower() {state.addGameObject(new Tower_Salt(state, control, true, this));}

    public int getCost()
    {
        return this.cost;
    }

}
