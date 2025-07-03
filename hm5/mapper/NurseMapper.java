package com.ho.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ho.dto.request.NurseRequestDTO;
import com.ho.dto.response.NurseResponseDTO;
import com.ho.entity.Nurse;
import com.ho.entity.Patient;

import lombok.experimental.UtilityClass;

@UtilityClass  // Makes all methods static
public class NurseMapper {

    public Nurse toEntity(NurseRequestDTO dto, List<Patient> patients) {
        if (dto == null) {
            return null;
        }

        return Nurse.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .contactNumber(dto.getContactNumber())
                .qualification(dto.getQualification())
                .patient(patients)
                .build();
    }

    public NurseResponseDTO toResponseDTO(Nurse nurse) {
        if (nurse == null) {
            return null;
        }

        return NurseResponseDTO.builder()
                .id(nurse.getId())
                .fullName(nurse.getFullName())
                .email(nurse.getEmail())
                .contactNumber(nurse.getContactNumber())
                .qualification(nurse.getQualification())
                .patientIds(
                        nurse.getPatient() != null ?
                                nurse.getPatient().stream().map(Patient::getId).collect(Collectors.toList())
                                : null
                )
                .build();
    }

    // For PUT request – update fields from DTO
    public void updateEntityFromDTO(NurseRequestDTO dto, Nurse nurse) {
        if (dto == null || nurse == null) return;

        nurse.setFullName(dto.getFullName());
        nurse.setEmail(dto.getEmail());
        nurse.setContactNumber(dto.getContactNumber());
        nurse.setQualification(dto.getQualification());
    }
}
