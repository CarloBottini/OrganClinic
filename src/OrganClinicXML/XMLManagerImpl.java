package OrganClinicXML;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import OrganClinicINTERFACEs.XMLManager;
import OrganClinicJDBC.JDBCManager;
import OrganClinicPOJOs.Doctor;
import OrganClinicPOJOs.Patient;

public class XMLManagerImpl implements XMLManager{

	JDBCManager manager;
	
	
	@Override
	public File patient2XML(Patient patient) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File file = new File("./xmls/External-Patient.xml");
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);
		return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public File doctor2XML(Doctor doctor) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		File file = new File("./xmls/External-Doctor.xml");
		marshaller.marshal(doctor, file);
		marshaller.marshal(doctor, System.out);
		return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Doctor XML2doctor(File Filexml) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	Doctor doctor = (Doctor) unmarshaller.unmarshal(Filexml);
    	return doctor;
   
	}catch (Exception e) {
		System.out.println("ERROR: Unable to load XML file");
		e.printStackTrace();
		return null;
		}
	}
	
	@Override
	public Patient XML2patient(File Filexml) {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    	Patient patient = (Patient) unmarshaller.unmarshal(Filexml);
    	return patient;
   
	}catch (Exception e) {
		System.out.println("ERROR: Unable to load XML file");
		e.printStackTrace();
		return null;
		}
	}
	
	@Override
	public void patient2HTML(Patient patient) {
		File f= patient2XML(patient);
		TransformerFactory transformerFactory= TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer(new StreamSource(new File("./xmls/Patient-Style.xslt")));
			transformer.transform(new StreamSource(f), new StreamResult (new File("./xmls/External-Patient.html")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void doctor2HTML(Doctor d) {
		File f= doctor2XML(d);
		TransformerFactory transformerFactory= TransformerFactory.newInstance();
		try {
			Transformer transformer=transformerFactory.newTransformer(new StreamSource(new File("./xmls/Doctor-Style.xslt")));
			transformer.transform(new StreamSource(f), new StreamResult (new File("./xmls/External-Doctor.html")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	/*
	public void patient2xml(Integer id) {
			Patient patient=null;
			Doctor doctor=null;
			
			manager = new JDBCManager();
		try {
			//maybe this is our search patient by id
				Statement stmt= manager.getConnection().createStatement();
				String sql = "SELECT * FROM Patient WHERE id=" +id;
				ResultSet rs= stmt.executeQuery(sql);
				String name = rs.getString("name");
				Date dob =rs.getDate("dob");
				String gender= rs.getString("gender");
				String organFailure= rs.getString("organFailure");
				String email= rs.getString("email");
				Integer telephone= rs.getInt("telephone");
				String bloodType= rs.getString("bloodType");

				stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
}
