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

import java.awt.*;

public class Tower_Magnifying extends Tower{

    protected String[][] information = new String[][]          //Information for Magnifying Glass upgrades - Information format: {image, name, cost, description},{sameinfo},{sameinfo}
            {{"concentrated_fire.png", "Concentrated Fire", "120", "Burn them to the ground"},{"wide_lens.png", "Wider Lens", "75", "I see all of you", "Cover a wider range"},{"diffraction.png", "Diffraction", "100", "MORE LASERS PLEASE"}};

    //Contstructor
    public Tower_Magnifying(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        fireRate = 4;
        spriteFile = "magnifying_glass.png";
        range = 300;
        towerName = "Magnifying Glass";
        shots = 1;
        shotType = 0;

    }

    @Override
    public void shoot(int version, int shots)
    {
        //Loop through and make as many projectiles as the current tower has shots of
        for (int i = 0; i <= shots; i++) {state.addGameObject(new Projectile_Fire(state, control, x, y));}
        //Gather information for creating burnspot/firespread
        Point p = state.findNearestPathPoint(new Point(x,y));


    }

    @Override
    public void setUpgrades(int upgrade, boolean value) {
        upgrades[upgrade] = value;
        totalUpgrades += 1;
        if (upgrade == 0) {shots = 5; shotType = 1;}
        if (upgrade == 1) {}
        if (upgrade == 2) {shotType = 1;}

        if (upgrades[0] && upgrades [1]) {spriteFile = "forest_fire.png";}
        if (upgrades[1] && upgrades [2]) {spriteFile = "wide_rainbow.png";}
        if (upgrades[0] && upgrades [2]) {spriteFile = "fire_hazard.png";}
        }

    @Override
    public String getUpgradeInfo(int upgrade, int info) {return information[upgrade][info];}

    @Override
    public void customUpdate() {

    }

}
