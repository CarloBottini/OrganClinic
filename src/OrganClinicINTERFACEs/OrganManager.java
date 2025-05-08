package OrganClinicINTERFACEs;

import OrganClinicPOJOs.Organ;

public interface OrganManager {

	public void addOrgan(Organ organ);
	public void modifyOrgan(Organ o);
	public void deleteOrgan(Integer id);
	public Organ getOrganByID(Integer id);
	
}
