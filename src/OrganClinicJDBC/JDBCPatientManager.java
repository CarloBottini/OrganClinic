package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.PatientManager;
import OrganClinicPOJOs.Patient;


public class JDBCPatientManager implements PatientManager{
	
	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addPatient(Patient p) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
	
	
	@Override
	public void modifyPatient(Patient p) {
		try {
			String query = "UPDATE Patient SET name= ?, dob= ?, gender= ?, organFailure= ?, email= ?, telephone= ?, bloodType= ? WHERE id=?";
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
	
	@Override
	public void deletePatient(Integer id) {
		try {
			String st= "DELETE FROM Patient WHERE id= ?";
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
	
	
	@Override
	public Patient getPatientByID(Integer id) {
		try {
			String sql = "SELECT * FROM Patient WHERE id = " + id;
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
	
	
	
	//in the future this will be the user email=username
	@Override
	public Patient getPatientByEmail(String email) {
		Patient patient= null;
		try {
			String sql = "SELECT * FROM Patient WHERE email = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs =prep.executeQuery();
			if(rs.next()) {
				patient= new Patient (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"),rs.getString("gender"), rs.getString("organFailure"),rs.getString("email"),rs.getInt("telephone"), rs.getString("bloodType"));
			}
			prep.close();
			rs.close();
		}catch(Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return patient;
	}
	
	@Override
	public List<Patient> getAllPatients() {
	    List<Patient> patients = new ArrayList<Patient>();
	    try {
	        Statement stmt = manager.getConnection().createStatement();
	        String sql = "SELECT * FROM Patient";  	       
	        ResultSet rs = stmt.executeQuery(sql);	        
	        while (rs.next()) {
	            Integer id = rs.getInt("id");
	            String name = rs.getString("name");
	            Date dob = rs.getDate("dob");
	            String gender = rs.getString("gender");
	            String organFailure = rs.getString("organFailure");
	            String email = rs.getString("email");
	            Integer telephone = rs.getInt("telephone");
	            String bloodType = rs.getString("bloodType");
	            
	            Patient patient = new Patient(id, name, dob, gender, organFailure, email, telephone, bloodType);
	            patients.add(patient);
	        }	        
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        System.out.println("Error in the database while getting all the patients with their info");
	        e.printStackTrace();
	    }	    
	    return patients;
	}
	
	
	@Override
	public List<Patient> getPatientsByName(String name) {
		List<Patient> patients = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Patient WHERE name LIKE ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, "%" + name + "%"); //for example: introduce Pe for searching Pedro
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Patient patient = new Patient(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getDate("dob"),
					rs.getString("gender"),
					rs.getString("organFailure"),
					rs.getString("email"),
					rs.getInt("telephone"),
					rs.getString("bloodType")
				);
				patients.add(patient);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return patients;
	}

	
	
}
	
	
	
	
	

