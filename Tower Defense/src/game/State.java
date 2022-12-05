/**
 *This class holds the current properties of the game objects in the Tower Defense game.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version December 3, 2022
 */
package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import game.enemies.*;
import game.gui.GameOver;
import path.Path;

public class State {

    protected List<GameObject> currentFrameGameObjects;             // The game objects currently being displayed
    protected List<GameObject> nextFrameGameObjects;                // The game objects to be displayed next
    private int health = 100;                                       // The user's starting health
    private int money = 300;                                        // The user's starting amount of money
    private  int score = 0;                                         // The user's starting score
    protected boolean gameOver;                                     // Whether the user has lost and the game is over
    protected Control control;

    protected double elapsedTime;                                   // Time elapsed since the last frame
    protected long totalTime;                                       // Total game run time
    private long startTime = System.currentTimeMillis();            // The time at which the game was started
    private long lastStartTime = startTime;                         // The last time a frame was updated
    private long currentStartTime;                                  // The time the current frame was updated


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

        nextFrameGameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameGameObjects.addAll(currentFrameGameObjects);  // Add all the current ones to the new list.  This is more clear
    }
    
    /**
     * A method that replaces the current frame list with the list
     * that's prepared for the next frame.
     */
    public void finishFrame ()
    {
    	for (GameObject go: currentFrameGameObjects)
    		if (go.isExpired)
    			nextFrameGameObjects.remove(go);
        currentFrameGameObjects = nextFrameGameObjects;
    }
    
    /**
     * A method that adds a game object to the next frame of the game.
     * 
     * @param go the game object to be added
     */
    public void addGameObject (GameObject go)
    {
        nextFrameGameObjects.add(go);
    }

    /**
     * An accessor method that gets the amount of health held by the user.
     *
     * @return health currently held by the user
     */
    public int getHealth() {return this.health;}

    /**
     * A method that changes the player's health.
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
     * An accessor method that gets the amount of money held by the user.
     *
     * @return money currently held by the player
     */
    public int getMoney() {return this.money;}

    /**
     * A method that changes the player's money.
     * 
     * @param i the amount of money to be added or removed
     */
    public void changeMoney(int i) {money += i;}
    
    /**
     * An accessor method that gets the user's score.
     *
     * @return the player's score
     */
    public int getScore() {return this.score;}

    /**
     * A method that changes the player's score.
     * 
     * @param i the points to be added to or removed from score
     */
    public void changeScore(int i) {score += i;}

    /**
     * A method that determines which enemy is nearest to the
     * provided x/y coordinate.
     *
     * @param loc the point from which the nearest enemy is found
     * @return the nearest Enemy object
     */
    public Enemy findNearestEnemy(Point loc)
    {
        // Setup
        double smallestDistance = 1000;             // Used to calculate the smallest distance
        double distance;                            // Stores length between points
        Enemy nearestEnemy = null;                  // The nearest enemy

        // Iterate through current game objects and find the closest enemy
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

    /**
     * A method that determines which enemy is nearest to the
     * provided x/y coordinate AND within the given range.
     *
     * @param loc the point from which the nearest enemy is found
     * @return the nearest Enemy object
     */
    public Enemy findNearestEnemy(Point loc, double range)
    {
        // Setup
        double smallestDistance = 1000;             // Used to calculate the smallest distance
        double distance;                            // Stores length between points
        Enemy nearestEnemy = null;                  // The nearest enemy

        // Iterate through current game objects and find the closest enemy
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
        distance = Math.sqrt(Math.pow((nearestEnemy.getPosition().x - loc.x),2) + Math.pow((nearestEnemy.getPosition().y - loc.y),2));
        if (distance < range)
            return nearestEnemy;
        return null;
    }


    /**
     * A method that determines which point along the path
     * is nearest to the provided x/y coordinate.
     *
     * @param loc the point from which the nearest path point is calculated
     * @return the nearest point on the path
     */
    public Point findNearestPathPoint(Point loc)
    {
        // Setup
        double smallestDistance = 1000;             // Used to calculate smallest distances
        double distance;                            // Stores length between points
        Point point1 = new Point();                 // First-smallest path point
        Point point2 = new Point();                 // Second-smallest path point
        Point nearestPoint = new Point();           // Overall nearest point to tower
        Path path = control.getPath();              // Path that points are taken from

        // Find the path segment (2 points) that is closest to the tower
        for (int i=0; i<path.getPointCount()-1; i++)
            {
                distance = Math.sqrt(Math.pow((path.getX(i) - loc.x),2) + Math.pow((path.getY(i) - loc.y),2));
                if (distance < smallestDistance)
                {
                    smallestDistance = distance;
                    point2 = new Point(path.getX(i+1),path.getY(i+1));
                    point1 = new Point(path.getX(i),path.getY(i));
                }
            }

        // Find the point along the segment that is closest to the tower (accuracy of 5 pixels)
            // Calculate segment length
        distance = Math.sqrt(Math.pow((point2.x - point1.x),2) + Math.pow((point2.y - point1.y),2));
            // Calculate how many iterations needed to accurately find nearest point
        int pathDivisions = (int)(distance/5);
        smallestDistance = 1000;    // Reset
            // Iterate along segment
        for(int i=0; i<pathDivisions; i++)
        {
            // Iterate along every 5 pixel division of the found segment and get x/y values
            double segmentPercent = (5*i)/distance;
            double x = (1-segmentPercent)*point1.x + (segmentPercent)*point2.x;
            double y = (1-segmentPercent)*point1.y + (segmentPercent)*point2.y;
            // Calculate distance from tower
            distance = Math.sqrt(Math.pow((x - loc.x),2) + Math.pow((y - loc.y),2));
            // Store smallest distance and point
            if (distance < smallestDistance)
            {
                smallestDistance = distance;
                nearestPoint = new Point((int)x,(int)y);
            }
        }
        return nearestPoint;
    }

    /**
     * An accessor method that gets the game's total run time.
     *
     * @return the game's total run time
     */
    public long getTotalTime()
    {
        return totalTime;
    }

    /**
     * A method that sets state's gameOver boolean value.
     *
     * @param b the boolean value to determine whether the game is over or not
     */
    public void setGameOver(boolean b)
    {
        this.gameOver = b;
    }

    /**
     * An accessor method that gets state's gameOver boolean value.
     *
     * @return a boolean value determining whether the game is over or not
     */
    public boolean getGameOver()
    {
        return this.gameOver;
    }

}
