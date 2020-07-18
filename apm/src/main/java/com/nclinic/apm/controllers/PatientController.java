package com.nclinic.apm.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nclinic.apm.models.Patient;
import com.nclinic.apm.services.PatientService;

@RestController
@RequestMapping(value = "/pat/")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@RequestMapping(value = "/getAllPatient")
	public List<Patient> getpatient() {
		return patientService.getPatients();
	}

	@RequestMapping(path = "/patients", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String addPatients(@RequestBody Patient patient) {

		return patientService.registerPatient(patient);
		// patientRepository.save(patient);
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile readExcelDataFile) throws IOException {

		try {
			patientService.readexcel(readExcelDataFile);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
