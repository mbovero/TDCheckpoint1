/**
 * This class holds information that represents a path (a sequence of points). It also
 * has the capability of adding points, getting x/y coordinates, getting the total number of points,
 * formatting the object to a string format, and drawing such objects using methods.
 * 
 * @author Miles Bovero, Kirt Robinson
 * @version November 8, 2022
 */
package path;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Path {

	private ArrayList<Point> points;
	
	/**
	 * Constructor - creates a path object with an empty array of points
	 */
	public Path()
	{
		points = new ArrayList<Point>(0);
	}
	
	/**
	 * Constructor - creates a path object with an array of points read in from a scanner
	 */
	public Path(Scanner scnr)
	{
		int l = scnr.nextInt();
		points = new ArrayList<Point>(l);		// Creates array list of length 'l' - specified in file
		for (int i=0; i<l; i++)					// Add each point to the array of points
			points.add(new Point(scnr.nextInt(), scnr.nextInt()));
	}
	
	
    /**
     * Returns the number of points in the path's ArrayList of points
     * 
     * @return the number of points in the path
     */
	public int getPointCount()
	{
		return points.size();
	}
	
    /**
     * Returns the y coordinate of the point at index n of the path's ArrayList of points
     * 
     * @param n the index of the desired point (starting at 0)
     */
	public int getX(int n)
	{
		return points.get(n).x;
	}
	
    /**
     * Returns the y coordinate of the point at index n of the path's ArrayList of points
     * 
     * @param n the index of the desired point (starting at 0)
     */
	public int getY(int n)
	{
		return points.get(n).y;
	}
	
    /**
     * Adds a new point to the path's ArrayList of points
     * 
     * @param x the point's x coordinate
     * @param y the point's y coordinate
     */
	public void add(int x, int y)
	{
		points.add(new Point(x, y));
	}
	
    /**
     * Formats and returns a string containing the number of points in the path followed by
     * each point's x and y coordinate.
     * 
     * @return String formatted with point count and data
     */
	public String toString()
	{
		String s = "";
		s += this.getPointCount();			// Add total point count to string
		
		for (int i=0; i<points.size(); i++)	// Add all x & y coordinates for each point
			s += "\n" + this.getX(i) + " " + this.getY(i);
		
		return s;
	}
	
    /**
     * Draws a path using the points from the path object's ArrayList of points
     * 
     * @param g
     */
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		for (int i=0; i<this.getPointCount()-1; i++)
			g.drawLine(this.getX(i), this.getY(i), this.getX(i+1), this.getY(i+1));
	}
}
