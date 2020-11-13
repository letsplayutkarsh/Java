package b;
import a.LabTest;

public class PathLabTest extends LabTest {

	public PathLabTest(String patientId) {
		super(patientId);
		setPrice(PathLab.getCost());
		setTestId(PathLab.generateTestId());
		setResult(PathLab.generateReport());
    }
}
