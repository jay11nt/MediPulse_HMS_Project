package com.ho.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ho.dto.request.DoctorRequestDTO;
import com.ho.dto.response.DoctorResponseDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.entity.Appointment;
import com.ho.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Create a new doctor
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(doctorService.createDoctor(dto));
    }

    // Update doctor details
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, dto));
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    // Get all doctors
    @GetMapping("/getall")
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // Delete doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    // Assign a patient to a doctor
    @PutMapping("/{doctorId}/assign-patients/{patientId}")
    public ResponseEntity<String> assignPatientToDoctor(@PathVariable Long doctorId, @PathVariable Long patientId) {
        doctorService.assignPatientToDoctor(doctorId, patientId);
        return ResponseEntity.ok("Patient assigned to doctor successfully");
    }

    // Get patients assigned to a doctor
    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatientsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getPatientsByDoctor(doctorId));
    }

    // Search doctors by specialization
    @GetMapping("/specialization")
    public ResponseEntity<List<DoctorResponseDTO>> searchDoctorsBySpecialization(@RequestParam String specialization) {
        return ResponseEntity.ok(doctorService.searchDoctorsBySpecialization(specialization));
    }

    // Get appointments for a doctor
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getAppointmentsForDoctor(doctorId));
    }
}
