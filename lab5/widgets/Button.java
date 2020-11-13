package widgets;

import display.Display;

public abstract class Button extends Widget implements Clickable {

	public Button(int width, int height)
	{
		super(width,height);
	}



	// has methods to get/set text
	private String text;
	public String getText()
	{
		return text;
	}

	public void setText(String s)
	{
		text = s;
	}

	@Override
	public void draw() {
		Display.drawText(getPos().getX(),getPos().getY(),text);
	}

	// implements/overrides draw to draw a box at the correct position and size
	// and displays the text of the button
	
	
	// implement the onClick method to check if the click is within its bounds
	// and if so, invokes protected method handleClick
	
	public abstract void onClick(Location p);

	
	public abstract void handleClick();


}
