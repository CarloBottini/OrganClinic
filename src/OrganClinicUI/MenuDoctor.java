package OrganClinicUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import OrganClinicINTERFACEs.CompatibleManager;
import OrganClinicINTERFACEs.DoctorManager;
import OrganClinicINTERFACEs.NurseManager;
import OrganClinicINTERFACEs.OperationManager;
import OrganClinicINTERFACEs.OrganManager;
import OrganClinicINTERFACEs.PatientManager;
import OrganClinicINTERFACEs.TreatmentManager;
import OrganClinicJDBC.JDBCCompatibleManager;
import OrganClinicJDBC.JDBCDoctorManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicJDBC.JDBCNurseManager;
import OrganClinicJDBC.JDBCOperationManager;
import OrganClinicJDBC.JDBCOrganManager;
import OrganClinicJDBC.JDBCPatientManager;
import OrganClinicJDBC.JDBCTreatmentManager;
import OrganClinicPOJOs.Compatible;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Nurse;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;
import OrganClinicPOJOs.Treatment;

public class MenuDoctor {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	private static JDBCManager connectionManager;
	private static DoctorManager doctorMan;
	private static PatientManager patientMan;
	private static NurseManager nurseMan;
	private static OperationManager operationMan;
	private static TreatmentManager treatmentMan;
	private static OrganManager organMan;
	private static CompatibleManager compatibleMan;


	public static void menuDoctor(String email) throws NumberFormatException, IOException {
		connectionManager= new JDBCManager();
		doctorMan = new JDBCDoctorManager(connectionManager);
		patientMan= new JDBCPatientManager(connectionManager);
		nurseMan = new JDBCNurseManager(connectionManager);
		operationMan = new JDBCOperationManager(connectionManager);
		treatmentMan= new JDBCTreatmentManager(connectionManager);
		organMan= new JDBCOrganManager(connectionManager);
		compatibleMan= new JDBCCompatibleManager(connectionManager);
		//TODO ??? see if conect the pojos
		
		System.out.println("Welcome Doctor! We are thrilled with your excellent work! Choose one of the following options: ");
		int whileDoctorVariable=1;
		Doctor doctor= doctorMan.getDoctorByEmail(email);
		while(whileDoctorVariable!=0) {
		System.out.println("1) ADD NEW PATIENT INTO THE DATABASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATABASE");
		//TODO DELETE THIS System.out.println("3) MODIFY THE PATIENT'S INFORMATION");
		System.out.println("3) SEE DOCTOR'S PROFILE");
		System.out.println("4) MODIFY DOCTOR'S PROFILE");
		System.out.println("5) SEE ALL NURSES AVAILABILITY");
		System.out.println("6) ASSIGN A NURSE TO AN OPERATION");
		System.out.println("7) DELETE A NURSE FROM AN OPERATION");
		System.out.println("8) SCHEDULE AN OPERATION");
		System.out.println("9) RESCHEDULE AN OPERATION");
		System.out.println("10) showAllTreatment");
		System.out.println("11) SEE ALL THE ORGANS");
		System.out.println("12) SEE THE CHARACTERISTICS OF AN ORGAN");
		
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
				//see doctro profile
				break;
				
			case 4: 
				//modify doctor profile
				break;	
				
			case 5: 
				//see nurses availability
	
				break;
				
			case 6: 
				//assign nurse to operation
				break;
				
			case 7: 
				//delete nurse from operation
				break;
				
			case 8: 
				//schedule operation
				break;
				
				
			case 9: 
				//reeschedule operation
				break;
				
			case 10: 
				//showAllTreatment
				break;
				
			case 11: 
				//see al organs
				break;
				
			case 12: 
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
	
	private static void showDoctorProfile(Doctor doctor) {
		System.out.print("Your profile: ");
		System.out.println(doctor.toString());

	}
	
	private static void modifyDoctorProfile(Doctor doctor) throws IOException {
		System.out.println("You will modify your profile. ");
		System.out.println("You can press ENTER without typing anything if you want to keep the current value. ");

		System.out.println("Current name is: " + doctor.getName());
	    System.out.print("Insert the new name: ");
	    String newName = r.readLine();
	    if (!newName.trim().isEmpty()) {
	        doctor.setName(newName);
	    }
	    System.out.println("Current date of birth is: " + doctor.getDob());
	    System.out.print("Insert new date of birth (yyyy-mm-dd): ");
	    String dob = r.readLine();
	    if (!dob.trim().isEmpty()) {
	        try {
	            Date newDob = Date.valueOf(dob);
	            doctor.setDob(newDob);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Invalid date format. You will keep the previous current date.");
	        }
	    }
	    System.out.println("Current gender: " + doctor.getGender());
	    System.out.print("Insert new gender: ");
	    String newGender = r.readLine();
	    if (!newGender.trim().isEmpty()) {
	    	doctor.setGender(newGender);
	    }
	    //We will not modify the email
	    System.out.println("Current telephone number: " + doctor.getTelephone());
	    System.out.print("Insert the new telephone number: ");
	    String telephone = r.readLine();
	    if (!telephone.trim().isEmpty()) {
	        try {
	            int newTelephone = Integer.parseInt(telephone);
	            doctor.setTelephone(newTelephone);
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number. You will keep the previus current telephone number.");
	        }
	    }
	    doctorMan.modifyDoctor(doctor);
	    System.out.println("Your profile has been successfully updated.");
	}
	
	
	
	//see only the nurses can be available
	private static void seeAvailabilityNurses() {
		List<Nurse> availableNurses = nurseMan.getAvailableNurses(); //gets only the available nurses with availability=1
	    if (availableNurses == null || availableNurses.isEmpty()) {
	        System.out.println("There are no available nurses at the moment.");
	    } else {
	        System.out.println("List of available nurses:");
	        for (Nurse nurse : availableNurses) {
	            System.out.println(nurse); 
	        }
	    }
	}
	
	
	
	private static void assignNurseToOperation() throws NumberFormatException, IOException {
        System.out.println("Insert the ID of the nurse:");
        int nurseId = Integer.parseInt(r.readLine());
        System.out.println("Insert the ID of the Operation to be add the nurse:");
        int operationId = Integer.parseInt(r.readLine());
        operationMan.assignedNurseToOperation(nurseId, operationId);
        System.out.println("Nurse assigned successfully.");
	}
	
	private static void unassignedNurseToOperation() throws NumberFormatException, IOException {
		System.out.print("Enter the Nurse ID to remove: ");
	    int nurseId = Integer.parseInt(r.readLine());
	    System.out.print("Enter the Operation ID: ");
	    int operationId = Integer.parseInt(r.readLine());
	    operationMan.unassignedNurseToOperation(nurseId, operationId);
	    System.out.println("Nurse removed from Operation.");
	}
	
	private static void scheduleOperation() throws IOException {
		System.out.print("Enter operation date (yyyy-mm-dd): ");
	    Date date = Date.valueOf(r.readLine());
		System.out.print("Showimg all the patients: ......");
		List<Patient> patients = patientMan.getAllPatients();
	    for (Patient p : patients) {
	        System.out.println(p); // usa el toString() completo
	    }
		System.out.print("Enter patient ID: ");
	    int patientId = Integer.parseInt(r.readLine());
	    System.out.print("Enter treatment ID: ");
	    Integer treatmentId = Integer.parseInt(r.readLine());
		System.out.print("Showimg all the doctors: ......");
	    List<Doctor> doctors = doctorMan.getAllDoctors();
		for (Doctor d : doctors) {
	        System.out.println(d); // usa el toString() completo
	    }
	    //so the doctor can select himself or another doctor
	    System.out.print("Enter doctor ID: ");
	    Integer doctorId = Integer.parseInt(r.readLine());
	    Date today = new Date(System.currentTimeMillis());
	    boolean isDone = !date.after(today);	//if the date is future the operation is not done	    

	    Operation operation = new Operation(null, isDone, date, patientId, treatmentId, doctorId, null);
	    operationMan.scheduleOperation(operation);
	    System.out.println("Operation scheduled.");
	}
	
	
	private static void rescheduleOperation() throws NumberFormatException, IOException {
	    System.out.print("Enter the patient ID: ");
	    Integer patientId = Integer.parseInt(r.readLine());

	    Patient patient = patientMan.getPatientByID(patientId);
	    if (patient == null) {
	        System.out.println("Patient not found!");
	        return;
	    }
	    System.out.println("Patient found: " + patient);
	    List<Operation> patientOperations = operationMan.getOperationsByPatientId(patientId);
	    if (patientOperations.isEmpty()) {
	        System.out.println("No operations found for this patient.");
	        return;
	    }
	    System.out.println("Operations for patient with " + patientId + "id :");
	    for (Operation op : patientOperations) {
	        System.out.println(op);
	    }
	    System.out.print("Enter the ID of the operation to reschedule: ");
	    Integer operationId = Integer.parseInt(r.readLine());
	    Operation operationToReschedule = operationMan.getOperationByID(operationId);
	    if (operationToReschedule == null) {
	        System.out.println("Operation not found.");
	        return;
	    }
	    System.out.println("Operation information : " + operationToReschedule);
	    System.out.print("Enter the new operation's date (yyyy-mm-dd): ");
	    String newDateString = r.readLine();
	    Date newDate = Date.valueOf(newDateString);
	    if (newDate.before(new Date(System.currentTimeMillis()))) {
	        System.out.println("You can only reschedule to a future date.");
	        return;
	    }
	    operationToReschedule.setDate(newDate);
	    operationMan.rescheduleOperation(operationToReschedule);
	    System.out.println("Operation rescheduled successfully.");
	    
	}
	
	private void showAllTreatment() {
		List<Treatment> treatments = treatmentMan.getAllTreatment(); 
	    if (treatments == null || treatments.isEmpty()) {
	        System.out.println("There are no treatments available at this moment.");
	        return;
	    }
	    System.out.println("These are all the available treatments:");
	    for (Treatment t : treatments) {
	        System.out.println(t); 
	    }        
	}

	
	private void viewAllOrgan() {
		List<Organ> organs = organMan.getAllOrgans();
	    if (organs == null || organs.isEmpty()) {
	        System.out.println("There are no organs in the system.");
	        return;
	    }
	    System.out.println("These are all the organs registered:");
	    for (Organ organ : organs) {
	        System.out.println(organ); 
	    }
	}
	
	private void viewCharacteristicsOfAnOrgan() {
	    try {
	        System.out.print("Introduce the ID of the organ your want to know its characteristics");
	        Integer organId = Integer.parseInt(r.readLine());
	        Organ organ = organMan.getOrganByID(organId);

	        if (organ != null) {
	            System.out.println("Characteristics of an Organ:");
	            System.out.println("ID: " + organ.getId());
	            System.out.println("Gender: " + organ.getGender());
	            System.out.println("Type: " + organ.getType());
	            System.out.println("size: " + organ.getSize());
	            System.out.println("Quality: " + organ.getQuality());
	            System.out.println("BloodType: " + organ.getBloodType());
	        } else {
	            System.out.println("Not found any organ with ID:  "+organId);
	        }
	    } catch (IOException e) {
	        System.out.println("Error while reading the input");
	        e.printStackTrace();
	    } catch (NumberFormatException e) {
	        System.out.println("The ID must be an Integer");
	    }
	}

	
	private static void checkCompatibility() throws IOException {
	    System.out.print("You will check the compatibility between a patient and an organ. ");
	    System.out.print("Enter patient ID: ");
	    int patientId = Integer.parseInt(r.readLine());
	    Patient patient = patientMan.getPatientByID(patientId);
	    if (patient == null) {
	        System.out.println("Patient not found.");
	        return;
	    }
	    System.out.print("Enter organ ID: ");
	    int organId = Integer.parseInt(r.readLine());
	    Organ organ = organMan.getOrganByID(organId);
	    if (organ == null) {
	        System.out.println("Organ not found.");
	        return;
	    }

	    String patientBlood = patient.getBloodType();
	    String organBlood = organ.getBloodType();
	    boolean bloodCompatible = isBloodCompatible(patientBlood, organBlood);
	    int percentCompatibility = 0;
	    
	    
	    if (bloodCompatible) {
	        LocalDate dob = patient.getDob().toLocalDate();
	    	int age = Period.between(dob, LocalDate.now()).getYears();

	        boolean isChild = age < 18;
	        String organSize = organ.getSize().toUpperCase(); //devuelve "CHILD" or "ADULT"

	        if (isChild && organSize.equalsIgnoreCase("CHILD")) {
	            percentCompatibility = getRandomNumber(90, 99);
	        } else if (isChild && organSize.equalsIgnoreCase("ADULT")) {
	            percentCompatibility = getRandomNumber(50, 85);
	        } else if (!isChild && organSize.equalsIgnoreCase("CHILD")) {
	            percentCompatibility = getRandomNumber(60, 70);
	        } else if (!isChild && organSize.equalsIgnoreCase("ADULT")) {
	            percentCompatibility = getRandomNumber(80, 99);
	        }
	    }  
	
	    Compatible compatible = new Compatible(patient,organ,(float)percentCompatibility,bloodCompatible);
	    compatibleMan.addCompatible(compatible);
	    
	    System.out.println("Blood-compatible: " + bloodCompatible);
	    System.out.println("Percent compatibility: " + percentCompatibility + "%");
	    
	}
	
	private static boolean isBloodCompatible(String patientBloodType, String organBloodType) {
	    //each blood type and his posible donors in a set
	    Map<String, Set<String>> compatibilityMap = Map.of(
	        "Negative_0", Set.of("Negative_0"),
	        "Positive_0", Set.of("Negative_0", "Positive_0"),
	        "Negative_A", Set.of("Negative_0", "Negative_A"),
	        "Positive_A", Set.of("Negative_0", "Positive_0", "Negative_A", "Positive_A"),
	        "Negative_B", Set.of("Negative_0", "Negative_B"),
	        "Positive_B", Set.of("Negative_0", "Positive_0", "Negative_B", "Positive_B"),
	        "Negative_AB", Set.of("Negative_0", "Negative_A", "Negative_B", "Negative_AB"),
	        "Positive_AB", Set.of("Negative_0", "Positive_0", "Negative_A", "Positive_A","Negative_B", "Positive_B", "Negative_AB", "Positive_AB")
	    );

	    Set<String> allowedDonors = compatibilityMap.get(patientBloodType);
	    return allowedDonors != null && allowedDonors.contains(organBloodType);//return true if is compatible //false if not compatible
	}
	
	private static int getRandomNumber(int min, int max) {
	    return new Random().nextInt(max - min + 1) + min;
	}
	
	
	
}
