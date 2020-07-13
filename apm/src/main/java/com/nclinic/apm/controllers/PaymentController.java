package com.nclinic.apm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nclinic.apm.models.Payment;
import com.nclinic.apm.services.PaymentService;

@RestController
@RequestMapping(value="/payment/")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(path = "/pay", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void addPay(@RequestBody Payment payment) {
		paymentService.addPayment(payment);
	}
}
