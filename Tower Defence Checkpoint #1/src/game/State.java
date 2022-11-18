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
    int health;
    int money;
	
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
     *
     */
    public void finishFrame ()
    {
    	for (GameObject go: currentFrameGameObjects)
    		if (go.isExpired)
    			nextFrameGameObjects.remove(go);
//        for (int i=0; i<currentFrameGameObjects.size(); i++)
//        {
//            GameObject go = currentFrameGameObjects.get(i);
//                if (go.isExpired() == true && go.< 1)
//                    nextFrameGameObjects.remove(go);
//                else if (go.isExpired() == true && go.getPercentage() >= 1)
//                {
//                        nextFrameGameObjects.remove(go);
//                        nextFrameGameObjects.add(new Snail());
//                        loseHealth();
//                }
//        }
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

    /**
     * Accessor method to gets the amount of health held by the user
     *
     * @return health currently held by the user
     */
    public int getHealth() 
    {
    	return this.health;
    }

    /**
     * Accessor method to gets the amount of money held by the user
     *
     * @return money currently held by the player
     */
    public int getMoney() 
    {
    	return this.money;
    }

    /**
     * When called, this method changes the player's money
     * 
     * @param i the amount of money to be added or removed
     */
    public void changeMoney(int i)
    {
    	money += i;
    }

    /**
     * When called, this method decreases the player's health
     * 
     * @param i the amount of health to be subtracted
     */
    public void loseHealth(int i)
    {
    	health -= i;
    }
}
