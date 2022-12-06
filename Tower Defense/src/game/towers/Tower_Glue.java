/**
 * This class holds the information for interacting with and displaying
 * Glue Tower objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.towers;

import game.Control;
import game.State;

import java.awt.*;

public class Tower_Glue extends Tower
{
    protected String[][] information = new String[][]      //Information for glue tower upgrades - Information format: {image, name, cost, description},{sameinfo},{sameinfo}
            {{"mass_gluing.png", "Mass Gluing", "120", "Cover them all"},{"glue_trap.png", "Glue Trap", "75", "Make them stay put and get what they deserve"},{"gorilla_glue.png", "Gorilla Glue", "100","Glue so strong it doesn't go away"}};
    protected boolean areaOfEffect = false;
    protected double setTrapTime=0;

    //Constructor
    public Tower_Glue(State state, Control control, boolean isMoving, PurchaseTower purchaseTower)
    {
        super(state, control, isMoving, purchaseTower);
        fireRate = 1.5;
        spriteFile = "glue_bottle.png";
        range = 150;
        towerName = "Glue";
        shots = 1;
        shotType = 0;
    }

    @Override
    public void shoot(int version, int shots) {
        for (int i = 0; i <= shots; i++) {state.addGameObject(new Projectile_Glue(state, control, x, y));}
    }

    @Override
    public void setUpgrades(int upgrade, boolean value) {
        upgrades[upgrade] = value;
        totalUpgrades += 1;
        if (upgrade == 0) {shots = 5;}
        if (upgrade == 1) {areaOfEffect = true;}
        if (upgrade == 2) {shotType = 1;}

        if (upgrades[0] && upgrades [1]) {spriteFile = "glue_factory.png";}
        if (upgrades[1] && upgrades [2]) {spriteFile = "sticky_cannon.png";}
        if (upgrades[0] && upgrades [2]) {spriteFile = "glue_guerrilla.png";}
    }

    @Override
    public String getUpgradeInfo(int upgrade, int info) {return information[upgrade][info];}

    @Override
    public void customUpdate() {
        if (upgrades[2] && System.currentTimeMillis()-setTrapTime> 10) {
            Point p = state.findNearestPathPoint(new Point(x,y));
            state.addGameObject(new AreaOfEffect_Glue(state, control, p.getX(), p.getY(), 0));
            setTrapTime = System.currentTimeMillis();
        }
    }

}
