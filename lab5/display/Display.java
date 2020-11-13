package display;


import widgets.*;

import java.util.ArrayList;

public class Display {

	public void set(Panel p, int x0, int y0) {
		// add a Panel at location x0,y0
		Location ploc = new Location(x0,y0);
		p.setPos(ploc);
		p.draw();
		
	}
	
	public void redisplay() {
		// invokes draw of panels
		
	}
	ArrayList<Clickable> clickables = new ArrayList<>();
	public void registerClickable(Clickable c) {

		clickables.add(c);

		// maintains list of Clickables that have been registered. These will
		// be notified when processClick is invoked
		
	}
	
	public void processClick(int x, int y) {  // x,y in global coordinates of Display
		// passes on to all Clickables registerd
		for(Clickable c : clickables)
		{
			Location loc = new Location(x,y);
			c.onClick(loc);
		}
		// invokes redisplay after each click
		
	}
	
	
	// methods used by other classes to actually draw on the display
	public static void drawBox(int x0, int y0, int x1, int y1) {

		System.out.println("Box from " +  x0 +", "+ y0 + " to "  + x1 +", " + y1);
		// draw a line from lower left x0,y0 to upper right x1, y1
		// for purposes of this exercise print to System.out:
		// Box from <x0>, <y0> to <x1>, <y1>
		 
		
	}
	
	public static void drawText(int x0, int y0, String s) {
		// display a string starting at position x0, y0
		// for purposes of this exercise, print to System.out:
		// Text at <x0>, <y0> : <input string s>
		System.out.println("Text at " + x0 + ", " + y0 + " : " + s);
		
	}
	
	public static void drawCircle(int cx, int cy, int radius) {

		System.out.println("Circle at " + cx + ", " + cy + " : radius " + radius);
		// draw a circle with given parameters
		// for purposes of this exercise, print to System.out:
		// Circle at <cx>, <cy> : radius <radius>
		
	}
	
	
}
