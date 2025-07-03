package com.ho.service;

import java.util.List;

import com.ho.dto.request.PrescriptionRequestDTO;
import com.ho.dto.response.PrescriptionResponseDTO;

public interface PrescriptionService {

    PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO dto);

    PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO dto);

    PrescriptionResponseDTO getPrescriptionById(Long id);

    List<PrescriptionResponseDTO> getAllPrescriptions();

    void deletePrescription(Long id);
}
