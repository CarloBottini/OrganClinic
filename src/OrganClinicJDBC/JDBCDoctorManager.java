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
			String sql = "Select * FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name= rs.getString("name");
				Date dob= rs.getDate("date");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				Integer telephone = rs.getInt("telephone");
				Doctor doctor= new Doctor(id,name,dob,gender,email,telephone);
				doctors.add(doctor);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			}
		return doctors;
	}
	
	
	public void addDoctor (Doctor doc) {
		try {//it is not  necessary to put the id beacause it is autoincremented
			String template = "INSERT INTO doctor (name, dob, gender, email, telephone) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(template);
			prep.setString(1, doc.getName());
			prep.setDate(2, doc.getDob());
			prep.setString(3, doc.getGender());
			prep.setString(4, doc.getEmail());
			prep.setInt(5, doc.getTelephone());
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	
	public Doctor getDoctorByID(Integer id) {
		try {
			String sql = "SELECT * FROM doctor WHERE id = " + id;
			Statement st = manager.getConnection().createStatement();
			ResultSet rs= st.executeQuery(sql);
			rs.next();
			Doctor doc = new Doctor (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("email"), rs.getInt("telephone"));
			return doc;
		}catch (Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * public Doctor getDoctorByusername(String username) {
		try {
			String sql = "SELECT * FROM doctor WHERE username = ?";
			PreparedStatement p= c.prepareStatement(sql);
			p.setString(1, username);
			ResultSet rs = p.executeQuery();
			rs.next();
			Doctor d = new Doctor (rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
			p.close();
			return d;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	 */
	
	
	public Doctor getDoctorByEmail(String email) {
		try {
			String sql = "SELECT * FROM doctor WHERE username = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1,email);
			ResultSet rs= prep.executeQuery();
			rs.next();
			Doctor doc = new Doctor (rs.getInt("id"), rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("email"), rs.getInt("telephone"));
			prep.close();
			return doc;
		}catch (Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
