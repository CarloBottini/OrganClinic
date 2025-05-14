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
		int variableWhileInitial=1;

		while (variableWhileInitial!=0) {	

			int choice=0;
			try {
				do {
				System.out.println("Welcome to the ORGAN CLINIC PROGRAM!!!");
				System.out.println("Choose one of the following options");
				System.out.println("1.Login");
				System.out.println("2.Sign-up");
				System.out.println("0.exit");
				choice=Integer.parseInt(reader.readLine());
				switch(choice) {
				case 1://Login 
					login();
					
					break;
				case 2://sign-up
					signUp();
					break;
				case 0:
					System.out.println("Closing the program. See you soon....");
	                variableWhileInitial=0;

					jdbcManager.closeConnection();
					userManager.disconnect();
					break;
				 default:
		                System.out.println("Option not available. Please, insert again.");
				}
				
			}while(choice!=0);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static void login() {
		try {
			System.out.println("Introduce email: ");
			String email= reader.readLine();
			System.out.println("Introduce the password: ");
			String password= reader.readLine();
			User u = userManager.checkPassword(email, password);
			
			//we have 2 roles, we can compare the role by id then it goes to doctor menu or patientmenu			
			if (u == null) 
				System.out.println("The information is incorrect");
			else if (u.getRole().getId()==1) {
				MenuPatient.menuPatient(email);
			}
			else if(u.getRole().getId()==2) {
				MenuDoctor.menuDoctor(email);				

			}			
	}catch(IOException e) {
		e.printStackTrace();
		}
	}
	
	//sign up method
	private static void signUp() {
		try {
			System.out.println("Introduce email: ");
			String email= reader.readLine();
			System.out.println("Introduce the password: ");
			String password= reader.readLine();
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[]digest= md.digest();
			
			System.out.println("choose one role by inserting the number: ");
			//TODO mirar en la tabla tables cual es role 1= y role 2=
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
