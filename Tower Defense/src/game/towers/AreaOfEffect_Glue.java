package game.towers;

import game.Control;
import game.State;
import game.enemies.Enemy;

import java.awt.*;

public class AreaOfEffect_Glue extends AreaOfEffect {
    public AreaOfEffect_Glue(State state, Control control, double x, double y, int spread) {
        super(state, control, x, y, spread);
        spriteFile = "trap.png";
        killRange = 33;
        duration = 8;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void effect(Enemy e) {
        e.setEffect("glued_trap.png", 2, 10);
    }
}
