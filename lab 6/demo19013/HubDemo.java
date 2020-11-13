package demo19013;

import base.*;

import java.util.*;

public class HubDemo extends Hub{
	private int totalTrucks = 0;
	private List<Truck> allTrucks;

	public HubDemo(Location loc){
		super(loc);
		this.allTrucks = Collections.synchronizedList(new ArrayList<Truck>());
	}

	@Override
	public synchronized boolean add(Truck truck){
		if(totalTrucks >= getCapacity()){
			return false;
		}
		totalTrucks++;
		allTrucks.add(truck);
		//tmp.updateHub(this);
		//System.out.println("aaa " + truck.getName());
		return true;
	}

	@Override
	public synchronized void remove(Truck truck){
		totalTrucks--;
		allTrucks.remove(truck);
	}

	private synchronized int depthFirstSearch(Highway srcHighway, Hub destination){
		ArrayList<Highway> visited = new ArrayList<Highway>();
		ArrayList<Highway> stack = new ArrayList<Highway>();
		Map<Highway, Integer> distance = new HashMap<>();
		Highway highway = null;
		distance.put(srcHighway, 1);
		stack.add(0, srcHighway);
		while(stack.size() > 0){
			highway = stack.remove(0);
			if(!visited.contains(highway)){
				visited.add(highway);
				if(highway.getEnd() == destination){
					return distance.get(highway);
				}
				for(Highway hwy: highway.getEnd().getHighways()){
					if(!visited.contains(hwy)){
						stack.add(0, hwy);
						distance.put(hwy, distance.get(highway) + 1);
					}
				}
			}
		}
		return -1;
	}

	@Override
	public synchronized Highway getNextHighway(Hub current, Hub dest){
		
		int minDist = Integer.MAX_VALUE;
		Highway nextHighway = null;
		for(Highway hwy: this.getHighways()){
			int dfs = this.depthFirstSearch(hwy, dest);
			if(hwy.getStart() == this && dfs > 0 && dfs < minDist){
				minDist = dfs;
				nextHighway = hwy;
			}
		}
		return nextHighway;
	}

	@Override
	protected synchronized void processQ(int delta){
		ArrayList<Truck> leftHub = new ArrayList<Truck>();
		for(Truck truck: allTrucks){
			Hub dest = NetworkDemo.getNearestHub(truck.getDest());
			if(dest.getLoc().getX() == this.getLoc().getX() && dest.getLoc().getY() == this.getLoc().getY()){
				// truck is at destination hub
				leftHub.add(truck);
				truck.setLoc(truck.getDest());
			}

			else{
				Highway nextHighway = this.getNextHighway(this, dest);
				if(nextHighway == null){
					//return;
					truck.setLoc(truck.getDest());
					System.out.println("this isn't supposed to happen");
				}
				else if(nextHighway.add(truck)){
					//System.out.println("1");
					leftHub.add(truck);
					truck.enter(nextHighway);
					//System.out.println("2");
				}
			}
		}
		for(Truck t: leftHub){
			this.remove(t);
		}
	}
}