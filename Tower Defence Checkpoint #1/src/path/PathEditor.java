/**
 * This class holds code that displays a program in which paths can be created point by point
 * on a background. The program also allows for the saving and loading of paths.
 * 
 * @author Miles Bovero, Kirt Robinson
 * @version November 8, 2022
 */
package path;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public class PathEditor extends JPanel implements Runnable, MouseListener, ActionListener 
{

    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem clear;
    BufferedImage backdrop;
    Path path;
	
    /**
	 * Constructor - creates a PathEditor object
	 */
    public PathEditor ()
    {
    	path = new Path();
    }
    
	public static void main(String[] args) 
	{
		 SwingUtilities.invokeLater(new PathEditor());
	}
	
	/**
	 * Runs the PathEditor program, displaying the window, background, and menus.
	 */
	public void run()
	{
		System.out.println("PathEditor is running!");
		
        JFrame f = new JFrame("Path Editor 2022");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Window setup/display
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(this.getMinimumSize());
        
        // Menu setup
        JPanel topLevel = new JPanel();
        topLevel.setLayout(new BorderLayout());  
        topLevel.add(this,BorderLayout.CENTER);
        topLevel.add(new JButton("Button"), BorderLayout.SOUTH);
        
        f.setContentPane(this);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        clear = new JMenuItem("Clear");
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(clear);
        //Register 'this' as the listener to load and save menu items
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        clear.addActionListener(this);
        // Put the menu bar in the frame.
        f.setJMenuBar(menuBar);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        this.addMouseListener(this);
        
        // Load backdrop path image
        try 
        {
            backdrop = javax.imageio.ImageIO.read(new File("path_2.jpg"));
        } 
        catch (IOException e) 
        {
            System.out.println("Backdrop failed to load.");
        }
	}

	/**
	 * Paints the path and background
	 * 
	 * @param g graphics object
	 */
    public void paint (Graphics g)
    {
    	g.drawImage(backdrop, 0, 0, null);
    	path.draw(g);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Detects when the mouse is pressed, gets the x/y coordinates, adds it to the
	 * ArrayList of points, and repaints the path.
	 * 
	 * @param e mouse event
	 */
	@Override
	public void mousePressed(MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();
		path.add(x, y);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		//System.out.println("Added new point at: " + e.getX() + " " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Detects when a menu item is selected and executes the necessary 
	 * code to save or load paths.
	 * 
	 * @param e action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == loadItem) {
			//System.out.println("Load has been clicked.");
			loadPath();
		}
		if (e.getSource() == saveItem) {
			//System.out.println("Save has been clicked.");
			savePath();
		}
		if (e.getSource() == clear) {
			clearPath();
		}
	}
	
	/**
	 * A method that loads a previously generated path from a text file, then repaints
	 */
	private void loadPath() {
		// Ask the user for a file to load, restrict their choices to .txt files
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("files containing path info", "txt");
		chooser.setFileFilter(filter);
		int result = chooser.showOpenDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return; // Bail out: user cancelled
		
		// Get the file the user selected
		File f = chooser.getSelectedFile();
		
		// Load the shapes
		try {
			// Create new path with by sending in scanner
			Scanner in = new Scanner(f);
			path = new Path(in);
		} catch (IOException e) {
			System.out.println("Error loading.");
		}
		repaint();
	}

	/**
	 * A method that saves a drawn path as a specifically formatted text file.
	 */
	private void savePath() {
		// Ask the user for a save filename
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result != JFileChooser.APPROVE_OPTION)
			return; // Bail out - user cancelled
		File f = chooser.getSelectedFile();
		
		//make sure the filename ends in .txt, if not, add .txt to the end of the filename.
		String completeFilePath = f.getAbsolutePath();
		if (!completeFilePath.endsWith(".txt"))
			f = new File(completeFilePath + ".txt");
		// Save the file.
		try {
			PrintWriter out = new PrintWriter(f);
			out.print(path);
			
			out.close();
		} catch (IOException e) {
			System.out.println("Error saving.");
		}
	}
	
	private void clearPath() {
		path = new Path();
		repaint();
	}
}
