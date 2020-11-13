package a;

import java.util.ArrayList;

public class Billing {

	private ArrayList<String> bills = new ArrayList<String>();

	public void billTest(LabTest test) {
		bills.add(test.getTestId() + " " + String.valueOf(test.getPrice()));
	}  // called by Reception

	public ArrayList<String> getBills() {
		return bills;
	} // one string for each test
	// each string has testId and billed amount, separated by a space

}
