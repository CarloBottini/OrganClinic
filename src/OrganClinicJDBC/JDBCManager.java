package OrganClinicJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
	
	private Connection connection = null;

	public JDBCManager() {
		
		try {
		
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:./db/DDBBOrganClinic.db");
		connection.createStatement().execute("PRAGMA foreign_keys=ON");
		
		System.out.println("The database connection is open");	
		
		createTables();
		
		
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}
	
	}
	
private void createTables() {
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "CREATE TABLE Doctor ("
					+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name TEXT NOT NULL,"
					+ "	dob DATE NOT NULL,"
					+ "	gender TEXT CHECK (gender IN ('M', 'F')),"
					+ "	email TEXT UNIQUE,"
					+ "	telephone TEXT UNIQUE)";
				stmt.executeUpdate(sql);

	
			 sql= "CREATE TABLE Patient ("
				+"	id INTEGER PRIMARY KEY AUTOINCREMENT , "
				+"	name TEXT NOT NULL,"
				+"	dob DATE NOT NULL,"
				+"	gender TEXT CHECK (gender IN ('M', 'F')), "
				+"	organFailure TEXT NOT NULL,"
				+"	email TEXT UNIQUE ,"
				+"	telephone TEXT UNIQUE,"
				+"	bloodType TEXT NOT NULL REFERENCES Blood(type))";
			stmt.executeUpdate(sql);
			
		
			sql= "CREATE TABLE Blood("
					+ " type TEXT PRIMARY KEY)";
			stmt.executeUpdate(sql);

			
			
		
			sql = "CREATE TABLE Nurse ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	name TEXT NOT NULL,"
				+ "	availability BOOLEAN NOT NULL)";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Organ ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	gender TEXT CHECK (gender IN ('M', 'F')),"
				+ "	type TEXT NOT NULL,"
				+ "	size TEXT CHECK (size IN ('ADULT', 'CHILD')),"
				+ "	quality INTEGER NOT NULL CHECK (quality BETWEEN 0 AND 100),"
				+ "	bloodType TEXT NOT NULL REFERENCES Blood(type))";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Treatment ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	name TEXT NOT NULL,"
				+ "	type TEXT NOT NULL,"
				+ "	duration INTEGER NOT NULL)";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Operation ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	isDone BOOLEAN NOT NULL,"
				+ "	date DATE NOT NULL,"
				+ "	patient_id INTEGER NOT NULL REFERENCES Patient(id),"
				+ "	treatment_id INTEGER NOT NULL REFERENCES Treatment(id),"
				+ "	doctor_id INTEGER NOT NULL REFERENCES Doctor(id))";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Compatible ("
				+ "	patient_id INTEGER NOT NULL REFERENCES Patient(id),"
				+ "	organ_id INTEGER NOT NULL REFERENCES Organ(id),"
				+ "	percentCompatibility INTEGER NOT NULL CHECK (percentCompatibility BETWEEN 0 AND 100),"
				+ "	bloodCompatible BOOLEAN NOT NULL,"
				+ "	PRIMARY KEY (patient_id, organ_id))";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Has ("
				+ "	nurse_id INTEGER NOT NULL REFERENCES Nurse(id),"
				+ "	operation_id INTEGER NOT NULL REFERENCES Operation(id),"
				+ "	PRIMARY KEY (operation_id, nurse_id))";
			stmt.executeUpdate(sql);
		
			//////////////////////////////
			
			
			
// insert default values to the tables
			
			
//insert the information of the blood
			sql = "INSERT INTO Blood (type) VALUES ('Negative_0')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Positive_0')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Positive_A')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Negative_A')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Positive_B')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Negative_B')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Positive_AB')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Blood (type) VALUES ('Negative_AB')";
			stmt.executeUpdate(sql);

			
//We insert the patients information
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('John Doe', '2000-10-31', 'M', 'Liver', 'JD@hotmail.com', '693847561', 'Positive_0')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('Aitor Menta', '2002-11-01', 'M', 'Kidney', 'stormbreaker@gmail.com', '273485960', 'Positive_A')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('Abraham Mateo', '1995-10-21', 'M', 'Heart', 'lincon@gmail.com', '172837465', 'Negative_B')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('Freddy Mercury', '1990-08-11', 'M', 'Pancreas', 'Venus@gmail.com', '909876543', 'Positive_AB')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('Giovanni Lo Celso', '1980-07-23', 'M', 'Kidney', 'argentino123@hotmail.com', '123456543', 'Positive_0')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Patient (name, dob, gender, organFailure, email, telephone, bloodType) "
			    + "VALUES('Sara Infante', '2003-10-31', 'F', 'Lung', 'InfanteSa@gmail.com', '982514209', 'Negative_A')";
			stmt.executeUpdate(sql);

		
//insert the doctor information
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Sandy March', '1985-06-19', 'F', 'SandyMarch@gmail.com', '345271623')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Selene Dion', '1990-07-16', 'F', 'DionSel@hotmail.com', '345676374')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Jack Sparrow', '1955-10-21', 'M', 'HeardOf@pirate.com', '569382745')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Bond James', '1985-07-15', 'M', '007@MI6.uk', '989726451')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Alejandro Salgado', '1975-06-26', 'M', 'Salgalejandro@gmail.com', '234657897')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Doctor (name, dob, gender, email, telephone) "
			    + "VALUES('Carmelo Benito', '1969-12-20', 'M', 'BenitoC@gmail.com', '989945634')";
			stmt.executeUpdate(sql);

		
//insert the values of the nurse
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Ferran Torres', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Luis Garcia', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Andrea Garcia', 0)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Elena Nito', 0)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Jorge Liso', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Paula Sanchez', 0)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Solo Cabo', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Diego Gonzalez', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Tu Youyou', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Laura Pastos', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Pilar Santos', 0)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Dolores Fuertes', 0)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Nicolas Ruiz', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Sara Col', 1)";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Nurse (name, availability) VALUES ('Luis Rubio', 1)";
			stmt.executeUpdate(sql);

			
//insert the values of Organs
			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('M', 'Heart', 'CHILD', 87, 'Positive_A')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('F', 'Kidney', 'ADULT', 85, 'Negative_B')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('M', 'Liver', 'CHILD', 89, 'Positive_AB')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('F', 'Lung', 'ADULT', 92, 'Positive_0')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('M', 'Pancreas', 'CHILD', 86, 'Positive_B')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Organ (gender, type, size, quality, bloodType) "
			    + "VALUES('F', 'Heart', 'ADULT', 90, 'Negative_AB')";
			stmt.executeUpdate(sql);

			
//inserts the values for treatments
			sql = "INSERT INTO Treatment (name, type, duration) "
			    + "VALUES('Anticoagulants', 'Pharmacological', 10)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Treatment (name, type, duration) "
			    + "VALUES('Nutritional therapy', 'Dietary', 14)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Treatment (name, type, duration) "
			    + "VALUES('Radiation therapy', 'Medical procedure', 20)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Treatment (name, type, duration) "
			    + "VALUES('Speech therapy', 'Rehabilitation', 6)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Treatment (name, type, duration) "
			    + "VALUES('Physiotherapy', 'Rehabilitation', 12)";
			stmt.executeUpdate(sql);

//inserts the values of the Operation
			
			sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) "
			    + "VALUES(0, '2025-10-15', 2, 9, 3)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) "
			    + "VALUES(0, '2025-11-20', 1, 10, 2)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) "
			    + "VALUES(1, '2025-01-12', 6, 11, 5)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) "
			    + "VALUES(1, '2025-02-25', 4, 12, 1)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Operation (isDone, date, patient_id, treatment_id, doctor_id) "
			    + "VALUES(0, '2025-04-30', 3, 13, 4)";
			stmt.executeUpdate(sql);

//inserts the values of the Compatible table		
		
			sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) "
			    + "VALUES(2, 13, 88, 1)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) "
			    + "VALUES(1, 14, 70, 0)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) "
			    + "VALUES(6, 15, 94, 1)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) "
			    + "VALUES(5, 16, 75, 1)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Compatible (patient_id, organ_id, percentCompatibility, bloodCompatible) "
			    + "VALUES(3, 17, 60, 0)";
			stmt.executeUpdate(sql);

//inserts the values of the Has table
			sql = "INSERT INTO Has (nurse_id, operation_id) "
			    + "VALUES(3, 7)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Has (nurse_id, operation_id) "
			    + "VALUES(6, 8)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Has (nurse_id, operation_id) "
			    + "VALUES(9, 9)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Has (nurse_id, operation_id) "
			    + "VALUES(11, 10)";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Has (nurse_id, operation_id) "
			    + "VALUES(12, 11)";
			stmt.executeUpdate(sql);

	
		
			
			System.out.println("Tables created and default values inserted");

			
		}catch(SQLException e) {
			
			if(!e.getMessage().contains("already exists"))
				e.printStackTrace();
		}			
		
	}
	
	public Connection getConnection() {
		return connection;
	}
public void closeConnection() {
		
		try {		
			connection.close();
		}catch(SQLException e)
		{	
			e.printStackTrace();
		}
	}
	
}

			
			
			
			
			
			
			
			
			
			
			
			
			
			