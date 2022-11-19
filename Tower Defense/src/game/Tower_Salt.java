package game;

import java.awt.*;

public class Tower_Salt extends GameObject implements Clickable
{

    boolean isMoving;
    int x;
    int y;
    public Tower_Salt(State state, Control control, boolean isMoving)
    {
        isVisible = true;
        isExpired = false;
        this.isMoving = isMoving;

        this.state = state;
        this.control = control;
    }

    @Override
    public void update(double elapsedTime)
    {
        if (isMoving)
        {
            x = control.getMouseX();
            y = control.getMouseY();
        }
    }

    @Override
    public void draw(Graphics g)
    {
        g.drawImage(control.loadImage("salt.png"), x, y, null);
    }

    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (isMoving)
        {
            isMoving = false;
            return true;
        }
        return false;
    }
}
