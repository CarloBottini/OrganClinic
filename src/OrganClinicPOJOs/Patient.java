package OrganClinicPOJOs;

import java.io.Serializable;
import java.sql.Date; //it will helps us later
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Patient")
@XmlType(propOrder = {"name", "dob", "gender","organFailure", "email", "telephone", "bloodType"})
public class Patient implements Serializable{


	private static final long serialVersionUID = 448543727153060322L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlAttribute
	private Date dob;
	@XmlAttribute
	private String gender;
	@XmlElement
	private String organFailure; 
	@XmlElement
	private String email;
	@XmlElement
	private Integer telephone;
	@XmlElement
	private String bloodType;
	
	
	public Patient(Integer id, String name, Date dob, String gender, String organFailure, String email, Integer telephone, String bloodType) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.organFailure = organFailure;
		this.email = email;
		this.telephone = telephone;
		this.bloodType = bloodType;
	}
	
	
	public Patient(String name, Date dob, String gender, String organFailure, String email, Integer telephone, String bloodType) {//without id
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.organFailure = organFailure;
		this.email = email;
		this.telephone = telephone;
		this.bloodType = bloodType;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOrganFailure() {
		return organFailure;
	}
	public void setOrganFailure(String organFailure) {
		this.organFailure = organFailure;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTelephone() {
		return telephone;
	}
	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", organFailure="
				+ organFailure + ", email=" + email + ", telephone=" + telephone + ", bloodType=" + bloodType + "]";
	}
	
	
	
	
}
