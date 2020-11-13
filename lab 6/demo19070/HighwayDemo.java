package demo19070;

import base.Highway; import base.Truck; 
import java.util.ArrayList;

class HighwayDemo extends Highway {

	private ArrayList<Truck> trucks;

	public HighwayDemo() {
		this.trucks = new ArrayList<Truck>();
	}

	@Override
	public boolean hasCapacity() {
		return this.trucks.size() < this.getCapacity();
	}

	@Override
	public synchronized boolean add(Truck truck) {
		if(!this.hasCapacity()) return false;
		this.trucks.add(truck);
		return true;
	}

	@Override
	public synchronized void remove(Truck truck) {
		this.trucks.remove(truck);
	}

}
