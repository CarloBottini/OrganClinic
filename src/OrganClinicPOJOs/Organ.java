package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Organ implements Serializable {

	private static final long serialVersionUID = 2337646964486212273L;	

	private Integer id;
	private String gender;
	private String type;
	private String size;
	private Float quality;
	private String bloodType;
	
	
	public Organ(Integer id, String gender, String type, String size, Float quality, String bloodType) {
		super();
		this.id = id;
		this.gender = gender;
		this.type = type;
		this.size = size;
		this.quality = quality;
		this.bloodType = bloodType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Float getQuality() {
		return quality;
	}
	public void setQuality(Float quality) {
		this.quality = quality;
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
		Organ other = (Organ) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Organ [id=" + id + ", gender=" + gender + ", type=" + type + ", size=" + size + ", quality="
				+ quality + ", bloodType=" + bloodType + "]";
	}
}
