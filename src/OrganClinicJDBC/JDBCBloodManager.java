package OrganClinicJDBC;

import OrganClinicINTERFACEs.BloodManager;

public class JDBCBloodManager implements BloodManager{

	private JDBCManager manager;
	
	private JDBCBloodManager(JDBCManager m) {
		this.manager=m;
	}
	
	//We will not need to add/delete/modify any blood because it all the existent types of blood
}
