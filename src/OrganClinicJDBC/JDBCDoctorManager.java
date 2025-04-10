package OrganClinicJDBC;

import java.sql.Date;
import java.sql.ResultSet;
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
				
		}catch(Exception e)
		{e.printStackTrace();}
		
		return doctors;
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
