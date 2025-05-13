package OrganClinicUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import OrganClinicINTERFACEs.PatientManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicJDBC.JDBCPatientManager;
import OrganClinicPOJOs.Patient;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Nurse;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Treatment;



public class MenuPatient {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	
	private static PatientManager patientMan;
	private static JDBCManager connectionManager;
	

	//we remember that the email is our username
	public static void menuPatient(String email) throws NumberFormatException, IOException {
		connectionManager= new JDBCManager();
		patientMan = new JDBCPatientManager(connectionManager);
		
		System.out.println("Welcome patient! Insert the option: ");
		int variableWhilePatient=1;
		Patient patient= patientMan.getPatientByEmail(email);
		while(variableWhilePatient!=0) {
		System.out.println("1) SEE YOUR PROFILE INFORMATION");
		System.out.println("2) MODIFY YOUR PROFILE INFORMATION");
		System.out.println("0) EXIT");
		int patientChoice = Integer.parseInt(r.readLine());

		switch (patientChoice) {
		
		case 1: 
			//see patient profile 
			seePatientInformation(patient);
			break;
		
		case 2: 
			//modify patient profile
			modifyPatientInformation(patient);
			break;
		
		case 0:
			variableWhilePatient=0;
			connectionManager.closeConnection();	
			break;
		
		default:
			System.out.println("You inserted a number that is not an option. Please, introduce again a number of the following options: ");
		}
		
	}
		
}
	
	
	public static void seePatientInformation(Patient patient) {
		System.out.println("You will see the information of your profile");
		//It could be not necessary the research by id because you entered before the patient to print
		int patientId=patient.getId();
		Patient patientToPrint= patientMan.getPatientByID(patientId);
		System.out.println("Your profile is:");
		System.out.println(patientToPrint.toString());
	}
	
	
	public static void modifyPatientInformation(Patient patient) throws IOException {
		System.out.println("You will modify your profile. ");
		System.out.println("You can press ENTER without typing anything if you want to keep the current value. ");

		System.out.println("Current name is: " + patient.getName());
	    System.out.print("Insert the new name: ");
	    String newName = r.readLine();
	    if (!newName.trim().isEmpty()) {
	        patient.setName(newName);
	    }
	    System.out.println("Current date of birth is: " + patient.getDob());
	    System.out.print("Insert new date of birth (yyyy-mm-dd): ");
	    String dob = r.readLine();
	    if (!dob.trim().isEmpty()) {
	        try {
	            Date newDob = Date.valueOf(dob);
	            patient.setDob(newDob);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Invalid date format. You will keep the previous current date.");
	        }
	    }
	    System.out.println("Current gender: " + patient.getGender());
	    System.out.print("Insert new gender: ");
	    String newGender = r.readLine();
	    if (!newGender.trim().isEmpty()) {
	        patient.setGender(newGender);
	    }
	    //We will not modify the organFailure because as the patient is added by the doctor the damaged organ cannot be changed
	    //We will not modify the email

	    System.out.println("Current telephone number: " + patient.getTelephone());
	    System.out.print("Insert the new telephone number: ");
	    String telephone = r.readLine();
	    if (!telephone.trim().isEmpty()) {
	        try {
	            int newTelephone = Integer.parseInt(telephone);
	            patient.setTelephone(newTelephone);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number. You will keep the previus current telephone number.");
	        }
	    }
	    //The bloodType cannot be changed.
	    patientMan.modifyPatient(patient);
	    System.out.println("Your profile has been successfully updated.");
		
	}
	
	
}
