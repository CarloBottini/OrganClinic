package OrganClinicJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.CompatibleManager;
import OrganClinicPOJOs.Compatible;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;

public class JDBCCompatibleManager implements CompatibleManager {

    private JDBCManager manager;

    public JDBCCompatibleManager(JDBCManager m) {
        this.manager = m;
    }

    @Override
    public void addCompatible(Compatible c) {
        try {
            String sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) VALUES (?, ?, ?, ?)";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, c.getPatient_id().getId());
            prep.setInt(2, c.getOrgan_id().getId()); 
            prep.setFloat(3, c.getPercentCompatibility());
            prep.setBoolean(4, c.getBloodCompatible());
           
            prep.executeUpdate();
            prep.close();
        } catch (Exception e) {
            System.out.println("Error adding Compatible to the database");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompatibleByPatientAndOrgan(int patientId, int organId) {
        try {
            String sql = "DELETE FROM Compatible WHERE patient_id = ? AND organ_id = ?";
            PreparedStatement prep = manager.getConnection().prepareStatement(sql);
            prep.setInt(1, patientId);
            prep.setInt(2, organId);
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compatible entry deleted for patient " + patientId + " and organ " + organId);
            } else {
                System.out.println("No Compatible entry found for patient " + patientId + " and organ " + organId);
            }
            prep.close();
        } catch (Exception e) {
            System.out.println("Error deleting Compatible entry");
            e.printStackTrace();
        }
    }

   

}

