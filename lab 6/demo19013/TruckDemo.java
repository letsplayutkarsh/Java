package demo19013;

import base.*;

import java.lang.Math;

public class TruckDemo extends Truck{
	private static int number = 0;
	private String name;
	private Highway onHighway;
	private int totalTime;
	private double dist;
	private Hub lastHub;
	private boolean wait;
	private boolean atDest;
	private boolean justBegun;

	public TruckDemo(){
		super();
		number++;
		this.name = "Truck19013" + String.format("%03d", number);
		onHighway = null;
		totalTime = 0;
		dist = 0;
		lastHub = null;
		wait = false;
		atDest = false;
		justBegun = true;
	}


	@Override
	public String getTruckName(){
		return name;
	}

	@Override
	public Hub getLastHub(){
		return lastHub;
	}

	private boolean inRange(Location l1, Location l2){
		return (Math.abs(l1.getX() - l2.getX()) < 10 && Math.abs(l1.getY() - l2.getY()) < 10);
	}

	@Override
	protected synchronized void update(int deltaT){
		totalTime += deltaT;
		if(totalTime < this.getStartTime()){ return; }
		if(wait){ return; }
		if(this.inRange(this.getDest(), this.getLoc())){ atDest = true;}
		if(atDest){ return; }

		//used only in the begining
		if(justBegun){
			Hub src = NetworkDemo.getNearestHub(this.getLoc());
			if(src.add(this)){
				wait = true;
				this.setLoc(src.getLoc());
				justBegun = false;
			}
			//return;
		}

		if(dist >= Math.sqrt(onHighway.getStart().getLoc().distSqrd(onHighway.getEnd().getLoc()))){
			if(onHighway.getEnd().add(this)){
				onHighway.remove(this);
				dist = 0;
				wait = true;
				lastHub = onHighway.getEnd();
				this.setLoc(lastHub.getLoc());
			}
			return;
		}
		else{
			if(onHighway == null){
				this.setLoc(getDest());
				return;
			}
			double x = ((onHighway.getEnd().getLoc().getX() - onHighway.getStart().getLoc().getX()) * onHighway.getMaxSpeed() * deltaT) / (500 * Math.sqrt(onHighway.getStart().getLoc().distSqrd(onHighway.getEnd().getLoc())));
			double y = ((onHighway.getEnd().getLoc().getY() - onHighway.getStart().getLoc().getY()) * onHighway.getMaxSpeed() * deltaT) / (500 * Math.sqrt(onHighway.getStart().getLoc().distSqrd(onHighway.getEnd().getLoc())));
			dist += Math.sqrt(x*x + y*y);
			//System.out.println(this.getTruckName() + " " + dist);
			this.setLoc(new Location(this.getLoc().getX() + (int)x, this.getLoc().getY() + (int)y));
		}

	}

	@Override
	public synchronized void enter(Highway hwy){
		this.onHighway = hwy;
		this.wait = false;
		this.lastHub = hwy.getStart();
		this.dist = 0;
		//System.out.println("3");
	}
}