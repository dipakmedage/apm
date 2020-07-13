package com.nclinic.apm.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nclinic.apm.models.Payment;
import com.nclinic.apm.repositories.PaymentRepository;

@Service
public class PaymentService {

	private static final int defaultPay = 300;

	@Autowired
	private PaymentRepository paymentRepository;

	public boolean fillInAdvance(String patientName, int advancePay, String transactionId, Date sessionDate) {
		Payment payment = new Payment();
		payment.setAdvancePayment(advancePay - defaultPay);
		payment.setPatientName(patientName);
		payment.setPayment(defaultPay);
		// payment.setRemainingPayment(advancePay - defaultPay);
		payment.setTransactionId(transactionId);
		payment.setCreatedBy("Neha");
		payment.setSessionDate(sessionDate);
		paymentRepository.save(payment);

		return true;

	}

	public String addPayment(Payment payment) {

		List<Payment> existedPayments = paymentRepository.findByPatientNameAndSessionDate(payment.getPatientName(),
				payment.getSessionDate());
		if (existedPayments.isEmpty()) {
			if (payment.getPayment() == 0) {
				Payment checkRemainingPayment = paymentRepository.findByPatientNameAndPayment(payment.getPatientName(),
						payment.getPayment());
				if (checkRemainingPayment != null && checkRemainingPayment.getRemainingPayment() > 0) {
					payment.setRemainingPayment((checkRemainingPayment.getRemainingPayment()) + defaultPay);
				} else {
					payment.setRemainingPayment(defaultPay);
				}
			}
			paymentRepository.save(payment);
		} else {

		}

		return "ok";
	}

	public void findandUpdateAdvancePay(String patientName) {
		Payment payment = paymentRepository.findByPatientNameOrderBySessionDateDesc(patientName);
		if (payment.getAdvancePayment() > 0) {
			payment.setAdvancePayment(payment.getAdvancePayment() - defaultPay);
			paymentRepository.save(payment);
		}
		
	}

	public void updateAdvancePay() {

	}
}
