/**
 * This class holds the information for displaying and interacting with a glue
 * tower that is available to buy in the sidebar menu.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class PurchaseTower_Glue extends PurchaseTower {

    //Constructor
    public PurchaseTower_Glue(State state, Control control)
    {
        super(state, control);
        this.xDraw = 710;
        this.yDraw = 75;
        this.cost = 150;
        this.spriteFile = "glue_bottle.png";
    }

    //Unused method
    @Override
    public void update(double elapsedTime) {

    }

    public void placeTower()
    {
        state.addGameObject(new Tower_Glue(state, control, true, this));
    }

    public int getCost()
    {
        return this.cost;
    }

}
