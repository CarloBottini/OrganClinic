package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Organ implements Serializable {

	private static final long serialVersionUID = 2337646964486212273L;	

	private Integer id;
	private String gender;
	private String typeOrgan;
	private String size;
	private Float quality;
	private String bloodType;
	/**
	 * @param id
	 * @param gender
	 * @param typeOrgan
	 * @param size
	 * @param quality
	 * @param bloodType
	 */
	public Organ(Integer id, String gender, String typeOrgan, String size, Float quality, String bloodType) {
		super();
		this.id = id;
		this.gender = gender;
		this.typeOrgan = typeOrgan;
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
	public String getTypeOrgan() {
		return typeOrgan;
	}
	public void setTypeOrgan(String typeOrgan) {
		this.typeOrgan = typeOrgan;
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
	
	
	
	
	
}
