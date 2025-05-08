package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.Objects;

public class Blood implements Serializable{


	private static final long serialVersionUID = 8646157018278551404L;
/*
 * WE CAN DO THAT WHEN THE THEY CHOOSE THE BLOOD WECAN DO THAT THEY SELECT THEY BLOOD WITH A SELECTION
 * */
	
	private String type;
	
	public Blood(String type) {
		super();
		this.type=type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blood other = (Blood) obj;
		return Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Blood [type=" + type + "]";
	}
	
	
	
	
}

/*
 * WE CAN DO THAT WHEN THE THEY CHOOSE THE BLOOD WECAN DO THAT THEY SELECT THEY BLOOD WITH A SELECTION
 * */
