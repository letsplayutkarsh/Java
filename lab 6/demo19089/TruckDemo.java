package demo19089;

import base.Truck;
import base.Highway;
import base.Location;
import base.Hub;

class TruckDemo extends Truck {

    TruckDemo(){
        super();
        this.truckId = this.truckIndex++;
    }

	@Override
	protected synchronized void update(int deltaT){
        this.totalTime += deltaT;
        if(this.totalTime < this.getStartTime()) return;
        if(this.waitingAtHub) return;
        if(this.reached) return;
        if(this.getLoc().getX() ==  getDest().getX() && this.getLoc().getY() == getDest().getY()){
            this.reached = true;
            System.out.println(this.getTruckName() + " has reached destination " + Integer.toString(getDest().getX()) + " " + Integer.toString(getDest().getY())) ;

        }

        if(this.leavingHub){
            Hub hub = NetworkDemo.getNearestHub(this.getLoc());
            hub.add(this);
            this.leavingHub = false;
            this.setLoc(hub.getLoc());

        }
        // if(this.getLoc().equals(this.currentHighway.getEnd().getLoc())){
        if(this.distTravel >= this.getHighwayLength()){
            if(this.currentHighway.getEnd().add(this)){
                this.currentHighway.remove(this);
                this.distTravel = 0;
                this.setLoc(this.currentHighway.getEnd().getLoc());
                this.waitingAtHub = true;
            }
        }
        else{
            if(this.currentHighway == null) return;

            int x1 = currentHighway.getStart().getLoc().getX();
            int y1 = currentHighway.getStart().getLoc().getY();
            int x2 = currentHighway.getEnd().getLoc().getX();
            int y2 = currentHighway.getEnd().getLoc().getY();

            double distance = (double)(this.currentHighway.getMaxSpeed() * (Double.valueOf(deltaT) / 500));
            double hypoteneus = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

            double dx = distance*(x2-x1)/hypoteneus;
            double dy = distance*(y2-y1)/hypoteneus;

            int x = (int)Math.round(this.getLoc().getX() + dx);
            int y = (int)Math.round(this.getLoc().getY() + dy);

            this.distTravel += this.currentHighway.getMaxSpeed() * (Double.valueOf(deltaT) / 500);
            this.setLoc(new Location(x, y));
        }
    }

    @Override
    public String getTruckName(){
        return "19089_" + Integer.toString(this.truckId);
    }

    @Override
    public Hub getLastHub(){
        return this.s;
    }

    @Override
    public void enter(Highway hwy){
        this.currentHighway = hwy;
        this.s = this.currentHighway.getStart();
        this.waitingAtHub = false;
    }

    private int getHighwayLength(){
		if(this.currentHighway == null) return 2147483646;
        int sqdist = this.currentHighway.getStart().getLoc().distSqrd(this.currentHighway.getEnd().getLoc());
		return (int) Math.round(Math.sqrt(sqdist));
	}

    private int totalTime= 0;
    private int truckId = 0;
    private int distTravel=0;
	private static int truckIndex=0;
	private Highway currentHighway = null;
	private boolean leavingHub = true;
    private boolean reached = false;
    private boolean waitingAtHub = false;
    private Hub d,s=null;
}
