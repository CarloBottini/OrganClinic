package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			prep.close();
		
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	
	
	

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
	
	public void modifyPatient(Patient p) {
		try {
			String query = "UPDATE patient SET name= ?, dob= ?, gender= ?, organFailure= ?, email= ?, telephone= ?, bloodType= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setString(1,p.getName());
			prep.setDate(2, p.getDob());
			prep.setString(3, p.getGender());
	        prep.setString(4, p.getOrganFailure()); 
	        prep.setString(5, p.getEmail());
	        prep.setInt(6, p.getTelephone());
	        prep.setString(7, p.getBloodType());
	        prep.setInt(8, p.getId());
	        prep.executeUpdate();
	        prep.close();
	        System.out.println("Patient with ID " + p.getId() + " updated successfully.");
	        
		}catch(Exception e){
	        System.out.println("Error in the database");
			e.printStackTrace();
			}	
	}
	
	
	public void deletePatient(Integer id) {
		try {
			String st= "DELETE FROM patient WHERE id= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(st);
			prep.setInt(1, id);
			int rowsAffected = prep.executeUpdate();
			if (rowsAffected>0) {
		        System.out.println("Deleted successfully the patient with ID"+ id);
			}else {
		        System.out.println("Patient not found with ID " + id);
			}
			prep.close();		
		}catch(Exception e) {
			System.out.println("Error in data bases with the patient ID " + id);
			e.printStackTrace();
		}
	}
	
	/*
	 * @Override
public Patient getPatientByID(Integer id) {
	
	try {
		String sql = "SELECT * FROM patient WHERE id = " + id;
		Statement st= c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		Patient p = new Patient (rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getDate("dateOfBirth"), rs.getString("gender"));
		return p;
	} catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return null;
}
	 */
	
	public Patient getPatientByID(Integer id) {
		try {
			String sql = "SELECT * FROM patient WHERE id = " + id;
			Statement st = manager.getConnection().createStatement();
			ResultSet rs= st.executeQuery(sql);
			rs.next();
			Patient p = new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"),rs.getString("gender"), rs.getString("organFailure"),rs.getString("email"),rs.getInt("telephone"), rs.getString("bloodType"));
			return p;
		}catch(Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
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
	
	
	
	
}
	
	
	
	
	

