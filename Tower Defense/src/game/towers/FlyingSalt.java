package game.towers;

import game.Control;
import game.GameObject;
import game.State;
import game.enemies.Enemy;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FlyingSalt extends Projectile
{
    public FlyingSalt (State state, Control control, int x, int y)
    {
        super(state, control, x, y);
        this.killRange = 50;
        this.spriteFile = "salt_crystals.png";
        this.speed = 10;
    }
}
