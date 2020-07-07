package com.nclinic.apm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
