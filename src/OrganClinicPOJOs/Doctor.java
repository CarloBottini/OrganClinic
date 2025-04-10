package OrganClinicPOJOs;

import java.sql.Date;
import java.io.Serializable;


public class Doctor implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1687897604850163624L;
	
	private Integer id;
	private String name;
	private Date dob;
	private String gender;
	private String email;
	private Integer telephone;

}
