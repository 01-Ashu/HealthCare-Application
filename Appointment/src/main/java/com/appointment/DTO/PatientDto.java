package com.appointment.DTO;

import lombok.Data;

import java.util.UUID;

@Data

public class PatientDto {

    private UUID id;
    private String name;
    private String email;
}