// package p1;
import java.lang.Math;
import java.util.ArrayList;

public class Line {

    private Point p1,p2;
    private double length;
    private static double totalLength = 0;

    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public double getLength(){
        length = Math.sqrt( Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y, 2) );
        return length;
    }
    public static double getTotalLength(ArrayList<Line> lines){
        for( Line line : lines ){
            totalLength += line.getLength();
        }

        return totalLength;
    }
}
