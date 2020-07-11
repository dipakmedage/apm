package com.nclinic.apm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nclinic.apm.models.Appointment;
import com.nclinic.apm.repositories.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PaymentService paymentService;

	public String bookAppointment(Appointment appointment) {
		if (appointment.getAdvancePayment() > 0) {
			insertIntoPaymentifAdvanceTrue(appointment.getPatientName(), appointment.getAdvancePayment(),
					appointment.getTransactionId());
		}
		appointmentRepository.save(appointment);
		return "ok";
	}

	public List<Appointment> getAllAppointments() {
		return (List<Appointment>) appointmentRepository.findAll();
	}

	private boolean insertIntoPaymentifAdvanceTrue(String patientName, int advancePay, String transactionId) {

		paymentService.fillInAdvance(patientName, advancePay, transactionId);
		return true;
	}
}
