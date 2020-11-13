import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;


public class TestClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n,x,y,no;
        String name;
        float width;
        ArrayList<Location> locationList = new ArrayList<Location>();
        ArrayList<Road> roadList= new ArrayList<Road>();
        ArrayList<Route> routeList = new ArrayList<Route>();

        n = scanner.nextInt();
        for(int i=0; i<n; i++){
            x = scanner.nextInt();
            y = scanner.nextInt();
            name = scanner.next();

            Point point = new Point(x, y);
            Location location = new Location(name,point);
            locationList.add(location);
        }

        n = scanner.nextInt();
        for(int i=0; i<n; i++){
            x = scanner.nextInt();
            y = scanner.nextInt();
            name = scanner.next();
            width = scanner.nextFloat();

            Location l1 = locationList.get(x);
            Location l2 = locationList.get(y);

            Road road = new Road(l1, l2, name, width);
            roadList.add(road);
        }

        n = scanner.nextInt();
        for(int i=0; i<n; i++){
            ArrayList<Road> routeRoadList= new ArrayList<Road>();
            no = scanner.nextInt();
            for(int j=0; j<no; j++){
                x = scanner.nextInt();
                routeRoadList.add(roadList.get(x));
            }
            Route route = new Route(routeRoadList);
            routeList.add(route);
        }

        for(int i=0; i<routeList.size(); i++){

            Location startLocation = routeList.get(i).getRoadList().get(0).getLocations()[0];
            Location endLocation;
            String nameOfRoad,length_,width_;
            int start = 0;
            int end;

            if(routeList.get(i).isConnected()){

                length_ = String.format("%.2f",routeList.get(i).getLengthOfRoute());
                width_ = String.format("%.2f",routeList.get(i).minWidth());


                System.out.print("Route "+ (i+1) +": Length " + length_ + ", max width " +
                                width_ + ": Start at " + startLocation.getLocationName()
                                + ". ");

                for(int j=0; j<routeList.get(i).getRoadList().size(); j++){

                    endLocation = routeList.get(i).getRoadList().get(j).getLocations()[1];

                    if(startLocation == endLocation){
                        endLocation = routeList.get(i).getRoadList().get(j).getLocations()[0];
                    }
                    startLocation = endLocation;

                    nameOfRoad = routeList.get(i).getRoadList().get(j).getRoadname();

                    System.out.print("Follow " + nameOfRoad + " to " + endLocation.getLocationName() + ". ");
                }
                System.out.println();
            }
            else{
                System.out.print("Route "+ (i+1) +": Invalid route");
                System.out.println();
            }
        }
    }
}
