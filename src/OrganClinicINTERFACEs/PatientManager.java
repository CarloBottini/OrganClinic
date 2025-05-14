package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Patient;

//TODO put @override in every method

public interface PatientManager {
	public void addPatient(Patient p);
	public void modifyPatient(Patient p);
	public void deletePatient(Integer id);
	public Patient getPatientByID(Integer id);
	public Patient getPatientByEmail(String email);
	public List<Patient> getAllPatients();
	public List<Patient> getPatientsByName(String name);




}
