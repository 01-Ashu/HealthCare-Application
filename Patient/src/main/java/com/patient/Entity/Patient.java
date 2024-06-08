package com.patient.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {
    private UUID id;
    private String name;
    private String email;
    private int age;
    private String gender;
    private String address;
    // other fields

}
