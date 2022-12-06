package game.gui;

import game.Control;
import game.GameObject;
import game.State;
import game.towers.Tower;

import java.awt.*;

public class TowerMenu extends GameObject
{
    protected String towerName;             //Holds the name of the tower to display
    protected Tower tower;
    protected UpgradeButton upgrade1;
    protected UpgradeButton upgrade2;
    protected UpgradeButton upgrade3;
    public TowerMenu (Tower tower, State state, Control control)
    {
        this.tower = tower;
        this.towerName = tower.getTowerName();
        this.upgrade1 = new UpgradeButton(tower, towerName,1);
    }
    @Override
    public void update(double elapsedTime) {

    }

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
}


class UpgradeButton extends GameObject {
    protected String towerName;
    protected int upgrade;
    protected Tower tower;

    UpgradeButton (Tower tower, String towerName, int upgrade) {
        this.towerName = towerName;
        this.upgrade = upgrade;
        this.tower = tower;
    }
    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void draw(Graphics g) {
        if (upgrade == 1) {
            if ()
            {
                //Draw "bought"
            }
            g.setColor(new Color(208, 208, 208));
            g.fillRect(600, 75, 200, 100);        //Box for upgrade
            g.drawImage(control.loadImage(), 710, 85);              //Image for upgrade
            //Title for upgrade
            //Cost for upgrade displayed
            //Description for upgrade
        }
        else if (upgrade == 2) {

        }
        else if (upgrade == 3) {

        }
    }
}
