package a;

import java.util.ArrayList;

public class Dispatch {

	private static ArrayList<String> allReports = new ArrayList<String>();

	public static void processReport(LabTest test) {
		// System.out.println(test.getTestId());
		allReports.add( test.getPatientId() + " " + test.getTestId() + " " +test.getResult());
	}   // called by individual labs

	public ArrayList<String> getAllResults() {
		return allReports;
	}

}
