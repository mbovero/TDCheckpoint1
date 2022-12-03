package game.towers;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;
import game.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Tower extends GameObject implements Clickable
{
    protected boolean isMoving;
    protected int x;
    protected int y;
    protected double fireRate;
    protected String spriteFile;
    private long lastProjectileFired = 0;

    //Constructor
    public Tower(State state, Control control, boolean isMoving)
    {

        this.state = state;
        this.control = control;
        isVisible = true;
        isExpired = false;
        this.isMoving = isMoving;
    }

    @Override
    public void update(double elapsedTime)
    {
        if (isMoving)                                                               // Move with mouse for initial placement
        {
            x = control.getMouseX();
            y = control.getMouseY();
        }

        if (!isMoving && (state.getTotalTime() - lastProjectileFired) >= fireRate)  // Shoot projectile based on fire rate
        {
            if (state.findNearestEnemy(new Point(x, y)) != null)
            {
                shoot();
                lastProjectileFired = state.getTotalTime();
            }
        }
    }

    /**
     * Method that draws the Tower object.
     *
     * @param g a graphics object to draw onto
     */
    @Override
    public void draw(Graphics g)
    {
        // Load image and center it
        BufferedImage image = control.loadImage(spriteFile);
        g.drawImage(image, x-(image.getWidth()/2), y-(image.getHeight()/2), null);

        // Display tower's distance from path
//        if (isMoving)
//        {
//            Point p = state.findNearestPathPoint(new Point(x,y));
//            g.setColor(Color.blue);
//            g.drawLine(p.x,p.y,x,y);
//        }
    }

    /**
     * Method that allows for the placement of the tower by updating the
     * isMoving value to false when it is "placed".
     *
     * @param mouseX the mouse's x position
     * @param mouseY the mouse's y position
     * @return boolean value that states if the object is moving
     */
    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        Point p = state.findNearestPathPoint(new Point(x,y));
        double distance = Math.sqrt(Math.pow((p.x - x),2) + Math.pow((p.y - y),2));

        if (isMoving &&
            mouseX < 600 && mouseY < 600 &&         // Restrict placement to game area
            distance > 33)                          // Restrict placement from path
        {
            isMoving = false;                       // Stop moving the tower
            return true;
        }
        return false;
    }

    abstract public void shoot ();
}
