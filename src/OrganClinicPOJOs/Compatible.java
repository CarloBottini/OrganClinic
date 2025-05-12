package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Compatible implements Serializable{

	
	private static final long serialVersionUID = 28399519838290707L;

	private Float percentCompatibility;
	private Boolean bloodCompatible;
	private Organ organ_id;
	private Patient patient_id;
	
	
	
	public Compatible(Float percentCompatibility, Boolean bloodCompatible, Organ organ_id, Patient patient_id) {
		super();
		this.percentCompatibility = percentCompatibility;
		this.bloodCompatible = bloodCompatible;
		this.organ_id = organ_id;
		this.patient_id = patient_id;
	}
	public Compatible(Patient patient_id, Organ organ_id,Float percentCompatibility, Boolean bloodCompatible) {
		super();
		this.patient_id = patient_id;
		this.organ_id = organ_id;
		this.percentCompatibility = percentCompatibility;
		this.bloodCompatible = bloodCompatible;
	}


	public Float getPercentCompatibility() {
		return percentCompatibility;
	}

	public void setPercentCompatibility(Float percentCompatibility) {
		this.percentCompatibility = percentCompatibility;
	}

	public Boolean getBloodCompatible() {
		return bloodCompatible;
	}

	public void setBloodCompatible(Boolean bloodCompatible) {
		this.bloodCompatible = bloodCompatible;
	}

	public Organ getOrgan_id() {
		return organ_id;
	}

	public void setOrgan_id(Organ organ_id) {
		this.organ_id = organ_id;
	}

	public Patient getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Patient patient_id) {
		this.patient_id = patient_id;
	}


	@Override
	public String toString() {
		return "Compatible [percentCompatibility=" + percentCompatibility + ", bloodCompatible=" + bloodCompatible
				+ ", organ_id=" + organ_id + ", patient_id=" + patient_id + "]";
	}


	//I dont think we need hashcode or equals, because this says if they are compatible a patient and the organ


	
	
	
	
}
