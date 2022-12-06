package game.gui;

import game.Clickable;
import game.Control;
import game.GameObject;
import game.State;
import game.towers.Tower;

import java.awt.*;

public class Trash extends GameObject implements Clickable {
    protected int xDraw = 610;                              // X position of the trash button to draw from
    protected int yDraw = 520;                              // Y position of the trash button to draw from
    protected String spritefile1 = "trash_closed.png";
    protected String spritefile2 = "trash_open.png";
    boolean trashable = false;                                      //Boolean to tell if the trash icon should be open or closed

    public Trash (State state, Control control)
    {
        this.state = state;
        this.control = control;
        isVisible = true;
        isExpired = false;
    }

    @Override
    public boolean consumeClick(int mouseX, int mouseY)
    {
        if (mouseX > 610 && mouseX < 790 &&
            mouseY > 520 && mouseY < 590)
        {
            for (GameObject go : state.getFrameObjects())
            {
                if (go instanceof Tower)
                {
                    Tower t = (Tower) go;
                    if (t.getMoving()){
                        t.trash();
                        control.setPlacingTower(false);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    @Override
    public void update(double elapsedTime) {}

    @Override
    public void draw(Graphics g) {
        if (control.getPlacingTower() &&
            control.getMouseX() > 610 && control.getMouseX() < 790 &&
            control.getMouseY() > 520 && control.getMouseY() < 590)
            g.drawImage(control.loadImage(spritefile2), xDraw, yDraw, null);
        else
            g.drawImage(control.loadImage(spritefile1), xDraw, yDraw, null);

    }
}
