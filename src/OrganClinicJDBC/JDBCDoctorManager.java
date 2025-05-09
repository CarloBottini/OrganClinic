package OrganClinicJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.DoctorManager;
import OrganClinicPOJOs.Doctor;




public class JDBCDoctorManager implements DoctorManager{
	public JDBCManager manager;

	public JDBCDoctorManager (JDBCManager m) {
		this.manager=m;
	}

	
	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "Select * FROM Doctor";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name= rs.getString("name");
				Date dob= rs.getDate("dob");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				Integer telephone = rs.getInt("telephone");
				Doctor doctor= new Doctor(id,name,dob,gender,email,telephone);
				doctors.add(doctor);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			System.out.println("Error in the database while getting all the doctors with their info");
			e.printStackTrace();
			}
		return doctors;
	}
	
	
	public void addDoctor (Doctor doc) {
		try {//it is not  necessary to put the id because it is autoincremented
			String template = "INSERT INTO Doctor (name, dob, gender, email, telephone) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(template);
			prep.setString(1, doc.getName());
			prep.setDate(2, doc.getDob());
			prep.setString(3, doc.getGender());
			prep.setString(4, doc.getEmail());
			prep.setInt(5, doc.getTelephone());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database while adding a new doctor");
			e.printStackTrace();
		}
	}
	
	
	public Doctor getDoctorByID(Integer id) {
		try {
			String sql = "SELECT * FROM Doctor WHERE id = " + id;
			Statement st = manager.getConnection().createStatement();
			ResultSet rs= st.executeQuery(sql);
			rs.next();
			Doctor doc = new Doctor (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("email"), rs.getInt("telephone"));
			return doc;
		}catch (Exception e) {
			System.out.println("Error in the database while getting the doctor by ID");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Doctor getDoctorByEmail(String email) {
		try {
			String sql = "SELECT * FROM Doctor WHERE username = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,email);
			ResultSet rs= prep.executeQuery();
			rs.next();
			Doctor doc = new Doctor (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("email"), rs.getInt("telephone"));
			prep.close();
			return doc;
		}catch (Exception e) {
			System.out.println("Error in the database while getting the doctor by email");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void modifyDoctor(Doctor doc) {
		try {
			String query = "UPDATE Doctor SET name= ?, dob= ?, gender= ?, email= ?, telephone= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setString(1,doc.getName());
			prep.setDate(2, doc.getDob());
			prep.setString(3, doc.getGender());
	        prep.setString(4, doc.getEmail());
	        prep.setInt(5, doc.getTelephone());
	        prep.setInt(6, doc.getId());
	        prep.executeUpdate();
	        prep.close();
	        System.out.println("Doctor with ID " + doc.getId() + " updated successfully.");
	        
		}catch(Exception e){
	        System.out.println("Error in the database while updating the doctor");
			e.printStackTrace();
			}	
	}
	
	
	public void deletePatient (Integer id) {
		try {
			String st= "DELETE FROM Doctor WHERE id= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(st);
			prep.setInt(1, id);
			int rowsAffected = prep.executeUpdate();
			if (rowsAffected>0) {
		        System.out.println("Deleted successfully the doctor with ID"+ id);
			}else {
		        System.out.println("Doctor not found with ID " + id);
			}
			prep.close();		
		}catch(Exception e) {
			System.out.println("Error while deleting the doctor in data bases with the doctor ID " + id);
			e.printStackTrace();
		}
	}
	
	
	
}
