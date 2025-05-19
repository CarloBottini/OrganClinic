package OrganClinicPOJOs;

import java.sql.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import OrganClinicXMLUtils.SQLDateAdapter;

import java.io.Serializable;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Doctor")
@XmlType(propOrder= {"name","dob","gender","email","telephone"})
public class Doctor implements Serializable{
	

	
	private static final long serialVersionUID = -1687897604850163624L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlAttribute
	private String gender;
	@XmlElement
	private String email;
	@XmlElement
	private Integer telephone;
	
	
	public Doctor(Integer id, String name, Date dob, String gender, String email, Integer telephone) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.telephone = telephone;
	}
	
	public Doctor(String name, Date dob, String gender, String email, Integer telephone) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.telephone = telephone;
	}
	
	//constructor for the JAXB so XML can be done
	public Doctor() {
		super();
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

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", email=" + email
				+ ", telephone=" + telephone + "]";
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
		Doctor other = (Doctor) obj;
		return Objects.equals(id, other.id);
	}
}
