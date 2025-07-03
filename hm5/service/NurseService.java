package com.ho.service;

import java.util.List;

import com.ho.dto.request.NurseRequestDTO;
import com.ho.dto.response.NurseResponseDTO;

public interface NurseService {

    NurseResponseDTO createNurse(NurseRequestDTO dto);

    NurseResponseDTO updateNurse(Long id, NurseRequestDTO dto);

    NurseResponseDTO getNurseById(Long id);

    List<NurseResponseDTO> getAllNurses();

    void deleteNurse(Long id);

    // Assign a patient to a nurse manually (if needed after creation)
    void assignPatientToNurse(Long nurseId, Long patientId);
}
