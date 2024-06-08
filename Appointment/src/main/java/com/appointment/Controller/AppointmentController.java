package com.appointment.Controller;

import com.appointment.Entity.Appointment;
import com.appointment.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> bookOrUpdateAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.bookOrUpdateAppointment(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable UUID id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.noContent().build();
    }
}
