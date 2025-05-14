package OrganClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.NurseManager;
import OrganClinicPOJOs.Nurse;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Patient;
import OrganClinicPOJOs.Treatment;

public class JDBCNurseManager implements NurseManager{
private JDBCManager manager;

	public JDBCNurseManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public Nurse getNurseByID(Integer id) {
		try {
			String sql = "SELECT * FROM Nurse WHERE id = " + id;
			Statement st = manager.getConnection().createStatement();
			ResultSet rs= st.executeQuery(sql);
			rs.next();
			Nurse n = new Nurse (rs.getInt("id"), rs.getString("name"), rs.getBoolean("availability"));
			return n;
		}catch(Exception e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteNurse(Integer id) {
		try {
			String st= "DELETE FROM Nurse WHERE id= ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(st);
			prep.setInt(1, id);
			int rowsAffected = prep.executeUpdate();
			if (rowsAffected>0) {
		        System.out.println("Deleted successfully the Nurse with ID"+ id);
			}else {
		        System.out.println("Nurse not found with ID " + id);
			}
			prep.close();		
		}catch(Exception e) {
			System.out.println("Error in data bases with the Nurse ID " + id);
			e.printStackTrace();
		}
	}
	
	@Override
	public void addNurse(Nurse n) {
		try {//it is not necessary to put the id, because it is autoincremented
			String sql = "INSERT INTO Nurse (name,availability) VALUES (?,?)";

			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, n.getName());	
			prep.setBoolean(2, n.getAvailability());
			
			prep.executeUpdate();
			prep.close();
		
		}catch(Exception e){
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}
	
	@Override
	public List<Nurse> getAllNurses(){
		List<Nurse> allNurse = new ArrayList<Nurse>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "Select * FROM Nurse";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Integer id = rs.getInt("id");
				String name= rs.getString("name");
				Boolean availability = rs.getBoolean("availability");
				
				Nurse n= new Nurse(id,name,availability);
				allNurse.add(n);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			}
		return allNurse;
		}
		
	@Override	
	public List<Nurse> getAvailableNurses() {
		List<Nurse> availableNurses = new ArrayList<Nurse>();
	    try {
	        Statement stmt = manager.getConnection().createStatement();
	        String sql = "SELECT * FROM Nurse WHERE availability = 1";
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	            Integer id = rs.getInt("id");
	            String name = rs.getString("name");
	            Boolean availability = rs.getBoolean("availability");

	            Nurse nurse = new Nurse(id, name, availability);
	            availableNurses.add(nurse);
	        }
	        rs.close();
	        stmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return availableNurses;
	}
	
	
}
