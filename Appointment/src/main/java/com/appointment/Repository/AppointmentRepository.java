package com.appointment.Repository;

import com.appointment.Entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, UUID> {
}
