package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;

import OrganClinicINTERFACEs.OperationManager;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Organ;

public class JDBCOperationManager implements OperationManager{

private JDBCManager manager;
	
	
	private JDBCOperationManager(JDBCManager m) {
		this.manager=m;
	}
	
	//it will be similar to addOperation
	public void scheduleOperation(Operation operation) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setBoolean(1, operation.getIsDone());
			prep.setDate(2, operation.getDate());	
			prep.setInt(3, operation.getPatient_id());
			prep.setInt(4, operation.getTreatment_id());
			prep.setInt(5, operation.getDoctor_id());
			
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
