/**
 * This class manages and sets up all aspects of the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import game.enemies.Generator_Snail;
import game.enemies.Generator_Snail_Fast;
import game.enemies.Snail;
import game.enemies.Snail_Fast;
import game.gui.Background;
import game.gui.Menu;
import game.towers.PurchaseTower_Glue;
import game.towers.PurchaseTower_Salt;
import path.Path;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener {

    protected State state;
    protected View  view;

    protected int mouseX;                   // The current x position of the mouse
    protected int mouseY;                   // The current y position of the mouse
    private Path path;                      // The path along which enemies are drawn
    HashMap<String, BufferedImage> images;

    //Constructor
	public Control()
	{
		run();
	}

    /**
     * Method that sets up and runs the Tower Defense game.
     */
    @Override
	public void run() 
	{	
		try 
		{
	        ClassLoader myLoader = this.getClass().getClassLoader();
	        InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
	        Scanner pathScanner = new Scanner(pathStream);
	        path = new Path(pathScanner); // Builds path using Path class
		} catch (Exception e){
			System.out.println("Error occurred while loading path.");
		}
	        
		state = new State(this);
	    view = new View(this, state);
        images = new HashMap<>();

        view.addMouseListener(this);
        view.addMouseMotionListener(this);

	    state.startFrame();  // Prepares the creation of the 'next' frame
        state.gameOver = false;

        state.addGameObject(new Background(state, this));           // Add one background object to list
        state.addGameObject(new Generator_Snail(state, this));      // Add snail generator to list
        state.addGameObject(new Generator_Snail_Fast(state, this)); // Add snail generator to list

        state.addGameObject(new Menu(state, this));
        state.addGameObject(new PurchaseTower_Salt(state, this));
        state.addGameObject(new PurchaseTower_Glue(state, this));

        state.finishFrame();      // Mark the next frame as ready
        view.repaint();           // Draw it.
        
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
	}

    /**
     * Method gets the path and returns it for further use.
     *
     * @return path object
     */
	public Path getPath()
	{
		return path;
	}

    /**
     * Method that places a desired image file inside the images map
     * to efficiently be used later.
     *
     * @param filename the full name of a file to call
     */
    public void getImage (String filename)
    {
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            images.put(filename, image);
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out
        }
    }

    /**
     * Method that calls to the images map to return an image. This
     * method also allows for the creation of a new map key if there
     * is no key previously stored.
     *
     * @param filekey the full name of a file to call inside the images map
     * @return a buffered image from inside the images map
     */
    public BufferedImage loadImage (String filekey)
    {
        if (!images.containsKey(filekey)) //Checks if images map includes the filekey
        {
            System.out.println("getting image...");
        	getImage(filekey);  //If there is no key, create a new key with an associated image
        }
        return images.get(filekey);
    }

    /**
     * Listener method to update the frame when the timer activates an event.
     *
     * @param e the event to be processed
     */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        // Setup
        List<GameObject> frameObjects = state.getFrameObjects();

        // Update objects and frames
        state.startFrame();
        if (!state.gameOver)
            for (GameObject go : frameObjects)
                go.update(state.elapsedTime);
        state.finishFrame();
        view.repaint();
	}

    /**
     * Listener method that updates the current mouse coordinates when
     * the mouse is moved.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * Accessor method that returns the current stored X-coordinate of the mouse on the screen.
     *
     * @return int x-coordinate of the mouse
     */
    public int getMouseX()
    {
        return mouseX;
    }

    /**
     *Accessor method that returns the current stored Y-coordinate of the mouse on the screen.
     *
     * @return int y-coordinate of the mouse
     */
    public int getMouseY()
    {
        return mouseY;
    }

    /**
     * Listener method that detects when clickable objects are clicked and responds accordingly.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        List<GameObject> list = state.getFrameObjects();
        for (GameObject go: list)
            if (go instanceof Clickable)
            {
                Clickable c = (Clickable) go;
                if (c.consumeClick(mouseX, mouseY))
                    break;
            }
    }

    //Unused method
    @Override
    public void mouseClicked(MouseEvent e) {}

    //Unused method
    @Override
    public void mousePressed(MouseEvent e) {}

    //Unused method
    @Override
    public void mouseEntered(MouseEvent e) {}

    //Unused method
    @Override
    public void mouseExited(MouseEvent e) {}

    //Unused method
    @Override
    public void mouseDragged(MouseEvent e) {}
}
