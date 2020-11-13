package b;
import a.LabTest;

public class BioChemTest extends LabTest {
	public BioChemTest(String patientId /* and any other arguments needed */) {
		super(patientId);
		setPrice(BioChemLab.getCost());
		setTestId(BioChemLab.generateTestId());
		setResult(BioChemLab.generateReport());
    }
}
