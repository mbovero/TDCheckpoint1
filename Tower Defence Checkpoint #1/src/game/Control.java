/**
 * This class manages and sets up all aspects of the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener {

    State state;
    View  view;
    private Path path;
	
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

	    state.startFrame();  // Prepares the creation of the 'next' frame
        state.health = 100;
        state.money = 100;
        state.addGameObject(new Background());  // Add one background object to our list
        state.addGameObject(new Snail());  // Add one snail to our list
        state.finishFrame();    // Mark the next frame as ready
        view.repaint();           // Draw it.
        
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
	}

	public Path getPath()
	{
		return path;
	}
	
    public BufferedImage getImage (String filename)
    {
        try
        {
            ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
            BufferedImage image = javax.imageio.ImageIO.read(imageStream);
            
            return image;
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out.
            return null;  // Does not happen, the application has exited.
        }
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
	
}
