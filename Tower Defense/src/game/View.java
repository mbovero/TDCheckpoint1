/**
 * This class sets up and allows for the graphical objects to be seen in the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {

    Control control;
    State state;

    public View(Control control, State state) {
        this.control = control;
        this.state = state;

        JFrame f = new JFrame("Miles's & Kirt's Tower Defense 2022");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(this);
        this.setMinimumSize(new Dimension(800, 650));
        this.setPreferredSize(this.getMinimumSize());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public void paint(Graphics g) {
        //System.out.println("repainting...");

        // Loop over the list of the current frame's game objects (from the game State object) and draw them
        List<GameObject> drawTop = new ArrayList<GameObject>();
        for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired() && go instanceof Clickable || go instanceof Menu)
                drawTop.add(go);

        for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired() && !(go instanceof Clickable)  && !(go instanceof Menu))
                go.draw(g);

        for (GameObject go : drawTop)
            go.draw(g);
    }

}