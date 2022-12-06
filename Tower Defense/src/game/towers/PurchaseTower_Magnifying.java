/**
 * This class holds the information for displaying and interacting with a
 * Magnifying Glass tower that is available to buy in the sidebar menu.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class PurchaseTower_Magnifying extends PurchaseTower {
    //Constructor
    public PurchaseTower_Magnifying(State state, Control control)
    {
        super(state, control);
        this.xDraw = 630;
        this.yDraw = 175;
        this.cost = 200;
        this.spriteFile = "magnifying_glass.png";
    }

    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void placeTower() {state.addGameObject(new Tower_Magnifying(state, control, true, this));}
}
