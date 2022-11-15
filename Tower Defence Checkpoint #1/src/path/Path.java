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

	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (integer x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned
	 * point object (or the caller could be changing the path).
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point convertToCoordinates(double percentTraveled) 
	{
		// Find total path length
		double length = 0;
		for (int i=0; i<this.getPointCount()-1; i++)
			length += Math.sqrt(this.getX(i)*this.getX(i+1) + this.getY(i)*this.getY(i+1));
		
		// Calculate distance along entire path
		length *= percentTraveled;
		//System.out.println("The snail is at length: " + length);
		
		// Find segment the object is at
		double lengthCalc = 0;
		int segmentNum = 0; 
		for (int i=0; i<this.getPointCount()-1; i++)
		{
			lengthCalc += Math.sqrt(this.getX(i)*this.getX(i+1) + this.getY(i)*this.getY(i+1));
			if (lengthCalc >= length)
			{
				segmentNum = i;
				break;
			}
		}
		//System.out.println("The snail is at segment: " + (segmentNum + 1));
		
		// Find percentage along specific segment
		double segmentDistance = (Math.sqrt(this.getX(segmentNum)*this.getX(segmentNum+1) + this.getY(segmentNum)*this.getY(segmentNum+1)));
		double distancePastSegment = segmentDistance - (lengthCalc-length);
		double segmentPercent = (distancePastSegment)/segmentDistance;
		double x = (1-segmentPercent)*this.getX(segmentNum) + (segmentPercent)*this.getX(segmentNum+1);
		double y = (1-segmentPercent)*this.getY(segmentNum) + (segmentPercent)*this.getY(segmentNum+1);

		
		return new Point((int)x, (int)y);
	}
}
