package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Treatment;

public interface TreatmentManager {

	public void addTreatment(Treatment treatment);
	public void modifyTreatment(Treatment treatment);
	public void deleteTreatment(Integer id);
	public List<Treatment> getAllTreatment();

	
} 
