/**
 * Class used to display the upgrades of a tower for purchasing and
 * reading what the current selected tower has.
 *
 * @author Miles Bovero, Kirt Robinson
 * @verison December 5, 2022
 */
package game.gui;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;
import game.towers.Tower;

import java.awt.*;

public class TowerMenu extends GameObject
{
    protected String towerName;             //Holds the name of the tower to display
    protected Tower tower;
    public TowerMenu (Tower tower, State state, Control control)            //State has to be called thing for some reason
    {
        isVisible = true;
        isExpired = false;
        this.tower = tower;                                                   //Set referenced tower
        this.towerName = tower.getTowerName();                                //Get the name of the type of tower
        this.state = state;                                                   //Set up state and control
        this.control = control;
        state.addGameObject(new UpgradeButton(tower, towerName,0, state, control));        //Set up all upgrade buttons
        state.addGameObject(new UpgradeButton(tower, towerName,1, state, control));
        state.addGameObject(new UpgradeButton(tower, towerName,2, state, control));
        state.addGameObject(new ExitButton(this, state, control));                //Make an Exit button


    }
    @Override
    public void update(double elapsedTime) {

    }

    /**
     * Draws the background & title of the TowerMenu Object
     *
     * @param g graphics object to draw with
     */
    @Override
    public void draw(Graphics g) {
        //draw side menu
        g.setColor(Color.gray);
        g.fillRect(600, 0, 200, 650);

        //Menu title
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.BOLD, 36));
        g.drawString(towerName, 650, 50);
    }

    /**
     * Method that allows the user to close the TowerMenu object and remove it from play
     */
    public void exit() {
        for (GameObject go : state.getFrameObjects())
        {
            if (go instanceof UpgradeButton)
            {
                UpgradeButton ub = (UpgradeButton) go;
                ub.exit();
            }
        }
        this.isVisible = false;
        this.isExpired = true;
        state.setTowerMenu(false);
    }
}