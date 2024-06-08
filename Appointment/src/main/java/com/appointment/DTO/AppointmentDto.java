package com.appointment.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data

public class AppointmentDto {
    @Id
    private UUID id;
    private String patientId;
    private Date appointmentDate;

}