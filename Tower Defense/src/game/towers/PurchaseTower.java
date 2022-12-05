/**
 * This superclass creates and allows for interactions and use of
 * tower purchase buttons.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.towers;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;

abstract public class PurchaseTower extends GameObject implements Clickable
{
    protected int xDraw;                        // The x position at which the purchase button is drawn
    protected int yDraw;                        // The y position at which the purchase button is drawn
    protected int cost;                         // The cost to place the tower
    protected String spriteFile;                // The name of the file to be used as the enemy's sprite

    //Constructor
    public PurchaseTower(State state, Control control)
    {
        this.state = state;
        this.control = control;
        isVisible = true;
        isExpired = false;
    }

    /**
     * Method that draws the icon for the tower in the
     * sidebar menu.
     *
     * @param g graphics object to draw onto
     */
    @Override
    public void draw(Graphics g)
    {
        // Draw button
        g.setColor(Color.darkGray);
        g.fillRect(xDraw, yDraw, 60, 60);                                  // Draw box
        g.drawImage(control.loadImage(spriteFile), xDraw+5, yDraw, null);   // Draw tower icon
        g.setColor(Color.lightGray);
        g.drawRect(xDraw, yDraw, 60, 60);                                  // Draw outline

        // Draw price when mouse hovers over button
        int mouseX = control.getMouseX();
        int mouseY = control.getMouseY();
        if (mouseX >= xDraw && mouseX <= xDraw+60 &&
                mouseY >= yDraw && mouseY <= yDraw+60 &&
                !state.getGameOver())
        {
            if (state.getMoney() < cost)
                g.setColor(Color.red);
            else
                g.setColor(Color.black);
            g.fillRect(xDraw, yDraw+61, 60, 20);                             // Draw box
            g.setColor(Color.white);
            g.setFont(new Font("SansSerif", Font.BOLD, 17));
            g.drawString(""+cost, xDraw+15, yDraw+77);                             // Draw price
        }
    }

    /**
     * Method that checks if the mouse is in a certain range - when clicked - then
     * allows the player to place a new salt tower by adding a new tower GameObject
     * through state.
     *
     * @param mouseX the mouse's x position
     * @param mouseY the mouse's y position
     * @return a boolean value
     */
    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (mouseX >= xDraw && mouseX <= xDraw+60 &&
                mouseY >= yDraw && mouseY <= yDraw+60 &&
                !state.getGameOver() &&
                state.getMoney() >= cost &&
                !control.getPlacingTower())
        {
            placeTower();
            control.setPlacingTower(true);
            return true;
        }
        return false;
    }

    /**
     *  A method that determines what tower is to be placed.
     */
    abstract public void placeTower();

    /**
     * This method returns the cost of the current PurchaseTower Object
     *
     * @return int the cost of the tower
     */
    public int getCost() {return cost;}

    /**
     * This method changes the cost of the current PurchaseTower Object
     *
     * @param percent the amount the cost should increase by
     */
    public void changeCost(double percent) {this.cost *= 1.1;}

}
