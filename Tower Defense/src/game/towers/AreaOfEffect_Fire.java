package game.towers;

import game.Control;
import game.State;
import game.enemies.Enemy;

import java.awt.*;

public class AreaOfEffect_Fire extends AreaOfEffect {

    public AreaOfEffect_Fire(State state, Control control, double x, double y, int spread) {
        super(state, control, x, y, spread);
        spriteFile = "on_fire.png";
        killRange = 33;
        duration = 5;


    }

    @Override
    public void draw(Graphics g) {
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
            if (distance < killRange) {effect(e);}
        }
    }

    @Override
    public void effect(Enemy e) {
        e.setEffect(spriteFile, 2, 7);
    }
}
