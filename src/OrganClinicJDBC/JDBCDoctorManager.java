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

	@Override
	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "Select * FROM Doctor";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name= rs.getString("name");
				String dobStr = rs.getString("dob");
				Date dob = null;
				if (dobStr != null && !dobStr.trim().isEmpty()) {
				    try {
				        dob = Date.valueOf(dobStr); // yyyy-MM-dd
				    } catch (IllegalArgumentException e) {
				        System.out.println("Invalid date format for doctor DOB: " + dobStr);
				    }
				}

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
	
	@Override
	public void addDoctor (Doctor doc) {
		try {//it is not  necessary to put the id because it is autoincremented
			String template = "INSERT INTO Doctor (name, dob, gender, email, telephone) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(template);
			prep.setString(1, doc.getName());
			prep.setString(2, doc.getDob().toString());
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
	
	@Override
	public Doctor getDoctorByID(Integer id) {
	    String sql = "SELECT * FROM Doctor WHERE id = ?";
	    try (PreparedStatement prep = manager.getConnection().prepareStatement(sql)) {
	        prep.setInt(1, id);
	        try (ResultSet rs = prep.executeQuery()) {
	            if (rs.next()) {
	                int docId = rs.getInt("id");
	                String name = rs.getString("name");
	                String dobStr = rs.getString("dob");
	                Date dob = null;
	                if (dobStr != null && !dobStr.trim().isEmpty()) {
	                    try {
	                        dob = Date.valueOf(dobStr); //yyyy-MM-dd
	                    } catch (IllegalArgumentException e) {
	                        System.out.println("Invalid date format found for doctor ID " + id + ": " + dobStr);
	                    }
	                }
	                String gender = rs.getString("gender");
	                String email = rs.getString("email");
	                int telephone = rs.getInt("telephone");
	                return new Doctor(docId, name, dob, gender, email, telephone);
	            } else {
	                System.out.println("No doctor found with ID: " + id);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in the database while getting the doctor by ID");
	        e.printStackTrace();
	    }
	    return null;
	}

	
	@Override
	public Doctor getDoctorByEmail(String email) {
		Doctor doc = null;
	    try {
	        String sql = "SELECT * FROM Doctor WHERE email = ?";
	        PreparedStatement prep = manager.getConnection().prepareStatement(sql);
	        prep.setString(1, email);
	        ResultSet rs = prep.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String dobStr = rs.getString("dob");
	            Date dob = null;

	            if (dobStr != null && !dobStr.trim().isEmpty()) {
	                try {
	                    dob = Date.valueOf(dobStr); // convertir solo si es válido
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Fecha inválida encontrada en la base de datos: " + dobStr);
	                }
	            }

	            String gender = rs.getString("gender");
	            String emailResult = rs.getString("email");
	            int telephone = rs.getInt("telephone");

	            doc = new Doctor(id, name, dob, gender, emailResult, telephone);
	        } else {
	            //System.out.println("No doctor found with that email.");
	        }

	        rs.close();
	        prep.close();

	    } catch (Exception e) {
	        System.out.println("Error in the database while getting the doctor by email");
	        e.printStackTrace();
	    }
	    return doc;
	}
	
	@Override
	public void modifyDoctor(Doctor doc) {
		try {
			String query = "UPDATE Doctor SET name= ?, dob= ?, gender= ?, email= ?, telephone= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setString(1,doc.getName());
			prep.setString(2, doc.getDob().toString());
			prep.setString(3, doc.getGender());
	        prep.setString(4, doc.getEmail());
	        prep.setInt(5, doc.getTelephone());
	        prep.setInt(6, doc.getId());
	        prep.executeUpdate();
	        System.out.println("Doctor with ID " + doc.getId() + " updated successfully.");
	        prep.close();

		}catch(Exception e){
	        System.out.println("Error in the database while updating the doctor");
			e.printStackTrace();
			}	
	}
	
	@Override
	public void deleteDoctor (Integer id) {
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
