/**
 * This class holds the information for interacting with and displaying
 * Salt Tower objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.towers;

import game.Control;
import game.State;

public class Tower_Salt extends Tower
{
    protected String[][] information = new String[][]          //information for salt tower upgrades - Information format: {image, name, cost, description},{sameinfo},{sameinfo}
            {{"faster_firing.png", "Faster Firing", "75", "Increases the speed of salt thrown at enemies"},{"more_salt.png", "More Salt", "100", "Take the cap off and really let loose"},{"special_salt.png", "Special Salt", "120", "Maybe I won't get em', but they're still gonna hurt"}};

    //Constructor
    public Tower_Salt(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        fireRate = .75;
        spriteFile = "salt.png";
        range = 250;
        towerName = "Salt";
        shots = 1;
        shotType = 0;
    }

    @Override
    public void shoot(int shotType, int shots) {
        if (shotType == 0){
            for (int i = 0; i <= shots; i++) {state.addGameObject(new Projectile_Salt(state, control, x, y));}}
        if (shotType == 1){
            for (int i = 0; i <= shots; i++) {state.addGameObject(new Projectile_SpecialSalt(state, control, x, y));}
        }
    }

    @Override
    public void setUpgrades(int upgrade, boolean value) {
        upgrades[upgrade] = value;
        totalUpgrades += 1;
        if (upgrade == 0) {fireRate = .4;}
        if (upgrade == 1) {shots = 3;}
        if (upgrade == 2) {shotType = 1;}

        if (upgrades[0] && upgrades [1]) {spriteFile = "gatling_grinder.png";}
        if (upgrades[1] && upgrades [2]) {spriteFile = "salt_lamp.png";}
        if (upgrades[0] && upgrades [2]) {spriteFile = "sling_salt_no.png";}

    }

    @Override
    public String getUpgradeInfo(int upgrade, int info) {return information[upgrade][info];}

    @Override
    public void customUpdate() {


    }

}
