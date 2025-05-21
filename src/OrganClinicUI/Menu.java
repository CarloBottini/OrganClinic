package OrganClinicUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.List;

import OrganClinicINTERFACEs.DoctorManager;
import OrganClinicINTERFACEs.PatientManager;
import OrganClinicINTERFACEs.UserManager;
import OrganClinicJDBC.*;
import OrganClinicJPA.JPAUserManager;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Patient;
import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.User;

public class Menu {

	private static JDBCManager connectionManager;

	private static JDBCManager jdbcManager;
	private static PatientManager patientMan;
	private static DoctorManager doctorMan;
	private static UserManager userManager;
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	
	public static void main(String[] args) {
		connectionManager= new JDBCManager();
		userManager= new JPAUserManager();
		doctorMan = new JDBCDoctorManager(connectionManager);
		patientMan=new JDBCPatientManager(connectionManager);
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

					connectionManager.closeConnection();
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
			
			Patient existingPatient = patientMan.getPatientByEmail(email);
            if (existingPatient != null) {
                System.out.println("This User is alreaady registered");
                return;
            }		
            Doctor existingDoctor= doctorMan.getDoctorByEmail(email);
            if(existingDoctor!= null) {
                System.out.println("This User is already registered");
                return;
            }
            
			System.out.println("choose one role by inserting the ID number: ");
			//role 1 =patient role 2= doctor
			List<Role> roles= userManager.getRoles();
			System.out.println("Insert 1 for Patient and 2 for Doctor");
			Integer roleID= Integer.parseInt(reader.readLine());
			Role r= userManager.getRole(roleID);
			User u = new User(email,digest, r);
			userManager.newUser(u);	
			
			if (roleID == 2) { //when is doctor
				System.out.println("DOCTOR NAME: ");
	            String name = reader.readLine();

	            System.out.println("DOCTOR DATE OF BIRTH (yyyy-mm-dd): ");
	            String dobStr = reader.readLine();
	            Date dob = Date.valueOf(dobStr);

	            String gender;
	            while (true) {
	                System.out.println("DOCTOR GENDER (M for male or F for female): ");
	                gender = reader.readLine().trim().toUpperCase();
	                if (gender.equals("M") || gender.equals("F")) break;
	                System.out.println("Invalid gender. Please enter M or F.");
	            }
	            System.out.println("DOCTOR TELEPHONE (numbers only): ");
	            int telephone = Integer.parseInt(reader.readLine());

	            Doctor doctor = new Doctor(name, dob, gender, email, telephone);
	            doctorMan.addDoctor(doctor);
	            
			}else if (roleID == 1) { //patients
               
                    System.out.println("PATIENT NAME: ");
                    String name = reader.readLine();
                    System.out.println("PATIENT DATE OF BIRTH (yyyy-mm-dd): ");
                    String dobStr = reader.readLine();
                    Date dob;
                    try {
                        dob = Date.valueOf(dobStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid format. Use:  yyyy-mm-dd.");
                        return;
                    }
                    String gender;
                    while (true) {
                        System.out.println("PATIENT GENDER (M for male or F for female): ");
                        gender = reader.readLine().trim().toUpperCase();
                        if (gender.equals("M") || gender.equals("F"))
                            break;
                        System.out.println("Invalid input, insert M or F ");
                    }

                    System.out.println("PATIENT ORGAN FAILURE (Brain, Kidney, Lung, Heart, Pancreas, Liver): ");
                    String organFailure = reader.readLine();

                    System.out.println("PATIENT TELEPHONE (numbers only): ");
                    int telephone;
                    try {
                        telephone = Integer.parseInt(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Telephone invlaid number.");
                        return;
                    }
                    String[] bloodTypes = { "Positive_B", "Positive_AB", "Positive_A", "Positive_0",
                            "Negative_B", "Negative_AB", "Negative_A", "Negative_0" };

                    System.out.println("Select Blood Type:");
                    for (int i = 0; i < bloodTypes.length; i++) {
                        System.out.println((i + 1) + ". " + bloodTypes[i]);
                    }
                    String bloodType = "";
                    while (true) {
                        System.out.print("Enter option (1-8): ");
                        try {
                            int choice = Integer.parseInt(reader.readLine());
                            if (choice >= 1 && choice <= bloodTypes.length) {
                                bloodType = bloodTypes[choice - 1];
                                break;
                            } else {
                                System.out.println("Insert a number between 1 and " + bloodTypes.length + ".");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input. Please insert a number: ");
                        }
                    }
			
                    Patient newPatient = new Patient(name, dob, gender, organFailure, email, telephone, bloodType);
                    patientMan.addPatient(newPatient);
                    System.out.println("Patient registered correctly");
                
            } else {
                System.out.println("Invalid role");
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
