package OrganClinicPOJOs;

import java.io.Serializable;
import java.sql.Date; //Date from sql so it will be correct later

public class Operation implements Serializable{
	
	private static final long serialVersionUID = -5763378309172484828L;
	
	private Integer id;
	
	private Boolean isDone;
	private Date date;
	private Integer patientId;
	private Integer treatmentId;
	private Integer doctorId;
	//TODO correct this so it will have lists
	
	/**
	 * @param id
	 * @param isDone
	 * @param date
	 * @param patientId
	 * @param treatmentId
	 * @param doctorId
	 */
	public Operation(Integer id, Boolean isDone, Date date, Integer patientId, Integer treatmentId, Integer doctorId) {
		super();
		this.id = id;
		this.isDone = isDone;
		this.date = date;
		this.patientId = patientId;
		this.treatmentId = treatmentId;
		this.doctorId = doctorId;
	}
	
	
}
