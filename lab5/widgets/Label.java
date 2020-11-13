package widgets;


import display.Display;

public class Label extends Widget
{
    String s = "Vote up or down";
    public Label(int width,int height)
    {
        super(0,0);
    }

	// has a text string as data - which is provided at create time
	// width and height are determined from this
	// assume each character is 5 units wide and 10 units high

	// does not react to mouse events

	// provides methods to get text


    @Override
    public void draw() {
        Display.drawText(getPos().getX(),getPos().getY(),s);

    }
}
