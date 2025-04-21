package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;

import OrganClinicINTERFACEs.PatientManager;
import OrganClinicPOJOs.Patient;


public class JDBCPatientManager implements PatientManager{
	
	private JDBCManager manager;

	

	private JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	
	//TODO METODOS ADD PATIENT DELETE NAD MODIFY PATIENT
	//TODO METODOS GETPATIENTBYid and getpatientbyname
	
	/*
	 * 	private Integer id;
	private String name;
	private Date dob;
	private String gender;
	private String organFailure; 
	private String email;
	private Integer telephone;
	private String bloodType;
	 */
	
	//TODO We will need to put Override wehn the interfaces are done
	
	public void addPatient(Patient p) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO patient (name, dob, gender, organFailure, email, telephone, bloodtype) VALUES (?, ?, ?, ?, ?,?,?)";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());	
			prep.setDate(2, p.getDob());
			prep.setString(3, p.getGender());
			prep.setString(4, p.getOrganFailure());
			prep.setString(5, p.getEmail());
			prep.setInt(6, p.getTelephone());
			prep.setString(7, p.getBloodType());
						
			prep.executeUpdate();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
}
