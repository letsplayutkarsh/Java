package b;
import a.Lab;
import a.Dispatch;
import java.util.ArrayList;

public class PathLab extends Lab {

	private static String testId,report;
	private static int cost = 1000;
	private static int pathTestCount = 1;
	private static ArrayList<PathLabTest> pathTestlist = new ArrayList<PathLabTest>();


	public void addTest(PathLabTest test) {
		// generates testId, the report,
		testId = generateTestId();
		report = generateReport();
		// test.setResult(report);

		pathTestCount++;
		// and then sends it to Dispatch using processReport
		Dispatch.processReport(test);
		pathTestlist.add(test);
	}

	public int getPathTestCount(){
		return pathTestCount;
	}

	public static String generateTestId(){
		return ("P" + String.valueOf(pathTestCount));
	}

	public static String generateReport(){
		if(pathTestCount%3 == 1){
			return "benign";
		}
		else if(pathTestCount%3 == 2){
			return "benign";
		}
		else if(pathTestCount%3 == 0){
			return "malignant";
		}
		return "-" ;
 	}

	public static int getCost(){
		return cost;
	}

	public static String getTestId(){
		return testId;
	}
	public static String getReport(){
		return report;
	}
	public static ArrayList<PathLabTest> getPathTestList(){
		return pathTestlist;
	}
}
