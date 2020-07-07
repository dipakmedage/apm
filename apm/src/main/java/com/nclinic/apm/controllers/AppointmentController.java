package com.nclinic.apm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nclinic.apm.models.Appointment;
import com.nclinic.apm.repositories.AppointmentRepository;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@PostMapping(path="/register" , consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void addPatients(@RequestBody Appointment appointment) {
		appointmentRepository.save(appointment);
	}
}
