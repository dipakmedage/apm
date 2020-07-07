package com.nclinic.apm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nclinic.apm.models.Payment;
import com.nclinic.apm.repositories.PaymentRepository;

@RestController
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping(path = "/pay", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void addPatients(@RequestBody Payment payment) {
		paymentRepository.save(payment);
	}
}
