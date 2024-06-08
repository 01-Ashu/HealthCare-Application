package com.appointment.Service;

import com.appointment.AppointmentRepository;
import com.appointment.DTO.PatientDto;
import com.appointment.Entity.Appointment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "appointment-bookings";

    public Appointment bookOrUpdateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        kafkaTemplate.send(TOPIC, new ObjectMapper().writeValueAsString(appointment));
        return appointment;
    }

    @KafkaListener(topics = "patient-updates", groupId = "group_id")
    public void listenPatientUpdates(String message) {
        PatientDto patient = new ObjectMapper().readValue(message, PatientDto.class);
        // update related appointment details based on patient info if needed
    }


    public Optional<Appointment> getAppointmentById(UUID id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointmentById(UUID id) {
        appointmentRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, String.format("{\"id\":\"%s\", \"action\":\"delete\"}", id));
    }
}