package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Treatment implements Serializable{

	private static final long serialVersionUID = 8982886436171336943L;
	
	private Integer id;
	private String name;
	private String type;
	private Integer duration;
	
	public Treatment(Integer id, String name, String type, Integer duration) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.duration = duration;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
		Treatment other = (Treatment) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Treatment [id=" + id + ", name=" + name + ", type=" + type + ", duration=" + duration
				+ "]";
	}
		
	
	
}
