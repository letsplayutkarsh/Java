package demo19070;

import java.util.*;
import base.Highway;
import base.Location;
import base.Hub;
import base.Truck;

class HubDemo extends Hub {

	private List<Truck> trucks;

	public HubDemo(Location loc) {
		super(loc);
		this.trucks = Collections.synchronizedList(new ArrayList<Truck>());	
	}	

	@Override
	public synchronized boolean add(Truck truck) {
		if(this.trucks.size() >= this.getCapacity()) return false;
		this.trucks.add(truck);
		return true;
	}

	@Override
	public synchronized void remove(Truck truck) {
		this.trucks.remove(truck);
	}

	private synchronized int BFS (Highway src,Hub destination){	
		// given a hyw returns -1 if there exists no path to dest hub
		// if path exists returns the minimum steps(using BFS) taken to reach their
		LinkedList<Highway> queue        = new LinkedList<>(); 
		Map <Highway, Integer> distance  = new HashMap<>(); 
		Set <Highway> visited            = new HashSet<Highway>(); 

		Highway hwy = null;

		distance.put(src,1);
		visited.add(src);
		queue.add(src);

		while(queue.size() != 0){

			hwy = queue.remove(); 

			if(hwy.getEnd() == destination){
				return distance.get(hwy);
			}

			Hub end = hwy.getEnd();

			for(Highway hw: end.getHighways()){
				if(!visited.contains(hw)){
					queue.add(hw); 
					visited.add(hw);
					distance.put(hw, distance.get(hwy) + 1);
				}		
			}
		}

		return -1;
	}
	
	@Override
	public synchronized Highway getNextHighway(Hub from, Hub dest){
		// checks which highway to go to next
		int minDistance  = Integer.MAX_VALUE;
		Highway nextPath = null; 

		for(Highway hwy: this.getHighways()){

			int bfs_dist = BFS(hwy,dest);
			
			if((hwy.getStart() == this) && bfs_dist > 0 && bfs_dist < minDistance ){
				minDistance = bfs_dist;
				nextPath = hwy;	
			}
		}
		
		return nextPath;
	}

	@Override
	protected synchronized void processQ(int deltaT) {
		// Goes through all trucks in a Hub
		// if it is in the final hub puts the truck to final Dest
		// Finds a highway tries to push it
		// If no Highway then puts the truck to final Dest
		ArrayList <Truck> toRemove = new ArrayList<>(); 
		for(Truck truck : trucks){
			Hub destination = NetworkDemo.getNearestHub(truck.getDest());

			int destX = NetworkDemo.getNearestHub(truck.getDest()).getLoc().getX();
			int destY = NetworkDemo.getNearestHub(truck.getDest()).getLoc().getY();

			if( destX == this.getLoc().getX() && destY == this.getLoc().getY() ){ 
				truck.setLoc(truck.getDest());
				toRemove.add(truck);
			}
			else{

				Highway nextHyw = getNextHighway(this, destination);			

				if(nextHyw == null){ // if there exists no road then set it to destination()
					truck.setLoc(truck.getDest());
					toRemove.add(truck);
					continue;
				}

				if(nextHyw.add(truck)){
					truck.enter(nextHyw);
					toRemove.add(truck);
					
				}
			}
		}

		for(Truck truck : toRemove){
			this.remove(truck);
		}
	}

	
}
