package widgets;

import display.Display;

import java.util.ArrayList;

public class Panel extends Widget {



	ArrayList<Widget> panelcomp = new ArrayList<>();
	int width, height;

	public Panel(int width, int height)
	{
		super(width, height);
	}
	
	public void addWidget(Widget w, Location loc) {

		w.setPos(loc);
		panelcomp.add(w);
		// add a child widget
		// position of lower left of child is at loc in Panel coordinates
		
	}

	@Override
	public void draw() {
		Display.drawBox(getPos().getX(),getPos().getY(),getPos().getX()+getWidth(),getPos().getY()+getHeight());
		for(Widget w : panelcomp)
		{
			w.draw();
		}
	}
	// implements/overrides draw. Draws a box corresponding to its size and
	// calls draw on each child widget

	

}
