package OrganClinicUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import OrganClinicINTERFACEs.DoctorManager;
import OrganClinicINTERFACEs.PatientManager;
import OrganClinicJDBC.JDBCDoctorManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicJDBC.JDBCPatientManager;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Patient;

public class MenuDoctor {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	private static JDBCManager connectionManager;
	private static DoctorManager doctorMan;
	private static PatientManager patientMan;

	public static void menuDoctor(String email) throws NumberFormatException, IOException {
		connectionManager= new JDBCManager();
		doctorMan = new JDBCDoctorManager(connectionManager);
		patientMan= new JDBCPatientManager(connectionManager);
		//TODO ??? see if conect the pojos
		
		System.out.println("Welcome Doctor! We are thrilled with your excellent work! Choose one of the following options: ");
		int whileDoctorVariable=1;
		Doctor doctor= doctorMan.getDoctorByEmail(email);
		while(whileDoctorVariable!=0) {
		System.out.println("1) ADD NEW PATIENT INTO THE DATABASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATABASE");
		System.out.println("3) MODIFY THE PATIENT'S INFORMATION");
		System.out.println("4) SEE DOCTOR'S PROFILE");
		System.out.println("5) MODIFY DOCTOR'S PROFILE");
		System.out.println("6) SEE ALL NURSES AVAILABILITY");
		System.out.println("7) ASSIGN A NURSE TO AN OPERATION");
		System.out.println("8) DELETE A NURSE FROM AN OPERATION");
		System.out.println("9) SCHEDULE AN OPERATION");
		System.out.println("10) RESCHEDULE AN OPERATION");
		System.out.println("11) DIAGNOSE A TREATMENT TO A PATIENT");
		System.out.println("12) SEE ALL THE ORGANS");
		System.out.println("13) SEE THE CHARACTERISTICS OF AN ORGAN");
		
		//later we will TODO the XML CASES
		
		
		System.out.println("0) EXIT");
		
		int doctorChoice = Integer.parseInt(r.readLine());
			switch (doctorChoice) {
			case 1: 
				//add doctor
				break;
			
			case 2: 
				//delete doctor
				break;
			
			case 3: 
				//modify doctor
				break;
			
			case 4: 
				//see doctro profile
				break;
				
			case 5: 
				//modify doctor profile
				break;	
				
			case 6: 
				//see nurses availability
	
				break;
				
			case 7: 
				//assign nurse to operation
				break;
				
			case 8: 
				//delete nurse from operation
				break;
				
			case 9: 
				//schedule operation
				break;
				
				
			case 10: 
				//reeschedule operation
				break;
				
			case 11: 
				//daignose treatment
				break;
				
			case 12: 
				//see al organs
				break;
				
			case 13: 
				//see characteristics of an organ
				break;
			
			case 0:
				whileDoctorVariable=0;
				connectionManager.closeConnection();	
			break;
			
			default:
				System.out.println("You inserted a number that is not an option. Please, introduce again a number of the following options: ");
			
			}		
		}		
	}
	
	private static void addPatient() throws IOException {
		System.out.println("Please, add the following patient's information.");
		System.out.println("Patient NAME: ");
		String name = r.readLine();
		System.out.println("Patient Date Of Birth (yyyy-mm-dd: ");
		String dobStr = r.readLine();
	    Date dob = Date.valueOf(dobStr);
	    System.out.print("Gender: ");
	    String gender = r.readLine();
	    System.out.print("Organ failure: ");
	    String organFailure = r.readLine();
	    System.out.print("Email: ");
	    String email = r.readLine();
	    System.out.print("Telephone: ");
	    int telephone = Integer.parseInt(r.readLine());
	    System.out.print("Blood type: ");
	    String bloodType = r.readLine();

	    Patient newPatient = new Patient(name, dob, gender, organFailure, email, telephone, bloodType);
	    
	    patientMan.addPatient(newPatient);
	    System.out.println("Patient added successfully.");
	}
	
	
	
	private static void deletePatient() {
		System.out.print("You will delete a patient from the database.");
		/*
		 * search the patient by name then get the list of those names,
		 * then print their info
		 * then the doctor knows his id
		 * then doctor select the id to delete the patient
		 */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
