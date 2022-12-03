package game.enemies;

import game.Control;
import game.GameObject;
import game.State;

import java.awt.*;

abstract public class Generator extends GameObject {

    private double timeToNextCycle;
    private double timeToNextDifficultyCycle = 30;
    protected double frequency;
    protected double initialDelay;
    protected State state;
    protected Control control;

    public Generator (State state, Control control)
    {
        this.state = state;
        this.control = control;
        this.isExpired = false;
        this.isVisible = false;
        timeToNextCycle = frequency;
    }

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

    abstract public void generate();
}
