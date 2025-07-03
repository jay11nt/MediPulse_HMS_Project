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

import com.ho.dto.request.MedicalRecordRequestDTO;
import com.ho.dto.response.MedicalRecordResponseDTO;
import com.ho.service.MedicalRecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicalrecords")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    //  Create a new medical record
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> createMedicalRecord(@Valid @RequestBody MedicalRecordRequestDTO dto) {
        MedicalRecordResponseDTO savedRecord = medicalRecordService.createMedicalRecord(dto);
        return ResponseEntity.ok(savedRecord);
    }

    //  Update an existing medical record
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> updateMedicalRecord(
            @PathVariable Long id,
            @Valid @RequestBody MedicalRecordRequestDTO dto) {
        MedicalRecordResponseDTO updated = medicalRecordService.updateMedicalRecord(id, dto);
        return ResponseEntity.ok(updated);
    }

    //  Get medical record by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> getMedicalRecordById(@PathVariable Long id) {
        MedicalRecordResponseDTO record = medicalRecordService.getMedicalRecordById(id);
        return ResponseEntity.ok(record);
    }

    //  Get all medical records
    @GetMapping("/getall")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getAllMedicalRecords() {
        List<MedicalRecordResponseDTO> records = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(records);
    }

    //  Delete medical record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.noContent().build();
    }

    //  Get medical records by patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getRecordsByPatientId(@PathVariable Long patientId) {
        List<MedicalRecordResponseDTO> records = medicalRecordService.getRecordsByPatientId(patientId);
        return ResponseEntity.ok(records);
    }
}
