package game.gui;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;
import game.towers.Tower;

import java.awt.*;

/**
 * This class is used for the TowerMenu to display upgrade information
 * for the towers that can be interacted with.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
public class UpgradeButton extends GameObject implements Clickable {
    protected String towerName;                         // The name of the current tower
    protected int upgrade;                              // The upgrade to this button is displaying
    protected Tower tower;                              // The actual Tower object to refer to
    protected int cost;                                 // The cost of the upgrade
    protected boolean purchased;                        // Stating if the upgrade has been purchased already
    protected int offset[] = new int[] {0, 120, 240};

    public UpgradeButton (Tower tower, String towerName, int upgrade, State state, Control control) {
        this.towerName = towerName;
        this.upgrade = upgrade;
        this.tower = tower;
        this.cost = Integer.parseInt(tower.getUpgradeInfo(upgrade, 2));
        this.purchased = tower.getUpgrades(upgrade);

        this.state = state;
        this.control = control;
        this.isVisible = true;
        this.isExpired = false;
    }
    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void draw(Graphics g) {
        if (state.getMoney() < cost) {
            g.setColor(new Color(145, 42, 42, 255));}       //Show that the user can't buy the upgrade
        else if (control.getMouseX() > 600 && control.getMouseX() < 800
                && control.getMouseY() > 75+offset[upgrade] && control.getMouseY() < 175+offset[upgrade]
                && !purchased && cost < state.getMoney()) {
            g.setColor(new Color(71, 183, 71, 255));
        }
        else{
            g.setColor(new Color(199, 199, 199));}
        //Draw box for upgrade
        g.fillRect(600, 75+offset[upgrade], 200, 100);

        //Draw image for upgrade
        g.drawImage(control.loadImage(tower.getUpgradeInfo(upgrade, 0)), 605, 95+offset[upgrade], null);

        //Draw title for upgrade
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 15));
        g.drawString(tower.getUpgradeInfo(upgrade, 1), 630, 95+offset[upgrade]);

        //Draw cost for upgrade
        if (tower.getUpgrades(upgrade))     //if the current upgrade is purchased
        {
            //Draw "Purchased"
            g.setColor(Color.GRAY);
            g.drawString("Purchased", 700, 160+offset[upgrade]);
            g.setColor(Color.black);
        }
        else {
            g.drawString(tower.getUpgradeInfo(upgrade, 2), 770, 160+offset[upgrade]);   //Draws cost
        }

        //Description for upgrade
        g.setFont(new Font("SansSerif", Font.ITALIC, 6));
        g.drawString(tower.getUpgradeInfo(upgrade, 3), 660, 105+offset[upgrade]);
    }

    @Override
    public boolean consumeClick(int mouseX, int mouseY) {
        //Check if mouse is on the current upgrade button
        if (mouseX > 600 && mouseX < 800
                && mouseY > 75+offset[upgrade] && mouseY < 175+offset[upgrade]
                && purchased == false               //Upgrade can be purchased
                && cost < state.getMoney()          //User has enough money to buy it
                && tower.getTotalUpgrades() < 2)    //User hasn't reached the upgrade limit
        {
            tower.setUpgrades(upgrade, true);   //Activate the upgrade
            state.changeMoney(-cost);   //Take the money used
            return true;
        }
        return false;
    }

    /**
     * Method to close the UpgradeButton objects from the TowerMenu.
     */
    public void exit () {isVisible = false; isExpired = true;}
}

