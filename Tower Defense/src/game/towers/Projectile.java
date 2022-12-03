package game.towers;

import game.Control;
import game.GameObject;
import game.State;
import game.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Projectile extends GameObject
{
    private double xVelocity;
    private double yVelocity;
    private double xPos;
    private double yPos;
    protected double speed;
    private double distance;    //distance to the enemy
    private double xIncrement;
    private double yIncrement;
    protected double killRange; // The range in which the projectile will collide with an enemy
    protected String spriteFile;
    private Enemy target;

    public Projectile (State state, Control control, int x, int y)
    {
        isVisible = true;
        isExpired = false;
        this.state = state;
        this.control = control;
        xPos = x;
        yPos = y;
        target = state.findNearestEnemy(new Point(x, y));   // Store nearest enemy
        xVelocity = (target.getPosition().x - x);    //gets the difference in x to the enemy
        yVelocity = (target.getPosition().y - y);    //gets the difference in y to the enemy
        distance = Math.sqrt((xVelocity*xVelocity)+(yVelocity*yVelocity));
        xIncrement = xVelocity * (1/distance);
        yIncrement = yVelocity * (1/distance);
    }

    @Override
    public void update(double elapsedTime)
    {
        if (xPos > 600 || yPos > 600 ||
                xPos < 0 || yPos < 0)
        {
            this.isExpired = true;
            this.isVisible = false;
        }
        // Homing projectile
//        xVelocity = (target.getPosition().x - xPos);
//        yVelocity = (target.getPosition().y - yPos);

        xPos += xIncrement * speed;
        yPos += yIncrement * speed;

        // Kill any enemy within range
        Enemy e = state.findNearestEnemy(new Point((int)xPos, (int)yPos));
        if (e != null)
        {
            double distance = Math.sqrt(Math.pow((e.getPosition().x - xPos), 2) + Math.pow((e.getPosition().y - yPos), 2));
            if (distance < killRange) {
                e.setExpiration(true);
                e.setVisibility(false);
                this.isExpired = true;
                this.isVisible = false;
                state.changeScore(e.getScoreReward());
                state.changeMoney(e.getMoneyReward());
            }
        }
    }

    @Override
    public void draw(Graphics g)
    {
        BufferedImage image = control.loadImage(spriteFile);
        g.drawImage(image, (int)xPos-(image.getWidth()/2), (int)yPos-(image.getHeight()/2), null);
    }

}
