/**
 *This class holds the current properties of the game objects in the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 14, 2022
 */
package game;

import java.util.ArrayList;
import java.util.List;

public class State {

	List<GameObject> currentFrameGameObjects;
	List<GameObject> nextFrameGameObjects;
	
    public State()
    {
        currentFrameGameObjects = new ArrayList<GameObject>();
    }
	
    /**
     * An accessor that returns the list of objects for the current frame
     * 
     * @return currentFrameGameObjects
     */
    public List<GameObject> getFrameObjects ()
    {
        return currentFrameGameObjects;
    }

    /**
     * Sets up the next frame's list to be a copy of the current list
     */
    public void startFrame ()
    {
        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
    }
    
    /**
     * Replaces the current frame list with the list we prepared for the next frame
     */
    public void finishFrame ()
    {
        currentFrameGameObjects = nextFrameGameObjects;
        nextFrameGameObjects = null;  // PJ added this -- it makes it clear there is only a current list now.
    }
    
    /**
     * Adds a game object to the next frame of the game
     * 
     * @param go the game object to be added
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
