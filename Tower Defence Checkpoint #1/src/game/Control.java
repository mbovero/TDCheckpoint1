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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener {

    State state;
    View  view;
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
        if (!(images.containsKey(filekey))) //Checks if images map includes the filekey
            getImage(filekey);  //If there is no key, this creates a new key with an associated image
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
	
}
