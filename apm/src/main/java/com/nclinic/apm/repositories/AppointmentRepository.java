package com.nclinic.apm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
