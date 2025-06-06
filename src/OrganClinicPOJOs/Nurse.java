package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Nurse implements Serializable {

	private static final long serialVersionUID = -8602463808973937810L;
	
	private Integer id;
	private String name;
	private Boolean availability;
	private List<Operation> listOperation;
	
	
	public Nurse(Integer id, String name, Boolean availability, List<Operation> listOperation) {
		super();
		this.id = id;
		this.name = name;
		this.availability = availability;
		this.listOperation = listOperation;
	}

	
	public Nurse(Integer id, String name, Boolean availability) {
		super();
		this.id = id;
		this.name = name;
		this.availability = availability;
		this.listOperation = null;
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

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public List<Operation> getListOperation() {
		return listOperation;
	}

	public void setListOperation(List<Operation> listOperation) {
		this.listOperation = listOperation;
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
		Nurse other = (Nurse) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", availability=" + availability + "]";
	}
	
	

	
}
