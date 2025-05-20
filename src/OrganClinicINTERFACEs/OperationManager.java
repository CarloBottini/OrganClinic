package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Operation;

public interface OperationManager {

	public void scheduleOperation(Operation operation);
	public void rescheduleOperation(Operation operation);
	public void assignedNurseToOperation(Integer nurse_id, Integer operation_id);
	public void unassignedNurseToOperation(Integer nurse_id, Integer operation_id);
	public List<Operation> getOperationsByPatientId(Integer patientID);
	public Operation getOperationByID(Integer operationId);
	public List<Operation> getAllOperations();
}
