package OrganClinicPOJOs;

import java.io.Serializable;
import java.sql.Date; //Date from sql so it will be correct later
import java.util.Objects;

public class Operation implements Serializable{
	
	private static final long serialVersionUID = -5763378309172484828L;
	
	private Integer id;
	private Boolean isDone;
	private Date date;
	private Integer patientId;
	private Integer treatmentId;
	private Integer doctorId;
	//TODO correct this so it will have lists
	
	
	public Operation(Integer id, Boolean isDone, Date date, Integer patientId, Integer treatmentId, Integer doctorId) {
		super();
		this.id = id;
		this.isDone = isDone;
		this.date = date;
		this.patientId = patientId;
		this.treatmentId = treatmentId;
		this.doctorId = doctorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(Integer treatmentId) {
		this.treatmentId = treatmentId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", isDone=" + isDone + ", date=" + date + ", patientId=" + patientId
				+ ", treatmentId=" + treatmentId + ", doctorId=" + doctorId + "]";
	}
	
	
	
}
