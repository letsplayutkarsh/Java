package countingApp;
import display.Display;
import widgets.Button;
import widgets.Location;


public class Counter extends Button {

    int counter =0;
    Toggle ctoggle = new Toggle(0,0);

    Counter(int width,int height,Toggle ctoggle)
    {
        super(width,height);
        this.ctoggle = ctoggle;
    }

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

        if(ctoggle.gettState().equals("Up"))
        {
            counter+=1;
        }
        else
        {
            counter-=1;
        }
        draw();


    }

    @Override
    public void draw() {


        Display.drawBox(getPos().getX(),getPos().getY(),getPos().getX()+getWidth(),getPos().getY()+getHeight());

        super.setText(Integer.toString(counter));
        super.draw();

    }
    // has a reference to an instance of Toggle button
	
	
	 // Everytime it is clicked, it increments or decrements counter by 1
	// depending on state of the Toggle instance
	

	// Shows the current count as its text
	
	
}
