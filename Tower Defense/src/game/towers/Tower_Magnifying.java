/**
 * This class holds the information for interacting with and displaying
 * Magnifying Glass Tower objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Tower_Magnifying extends Tower{

    protected String[][] magnifying_glass = new String[][]          //Information for Magnifying Glass upgrades
            {{"", "Concentrated Fire", "120", "Burn them to the ground"},{"", "Wider Lens", "75", "I see all of you", "Cover a wider range"},{"", "Diffraction", "100", "MORE LASERS PLEASE"}};

    //Contstructor
    public Tower_Magnifying(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        this.fireRate = 4;
        this.spriteFile = "magnifying_glass.png";
        this.range = 300;
        this.towerName = "Magnifying Glass";
    }

    @Override
    public void shoot() {

    }
}
