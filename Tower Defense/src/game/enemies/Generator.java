package game.enemies;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;

abstract public class Generator extends GameObject {

    private double timeToNextCycle;                         // The time until the next spawn cycle
    private double timeToNextDifficultyCycle = 30;          // The time until the spawn rate is increased
    protected double frequency;                             // The time interval (in seconds) that the enemy is spawned in
    protected double initialDelay;                          // The time (in seconds) before the enemy begins spawning
    protected State state;
    protected Control control;

    // Constructor
    public Generator (State state, Control control)
    {
        this.state = state;
        this.control = control;
        this.isExpired = false;
        this.isVisible = false;
        timeToNextCycle = frequency;
    }

    /**
     * A method that controls the cycle in which
     * @param elapsedTime
     */
    @Override
    public void update(double elapsedTime)
    {
        timeToNextDifficultyCycle -= elapsedTime;
        if (timeToNextDifficultyCycle <= 0 && !state.getGameOver())
        {
            frequency *= .95;
            timeToNextDifficultyCycle += 30;
        }

            if (state.getTotalTime() > initialDelay)
        {
            timeToNextCycle -= elapsedTime;
            if (timeToNextCycle <= 0 && !state.getGameOver())
            {
                generate();
                timeToNextCycle += frequency;
            }
        }
    }

    /**
     *  A method that generates an enemy that
     *  is specified by the generator being used.
     */
    abstract public void generate();
}
