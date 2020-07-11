package com.nclinic.apm.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nclinic.apm.models.Patient;
import com.nclinic.apm.repositories.PatientRepository;

@Service
public class PatientService {

	private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	
	private static final String contactRegex = "(0/91)?[7-9][0-9]{9}";
	
	@Autowired
	private PatientRepository patientRepository;
	
	public List<Patient> getPatients(){
		return (List<Patient>) patientRepository.findAll();
	}
	
	public String registerPatient(Patient patient) {
		
		List<Patient> patients=patientRepository.findByPatientName(patient.getPatientName());
		if(!patients.isEmpty()) {
			return "Patient already exists";
		}else {
			//redirection plan
		}
		if(patient.getEmailId()!= "" && !isValid(patient.getEmailId(), "email")) {
				return "email is not valid";
		}
		if(patient.getContactNumber()!= "" && !isValid(patient.getContactNumber(), "contact")) {
			return "contact is not valid";
		}
		patientRepository.save(patient);
		return "ok";
	}

	/**** Email Id validation ***/
	private boolean isValid(String email, String type) {
		String regString = "";

		if (type.equals("email")) {
			regString = emailRegex;
		} else {
			regString = contactRegex;
		}
		Pattern pat = Pattern.compile(regString);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}
