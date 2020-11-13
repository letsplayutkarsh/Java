package a;
import b.BioChemTest;
import b.PathLabTest;

public class LabTest {
	private String patientId,testId,result;
	private int price;

	public LabTest(String patientId) {
		this.patientId = patientId;
	}   // note that this should never get called explicitly

	public String getPatientId() {
		return this.patientId;
	}

	public String getTestId() {
		return this.testId;
	}

	public int getPrice() {
		return this.price;
	}
	public String getResult() {
		return this.result;
	}


	public void setTestId(String ID){
		this.testId = ID;
	}
	public void setPrice(int cost){
		this.price = cost;
	}

	public void setResult(String result){
		this.result = result;
	}
}
