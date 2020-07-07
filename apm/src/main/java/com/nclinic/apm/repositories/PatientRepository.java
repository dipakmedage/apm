package com.nclinic.apm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
