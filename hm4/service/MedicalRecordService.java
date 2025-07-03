package com.ho.service;

import java.util.List;

import com.ho.dto.request.MedicalRecordRequestDTO;
import com.ho.dto.response.MedicalRecordResponseDTO;

public interface MedicalRecordService {

    MedicalRecordResponseDTO createMedicalRecord(MedicalRecordRequestDTO dto);

    MedicalRecordResponseDTO updateMedicalRecord(Long id, MedicalRecordRequestDTO dto);

    MedicalRecordResponseDTO getMedicalRecordById(Long id);

    List<MedicalRecordResponseDTO> getAllMedicalRecords();

    void deleteMedicalRecord(Long id);

    List<MedicalRecordResponseDTO> getRecordsByPatientId(Long patientId);
}
