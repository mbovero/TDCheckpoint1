package game.towers;

import game.Control;
import game.GameObject;
import game.State;
import game.enemies.Enemy;

import java.awt.*;

abstract public class AreaOfEffect extends GameObject {

    protected double killRange;
    protected double xPos;
    protected double yPos;
    protected double duration;
    protected double startTime = System.currentTimeMillis();
    protected String spriteFile;
    protected int spread;
    private Enemy target;



    public AreaOfEffect (State state, Control control, double x, double y, int spread) {
        this.state = state;
        this.control = control;
        this.xPos = x;
        this.yPos = y;
        this.spread = spread;

    }

    /**
     * A method that updates its position, and allows for collisions
     * with enemies.
     *
     * @param elapsedTime the time elapsed since the last frame
     */
    @Override
    public void update(double elapsedTime)
    {
        //Interact with the enemy
        collide();
        //Check if AreaOfEffect lifespan is over
        if (System.currentTimeMillis()-startTime > duration) {
            this.isExpired = true;
            this.isVisible = false;
            return;
        }
    }

    /**
     *  A method that determines the conditions to, and
     *  result of, colliding with an enemy.
     */
     public void collide()
     {
         Enemy e = state.findNearestEnemy(new Point((int)xPos, (int)yPos));
         if (e != null)
         {
             double distance = Math.sqrt(Math.pow((e.getPosition().x - xPos), 2) + Math.pow((e.getPosition().y - yPos), 2));
             if (distance < killRange) {
                 effect(e);
             }
         }
     }

    /**
     * Abstract method lets the specific AreaOfEffect object interact
     * with the Enemy object in an individual way
     *
     * @param e enemy to effect
     */
    abstract public void effect (Enemy e);
}
