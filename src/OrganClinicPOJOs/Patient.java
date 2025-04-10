package OrganClinicPOJOs;

import java.io.Serializable;
import java.sql.Date; //it will helps us later


public class Patient implements Serializable{


	private static final long serialVersionUID = 448543727153060322L;
	
	private Integer id;
	private String name;
	private Date dob;
	private String gender;
	private String organ_failing; //TODO Organ or typeOrgan??? i think it is TypeOrgan
	private String email;
	private Integer telephone;
	private String bloodType;
}
