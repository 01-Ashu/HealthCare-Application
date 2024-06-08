package com.Notification.DTO;



import lombok.Data;
import java.util.Date;

@Data
public class AppointmentDto {
    private String id;
    private String patientId;
    private Date appointmentDate;

}

