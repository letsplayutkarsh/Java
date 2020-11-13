// package p1;
import java.lang.Math;

public class Point {

    public double x;
    public double y;
    private double dist;
    private double[] point = new double[2];

    public Point(double x, double y){
        this.x = x;
        this.y = y;
        point[0] = this.x;
        point[1] = this.y;
    }

    public void setPoint(double x, double y){
        this.y = y;
        this.x = x;
    }

    public double[] getPoint(double x, double y){
        return point;
    }

    public double getDistance(double x2, double y2){
        dist = Math.sqrt( Math.pow(x2 - this.x,2) + Math.pow(y2 - this.y, 2) );
        return dist;
    }
}
