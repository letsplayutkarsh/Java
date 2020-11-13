package countingApp;

import display.Display;
import widgets.Button;
import widgets.Location;

public class Toggle extends Button {

    private String tState = "Up";
    Toggle(int width ,int height)
    {
        super(width,height);
    }
	
	
	// redefines draw so that it draws a circular outline 
	// (instead of the default rectangular outline of Button)
	// "position" of Toggle is defined as the lower left corner of the
	// box that would enclose the circle

    @Override
    public void onClick(Location p) {

        if(p.getX() >= getPos().getX() && p.getX() <= getPos().getX() + getWidth()
                && p.getY() >= getPos().getY() && p.getY()<= getPos().getY() + getHeight())
        {
            handleClick();
        }

    }

    @Override
    public void handleClick() {

        if(tState.equals("Up"))
        {
            tState = "Down";
        } else {
            tState = "Up";
        }
        draw();

    }

    @Override
    public void draw() {
        Display.drawCircle(getPos().getX() + (getWidth()/2),getPos().getY() + (getHeight()/2),getHeight()/2 );
        super.setText(tState);
        super.draw();

    }
    // Toggles its state on each click.
	// Displays the text "Up" or "Down" accordingly
	

	// has a method to get its current state
	public String gettState()
    {
        return tState;
    }
	
}
