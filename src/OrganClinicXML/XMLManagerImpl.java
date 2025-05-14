package OrganClinicXML;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import OrganClinicINTERFACEs.XMLManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Patient;

public class XMLManagerImpl implements XMLManager{

	JDBCManager manager;
	
	public void patient2xml(Integer id) {
			Patient patient=null;
			Doctor doctor=null;
			
			manager = new JDBCManager();
		try {
				Statement stmt= manager.getConnection().createStatement();
				String sql = "SELECT * FORM Patient WHERE id=" +id;
				ResultSet rs= stmt.executeQuery(sql);
				String name = rs.getString("name");
				Date dob =rs.getDate("dob");
				String gender= rs.getString("gender");
				String organFailure= rs.getString("organFailure");
				String email= rs.getString("email");
				Integer telephone= rs.getInt("telephone");
				String bloodType= rs.getString("bloodType");

				stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
