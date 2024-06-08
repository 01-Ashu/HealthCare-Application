package com.Notification.Service;

import com.Notification.DTO.AppointmentDto;
import com.Notification.DTO.NotificationDTO;
import com.Notification.DTO.PatientDTO;
import com.Notification.Entity.Notification;
import com.Notification.Repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(topics = "appointment-bookings", groupId = "group_id")
    public void listenAppointmentBookings(String message) {
        try {
            AppointmentDto appointmentDTO = new ObjectMapper().readValue(message, AppointmentDto.class);
            NotificationDTO notificationDTO = modelMapper.map(appointmentDTO, NotificationDTO.class);
            notificationDTO.setType("appointment");
            notificationDTO.setMessage("Appointment booked for " + appointmentDTO.getAppointmentDate());
            notificationRepository.save(modelMapper.map(notificationDTO, Notification.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "patient-updates", groupId = "group_id")
    public void listenPatientUpdates(String message) {
        try {
            PatientDTO patientDTO = new ObjectMapper().readValue(message, PatientDTO.class);
            NotificationDTO notificationDTO = modelMapper.map(patientDTO, NotificationDTO.class);
            notificationDTO.setType("patient-update");
            notificationDTO.setMessage("Patient record updated: " + patientDTO.getName());
            notificationRepository.save(modelMapper.map(notificationDTO, Notification.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(UUID id) {
        return notificationRepository.findById(id);
    }
}
