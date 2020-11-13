package demo19070;

import base.*;
import java.util.ArrayList;

public class NetworkDemo extends Network {
	public NetworkDemo(){
		super();
		this.trucks   = new ArrayList<Truck>();
		this.highways = new ArrayList<Highway>();
		this.hubs     = new ArrayList<Hub>();
	}

	@Override
	public void add(Hub hub){
		this.hubs.add(hub);
	}

	@Override
	public void add(Highway hwy){
		this.highways.add(hwy);
	}
	
	@Override
	public void add(Truck truck){
		this.trucks.add(truck);
	}

	@Override
	protected Hub findNearestHubForLoc(Location loc){
		int minDistance = Integer.MAX_VALUE;
		Hub nearestHub = null; 
		for(Hub hub: hubs){
			Location hubLoc = hub.getLoc();
			if(loc.distSqrd(hubLoc) < minDistance){
				minDistance = loc.distSqrd(hubLoc);
				nearestHub = hub;
			}
		}
		
		return nearestHub;
	}

	@Override
	public void redisplay(Display disp){
		for(Hub hub: hubs){
			hub.draw(disp);
		} 

		for(Highway hwy: highways) {
			hwy.draw(disp);
		}

		for(Truck truck: trucks) {
			truck.draw(disp);
		}
	}

	@Override
	public void start(){
		for(Hub hub: hubs){
			hub.start();
		} 

		for(Truck truck: trucks) {
			truck.start();
		}
	}

	private ArrayList<Truck> trucks;
	private ArrayList<Highway> highways;
	private ArrayList<Hub> hubs;
}