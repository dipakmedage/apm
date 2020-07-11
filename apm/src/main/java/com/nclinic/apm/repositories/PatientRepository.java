package com.nclinic.apm.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	List<Patient> findByPatientName(String patientName);

}
