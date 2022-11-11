package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import path.Path;

public class Control implements Runnable {

    State state;
    View  view;
    private Path path;
	
	public Control()
	{
		run();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void run() 
	{
		System.out.println("Running...");
		
		try 
		{
	        ClassLoader myLoader = this.getClass().getClassLoader();
	        InputStream pathStream = myLoader.getResourceAsStream("resources/path_2.txt");
	        Scanner pathScanner = new Scanner(pathStream);
	        path = new Path(pathScanner); // Builds your path using your Path class
		} catch (Exception e){
			System.out.println("Error occured while loading path.");
		}
	        
		state = new State();
	    view = new View(this, state);
	    
	    state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background());  // Add one background object to our list
        state.addGameObject(new Snail());  // Add one snail to our list
        state.finishFrame();    // Mark the next frame as ready

        view.repaint();           // Draw it.
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
            
            System.out.println("returning image...");
            
            return image;
        }
        catch (IOException e)
        {
            System.out.println("Could not find or load resources/" + filename);
            System.exit(0);  // Close the frame, bail out.
            return null;  // Does not happen, the application has exited.
        }
    }
	
}
