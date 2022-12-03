package game.enemies;

import game.Control;
import game.State;

import java.awt.*;

public class Generator_Snail extends Generator
{

    public Generator_Snail(State state, Control control)
    {
        super(state, control);
        this.frequency = 5.0;
        this.initialDelay = 5.0;
    }

    @Override
    public void generate()
    {
        state.addGameObject(new Snail(state, control));
    }

    @Override
    public void draw(Graphics g) {

    }
}
