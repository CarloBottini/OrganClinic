package OrganClinicINTERFACEs;

import java.util.List;

import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.User;

public interface UserManager {
	void connect();

	void disconnect();

	void newUser(User u);

	void newRole(Role r);
	
	User getUser(String email);

	List<Role> getRoles();

	Role getRole(Integer id);

	User checkPassword(String email, String pw);

}
