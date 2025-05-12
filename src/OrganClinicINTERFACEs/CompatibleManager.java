package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Compatible;

public interface CompatibleManager {

	
	public void addCompatible(Compatible c);
    public void deleteCompatibleByPatientAndOrgan(int patientId, int organId);
}
