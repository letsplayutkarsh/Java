// package p2;
// import p1.Line;

public class Road{

    private Location l1,l2;
    private String roadName;
    private double width;
    private Line lineForRoad;
    private Location[] endPoints = new Location[2];


    public Road(Location l1, Location l2, String roadName, double width){
        this.roadName = roadName;
        this.width = width;
        this.l1 = l1;
        this.l2 = l2;

        endPoints[0] = this.l1;
        endPoints[1] = this.l2;
        lineForRoad = new Line(l1.getPoint(), l2.getPoint());
    }

    public double getWidth(){
        return this.width;
    }

    public Line getLineForRoad(){
        return this.lineForRoad;
    }

    public String getRoadname(){
        return this.roadName;
    }

    public Location[] getLocations(){
        return endPoints;
    }
}
