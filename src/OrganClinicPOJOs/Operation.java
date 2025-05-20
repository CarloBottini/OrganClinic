package OrganClinicPOJOs;

import java.io.Serializable;
import java.sql.Date; //Date from sql so it will be correct later
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Operation implements Serializable{
	
	private static final long serialVersionUID = -5763378309172484828L;
	
	private Integer id;
	private Boolean isDone;
	private Date date;
	private Integer patient_id;
	private Integer treatment_id;
	private Integer doctor_id;
	private List<Nurse> listNurse = new ArrayList<>();
	
	
	
	public Operation(Boolean isDone, Date date, Integer patient_id, Integer treatment_id, Integer doctor_id,List<Nurse> listNurse) {
		super();
		this.isDone = isDone;
		this.date = date;
		this.patient_id = patient_id;
		this.treatment_id = treatment_id;
		this.doctor_id = doctor_id;
		this.listNurse = listNurse;
	}



	public Operation(Integer id, Boolean isDone, Date date, Integer patient_id, Integer treatment_id, Integer doctor_id, List<Nurse> listNurse) {
		super();
		this.id = id;
		this.isDone = isDone;
		this.date = date;
		this.patient_id = patient_id;
		this.treatment_id = treatment_id;
		this.doctor_id = doctor_id;
		this.listNurse = listNurse;
	}
	


	public Operation(Integer id, Boolean isDone, Date date, Integer patient_id, Integer treatment_id,
			Integer doctor_id) {
		super();
		this.id = id;
		this.isDone = isDone;
		this.date = date;
		this.patient_id = patient_id;
		this.treatment_id = treatment_id;
		this.doctor_id = doctor_id;
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

	public Integer getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	public Integer getTreatment_id() {
		return treatment_id;
	}

	public void setTreatment_id(Integer treatment_id) {
		this.treatment_id = treatment_id;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public List<Nurse> getListNurse() {
		return listNurse;
	}

	public void setListNurse(List<Nurse> listNurse) {
		this.listNurse = listNurse;
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
		return "Operation [id=" + id + ", isDone=" + isDone + ", date=" + date + ", patient_id=" + patient_id
				+ ", treatment_id=" + treatment_id + ", doctor_id=" + doctor_id + ", listNurse=" + listNurse + "]";
	}

}
