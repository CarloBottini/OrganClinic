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
	/*
	 * public void addOrgan(Organ organ) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Patient (gender, typeOrgan, size, quality, bloodType) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, organ.getGender());	
			prep.setString(2, organ.getSize());
			prep.setFloat(3, organ.getQuality());
			prep.setString(4, organ.getBloodType());
			
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	 */
	//it will be similar to addOperation
	public void scheduleOperation(Operation operation) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Operation (isDone, date, patientId, quality, bloodType) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, organ.getGender());	
			prep.setString(2, organ.getSize());
			prep.setFloat(3, organ.getQuality());
			prep.setString(4, organ.getBloodType());
			
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	/*
	 * private Integer id;
	private Boolean isDone;
	private Date date;
	private Integer patientId;
	private Integer treatmentId;
	private Integer doctorId;
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
