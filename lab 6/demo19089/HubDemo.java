package demo19089;

import java.util.*;
import base.Highway;
import base.Highway;
import base.Hub;
import base.Location;
import base.Truck;

class HubDemo extends Hub {

	public HubDemo(Location loc) {
		super(loc);
        this.trucks = Collections.synchronizedList(new ArrayList<Truck>());
	}

	@Override
	public synchronized boolean add(Truck truck) {
        if(trucks.size() < this.getCapacity()){
            this.trucks.add(truck);
            return true;
        }
		return false;
	}

	@Override
	public synchronized void remove(Truck truck) {
        this.trucks.remove(truck);
	}

    private void DFS(Hub s, Hub d , ArrayList<Hub> visit, ArrayList<Highway> path){
        visit.add(s);
        if(d.equals(s)) return;

        for(Highway highway : s.getHighways()){
            Hub end = highway.getEnd();
            if(!visit.contains(end)){
                DFS(end,d,visit,path);
            }
            if(visit.contains(d)){
                path.add(highway);
                return ;
            }
        }
    }

	public synchronized Highway getNextHighway(Hub from, Hub dest) {
        if(this.equals(dest)) return null;
        ArrayList<Hub> visit = new ArrayList<>();
        ArrayList<Highway> path = new ArrayList<>();
        visit.add(this);
        DFS(this,dest,visit,path);
        return path.get(path.size()-1);
	}

	@Override
	protected synchronized void processQ(int deltaT) {
        ArrayList<Truck> trks = new ArrayList<>();
        for(Truck truck : trucks){
            Hub d = NetworkDemo.getNearestHub(truck.getDest());

            if(NetworkDemo.getNearestHub(truck.getDest()).getLoc().equals(this.getLoc())){
				truck.setLoc(truck.getDest());
                trks.add(truck);
            }
            else{
                Highway nextHighway = getNextHighway(this, d);

                if(nextHighway == null){
                    trks.add(truck);
                    truck.setLoc(truck.getDest());
                    continue;
                }

                if(nextHighway.hasCapacity()){
                    nextHighway.add(truck);
                    truck.enter(nextHighway);
                    trks.add(truck);
                }

            }
        }

        for(Truck truck : trks){
			this.remove(truck);
		}
	}

    private List<Truck> trucks;
}
