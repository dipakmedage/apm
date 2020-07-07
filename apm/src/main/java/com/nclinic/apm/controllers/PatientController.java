package com.nclinic.apm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nclinic.apm.models.Patient;
import com.nclinic.apm.repositories.PatientRepository;

@RestController
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/welcome")
	public String getData() {
		return "Hello! Welcome to Neha Clinic....";
	}
	
	@GetMapping
	public List<Patient> getpatient() {
		return (List<Patient>) patientRepository.findAll();
	}
	
	
	@PostMapping(path="/patients" , consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void addPatients(@RequestBody Patient patient) {
		 patientRepository.save(patient);
	}
}
