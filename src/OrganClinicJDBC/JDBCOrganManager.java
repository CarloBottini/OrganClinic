package OrganClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.OrganManager;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;

public class JDBCOrganManager implements OrganManager {

	private JDBCManager manager;
	
	
	public JDBCOrganManager(JDBCManager m) {
		this.manager=m;
	}
	
	@Override
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
	
	@Override
	public void modifyOrgan(Organ o) {
		try {
			String query = "UPDATE Organ SET gender= ?, type= ?, size= ?, quality= ?, bloodType= ? WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			prep.setString(1, o.getGender());
			prep.setString(2, o.getType());
	        prep.setString(3, o.getSize()); 
	        prep.setFloat(4, o.getQuality());
	        prep.setString(5, o.getBloodType());
			prep.setInt(6, o.getId());

	        prep.executeUpdate();
	        prep.close();
	        System.out.println("Organ with ID " + o.getId() + " updated successfully.");
	        
		}catch(Exception e){
	        System.out.println("Error in the database");
			e.printStackTrace();
			}	
	}
	
	@Override
	public void deleteOrgan(Integer id) {
		try {
			String st= "DELETE FROM Organ WHERE id= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(st);
			prep.setInt(1, id);
			int rowsAffected = prep.executeUpdate();
			if (rowsAffected>0) {
		        System.out.println("Deleted successfully the organ with ID"+ id);
			}else {
		        System.out.println("Organ not found with ID " + id);
			}
			prep.close();		
		}catch(Exception e) {
			System.out.println("Error in data bases with the organ ID " + id);
			e.printStackTrace();
		}
	}
	
	@Override
	public Organ getOrganByID(Integer id) {
		try {
			String sql = "SELECT * FROM Organ WHERE id = " + id;
			Statement st = manager.getConnection().createStatement();
			ResultSet rs= st.executeQuery(sql);
			rs.next();
			Organ o = new Organ (rs.getInt("id"), rs.getString("gender"), rs.getString("type"), rs.getString("size"), rs.getFloat("quality"),rs.getString("bloodType"));
			return o;
		}catch(Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Organ> getAllOrgans() {
	    List<Organ> allOrgans = new ArrayList<Organ>();
	    try {
	        Statement stmt = manager.getConnection().createStatement();
	        String sql = "SELECT * FROM Organ";
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	            Integer id = rs.getInt("id");
	            String gender = rs.getString("gender");
	            String type = rs.getString("type");
	            String size = rs.getString("size");
	            Float quality = rs.getFloat("quality");
	            String bloodType = rs.getString("bloodType");
	            
	            Organ organ = new Organ(id, gender, type, size, quality, bloodType);
	            allOrgans.add(organ);
	        }
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return allOrgans;
	}
	
	
	
	
}
