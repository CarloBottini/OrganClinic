package OrganClinicJPA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OrganClinicINTERFACEs.UserManager;
import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.User;

public class JPAUserManager implements UserManager{
	
	private static final MessageDigest MessagestDigest = null;
	private EntityManager em;
	
	public JPAUserManager() {
		super();
		this.connect();
	}

	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("OrganClinic-provider").createEntityManager();
												
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty()) {
			Role Patient = new Role("Patient"); //role 1= Patient
			Role Doctor = new Role("Doctor");	//role 2= Doctor
			this.newRole(Patient);
			this.newRole(Doctor);
			}
		}
		@Override
		public void disconnect() {
			em.close();
		}
		
		//create user
		@Override
		public void newUser(User u) {
			// TODO Auto-generated method stub
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		}
		//create role
		@Override
		public void newRole(Role r) {
			// TODO Auto-generated method stub
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			
		}
		//read role
		@Override
		public Role getRole(Integer id) {
			// TODO Auto-generated method stub
			Query q = em.createNativeQuery("SELECT * FROM roles WHERE id="+id, Role.class);
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
		//read user
		@Override
		public User getUser(String email) {
			// TODO Auto-generated method stub
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email="+email, User.class);
			User u = (User) q.getSingleResult();
			
			return u;
		}
		
		@Override
		public User checkPassword(String email,String pw) {
			User u= null;
			
			Query q = em.createNativeQuery("SELECT * FROM users WHERE email= ? AND password= ?",User.class);
			q.setParameter(1, email);
			try {
				MessageDigest md= MessagestDigest.getInstance("MD5");
				md.update(pw.getBytes());
				byte[]digest=md.digest();
				q.setParameter(2, digest);
			}catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			try {
				u=(User)q.getSingleResult();
			}catch(NoResultException e) {}
			return u;
		}
		//update user
		@Override
	    public void updateUser(User u) {
	        em.getTransaction().begin();
	        em.merge(u);
	        em.getTransaction().commit();
	    }
		//update role
		@Override
	    public void updateRole(Role r) {
	        em.getTransaction().begin();
	        em.merge(r);
	        em.getTransaction().commit();
	    }
		
		public void updatePassword(Integer userId, String newPassword) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(newPassword.getBytes());
	            byte[] newDigest = md.digest();
	            
	            em.getTransaction().begin();
	            User user = em.find(User.class, userId);
	            if (user != null) {
	                user.setPassword(newDigest);
	            }
	            em.getTransaction().commit();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		//delete user
		@Override
	    public void deleteUser(User u) {
	        em.getTransaction().begin();
	        User managedUser = em.find(User.class, u.getId());
	        if (managedUser != null) {
	            em.remove(managedUser);
	        }
	        em.getTransaction().commit();
	    }
		
		//delete role
		@Override
	    public void deleteRole(Role r) {
	        em.getTransaction().begin();
	        Role managedRole = em.find(Role.class, r.getId());
	        if (managedRole != null) {
	            em.remove(managedRole);
	        }
	        em.getTransaction().commit();
	    }
		
}
