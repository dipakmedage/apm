package com.nclinic.apm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nclinic.apm.models.Payment;
import com.nclinic.apm.repositories.PaymentRepository;

@Service
public class PaymentService {

	private static final int defaultPay = 300;
	
	@Autowired
	private PaymentRepository paymentRepository;

	public boolean fillInAdvance(String patientName, int advancePay, String transactionId) {
		Payment payment=new Payment();
		payment.setAdvancePayment(advancePay-defaultPay);
		payment.setPatientName(patientName);
		payment.setPayment(defaultPay);
		//payment.setRemainingPayment(advancePay - defaultPay);
		payment.setTransactionId(transactionId);
		payment.setCreatedBy("Neha");
		paymentRepository.save(payment);

		return true;

	}
}
