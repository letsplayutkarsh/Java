package b;
import a.Lab;
import a.Dispatch;
import java.util.ArrayList;

public class BioChemLab extends Lab {

	private static String report;
	private static String testId;
	private static int cost = 400;
	private static int bioChemTestCount = 1;
	private static ArrayList<BioChemTest> bioChemTestlist = new ArrayList<BioChemTest>();



	public void addTest(BioChemTest test) {
		// generates testId, the report,
		testId = generateTestId();
		report = generateReport();
		// test.setResult(report);

		super.addLabTest(test);

		bioChemTestCount++;
		// and then sends it to Dispatch using processReport
		Dispatch.processReport(test);
		bioChemTestlist.add(test);
	}

	public int getBioChemTestCount(){
		return bioChemTestCount;
	}

	public static String generateTestId(){
		return ("B" + String.valueOf(bioChemTestCount));
	}

	public static String generateReport(){
		return String.valueOf(100+bioChemTestCount-1);
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

	public static ArrayList<BioChemTest> getBioChemTestList(){
		return bioChemTestlist;
	}
}
