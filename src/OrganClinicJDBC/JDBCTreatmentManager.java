package OrganClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.TreatmentManager;
import OrganClinicPOJOs.Treatment;

public class JDBCTreatmentManager implements TreatmentManager{
	
	private JDBCManager manager;	
	public JDBCTreatmentManager(JDBCManager m) {
		this.manager= m;
	}
	
	@Override
	public void addTreatment(Treatment treatment) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Treatment (name, type, duration) VALUES (?, ?, ?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, treatment.getName());	
			prep.setString(2, treatment.getType());
			prep.setInt(3, treatment.getDuration());
			
			prep.executeUpdate();
			prep.close();
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	
	@Override
	public void modifyTreatment(Treatment treatment) {
		try {
			String query = "UPDATE Treatment SET name= ?, type= ?, duration= ?  WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setString(1, treatment.getName());
			prep.setString(2, treatment.getType());
	        prep.setInt(3, treatment.getDuration()); 
			prep.setInt(4, treatment.getId());

	        prep.executeUpdate();
	        prep.close();
	        System.out.println("Organ with ID " + treatment.getId() + " updated successfully.");
	        
		}catch(Exception e){
	        System.out.println("Error in the database");
			e.printStackTrace();
			}	
	}
	
	@Override
	public void deleteTreatment(Integer id) {
		try {
			String st= "DELETE FROM Treatment WHERE id= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(st);
			prep.setInt(1, id);
			int rowsAffected = prep.executeUpdate();
			if (rowsAffected>0) {
		        System.out.println("Deleted successfully the treatment with ID"+ id);
			}else {
		        System.out.println("treatment not found with ID " + id);
			}
			prep.close();		
		}catch(Exception e) {
			System.out.println("Error in data bases with the treatment ID " + id);
			e.printStackTrace();
		}
	}
	
	@Override
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
				String type = rs.getString("type");
				Integer duration = rs.getInt("duration");
				
				Treatment t= new Treatment(id,name,type,duration);
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
