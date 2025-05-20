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
	@Override
	public void scheduleOperation(Operation operation) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
	        prep.setInt(1, operation.getIsDone() ? 1 : 0); //1 or 0		
			prep.setString(2, operation.getDate().toString()); //doing the conversion
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
	
	
	  @Override
	  public void rescheduleOperation(Operation operation) {
		try {
			String query = "UPDATE Operation SET isDone= ?, date= ?, patient_id= ?, treatment_id= ?, doctor_id= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
	        prep.setInt(1, operation.getIsDone() ? 1 : 0); 
			prep.setString(2, operation.getDate().toString()); //doing the conversion
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
	  @Override
	  public void assignedNurseToOperation(Integer nurse_id, Integer operation_id) {
		  try {
			  //HAS table
				String template = "INSERT INTO Has (nurse_id, operation_id) VALUES (?,?)";
				PreparedStatement assignPrep = manager.getConnection().prepareStatement(template);
				assignPrep.setInt(1, nurse_id);
				assignPrep.setInt(2, operation_id);
				assignPrep.executeUpdate();
				assignPrep.close();
			//UPDATE nurse availability	0 equals false
				String updateAvailability = "UPDATE Nurse SET availability = 0 WHERE id = ?";
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
	  	@Override
		public void unassignedNurseToOperation(Integer nurse_id, Integer operation_id) {
		    try {
		        String template = "DELETE FROM Has WHERE nurse_id = ? AND operation_id = ?";
		        PreparedStatement deletePrep  = manager.getConnection().prepareStatement(template);
		        deletePrep.setInt(1, nurse_id);
		        deletePrep.setInt(2, operation_id);		       
		        deletePrep.executeUpdate();
		        deletePrep.close();
		        
		        String updateAvailability = "UPDATE Nurse SET availability = 1 WHERE id = ?";
		        PreparedStatement availPrep = manager.getConnection().prepareStatement(updateAvailability);
		        availPrep.setInt(1, nurse_id);
		        availPrep.executeUpdate();
		        availPrep.close();
		        
		    } catch (Exception e) {
		        System.out.println("Error in the database while unassigning nurse from operation");
		        e.printStackTrace();
		    }
		}

	  	@Override
		public List<Operation> getOperationsByPatientId(Integer patientId) {
		    List<Operation> operations = new ArrayList<>();
		    try {
		        String sql = "SELECT * FROM Operation WHERE patient_id = ?";
		        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		        prep.setInt(1, patientId);
		        ResultSet rs = prep.executeQuery();

		        while (rs.next()) {
		            Integer id = rs.getInt("id");
		            Boolean isDone= rs.getBoolean("isDone");
		            Date date = Date.valueOf(rs.getString("date"));
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
		
	  	@Override
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
		            Date date = Date.valueOf(rs.getString("date")); //being consistent in the parse between ddbb and program
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
	  	@Override
	  	public List<Operation> getAllOperations() {
	  	    List<Operation> operations = new ArrayList<>();
	  	    try {
	  	        String sql = "SELECT * FROM Operation";
	  	        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
	  	        ResultSet rs = prep.executeQuery();

	  	        while (rs.next()) {
	  	            Integer id = rs.getInt("id");
	  	            Boolean isDone = rs.getBoolean("isDone");
	  	            Date date = Date.valueOf(rs.getString("date"));
	  	            Integer patient_id = rs.getInt("patient_id");
	  	            Integer treatment_id = rs.getInt("treatment_id");
	  	            Integer doctor_id = rs.getInt("doctor_id");

	  	            Operation operation = new Operation(id, isDone, date, patient_id, treatment_id, doctor_id);
	  	            operations.add(operation);
	  	        }
	  	        rs.close();
	  	        prep.close();
	  	    } catch (Exception e) {
	  	        System.out.println("Error in the database while getting all operations");
	  	        e.printStackTrace();
	  	    }
	  	    return operations;
	  	}

	
	
}
