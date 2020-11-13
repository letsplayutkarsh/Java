package a;

import b.BioChemLab;
import b.BioChemTest;
import b.PathLab;
import b.PathLabTest;
import c.Lab4B;

public class Reception {

	private String testType,patientName;
	private int patientId ;
	private int no = 1;
	LabTest test;


	public boolean addRequest(String testType, String patientName, Billing bill) {

		this.testType = testType;
		this.patientName = patientName ;
		this.patientId = generatePatientId();

		// should create the appropriate sub-type of LabTest,
		if(testType.equals("Bio")){
			test = new BioChemTest(String.valueOf(this.patientId));
		}
		else{
			test = new PathLabTest(String.valueOf(this.patientId));
		}

		// add that to Billing
		bill.billTest( test );

		// then to the specific lab, after generating a patient ID
		if(testType.equals("Bio")){
			Lab4B.getBioChemLab().addTest((BioChemTest)test);
		}
		else if(testType.equals("Path")){
			Lab4B.getPathLab().addTest((PathLabTest)test);
		}

		return false;
	}

	private int generatePatientId(){
		return no++;
	}

}
