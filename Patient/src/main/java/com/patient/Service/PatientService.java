package com.patient.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.Entity.Patient;
import com.patient.PatientDto.PatientDTO;
import com.patient.Repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ModelMapper modelMapper;

    private static final String TOPIC = "patient-updates";

    public PatientDTO addOrUpdatePatient(PatientDTO patientDTO) throws JsonProcessingException {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patientRepository.save(patient);
        kafkaTemplate.send(TOPIC, new ObjectMapper().writeValueAsString(patient));

        return modelMapper.map(patient,PatientDTO.class);
    }
    public Optional<Patient> getPatientById(UUID id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatientById(UUID id) {
        patientRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, String.format("{\"id\":\"%s\", \"action\":\"delete\"}", id));
    }
}