package demo19013;

import base.*;

import java.util.ArrayList;

public class HighwayDemo extends Highway{

	private int totalTrucks = 0;
	private ArrayList<Truck> allTrucks = new ArrayList<Truck>();
	
	@Override
	public synchronized boolean hasCapacity(){
		return (getCapacity() > totalTrucks);
	}

	@Override
	public synchronized boolean add(Truck truck){
		if(this.hasCapacity()){
			allTrucks.add(truck);
			//truck.setRoute(this);
			totalTrucks++;
			return true;
		}
		return false;
	}

	public synchronized void remove(Truck truck){
		if(this.getEnd().add(truck)){
			//System.out.println(truck.getName());
			totalTrucks--;
			allTrucks.remove(truck);
		}
	}
}