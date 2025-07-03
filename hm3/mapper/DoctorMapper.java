package com.ho.mapper;

import com.ho.dto.request.DoctorRequestDTO;
import com.ho.dto.response.DoctorResponseDTO;
import com.ho.entity.Admin;
import com.ho.entity.Doctor;

import lombok.experimental.UtilityClass;

@UtilityClass   // Makes all methods static
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestDTO dto, Admin admin) {
        if (dto == null) {
            return null;
        }

        return Doctor.builder()
        	
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .contactNumber(dto.getContactNumber())
                .specialization(dto.getSpecialization())
                .consultationFee(dto.getConsultationFee())
                .qualification(dto.getQualification())
                .experienceInYears(dto.getExperienceInYears())
                .admin(admin)
                .build();
    }

    public DoctorResponseDTO toResponseDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .email(doctor.getEmail())
                .contactNumber(doctor.getContactNumber())
                .specialization(doctor.getSpecialization())
                .consultationFee(doctor.getConsultationFee())
                .qualification(doctor.getQualification())
                .experienceInYears(doctor.getExperienceInYears())
                .adminId(doctor.getAdmin() != null ? doctor.getAdmin().getId() : null)
                .build();
    }

    // For PUT request – update fields from DTO
    public void updateEntityFromDTO(DoctorRequestDTO dto, Doctor doctor) {
        if (dto == null || doctor == null) return;

        doctor.setFullName(dto.getFullName());
        doctor.setEmail(dto.getEmail());
        doctor.setContactNumber(dto.getContactNumber());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setConsultationFee(dto.getConsultationFee());
        doctor.setQualification(dto.getQualification());
        doctor.setExperienceInYears(dto.getExperienceInYears());
        
    }
}
