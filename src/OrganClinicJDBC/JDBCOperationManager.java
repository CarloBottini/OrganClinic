package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.OperationManager;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;

public class JDBCOperationManager implements OperationManager{

private JDBCManager manager;
	
	
	public JDBCOperationManager(JDBCManager m) {
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
			System.out.println("Error in the database while scheduling an operation");
			e.printStackTrace();
		}	
	}
	
	
	
	  public void rescheduleOperation(Operation operation) {
		try {
			String query = "UPDATE Operation SET isDone= ?, date= ?, patient_id= ?, treatment_id= ?, doctor_id= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setBoolean(1,operation.getIsDone());
			prep.setDate(2,operation.getDate());
			prep.setInt(3,operation.getPatient_id());
			prep.setInt(4,operation.getTreatment_id());
			prep.setInt(5,operation.getDoctor_id());
			prep.setInt(6,operation.getId());

	        prep.executeUpdate();
	        prep.close();
	        System.out.println("Operation with ID " + operation.getId() + " rescheduled successfully.");
		}catch(Exception e){
	        System.out.println("Error in the database rescheduling an operation");
			e.printStackTrace();
			}	
	}
	
	  //we need to insert into HAS but also update the nurse availability
	  public void assignedNurseToOperation(Integer nurse_id, Integer operation_id) {
		  try {
			  //HAS table
				String template = "INSERT INTO Has (nurse_id, operation_id) VALUES (?,?)";
				PreparedStatement assignPrep = manager.getConnection().prepareStatement(template);
				assignPrep.setInt(1, nurse_id);
				assignPrep.setInt(2, operation_id);
				assignPrep.executeUpdate();
				assignPrep.close();
			//UPDATE nurse availability	
				String updateAvailability = "UPDATE Nurse SET availability = false WHERE id = ?";
		        PreparedStatement availPrep = manager.getConnection().prepareStatement(updateAvailability);
		        availPrep.setInt(1, nurse_id);
		        availPrep.executeUpdate();
		        availPrep.close();
				
				
			} catch (Exception e) {
				System.out.println("Error in the database while assigning a nurse to operation");
				e.printStackTrace();
			}


		  }
	  
	//DELETE HAS and nurse availability set TRUE
		public void unassignedNurseToOperation(Integer nurse_id, Integer operation_id) {
		    try {
		        String template = "DELETE FROM Has WHERE nurse_id = ? AND operation_id = ?";
		        PreparedStatement deletePrep  = manager.getConnection().prepareStatement(template);
		        deletePrep.setInt(1, nurse_id);
		        deletePrep.setInt(2, operation_id);		       
		        deletePrep.executeUpdate();
		        deletePrep.close();
		        
		        String updateAvailability = "UPDATE Nurse SET availability = true WHERE id = ?";
		        PreparedStatement availPrep = manager.getConnection().prepareStatement(updateAvailability);
		        availPrep.setInt(1, nurse_id);
		        availPrep.executeUpdate();
		        availPrep.close();
		        
		    } catch (Exception e) {
		        System.out.println("Error in the database while unassigning nurse from operation");
		        e.printStackTrace();
		    }
		}

		public List<Operation> getOperationsByPatientId(Integer patientId) {
		    List<Operation> operations = new ArrayList<>();
		    try {
		        String sql = "SELECT * FROM Operation WHERE patientId = ?";
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setInt(1, patientId);
		        ResultSet rs = prep.executeQuery();

		        while (rs.next()) {
		            Integer id = rs.getInt("id");
		            Boolean isDone= rs.getBoolean("isDone");
		            Date date = rs.getDate("date");
		            Integer patient_id =rs.getInt("patient_id");
		            Integer treatment_id = rs.getInt("treatment_id");
		            Integer doctor_id = rs.getInt("doctor_id");
		            operations.add(new Operation(id,isDone, date,patient_id, treatment_id, doctor_id));
		        }
		        prep.close();
		        rs.close();
		    } catch (Exception e) {
		        System.out.println("Error in the database while getting operations from patient:  " + patientId);
		        e.printStackTrace();
		    }
		    return operations;
		}
		
		
		public Operation getOperationByID(Integer operationId) {
			Operation operation = null;
		    try {
		        String sql = "SELECT * FROM Operation WHERE id = ?";
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setInt(1, operationId);
		        ResultSet rs = prep.executeQuery();

		        if (rs.next()) {
		            Integer id = rs.getInt("id");
		            Boolean isDone= rs.getBoolean("isDone");
		            Date date = rs.getDate("date");
		            Integer patient_id =rs.getInt("patient_id");
		            Integer treatment_id = rs.getInt("treatment_id");
		            Integer doctor_id = rs.getInt("doctor_id");
		            operation = new Operation(id, isDone,date,patient_id, treatment_id, doctor_id);
		        }
		        prep.close();
		        rs.close();
		    } catch (Exception e) {
		        System.out.println("Error in the database while getting one operation by the patient id " + operationId);
		        e.printStackTrace();
		    }
		    return operation;
		}
	
	
}
