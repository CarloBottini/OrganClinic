package OrganClinicJDBC;

import java.sql.PreparedStatement;

import OrganClinicINTERFACEs.OrganManager;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;

public class JDBCOrganManager implements OrganManager {

	private JDBCManager manager;
	
	
	private JDBCOrganManager(JDBCManager m) {
		this.manager=m;
	}
	
	
	public void addOrgan(Organ organ) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, organ.getGender());	
			prep.setString(2, organ.getType());
			prep.setString(3, organ.getSize());
			prep.setFloat(4, organ.getQuality());
			prep.setString(5, organ.getBloodType());
			
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	
	
	
}
