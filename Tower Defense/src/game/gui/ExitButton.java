/**
 * Class used for exiting the TowerMenu GameObject and removing
 * all upgrade buttons along with the menu from play
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 5, 2022
 */
package game.gui;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;

public class ExitButton extends GameObject implements Clickable {

    TowerMenu towerMenu;
    public ExitButton (TowerMenu towerMenu, State state, Control control)
    {
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
        this.towerMenu = towerMenu;
    }
    @Override
    public boolean consumeClick(int mouseX, int mouseY) {
        if (mouseX > 740 && mouseX < 790
            && mouseY > 10 && mouseY < 60)
        {
            towerMenu.exit();
            this.isVisible = false;
            this.isExpired = true;
            return true;
        }
        return false;
    }

    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(control.loadImage("exit_button.png"), 760, 10, null);
    }
}
