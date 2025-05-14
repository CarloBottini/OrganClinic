package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Doctor;

public interface DoctorManager {

	public List<Doctor> getAllDoctors();
	public void addDoctor (Doctor doc);
	public Doctor getDoctorByID(Integer id);
	public Doctor getDoctorByEmail(String email);
	public void modifyDoctor(Doctor doctor);
	public void deletePatient(Integer id);


	
	
}
