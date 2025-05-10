package OrganClinicJPA;

import javax.persistence.Persistence;

import OrganClinicINTERFACEs.UserManager;

public class JPAUserManager implements UserManager{
	//todo fijarse en el video las diferentes librerias que introducen
	
	//TODO java persistence etc
	private EntityManager em;
	
	public JPAUserManager() {
		super();
	}
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
		em = Persistence.createEntityManagerFactory("vetclinic-provider").createEntityManager();
	
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
		
		if(this.getRoles().isEmpty())
		{
			Role owner = new Role("owner");
			Role vet = new Role("vet");
			this.newRole(owner);
			this.newRole(vet);
		}
}
