package OrganClinicINTERFACEs;

import java.io.File;

import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Patient;

public interface XMLManager {

	public File patient2XML(Patient p);
	public File doctor2XML(Doctor d);
	public Doctor XML2doctor(File Fxml);
	public Patient XML2patient(File Fxml);
	public void patient2Html(Patient p);
	public void doctor2Html(Doctor d);

	
	
}
