package game;

import java.awt.*;

public class Menu extends GameObject{

    public Menu (State state, Control control)
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
        // Draw side menu
        g.setColor(Color.gray);
        g.fillRect(600, 0, 200, 650);
            // Menu title
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.BOLD, 36));
        g.drawString("Menu", 650, 50);

        // Draw bottom info display
        g.setColor(Color.lightGray);
        g.fillRect(0, 600, 600, 50);
            // Health
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        g.setColor(Color.red);
        g.drawString("Health: " + state.getHealth(), 15, 633);
            // Money
        g.setColor(Color.green);
        g.drawString("Money: " + state.getMoney(), 200, 633);
            // Score
        g.setColor(Color.yellow);
        g.drawString("Score: " + state.getScore(), 425, 633);




    }
}
