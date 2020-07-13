package com.nclinic.apm.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

	List<Payment> findByPatientNameAndSessionDate(String PatientName, Date SessionDate);

	Payment findByPatientNameAndPayment(String PatientName, int Payment);
	
	Payment findByPatientNameOrderBySessionDateDesc(String PatientName);
}
