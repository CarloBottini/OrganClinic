package OrganClinicJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OrganClinicINTERFACEs.UserManager;
import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.User;

public class JPAUserManager implements UserManager{
	
	private EntityManager em;
	
	public JPAUserManager() {
		super();
	}
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("OrganClinic-provider").createEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty())
		{
			Role Patient = new Role("Patient");
			Role Doctor = new Role("Doctor");
			this.newRole(Patient);
			this.newRole(Doctor);
		}
		}
		@Override
		public void disconnect() {
			em.close();
		}
		
		@Override
		public void newUser(User u) {
			// TODO Auto-generated method stub
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		}
		
		@Override
		public void newRole(Role r) {
			// TODO Auto-generated method stub
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			
		}
		
		@Override
		public Role getRole(Integer id) {
			// TODO Auto-generated method stub
			Query q = em.createNativeQuery("SELECT * FROM roles where id="+id, Role.class);
			Role r = (Role) q.getSingleResult();
			
			return r;
		}
		
		@Override
		public List<Role> getRoles() {
			// TODO Auto-generated method stub
			Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
			List<Role> roles = (List<Role>) q.getResultList();
		
			return roles;
		}

		@Override
		public User getUser(String email) {
			// TODO Auto-generated method stub
			Query q = em.createNativeQuery("SELECT * FROM users where email="+email, User.class);
			User u = (User) q.getSingleResult();
			
			return u;
		}
}
