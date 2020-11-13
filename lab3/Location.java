// package p2;
// import p1.Point;

public class Location{
    private Point point;
    private String locationName;

    public Location(String locationName, Point point){
        this.locationName = locationName;
        this.point = point;
    }

    public Point getPoint(){
        return point;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public Point getCordinates(){
        return this.point;
    }
}
