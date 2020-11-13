package demo19089;
import java.util.ArrayList;

import base.*;

public class NetworkDemo extends Network {

    @Override
    public void add(Hub hub){
        hubs.add(hub);
    }

    @Override
    public void add(Highway hwy){
        highways.add(hwy);
    }

    @Override
    public void add(Truck truck){
        trucks.add(truck);
    }

    @Override
    public void start(){
        for(Hub hub : hubs)
            hub.start();

        for(Truck truck : trucks)
            truck.start();
    }

    @Override
    public void redisplay(Display disp){
        for(Hub hub : hubs)
            hub.draw(disp);

        for(Truck truck : trucks)
            truck.draw(disp);

        for(Highway hwy : highways)
            hwy.draw(disp);
    }

    @Override
    protected Hub findNearestHubForLoc(Location loc){
        Hub temp = null;
        int minimumDist = 2147483647; //max int value in java

        for(Hub hub : hubs){
            if(minimumDist > hub.getLoc().distSqrd(loc)){
                temp = hub;
                minimumDist = hub.getLoc().distSqrd(loc);
            }
        }
        return temp;
    }


    private ArrayList <Hub> hubs = new ArrayList<>();
    private ArrayList <Highway> highways = new ArrayList<>();
    private ArrayList <Truck> trucks = new ArrayList<>();
}
