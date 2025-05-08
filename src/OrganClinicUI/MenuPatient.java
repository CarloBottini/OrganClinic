package OrganClinicUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import OrganClinicINTERFACEs.PatientManager;
import OrganClinicJDBC.JDBCManager;
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
	

	//TODO we remember that the email is our username
	public static void menuPatient(String email) {
		connectionManager= new JDBCManager();
		//TODO ??? see if conect the pojos
		
		System.out.println("Welcome patient! Select the option: ");
		int variableWhilePatient=1;
		Patient patient= patientMan.getPatientByEmail(email);
		while(variableWhilePatient!=0) {
		System.out.println("1) SEE YOUR PROFILE INFORMATION");
		System.out.println("2) MODIFY YOUR PROFILE INFORMATION");
		System.out.println("3) SEE DOCTOR'S INFORMATION");
		System.out.println("0) EXIT");
		int patientChoice = Integer.parseInt(r.readLine());

		switch (patientChoice) {
		
		case 1: 
			//see patient profile 
			break;
		
		case 2: 
			//modify patient profile
			break;
		
			
		case 3:
			//see doctor infor
			break;
			
		case 0:
			variableWhilePatient=0;
			connectionManager.close();	
			break;
		
		default:
			System.out.println("You inserted a number that is not an option. Please, introduce again a number of the following options: ");
		}
		
	}
		
}
	
	
	public static void seePatientInformation(Patient patient) {
	}
	
	
	public static void modifyPatientInformation() {
		
	}
	
	
	public static void seeDoctorInformation() {
		
	}
	
}
