package game;

import java.awt.*;

public class PurchaseTower extends GameObject implements Clickable{

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
//        g.setColor(Color.white);
//        g.setFont(new Font("SansSerif", Font.BOLD, 46));
//        g.drawString("S", 645, 123);
        g.drawImage(control.loadImage("salt.png"), 635, 75, null);
        g.setColor(Color.lightGray);
        g.drawRect(630, 75, 60, 60);

    }

    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (mouseX >= 630 && mouseX <= 690 &&
            mouseY >= 75 && mouseY <= 135)
        {
            state.addGameObject(new Tower_Salt(state, control, true));
            return true;
        }
        return false;
    }
}
