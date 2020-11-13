package countingApp;


import display.Display;
import widgets.*;


public class CountingApp {
	Display disp;
	public CountingApp(Display d) {
		// Display object made available at construction. Cannot be changed
		disp  = d;
	}

	public void init() {
		// sets up widgets


		Panel panel = new Panel(400,200);
		Toggle toggle = new Toggle(50,50);
		Counter counter = new Counter(100,40,toggle);
		Label label = new Label(0,0);

		Location loctog = new Location(100 + 50,60+50);
		Location loccount = new Location(200+50,60+50);
		Location loclabel = new Location(150+50,150+50);


		panel.addWidget(toggle,loctog);
		panel.addWidget(counter,loccount);
		panel.addWidget(label,loclabel);

		disp.set(panel, 50, 50);

		// creates the following widgets, which are added as children of this panel
		// Note locations are relative to Panel


		disp.registerClickable(toggle);
		disp.registerClickable(counter);

		// registers those widgets that are Clickable with Display


		disp.redisplay();
	}




}
