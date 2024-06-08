package com.patient.PatientDto;

import lombok.Data;

import java.util.UUID;

@Data
public class PatientDTO {
    private UUID id;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String address;

}
