package demo19013;

import java.util.ArrayList;
//import java.util.*; 
import java.lang.Math;

import base.*;

public class NetworkDemo extends Network{
	public NetworkDemo(){
		super();
		allTrucks = new ArrayList<Truck>();
		allHubs = new ArrayList<Hub>();
		allHighways = new ArrayList<Highway>();
	}

	private ArrayList<Truck> allTrucks;
	private static ArrayList<Hub> allHubs;
	private ArrayList<Highway> allHighways;

	@Override
	public void add(Truck truck){
		allTrucks.add(truck);
	}

	@Override
	public void add(Hub hub){
		allHubs.add(hub);
	}

	@Override
	public void add(Highway hwy){
		allHighways.add(hwy);
	}

	@Override
	public void start(){
		for(Truck t: allTrucks){
			t.start();
		}

		for(Hub hub: allHubs){
			hub.start();
		}
	}

	@Override
	public void redisplay(Display disp){
		for(Truck t: allTrucks){
			t.draw(disp);
		}
		for(Hub hub: allHubs){
			hub.draw(disp);
		}
		for(Highway hwy: allHighways){
			hwy.draw(disp);
		}
	}

	@Override
	protected Hub findNearestHubForLoc(Location loc){
		double minDist = Integer.MAX_VALUE;
		double tmp;
		Hub hub = null;
		for(Hub h: allHubs){
			tmp = Math.sqrt(loc.distSqrd(h.getLoc()));
			if(tmp < minDist){
				minDist = tmp;
				hub = h;
			}
		}
		return hub;
	}


	public static Hub getnearestHub(Location loc){
		double minDist = Integer.MAX_VALUE;
		double tmp;
		Hub hub = null;
		for(Hub h: allHubs){
			tmp = Math.sqrt(loc.distSqrd(h.getLoc()));
			if(tmp < minDist){
				minDist = tmp;
				hub = h;
			}
		}
		return hub;
	}

}