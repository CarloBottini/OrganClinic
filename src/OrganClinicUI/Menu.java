package OrganClinicUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.List;

import OrganClinicINTERFACEs.PatientManager;
import OrganClinicINTERFACEs.UserManager;
import OrganClinicJDBC.*;
import OrganClinicJPA.JPAUserManager;
import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.User;

public class Menu {

	
	private static JDBCManager jdbcManager;
	private static PatientManager patientManager;
	private static UserManager userManager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	
	public static void main(String[] args) {
		jdbcManager = new JDBCManager();
		userManager= new JPAUserManager();
		
	
		userManager.connect();
		int choice=0;
		try {
			do {
				System.out.println("Welcome to the organ clinic");
				System.out.println("choose one of the following options");
				System.out.println("1.Login");
				System.out.println("2.Sign-up");
				System.out.println("0.exit");
				choice=Integer.parseInt(reader.readLine());
				switch(choice) {
				case 1://Login 
					login();
					
					break;
				case 2://sign-up
					addNewUser();
					break;
				case 0:
					jdbcManager.closeConnection();
					userManager.disconnect();
					break;
				}
				
			}while(choice!=0);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void login() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Introduce email: ");
			String email= reader.readLine();
			
			System.out.println("Introduce the password: ");
			String password= reader.readLine();
			User u = userManager.checkPassword(email, password);
			
			//TODO CORRECT THIS, wew have 2 roles, we can compare the role by id then it goes to doctor menu or patientmenu
			
//here correct here			
			if(u!=null& u.getRole().getDescription().equals("Clinician")){
				System.out.println("Login Successful!");
				clinicianmenu(u.getEmail());
			}
			
			
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	
	//TODO delete this dont need this, just call the menu class of doctor
	private static void clinicianmenu(String string) {
		// TODO Auto-generated method stub
		
	}
	
	//sign up method
	private static void addNewUser() {
		try {
			System.out.println("Introduce email: ");
			String email= reader.readLine();
			System.out.println("Introduce the password: ");
			String password= reader.readLine();
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[]digest= md.digest();
			
			System.out.println("choose one role: ");
			List<Role> roles= userManager.getRoles();
			System.out.println(roles.toString());
			Integer role= Integer.parseInt(reader.readLine());
			
			Role r= userManager.getRole(role);
			User u = new User(email,digest, r);
			userManager.newUser(u);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
