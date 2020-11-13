package a;
import b.BioChemLab;
import b.PathLab;
import java.util.ArrayList;

public class Lab {

	private ArrayList<LabTest> allTest = new ArrayList<LabTest>();

	public void addLabTest(LabTest test){
		allTest.add(test);
	}

	public ArrayList<LabTest> getTests() {
		return allTest;
	}

}
