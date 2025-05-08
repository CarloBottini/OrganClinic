package OrganClinicINTERFACEs;

import OrganClinicPOJOs.Operation;

public interface OperationManager {

	public void scheduleOperation(Operation operation);
	public void rescheduleOperation(Operation operation);
	public void assignedNurseToOperation(Integer nurse_id, Integer operation_id);
	public void unassignedNurseToOperation(Integer nurse_id, Integer operation_id);
	
}
