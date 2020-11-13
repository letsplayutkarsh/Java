package demo19070;

import base.Truck;
import base.Highway;
import base.Location;
import base.Hub;

class TruckDemo extends Truck {

	private int        totalTime;
	private static int truckIndex = 0;
	private int        truckId;
	private int        distOnHighway;
	private Highway    currentHighway;
	private boolean    justStarted;
	private boolean    hasReached;
	private boolean    waitingAtHub;
	private Hub        lastHub;

	TruckDemo(){
		super();
		this.totalTime      = 0;
		this.currentHighway = null;
		this.distOnHighway  = 0;
		this.lastHub        = null;
		this.truckId        = this.truckIndex++;
		this.justStarted    = true;
		this.hasReached     = false;
		this.waitingAtHub   = false;
		
	}	

	@Override
	protected synchronized void update(int deltaT) {
		this.totalTime += deltaT;

		if(this.totalTime < this.getStartTime()) {
			return;
		}

		if(this.hasReached) { 
			return;
		}

		if(this.getLoc().getX() ==  getDest().getX() && this.getLoc().getY() == getDest().getY()){ 
			System.out.println(this.getTruckName() + " has reached destination " + Integer.toString(getDest().getX()) + " " + Integer.toString(getDest().getY())) ;
			this.hasReached = true;
		}
		
		if(this.waitingAtHub){
			return;
		}
		
		
		if(this.justStarted){
			// truck gets assigned to nearest hub to the nearest hub 
			Hub first = NetworkDemo.getNearestHub(this.getLoc());
			first.add(this);
			this.setLoc(first.getLoc());
			this.justStarted = false;
		}

		if(this.distOnHighway >= this.getHwyLength()){ 
			// To check if Truck has traversed a highway
			if(this.currentHighway.getEnd().add(this)){
				this.currentHighway.remove(this);
				this.distOnHighway = 0;
				this.setLoc(this.currentHighway.getEnd().getLoc());
				this.waitingAtHub  = true;
			}
		}

		else{
			if(this.currentHighway == null) return;
			// calulate the distance it travels in deltaT 
			// Update the new location of truck
			
			double deltaX   = this.currentHighway.getEnd().getLoc().getX() - this.currentHighway.getStart().getLoc().getX();
			double deltaY   = this.currentHighway.getEnd().getLoc().getY() - this.currentHighway.getStart().getLoc().getY();

			double distance = (double)(this.currentHighway.getMaxSpeed() * (Double.valueOf(deltaT) / 500));

			int newX = (int)Math.round(this.getLoc().getX() +  distance *  Math.cos(Math.atan2(deltaY,deltaX))); 
			int newY = (int)Math.round(this.getLoc().getY() +  distance *  Math.sin(Math.atan2(deltaY,deltaX)));

			this.distOnHighway += this.currentHighway.getMaxSpeed() * (Double.valueOf(deltaT) / 500);

			this.setLoc(new Location(newX, newY));
		}
	

	}

	@Override
	public String getTruckName(){
		return  "19070_" + Integer.toString(this.truckId);
	}

	@Override
	public Hub getLastHub(){
		return this.lastHub;
	}

	@Override
	public void enter(Highway hwy){
		this.currentHighway = hwy;
		this.lastHub        = this.currentHighway.getStart();
		this.waitingAtHub   = false;
	}

	private int getHwyLength(){
		if(this.currentHighway == null) {
			return Integer.MAX_VALUE;
		}

		Location startHyw = this.currentHighway.getStart().getLoc();
		Location endHyw   = this.currentHighway.getEnd().getLoc();

		return (int) Math.round(Math.sqrt(startHyw.distSqrd(endHyw)));
	}

}
