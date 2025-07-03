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
import org.springframework.web.bind.annotation.RestController;

import com.ho.dto.request.PatientRequestDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // Create new patient
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO dto) {
        PatientResponseDTO created = patientService.createPatient(dto);
        return ResponseEntity.ok(created);
    }

    // Update patient details
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientRequestDTO dto) {
        PatientResponseDTO updated = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        PatientResponseDTO response = patientService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    // Get all patients
    @GetMapping("/getall")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // Delete patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


    // Get all nurses assigned to a patient
    @GetMapping("/{id}/nurses")
    public ResponseEntity<List<String>> getAllNursesForPatient(@PathVariable Long id) {
        List<String> nurses = patientService.getAllNurseNamesByPatientId(id);
        return ResponseEntity.ok(nurses);
    }
}
