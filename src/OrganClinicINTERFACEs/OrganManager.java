package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Organ;

public interface OrganManager {

	public void addOrgan(Organ organ);
	public void modifyOrgan(Organ o);
	public void deleteOrgan(Integer id);
	public Organ getOrganByID(Integer id);
	public List<Organ> getAllOrgans();
	
}
