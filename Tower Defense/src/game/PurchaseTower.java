package game;

import java.awt.*;

public class PurchaseTower extends GameObject{

    public PurchaseTower (State state, Control control)
    {
        isVisible = true;
        isExpired = false;

        this.state = state;
        this.control = control;
    }

    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void draw(Graphics g)
    {
        // Draw button
        g.setColor(Color.darkGray);
        g.fillRect(630, 75, 60, 60);
        g.setColor(Color.white);
        g.setFont(new Font("SansSerif", Font.BOLD, 46));
        g.drawString("T", 645, 123);
        g.setColor(Color.lightGray);
        g.drawRect(630, 75, 60, 60);

    }
}
