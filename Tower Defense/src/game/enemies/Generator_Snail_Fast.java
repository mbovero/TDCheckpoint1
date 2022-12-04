/**
 * This superclass creates and allows for the systematic generation of Fast Snail objects.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game.enemies;

import game.Control;
import game.State;

import java.awt.*;

public class Generator_Snail_Fast extends Generator
{

    public Generator_Snail_Fast(State state, Control control)
    {
        super(state, control);
        this.frequency = 15.0;
        this.initialDelay = 20.0;
    }

    @Override
    public void generate()
    {
        state.addGameObject(new Snail_Fast(state, control));
    }

    // Unused method
    @Override
    public void draw(Graphics g) {}
}
