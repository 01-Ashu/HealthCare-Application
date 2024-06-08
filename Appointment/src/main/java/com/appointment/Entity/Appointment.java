package com.appointment.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "appointments")
public class Appointment {
    @Id
    private UUID id;
    private String patientId;
    private Date appointmentDate;
    // other fields
}