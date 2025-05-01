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
	
	/*
	 * private Integer id;
	private String gender;
	private String typeOrgan;
	private String size;
	private Float quality;
	private String bloodType;
	
	 */
	
}
