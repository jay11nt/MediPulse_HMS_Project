package com.ho.mapper;

import com.ho.dto.request.PatientRequestDTO;
import com.ho.dto.response.PatientResponseDTO;
import com.ho.entity.Patient;

import lombok.experimental.UtilityClass;

@UtilityClass         //to make class all methods static
public class PatientMapper {

    public Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) 
        	{return null;
        	}

        return Patient.builder()
                .firstName(dto.getFirstName())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .age(dto.getAge())
                .address(dto.getAddress())
                .contactNumber(dto.getContactNumber())
                .email(dto.getEmail())               
                .build();
    }

    public PatientResponseDTO toResponseDTO(Patient patient) {
        if (patient == null) 
        	{ return null;
        	}
        return PatientResponseDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .fullName(patient.getFullName())
                .gender(patient.getGender())
                .age(patient.getAge())
                .address(patient.getAddress())
                .contactNumber(patient.getContactNumber())
                .email(patient.getEmail())
                .roomId(patient.getRoom() != null ? patient.getRoom().getId() : null)
                //.roomNumber(patient.getRoom() != null ? patient.getRoom().getRoomNumber() : null)
                .build();
    }

    // protection against nullpointerException, used for PUT because dto and patient both null
    public void updateEntityFromDTO(PatientRequestDTO dto, Patient patient) 
    {
        if (dto == null || patient == null) return;

        patient.setFirstName(dto.getFirstName());
        patient.setFullName(dto.getFullName());
        patient.setPassword(dto.getPassword());
        patient.setGender(dto.getGender());
        patient.setAge(dto.getAge());
        patient.setAddress(dto.getAddress());
        patient.setContactNumber(dto.getContactNumber());
        patient.setEmail(dto.getEmail());
    }
}
