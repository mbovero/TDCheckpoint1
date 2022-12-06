package game.towers;

import game.Control;
import game.State;
import game.enemies.Enemy;

public class Projectile_RedFire extends Projectile {
    public Projectile_RedFire(State state, Control control, int x, int y) {
        super(state, control, x, y);
        killRange = 50;
        spriteFile = "light.png";
        this.speed = 20;
        damage = 5;
    }

    @Override
    public void effect(Enemy e) {
        e.changeHealth(-damage);
        state.addGameObject(new AreaOfEffect_Fire(state, control, xPos, yPos, 0));
    }


}
