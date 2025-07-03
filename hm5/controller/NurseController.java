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

import com.ho.dto.request.NurseRequestDTO;
import com.ho.dto.response.NurseResponseDTO;
import com.ho.service.NurseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/nurses")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;

    //  Create Nurse
    @PostMapping
    public ResponseEntity<NurseResponseDTO> createNurse(@Valid @RequestBody NurseRequestDTO dto) {
        NurseResponseDTO created = nurseService.createNurse(dto);
        return ResponseEntity.ok(created);
    }

    //  Update Nurse
    @PutMapping("/{id}")
    public ResponseEntity<NurseResponseDTO> updateNurse(
            @PathVariable Long id,
            @Valid @RequestBody NurseRequestDTO dto
    ) {
        NurseResponseDTO updated = nurseService.updateNurse(id, dto);
        return ResponseEntity.ok(updated);
    }

    //  Get Nurse by ID
    @GetMapping("/{id}")
    public ResponseEntity<NurseResponseDTO> getNurseById(@PathVariable Long id) {
        NurseResponseDTO nurse = nurseService.getNurseById(id);
        return ResponseEntity.ok(nurse);
    }

    //  Get All Nurses
    @GetMapping("/getall")
    public ResponseEntity<List<NurseResponseDTO>> getAllNurses() {
        List<NurseResponseDTO> nurses = nurseService.getAllNurses();
        return ResponseEntity.ok(nurses);
    }

    //  Delete Nurse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long id) {
        nurseService.deleteNurse(id);
        return ResponseEntity.noContent().build();
    }

    //  Assign a patient to a nurse
    @PostMapping("/{nurseId}/assign-patient/{patientId}")
    public ResponseEntity<Void> assignPatientToNurse(
            @PathVariable Long nurseId,
            @PathVariable Long patientId
    ) {
        nurseService.assignPatientToNurse(nurseId, patientId);
        return ResponseEntity.ok().build();
    }
}
