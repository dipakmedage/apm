package com.nclinic.apm.reositories;

import org.springframework.data.repository.CrudRepository;

import com.nclinic.apm.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
