package OrganClinicUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
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
import OrganClinicINTERFACEs.UserManager;
import OrganClinicINTERFACEs.XMLManager;
import OrganClinicJDBC.JDBCCompatibleManager;
import OrganClinicJDBC.JDBCDoctorManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicJDBC.JDBCNurseManager;
import OrganClinicJDBC.JDBCOperationManager;
import OrganClinicJDBC.JDBCOrganManager;
import OrganClinicJDBC.JDBCPatientManager;
import OrganClinicJDBC.JDBCTreatmentManager;
import OrganClinicJPA.JPAUserManager;
import OrganClinicPOJOs.Compatible;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Nurse;
import OrganClinicPOJOs.Operation;
import OrganClinicPOJOs.Organ;
import OrganClinicPOJOs.Patient;
import OrganClinicPOJOs.Role;
import OrganClinicPOJOs.Treatment;
import OrganClinicPOJOs.User;
import OrganClinicXML.XMLManagerImpl;

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
	private static UserManager userMan;
	private static XMLManager xmlMan;
	private static UserManager userManager;


	public static void menuDoctor(String email) throws NumberFormatException, IOException {
		connectionManager= new JDBCManager();
		doctorMan = new JDBCDoctorManager(connectionManager);
		patientMan= new JDBCPatientManager(connectionManager);
		nurseMan = new JDBCNurseManager(connectionManager);
		operationMan = new JDBCOperationManager(connectionManager);
		treatmentMan= new JDBCTreatmentManager(connectionManager);
		organMan= new JDBCOrganManager(connectionManager);
		compatibleMan= new JDBCCompatibleManager(connectionManager);
		userMan= new JPAUserManager();
		xmlMan= new XMLManagerImpl();
		userManager= new JPAUserManager();

		
		System.out.println("Welcome Doctor! We are thrilled with your excellent work! ");
		int whileDoctorVariable=1;
		Doctor doctor= doctorMan.getDoctorByEmail(email);
		while(whileDoctorVariable!=0) {
		System.out.println("");
		System.out.println("Choose one of the following options:");
		System.out.println("1) ADD NEW PATIENT INTO THE DATABASE");
		System.out.println("2) DELETE A PATIENT FROM THE DATABASE");
		System.out.println("3) SEE DOCTOR'S PROFILE");
		System.out.println("4) MODIFY DOCTOR'S PROFILE");
		System.out.println("5) SEE ALL NURSES AVAILABILITY");
		System.out.println("6) ASSIGN A NURSE TO AN OPERATION");
		System.out.println("7) DELETE A NURSE FROM AN OPERATION");
		System.out.println("8) SCHEDULE AN OPERATION");
		System.out.println("9) RESCHEDULE AN OPERATION");
		System.out.println("10) SHOW ALL TREATMENTS");
		System.out.println("11) SEE ALL THE ORGANS");
		System.out.println("12) SEE THE CHARACTERISTICS OF AN ORGAN");
		System.out.println("13) CHECK COMPATIBILITY BETWEEN PATIENT AND ORGAN");
		System.out.println("14) GENERATE A XML FILE");
		System.out.println("15) DOWNLOAD A XML INTO THE PROGRAM");
		System.out.println("16) GENERATE A HTML FILE");
		//System.out.println("17) DOWNLOAD A HTML INTO THE PROGRAM");
			
		System.out.println("0) EXIT");
		
		int doctorChoice = Integer.parseInt(r.readLine());
			switch (doctorChoice) {
			case 1: 
				addPatient();
				break;
			
			case 2: 
				deletePatient();
				break;
						
			case 3: 
				showDoctorProfile(doctor);
				break;
				
			case 4: 
				modifyDoctorProfile(doctor);
				break;	
				
			case 5: 
				seeAvailableNurses();	
				break;
				
			case 6: 
				assignNurseToOperation();

				break;
				
			case 7: 
				unassignedNurseToOperation();
				break;
				
			case 8: 
				scheduleOperation();
				break;
				
				
			case 9: 
				rescheduleOperation();
				break;
				
			case 10: 
				showAllTreatment();
				break;
				
			case 11: 
				viewAllOrgan();
				break;
				
			case 12: 
				viewCharacteristicsOfAnOrgan();
				break;
				
			case 13:
				checkCompatibility();
				break;
				
			case 14:
				//XML
				System.out.println("You will generate a XML file. The file will be of a DOCTOR(1) or a PATIENT (2)");
				System.out.println("The doctor option will create XML file with your information as a doctor");
				int choice=0;
				while (choice != 1 && choice != 2) {
					System.out.print("Choose 1 for Doctor or 2 for Patient: ");
					choice = Integer.parseInt(r.readLine());
					if (choice != 1 && choice != 2) {
						System.out.println("Invalid option. Please, choose 1 or 2.");
					}
				}
				if (choice == 1) {
					saveDoctorToXMLFile(doctor);
					System.out.println("Doctor's data saved to XML successfully.");
				} else {
					Patient selectedPatient = selectPatientFromList();		
					if (selectedPatient == null) {
						System.out.println("No patient found with that ID.");
					} else {
						savePatientToXMLFile(selectedPatient);
						System.out.println("Patient's data saved to XML successfully.");
					}
				}
				
				break;
				
			case 15:
				//download xml
				List<String> filesInProyect = getXMLFilenamesInFolderProyect();
				downloadXML(filesInProyect);
				
				break;
					
			case 16:
				//HTML
				System.out.println("You will generate a HTML file. The file will be of a DOCTOR(1) or a PATIENT (2)");
				System.out.println("The doctor option will create HTML file with your information as a doctor");
				int choiceHTML=0;
				while (choiceHTML != 1 && choiceHTML != 2) {
					System.out.print("Choose 1 for Doctor or 2 for Patient: ");
					choiceHTML = Integer.parseInt(r.readLine());
					if (choiceHTML != 1 && choiceHTML != 2) {
						System.out.println("Invalid option. Please, choose 1 or 2.");
					}
				}
				if (choiceHTML == 1) {
					saveDoctorToHTMLFile(doctor);
					System.out.println("Doctor's data saved to HTML successfully.");
				} else {
					Patient selectedPatient = selectPatientFromList();				
					if (selectedPatient == null) {
						System.out.println("No patient found with that ID.");
					} else {
						savePatientToHTMLFile(selectedPatient);
						System.out.println("Patient's data saved to HTML successfully.");
					}
				}
				break;
				
			/*case 17:
				// download HTML
				List<String> htmlFiles = getHTMLFilenamesInFolderProyect();
				downloadHTML(htmlFiles);
				break;
			*/	
			case 0:
				whileDoctorVariable=0;
				connectionManager.closeConnection();	
			break;
			
			default:
				System.out.println("You inserted a number that is not an option. Please, introduce again a number of the following options: ");
			
			}		
		}		
	}
	
	private static void addPatient() throws IOException, Exception {
		System.out.println("Please, add the following patient's information.");
		System.out.print("Patient EMAIL: ");
        String email = r.readLine();
        Patient existingPatient = patientMan.getPatientByEmail(email);
        if (existingPatient != null) {
            System.out.println("The patient with the mail: " + email + " is already registered.");
            return;
        }
        System.out.print("Patient PASSWORD: ");
        String password = r.readLine();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        Role patientRole = userManager.getRole(1); // role 1 = paciente
        User newUser = new User(email, digest, patientRole);
        userManager.newUser(newUser);
        
		System.out.println("Patient NAME: ");
		String name = r.readLine();
		System.out.print("Patient Date Of Birth (yyyy-mm-dd): ");
	    String dobStr = r.readLine();
	    Date dob = null;
	    try {
	        dob = Date.valueOf(dobStr);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid date format. Use yyyy-mm-dd.");
	        return;
	    }
	    String gender = "";
	    while (true) {
		    System.out.print("Gender M for male and F for female: ");
	        gender = r.readLine().trim().toUpperCase();
	        if (gender.equals("M") || gender.equals("F")) {
	            break;
	        } else {
	            System.out.println("Invalid input. Please enter 'M' or 'F'.");
	        }
	    }
	    System.out.print("Organ failure (Brain, Lung, Liver, Kidney, Heart: ");
	    String organFailure = r.readLine();
	    
	    /*
	    System.out.print("Email: ");
	    String email = r.readLine();
	    */
	    
	    System.out.print("Telephone: ");
	    int telephone;
	    try {
	        telephone = Integer.parseInt(r.readLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid telephone number.");
	        return;
	    }
	    String[] bloodTypes = {"Positive_B", "Positive_AB", "Positive_A", "Positive_0", "Negative_B","Negative_AB", "Negative_A","Negative_0"};

	        System.out.println("Select Blood Type:");
	        for (int i = 0; i < bloodTypes.length; i++) {
	            System.out.println((i + 1) + ". " + bloodTypes[i]);
	        }

	        String bloodType = "";
	        while (true) {
	            System.out.print("Enter option (1-8): ");
	            try {
	                int choice = Integer.parseInt(r.readLine());
	                if (choice >= 1 && choice <= bloodTypes.length) {
	                    bloodType = bloodTypes[choice - 1];
	                    break;
	                } else {
	                    System.out.println("Please enter a number between 1 and " + bloodTypes.length + ".");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a number.");
	            }
	        }

	    Patient newPatient = new Patient(name, dob, gender, organFailure, email, telephone, bloodType);
	    
	    patientMan.addPatient(newPatient);
	    System.out.println("Patient added successfully.");
	}
	
	
	
	private static void deletePatient() throws IOException {		
		System.out.println("Enter the name of the patient you want to delete:");
	    String name = r.readLine();

	    List<Patient> patientsFound = patientMan.getPatientsByName(name);
	    if (patientsFound.isEmpty()) {
	        System.out.println("No patients found with the name: " + name);
	        return;
	    }
	    System.out.println("patients found :");
	    for (Patient p : patientsFound) {
	        System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", DOB: " + p.getDob() + ", Email: " + p.getEmail() + ", Phone: " + p.getTelephone());
	    }
	    System.out.println("Enter the ID of the patient to delete:");
	    int idToDelete = Integer.parseInt(r.readLine());
	    patientMan.deletePatient(idToDelete);
	    System.out.println("Patient deleted successfully.");
		
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
	    String newGender;
	    while (true) {
	        System.out.print("Insert new gender (M or F): ");
	        newGender = r.readLine();
	        if (newGender.trim().isEmpty()) {
	            //the same gender if we do not write nothing
	            break;
	        }
	        newGender = newGender.trim().toUpperCase();
	        if (newGender.equals("M") || newGender.equals("F")) {
	            doctor.setGender(newGender);
	            break;
	        } else {
	            System.out.println("Invalid gender. Please enter 'M' or 'F' (or press ENTER to keep current).");
	        }
	    }
	    //we will not modify the email
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
	
	
	
	//see only the nurses that are available
	private static void seeAvailableNurses() {
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
        System.out.println("Showing all operations: ");
        List<Operation> operations = operationMan.getAllOperations(); // << Llama al nuevo método
        for (Operation op : operations) {
            System.out.println(op);
        }
        System.out.println("Insert the ID of the Operation to be add the nurse:");
        int operationId = Integer.parseInt(r.readLine());
        operationMan.assignedNurseToOperation(nurseId, operationId);
        System.out.println("Nurse assigned successfully.");
	}
	
	private static void unassignedNurseToOperation() throws NumberFormatException, IOException {
		List<Operation> allOperations = operationMan.getAllOperations(); 
	    if (allOperations.isEmpty()) {
	        System.out.println("There are no operations registered in the system.");
	        return;
	    }	    
	    System.out.println("These are all the operations in the system:");
	    for (Operation op : allOperations) {
	        System.out.println(op);
	    }
		System.out.print("Enter the Nurse ID to remove: ");
	    int nurseId = Integer.parseInt(r.readLine());
	    System.out.print("Enter the Operation ID: ");
	    int operationId = Integer.parseInt(r.readLine());
	    operationMan.unassignedNurseToOperation(nurseId, operationId);
	    System.out.println("Nurse removed from Operation.");
	}
	
	private static void scheduleOperation() throws IOException {
		System.out.print("Enter operation date (yyyy-mm-dd): ");
		Date date;
	    try {
	        date = Date.valueOf(r.readLine());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid date format. Use yyyy-mm-dd.");
	        return;
	    }
		System.out.print("Showimg all the patients: ......");
		List<Patient> patients = patientMan.getAllPatients();
	    for (Patient p : patients) {
	        System.out.println(p); // usa el toString() completo
	    }
		System.out.print("Enter patient ID: ");
		int patientId;
	    try {
	        patientId = Integer.parseInt(r.readLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid patient ID.");
	        return;
	    }
	    System.out.println("Showing all treatments:");
	    List<Treatment> treatments = treatmentMan.getAllTreatment(); 
	    for (Treatment t : treatments) {
	        System.out.println(t);
	    }	    
	    System.out.print("Enter treatment ID: ");
	    int treatmentId;
	    try {
	        treatmentId = Integer.parseInt(r.readLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid treatment ID.");
	        return;
	    }	   
	    System.out.println("Showing all organs:");
	    List<Organ> organs = organMan.getAllOrgans(); 
	    for (Organ o : organs) {
	        System.out.println(o);
	    }	   	    
	    System.out.print("Enter organ ID: ");
	    Integer organID = Integer.parseInt(r.readLine());
	    	    
		System.out.print("Showimg all the doctors: ......");
	    List<Doctor> doctors = doctorMan.getAllDoctors();
		for (Doctor d : doctors) {
	        System.out.println(d); 
	    }
	    //so the doctor can select himself or another doctor
	    System.out.print("Enter doctor ID: ");
	    Integer doctorId = Integer.parseInt(r.readLine());
	    
	    System.out.println("Showing all available nurses:");
	    List<Nurse> nurses = nurseMan.getAvailableNurses(); 
	    for (Nurse n : nurses) {
	        System.out.println(n);
	    }
	    System.out.print("Enter nurse ID to assign: ");
	    int nurseId;
	    try {
	        nurseId = Integer.parseInt(r.readLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid nurse ID.");
	        return;
	    }
	   	    
	 	    
	    Date today = new Date(System.currentTimeMillis());
	    boolean isDone = !date.after(today);	//if the date is future the operation is not done	    

	    Operation operation = new Operation(isDone, date, patientId, treatmentId, doctorId,null);
	    
	    operationMan.scheduleOperation(operation);
	    int operationId = operation.getId();
	    operationMan.assignedNurseToOperation(nurseId, operationId);
	    
	    Nurse nurse = nurseMan.getNurseByID(nurseId);
	    if (nurse != null) {
	        operation.getListNurse().add(nurse);
	    } else {
	        System.out.println("Error: Nurse not found with the provided ID.");
	        return;
	    }
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
	    System.out.print("Do you want to assign additional nurses to this operation? (Y for yes/N for no): ");
	    String addNurseResponse = r.readLine();
	    while (addNurseResponse.equalsIgnoreCase("Y")) {
	        System.out.println("Showing all available nurses:");
	        List<Nurse> availableNurses = nurseMan.getAvailableNurses();
	        for (Nurse n : availableNurses) {
	            System.out.println(n);
	        }
	        System.out.print("Enter nurse ID to assign: ");
	        int nurseId = Integer.parseInt(r.readLine());
	        operationMan.assignedNurseToOperation(nurseId, operationId);
	        Nurse nurse = nurseMan.getNurseByID(nurseId);
	        if (nurse != null) {
	            operationToReschedule.getListNurse().add(nurse);
	        }
	        System.out.print("Do you want to assign another nurse? (Y for yes /N for no): ");
	        addNurseResponse = r.readLine();
	    }
	    System.out.print("Do you want to UNASSIGN a nurse from this operation? (Y for yes/N for no): ");
	    String unassignResponse = r.readLine();
	    while (unassignResponse.equalsIgnoreCase("Y")) {
	        if (operationToReschedule.getListNurse().isEmpty()) {
	            System.out.println("No nurses are currently assigned to this operation.");
	            break;
	        }
	        System.out.println("Nurses assigned to this operation:");
	        for (Nurse n : operationToReschedule.getListNurse()) {
	            System.out.println(n);
	        }
	        System.out.print("Enter nurse ID to unassign: ");
	        int nurseIdToRemove = Integer.parseInt(r.readLine());
	        operationMan.unassignedNurseToOperation(nurseIdToRemove, operationId);
	        Nurse nurseToRemove = nurseMan.getNurseByID(nurseIdToRemove);
	        operationToReschedule.getListNurse().remove(nurseToRemove);
	        System.out.print("Do you want to unassign another nurse? (Y for yes/N for no): ");
	        unassignResponse = r.readLine();
	    } 
	        System.out.print("Do you want to change the doctor for this operation? (Y for yes/N for no): ");
	        String changeDoctorResponse = r.readLine();
	        if (changeDoctorResponse.equalsIgnoreCase("Y")) {
	            System.out.println("Showing all doctors:");
	            List<Doctor> doctors = doctorMan.getAllDoctors();
	            for (Doctor d : doctors) {
	                System.out.println(d);
	            }
	            System.out.print("Enter the new doctor ID: ");
	            int newDoctorId = Integer.parseInt(r.readLine());
	            operationToReschedule.setDoctor_id(newDoctorId);
	            operationMan.rescheduleOperation(operationToReschedule);
	            System.out.println("Doctor updated successfully.");
	        }
	    
	    
	}
	
	private static void showAllTreatment() {
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

	
	private static void viewAllOrgan() {
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
	
	private static void viewCharacteristicsOfAnOrgan() {
	    try {
	        System.out.print("Introduce the ID of the organ your want to know its characteristics: ");
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
	        String organSize = organ.getSize().toUpperCase(); //return "CHILD" or "ADULT"

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
	    //each blood type and his possible donors in a set
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
	
	private static Patient selectPatientFromList() throws IOException {
		List<Patient> patients = patientMan.getAllPatients(); 
		if (patients.isEmpty()) {
			System.out.println("No patients available in the database.");
			return null;
		}
		System.out.println("List of all patients:");
		for (Patient p : patients) {
			System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + " , Email: " + p.getEmail());
		}
		System.out.print("Enter the ID of the patient you want to export: ");
		int patientId = Integer.parseInt(r.readLine());
		return patientMan.getPatientByID(patientId);
	}

	
	private static void saveDoctorToXMLFile(Doctor doctor ) throws NumberFormatException, IOException {
		System.out.println(" Generating XML of the Doctor: ");
		System.out.println(doctor.toString());
		xmlMan.doctor2XML(doctor);
	}
	
	private static void saveDoctorToHTMLFile(Doctor doctor ) throws NumberFormatException, IOException {
		System.out.println(" Generating HTML of the Doctor: ");
		System.out.println(doctor.toString());
		xmlMan.doctor2HTML(doctor);
}
		
	private static void savePatientToXMLFile(Patient patient ) throws NumberFormatException, IOException {
		System.out.println(" Generating XML of the Patient: ");
		System.out.println(patient.toString());
		xmlMan.patient2XML(patient);
}
	
	private static void savePatientToHTMLFile(Patient patient ) throws NumberFormatException, IOException {
		System.out.println(" Generating HTML of the Patient: ");
		System.out.println(patient.toString());
		xmlMan.patient2HTML(patient);
}
	
	
	private static List<String> getXMLFilenamesInFolderProyect() {
		List<String> xmlFiles = new ArrayList<>();
		File folder = new File("./xmls");
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
						xmlFiles.add(file.getName());
					}
				}
			}
		}
		return xmlFiles;
	}
	
	 private static void downloadXML(List<String> xmlFile) throws NumberFormatException, IOException {
	    	int cont = 1;
	    	System.out.println(" -Which file do you want to load: ");
	    	Iterator<String> it = xmlFile.iterator();
	    	while(it.hasNext()) {
	    		System.out.println("   " + cont + ". " + it.next());
	    		cont++;
	    	}
	    	Integer option=0;
	    		try {
	    	 	do {
	    	 		System.out.println(" Choose a file given: ");
	        		option = Integer.parseInt(r.readLine())-1;
	        		if(option < 0 || option >= xmlFile.size()) {
	        		    System.out.println(" ERROR: Invalid option.");
	        		}
	        	} while(option < 0 );	
	    		File fileName = new File("./xmls/" + xmlFile.get(option));
	    		
	    	   	if(xmlFile.get(option).endsWith("-Patient.xml") ){
	        		Patient patient = xmlMan.XML2patient(fileName);
	        		patientMan.addPatient(patient);
	        		}
	        	if(xmlFile.get(option).endsWith("-Doctor.xml")) {
	        		Doctor doctor = xmlMan.XML2doctor(fileName);
	        		doctorMan.addDoctor(doctor);
	    		}    

	    }catch (NumberFormatException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	 private static List<String> getHTMLFilenamesInFolderProyect() {
			List<String> htmlFiles = new ArrayList<>();
			File folder = new File("./xmls");	//in our case we save everything in our file xmls
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isFile() && file.getName().toLowerCase().endsWith(".html")) {
							htmlFiles.add(file.getName());
						}
					}
				}
			}
			return htmlFiles;
		}

		private static void downloadHTML(List<String> htmlFiles) throws NumberFormatException, IOException {
			int cont = 1;
			System.out.println(" -Which HTML file do you want to load: ");
			for (String name : htmlFiles) {
				System.out.println("   " + cont + ". " + name);
				cont++;
			}
			int option = -1;
			do {
				System.out.print("Choose a file: ");
				option = Integer.parseInt(r.readLine()) - 1;
				if (option < 0 || option >= htmlFiles.size()) {
					System.out.println("ERROR: Invalid option.");
				}
			} while (option < 0 || option >= htmlFiles.size());

			System.out.println("You selected: " + htmlFiles.get(option));
			//this could be the code for HTML but HTML is only for view not for importing
			//we need to use something else than JAXB

		}

	
	
}
