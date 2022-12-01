/**
 *This class holds the current properties of the game objects in the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November 20, 2022
 */
package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import game.enemies.*;
import game.gui.GameOver;

public class State {

    protected List<GameObject> currentFrameGameObjects;
    protected List<GameObject> nextFrameGameObjects;
    private int health = 100;
    private int money = 300;
    private  int score = 0;
    protected boolean gameOver;
    protected Control control;

    protected double elapsedTime;
    protected long totalTime;
    private long startTime = System.currentTimeMillis();
    private long lastStartTime = startTime;
    private long currentStartTime;


    // Constructor
    public State(Control control)
    {
        currentFrameGameObjects = new ArrayList<GameObject>();
        this.control = control;
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
        currentStartTime = System.currentTimeMillis();
        elapsedTime = ((double)(currentStartTime - lastStartTime))/1000;
        lastStartTime = currentStartTime;
        totalTime = (currentStartTime - startTime)/1000;
//        System.out.println("elapsedTime: " + elapsedTime);
//        System.out.println("totalTime: " + totalTime);


        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
    }
    
    /**
     * Replaces the current frame list with the list we prepared for the next frame.
     */
    public void finishFrame ()
    {
    	for (GameObject go: currentFrameGameObjects)
    		if (go.isExpired)
    			nextFrameGameObjects.remove(go);
    	//System.out.println(nextFrameGameObjects);	//To test if enemy was removed at end of path
        currentFrameGameObjects = nextFrameGameObjects;
    }
    
    /**
     * Adds a game object to the next frame of the game.
     * 
     * @param go the game object to be added
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }

    /**
     * Accessor method to gets the amount of health held by the user.
     *
     * @return health currently held by the user
     */
    public int getHealth() {return this.health;}

    /**
     * Method that changes the player's health.
     * 
     * @param i the amount of health to be added or removed
     */
    public void changeHealth(int i)
    {
        health += i;
        if (health <= 0)
        {
            gameOver = true;
            addGameObject(new GameOver(this, control));  	// Display Game Over
        }
    }

    /**
     * Accessor method to gets the amount of money held by the user.
     *
     * @return money currently held by the player
     */
    public int getMoney() {return this.money;}

    /**
     * Method that changes the player's money.
     * 
     * @param i the amount of money to be added or removed
     */
    public void changeMoney(int i) {money += i;}
    
    /**
     * Accessor method to gets the user's score.
     *
     * @return the player's score
     */
    public int getScore() {return this.score;}

    /**
     * Method that changes the player's score.
     * 
     * @param i the points to be added to or removed from score
     */
    public void changeScore(int i) {score += i;}

    public Enemy findNearestEnemy(Point loc)
    {
        double smallestDistance = 1000;
        double distance;
        Enemy nearestEnemy = null;
        for (GameObject go : getFrameObjects())
            if (go instanceof Enemy)
            {
                Enemy e = (Enemy) go;
                distance = Math.sqrt(Math.pow((e.getPosition().x - loc.x),2) + Math.pow((e.getPosition().y - loc.y),2));
                if (distance < smallestDistance)
                {
                    smallestDistance = distance;
                    nearestEnemy = e;
                }
            }
        return nearestEnemy;
    }
}
