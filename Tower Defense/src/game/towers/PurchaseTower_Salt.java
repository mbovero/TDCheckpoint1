/**
 * This class hold the information for displaying and interacting with a salt
 * tower that is available to buy in the sidebar menu.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November, 20, 2022
 */
package game.towers;

import game.*;

import java.awt.*;

public class PurchaseTower_Salt extends GameObject implements Clickable {

    //Constructor
    public PurchaseTower_Salt(State state, Control control)
    {
        isVisible = true;
        isExpired = false;

        this.state = state;
        this.control = control;
    }

    //Unused method
    @Override
    public void update(double elapsedTime) {

    }

    /**
     * Method that draws the icon for the Tower_Salt in the
     * sidebar menu.
     *
     * @param g graphics object to draw onto
     */
    @Override
    public void draw(Graphics g)
    {
        // Draw button
        g.setColor(Color.darkGray);
        g.fillRect(630, 75, 60, 60);                                  // Draw box
        g.drawImage(control.loadImage("salt.png"), 635, 75, null);  // Draw salt tower
        g.setColor(Color.lightGray);
        g.drawRect(630, 75, 60, 60);                                  // Draw outline

        // Draw price when mouse hovers over button
        int mouseX = control.getMouseX();
        int mouseY = control.getMouseY();
        if (mouseX >= 630 && mouseX <= 690 &&
                mouseY >= 75 && mouseY <= 135 &&
                !state.getGameOver())
        {
            if (state.getMoney() < 100)
                g.setColor(Color.red);
            else
                g.setColor(Color.black);
            g.fillRect(630, 136, 60, 20);                             // Draw box
            g.setColor(Color.white);
            g.setFont(new Font("SansSerif", Font.BOLD, 17));
            g.drawString("100", 645, 152);                                    // Draw price
        }

    }

    /**
     * Method that checks if the mouse is in a certain range - when clicked - then
     * allows the player to place a new salt tower by adding a new Tower_Salt GameObject 
     * through state.
     *
     * @param mouseX the mouse's x position
     * @param mouseY the mouse's y position
     * @return a boolean value
     */
    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (mouseX >= 630 && mouseX <= 690 &&
            mouseY >= 75 && mouseY <= 135 &&
            !state.getGameOver() &&
            state.getMoney() >= 100)
        {
            state.addGameObject(new Tower_Salt(state, control, true));
            return true;
        }
        return false;
    }
}
