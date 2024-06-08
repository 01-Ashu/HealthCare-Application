package com.patient.Repository;

import com.patient.Entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PatientRepository extends MongoRepository<Patient, UUID> {
}
