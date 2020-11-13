// package  p2;
import java.util.*;
public class Route{

    ArrayList<Road> roadList;
    private Location[] endPoints = new Location[2];
    private Location[] nextEndPoints = new Location[2];
    private Point point;
    private double totalLength = 0.0;

    public Route(ArrayList<Road> roadList){
        this.roadList = roadList;
    }

    public double getLengthOfRoute(){
        for( Road road : this.roadList ){
            totalLength += road.getLineForRoad().getLength();
        }
        return totalLength;
    }

    public ArrayList<Road> getRoadList(){
        return this.roadList;
    }

    public boolean isConnected(){


        endPoints = roadList.get(0).getLocations();
        point = endPoints[1].getPoint();
        for(int i = 0; i<roadList.size()-1; i++){
            nextEndPoints = roadList.get(i+1).getLocations();

                    if(point.x == nextEndPoints[0].getPoint().x &&
                        point.y == nextEndPoints[0].getPoint().y) {
                            point = nextEndPoints[1].getPoint();
                            continue;
                        }
                    else if(point.x == nextEndPoints[1].getPoint().x &&
                        point.y == nextEndPoints[1].getPoint().y){
                            point = nextEndPoints[0].getPoint();
                            continue;
                        }
                    else{
                        return false;
                    }
        }
        return true;
    }

    public float minWidth(){
        double minWidthValue = 999999.9;
        double currWidth = 999999.9;

        for(Road road : roadList){
            currWidth = road.getWidth();
            if(currWidth <= minWidthValue){
                minWidthValue = currWidth;
            }
        }
        return (float)minWidthValue;
    }
}
