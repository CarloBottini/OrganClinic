package OrganClinicPOJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

public class Role implements Serializable {

	private static final long serialVersionUID = -5694159048106269188L;
	
	@Id
	@GeneratedValue(generator = "roles")
	@TableGenerator(name = "roles", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "roles")
	
	private Integer id;
	private String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users;
	
	public Role() {
		super();
		users = new ArrayList<User>();
	}

	/**
	 * @param id
	 * @param description
	 */
	public Role(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
		users = new ArrayList<User>();
	}

	


	public Role(String description) {
		super();
		this.description = description;
		users = new ArrayList<User>();	
		}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Role [description=" + description + "]";
	}
	
	
	
}
