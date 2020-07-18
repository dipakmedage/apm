package com.nclinic.apm.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nclinic.apm.models.Appointment;
import com.nclinic.apm.models.Patient;
import com.nclinic.apm.repositories.PatientRepository;

@Service
public class PatientService {

	private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
			+ "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

	private static final String contactRegex = "(0/91)?[7-9][0-9]{9}";

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentService appointmentService;

	public List<Patient> getPatients() {
		return (List<Patient>) patientRepository.findAll();
	}

	public String registerPatient(Patient patient) {

		List<Patient> patients = patientRepository.findByPatientName(patient.getPatientName());
		if (!patients.isEmpty()) {
			return "Patient already exists";
		} else {
			// redirection plan
		}
		if (patient.getEmailId() != "" && !isValid(patient.getEmailId(), "email")) {
			return "email is not valid";
		}
		if (patient.getContactNumber() != "" && !isValid(patient.getContactNumber(), "contact")) {
			return "contact is not valid";
		}
		patientRepository.save(patient);
		return "ok";
	}

	/**** Email Id validation ***/
	private boolean isValid(String email, String type) {
		if (email == null)
			return false;

		String regString = "";

		if (type.equals("email")) {
			regString = emailRegex;
		} else {
			regString = contactRegex;
		}
		Pattern pat = Pattern.compile(regString);

		return pat.matcher(email).matches();
	}

	public void readexcel(MultipartFile readExcelDataFile) throws IOException, ParseException {
		DataFormatter formatter = new DataFormatter(Locale.US);
		List<Patient> patientList = new ArrayList<Patient>();
		List<Appointment> appointmentList = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(readExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		System.out.println(worksheet.getPhysicalNumberOfRows());

		for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = worksheet.getRow(i);
			Patient patient = setPatientFromExcel(row, formatter);
			if (patient.getPatientName() != null) {
				patientList.add(patient);
			}
			Appointment appointment = appointmentService.setAppointmentFromExcel(row, formatter);
			if (appointment.getPatientName() != null) {
				appointmentList.add(appointment);
			}

		}
		patientRepository.saveAll(patientList);
		appointmentService.saveFromExcel(appointmentList);
		workbook.close();
	}

	private Patient setPatientFromExcel(XSSFRow row, DataFormatter formatter) {
		String patientname;
		String emailString;
		String contactString;
		Patient patient = new Patient();
		patientname = formatter.formatCellValue(row.getCell(1));
		if (!patientname.equals("") && patientname != null) {

			patient.setPatientName((String) row.getCell(1).getStringCellValue());

			contactString = formatter.formatCellValue(row.getCell(4));
			if (isValid(contactString, "contact"))
				patient.setContactNumber(contactString);

			patient.setFathersName((String) row.getCell(3).getStringCellValue());

			patient.setMothersName((String) row.getCell(2).getStringCellValue());

			emailString = (String) row.getCell(5).getStringCellValue();
			if (isValid(emailString, "email"))
				patient.setEmailId(emailString);

			patient.setIsActive('Y');
		}
		return patient;
	}
}
