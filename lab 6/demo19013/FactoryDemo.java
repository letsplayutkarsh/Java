package demo19013;

import base.*;

public class FactoryDemo extends Factory{
	@Override
	public Network createNetwork(){
		return new demo19013.NetworkDemo();
	}

	@Override
	public Highway createHighway(){
		return new HighwayDemo();
	}

	@Override
	public Hub createHub(Location location){
		return new HubDemo(location);
	}

	@Override
	public Truck createTruck(){
		return new TruckDemo();
	}
}