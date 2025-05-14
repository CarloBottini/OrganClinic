package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Nurse;

public interface NurseManager {

	public List<Nurse> getAvailableNurses();
	public Nurse getNurseByID(Integer id);
	public void addNurse(Nurse n);
	public void deleteNurse(Integer id);
	public List<Nurse> getAllNurses();
}
