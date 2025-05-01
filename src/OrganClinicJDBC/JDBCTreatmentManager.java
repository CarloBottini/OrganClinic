package OrganClinicJDBC;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.TreatmentManager;
import OrganClinicPOJOs.Treatment;

public class JDBCTreatmentManager implements TreatmentManager{
	
	private JDBCManager manager;	
	private JDBCTreatmentManager(JDBCManager m) {
		this.manager= m;
	}
	
	public List<Treatment> getAllTreatment(){
		List<Treatment> allTreatments = new ArrayList<Treatment>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "Select * FROM Treatment";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name= rs.getString("name");
				String treatmentType = rs.getString("treatmentType");
				Integer duration = rs.getInt("duration");
				
				
				Treatment t= new Treatment(id,name,treatmentType,duration);
				allTreatments.add(t);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			}
		return allTreatments;
		}
		
		
	}
	
}
