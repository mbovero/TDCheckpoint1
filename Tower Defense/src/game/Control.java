/**
 * This class manages and sets up all aspects of the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener {

    State state;
    View  view;

    int mouseX;
    int mouseY;
    private Path path;
    HashMap<String, BufferedImage> images;
	
	public Control()
	{
		run();
	}

	@Override
	public void run() 
	{	
		try 
		{
	        ClassLoader myLoader = this.getClass().getClassLoader();
	        InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
	        Scanner pathScanner = new Scanner(pathStream);
	        path = new Path(pathScanner); // Builds your path using your Path class
		} catch (Exception e){
			System.out.println("Error occurred while loading path.");
		}
	        
		state = new State();
	    view = new View(this, state);
        images = new HashMap<>();

        view.addMouseListener(this);
        view.addMouseMotionListener(this);

	    state.startFrame();  // Prepares the creation of the 'next' frame
        state.health = 100;
        state.money = 100;
        state.score = 0;

        state.addGameObject(new Background(state, this));  // Add one background object to our list
        state.addGameObject(new Snail(state, this));  // Add one snail to our list

        state.addGameObject(new Menu(state, this));
        state.addGameObject(new PurchaseTower(state, this));

        state.finishFrame();    // Mark the next frame as ready
        view.repaint();           // Draw it.
        
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
	}

	public Path getPath()
	{
		return path;
	}
	
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

    public BufferedImage loadImage (String filekey)
    {
        if (!images.containsKey(filekey)) //Checks if images map includes the filekey
        {
            System.out.println("getting image...");
        	getImage(filekey);  //If there is no key, this creates a new key with an associated image
        }
        return images.get(filekey);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
        state.startFrame();
        for (GameObject go : state.getFrameObjects())
            go.update(0);    
        state.finishFrame();
        view.repaint();
	}

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();

        //System.out.println("X:" + mouseX + " Y:" + mouseY);
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
