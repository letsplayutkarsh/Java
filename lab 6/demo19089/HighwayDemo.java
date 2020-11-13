package demo19089;

import base.Highway;
import base.Truck;
import java.util.ArrayList;

class HighwayDemo extends Highway {

	@Override
	public boolean hasCapacity() {
        if(this.trucks.size() < this.getCapacity())
			return true;
		return false;
	}

	@Override
	public synchronized boolean add(Truck truck) {
        if(this.hasCapacity()){
            trucks.add(truck);
            return true;
        }
		return false;
	}

	@Override
	public synchronized void remove(Truck truck) {
        this.trucks.remove(truck);
	}

    private ArrayList <Truck> trucks = new ArrayList<>();

}
