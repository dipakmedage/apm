package com.nclinic.apm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nclinic.apm.models.Appointment;
import com.nclinic.apm.services.AppointmentService;

@RestController
@RequestMapping(value="/app/")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping(value="/getAppointment")
	public List<Appointment> getpatient() {
		return appointmentService.getAllAppointments();
	}
	
	
	@PostMapping(path="/appointment" , consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void takeAppointment(@RequestBody Appointment appointment) {
		appointmentService.bookAppointment(appointment);
	}
}
